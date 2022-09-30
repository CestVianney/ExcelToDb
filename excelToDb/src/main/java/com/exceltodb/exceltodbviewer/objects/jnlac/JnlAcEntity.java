package com.exceltodb.exceltodbviewer.objects.jnlac;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class JnlAcEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String journal;
    private String date;
    private String refPiece;
    private String libelle;
    private Double ttc;
    private Double ht;
    private Double tva;
}
