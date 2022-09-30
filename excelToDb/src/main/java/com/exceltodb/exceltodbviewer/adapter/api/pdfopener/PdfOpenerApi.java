package com.exceltodb.exceltodbviewer.adapter.api.pdfopener;

import com.exceltodb.exceltodbviewer.adapter.ports.PdfOpenerPort;
import com.exceltodb.exceltodbviewer.service.ExcelToDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/xlsx")
@Api(value = "xslx", tags = "xslx")
@AllArgsConstructor
@CrossOrigin
public class PdfOpenerApi implements PdfOpenerPort {

    private final ExcelToDbService service;

    @Override
    @PostMapping
    @ApiOperation("Ouverture via openPDF")
    public void openPdfAsked(String path, boolean isAppelManuel) throws IOException {
        if(!isAppelManuel) {
            service.openPdf(path);
        } else {
            service.openChosenPdf(path);
        }
    }

    @Override
    @GetMapping("/path")
    @ApiOperation("Recupération du chemin dossier")
    public String determinePath() {
        return service.getPath();
    }

}