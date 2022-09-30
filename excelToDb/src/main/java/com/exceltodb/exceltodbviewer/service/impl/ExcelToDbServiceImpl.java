package com.exceltodb.exceltodbviewer.service.impl;

import com.exceltodb.exceltodbviewer.adapter.api.pdfopener.JFileChooser;
import com.exceltodb.exceltodbviewer.enums.TypePaiementEnum;
import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcEntity;
import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcExcel;
import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcRepository;
import com.exceltodb.exceltodbviewer.service.ExcelToDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExcelToDbServiceImpl implements ExcelToDbService {

    private final JnlAcRepository repository;
    private final JFileChooser fileOpener;
    private String path = "";

    @Override
    public void sendXlsxDataToDb(List<JnlAcExcel> donnees) {
        donnees.forEach(exc -> {
            if (null != exc.getCredit()) {

                JnlAcEntity entity = repository.findByRefPiece(exc.getRefPiece());
                TypePaiementEnum typePaiement = TypePaiementEnum.getTypePaiement(exc);
                Double tba;
                boolean isCredit = exc.getCredit() != 0.0;
                String typeCompteString = Character.toString(exc.getCompte().charAt(0));
                if (TypePaiementEnum.TTC.getNumId().equals(typeCompteString)) {
                    if (isCredit) {
                        tba = exc.getCredit();
                    } else {
                        tba = -exc.getDebit();
                    }
                } else {
                    if (isCredit) {
                        tba = -exc.getCredit();
                    } else {
                        tba = exc.getDebit();
                    }
                }
                boolean isNouvelleEntity = false;

                if (null == entity) {
                    entity = new JnlAcEntity();
                    isNouvelleEntity = true;
                }
                updateOrCompleteEntity(entity, exc, tba, isNouvelleEntity, typePaiement);
                repository.save(entity);
            }
        });
    }

    @Override
    public List<JnlAcEntity> getAllValues() {
        return repository.findAll();
    }

    @Override
    public void openPdf(String item) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(path + "\\" + item + ".pdf"));
    }

    @Override
    public void openChosenPdf(String characters) {
        Desktop desktop = Desktop.getDesktop();
        File listFiles = new File(path);
        try {
            File fileToOpen = Arrays.stream(Objects.requireNonNull(listFiles.listFiles())).filter(
                    f -> f.toString().endsWith(characters + ".pdf")
            ).findFirst().get();
            desktop.open(fileToOpen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPath() {
        javax.swing.JFileChooser chooser = fileOpener.chooseDirectory();
        path = chooser.getSelectedFile().getPath();
        return path;
    }

    private void updateOrCompleteEntity(JnlAcEntity entity, JnlAcExcel data, Double vtba, boolean isNouvelleEntity, TypePaiementEnum typePaiement) {
        if (isNouvelleEntity) {
            entity.setJournal(data.getJournal());
            entity.setDate(data.getDate());
            entity.setRefPiece(data.getRefPiece());
            entity.setLibelle(data.getLibelle());
        }
        calculerSommes(entity, vtba, isNouvelleEntity, typePaiement);
    }

    private void calculerSommes(JnlAcEntity entity, Double vtba, boolean isNouvelleEntity, TypePaiementEnum typePaiement) {
        switch (typePaiement) {
            case IMMOBILISATION, HT -> {
                if (isNouvelleEntity) {
                    entity.setHt(vtba);
                    entity.setTtc(0.0);
                    entity.setTva(0.0);
                } else {
                    entity.setHt(entity.getHt() + vtba);
                }
            }
            case TTC -> {
                if (isNouvelleEntity) {
                    entity.setHt(0.0);
                    entity.setTtc(vtba);
                    entity.setTva(0.0);
                } else {
                    entity.setTtc(entity.getTtc() + vtba);
                }
            }
            case TVA -> {
                if (isNouvelleEntity) {
                    entity.setHt(0.0);
                    entity.setTtc(0.0);
                    entity.setTva(vtba);
                } else {
                    entity.setTva(entity.getTva() + vtba);
                }
            }
        }
    }
}
