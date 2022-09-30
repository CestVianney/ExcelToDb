package com.exceltodb.exceltodbviewer.objects.jnlac;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JnlAcExcel {
    private String journal;
    private String date;
    private String compte;
    private String refPiece;
    private String libelle;
    private Double debit;
    private Double credit;
    private Date echeance;
}
