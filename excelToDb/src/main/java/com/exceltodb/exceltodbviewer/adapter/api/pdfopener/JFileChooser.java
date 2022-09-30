package com.exceltodb.exceltodbviewer.adapter.api.pdfopener;

import org.springframework.context.annotation.Configuration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Configuration
public class JFileChooser extends JPanel implements ActionListener {

    javax.swing.JFileChooser chooser;

    public JFileChooser() {
    }

    public void actionPerformed(ActionEvent e) {
    }

    public javax.swing.JFileChooser chooseDirectory() {
        chooser = new javax.swing.JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Définition du dossier de destination...");
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
        return chooser;
    }
}