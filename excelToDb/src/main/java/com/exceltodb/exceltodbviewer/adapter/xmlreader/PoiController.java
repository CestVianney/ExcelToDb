package com.exceltodb.exceltodbviewer.adapter.xmlreader;

import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcExcel;
import com.exceltodb.exceltodbviewer.service.ExcelToDbService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
@Component
public class PoiController extends JFrame {

    private final ExcelToDbService service;

    public String openXlsxFromFile() throws IOException {
        try {
            String input = getFileLocationWithJFrame();
            Workbook excel = new XSSFWorkbook(input);
            Sheet page = excel.getSheetAt(0);
            Map<Integer, List<String>> data = new HashMap<>();
            int i = 0;
            for (Row row : page) {
                data.put(i, new ArrayList<>());
                for (Cell cell : row) {
                    String cellValue = cell.toString();
                    data.get(i).add(cellValue);
                }
                i++;
            }
            List<JnlAcExcel> listToBeSent = new ArrayList<>();

            try {
                data.forEach((value, object) -> {
                    if(value != 0) {
                        if (object != null && !object.get(0).isEmpty()) {
                            listToBeSent.add(convertFromXlsxToObject(object));
                        }
                    }
                });
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("Fin de l'itération");
            } catch (Exception e) {
                e.getStackTrace();
            }
            service.sendXlsxDataToDb(listToBeSent);
            return input;
        } catch (IOException ioe) {
            ioe.getStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "erreur de traitement";
    }

    private String getFileLocationWithJFrame() throws Exception {
        String path = "";
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Récupération du fichier Excel");

        if (jfc.showDialog(this, "Selectionner") == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            path = selectedFile.getAbsolutePath();
        } else {
            throw new Exception();
        }
        System.out.println(path);
        return path;
    }

    private JnlAcExcel convertFromXlsxToObject(List<String> items) {
        JnlAcExcel object = new JnlAcExcel();
        object.setJournal(items.get(0));
        object.setDate(items.get(1));
        object.setCompte(items.get(2));
        object.setRefPiece(items.get(3));
        object.setLibelle(items.get(5));
        if (!items.get(6).isEmpty()) {
            object.setDebit(Double.parseDouble(items.get(6)));
        }
        if (!items.get(7).isEmpty()) {
            object.setCredit(Double.parseDouble(items.get(7)));
        }
        return object;
    }
}
