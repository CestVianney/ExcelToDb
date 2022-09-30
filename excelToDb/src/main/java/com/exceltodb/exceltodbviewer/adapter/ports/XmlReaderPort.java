package com.exceltodb.exceltodbviewer.adapter.ports;

import com.exceltodb.exceltodbviewer.objects.jnlac.JnlAcEntity;

import java.util.List;

public interface XmlReaderPort {
    List<JnlAcEntity> getDbValues();
}
