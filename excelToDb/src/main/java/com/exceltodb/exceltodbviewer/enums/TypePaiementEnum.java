package com.exceltodb.exceltodbviewer.enums;

import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcExcel;
import lombok.Getter;

@Getter
public enum TypePaiementEnum {
    TTC("9"),
    HT("6"),
    TVA("4"),
    IMMOBILISATION("2"),
    NOTYPE("");

    private String numId;
    TypePaiementEnum(String numId) {
        this.numId = numId;
    }

    public static TypePaiementEnum getTypePaiement(JnlAcExcel exc) {
        if(exc.getCompte().startsWith("9")) {
            return TypePaiementEnum.TTC;
        } else if (exc.getCompte().startsWith("6") || exc.getCompte().startsWith("2")) {
            return TypePaiementEnum.HT;
        } else if (exc.getCompte().startsWith("4")) {
            return TypePaiementEnum.TVA;
        } else {
            return TypePaiementEnum.NOTYPE;
        }
    }
}
