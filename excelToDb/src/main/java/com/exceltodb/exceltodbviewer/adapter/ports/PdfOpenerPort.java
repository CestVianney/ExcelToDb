package com.exceltodb.exceltodbviewer.adapter.ports;

import java.io.IOException;

public interface PdfOpenerPort {

    void openPdfAsked(String path, boolean isAppelManuel) throws IOException;
    String determinePath();
}
