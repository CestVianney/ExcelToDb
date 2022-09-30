package com.exceltodb.exceltodbviewer.adapter.api.xmlreader;

import com.exceltodb.exceltodbviewer.adapter.ports.XmlReaderPort;
import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcEntity;
import com.exceltodb.exceltodbviewer.service.ExcelToDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/xlsx")
@Api(value = "xslx", tags = "xslx")
@AllArgsConstructor
public class XmlReaderApi implements XmlReaderPort {

    private final ExcelToDbService service;

    @Override
    @GetMapping(path = "/values")
    @ApiOperation("Recuperation des données via la base H2")
    public List<JnlAcEntity> getDbValues() {
        return service.getAllValues();
    }
}
