package com.exceltodb.exceltodbviewer;

import com.formdev.flatlaf.FlatDarkLaf;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ExcelToDbApplication {

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new FlatDarkLaf());
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ExcelToDbApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

}
