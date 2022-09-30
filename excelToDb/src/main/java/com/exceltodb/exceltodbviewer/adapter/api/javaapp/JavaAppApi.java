package com.exceltodb.exceltodbviewer.adapter.api.javaapp;

import com.exceltodb.exceltodbviewer.adapter.ports.JavaAppPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/javaapp")
@Api(value = "javaapp", tags = "javaapp")
public class JavaAppApi implements JavaAppPort {

    @Override
    @PostMapping
    @ApiOperation("Fermer l'application")
    public void exitApp() {
        System.exit(0);
    }

    @Override
    @GetMapping(path = "/health")
    @ApiOperation("Check de l'état de l'application")
    public String checkHealth() {
        return "OK";
    }

}
