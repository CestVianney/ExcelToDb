package com.exceltodb.exceltodbviewer.service;

import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcEntity;
import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcExcel;

import java.io.IOException;
import java.util.List;

public interface ExcelToDbService {

    void sendXlsxDataToDb(List<JnlAcExcel> donnees);

    List<JnlAcEntity> getAllValues();

    void openPdf(String path) throws IOException;

    void openChosenPdf(String characters);

    String getPath();

}
