package com.exceltodb.exceltodbviewer.adapter.api.xlsximporter;

import com.exceltodb.exceltodbviewer.adapter.ports.XlsxImportPort;
import com.exceltodb.exceltodbviewer.adapter.xmlreader.PoiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/import")
@Api(value = "import", tags = "import")
@AllArgsConstructor
public class XlsxImportApi implements XlsxImportPort {

    private final PoiController poiController;

    @Override
    @PostMapping
    @ApiOperation("Import du document Excel")
    public String importExcel() throws IOException {
        return poiController.openXlsxFromFile();
    }
}
