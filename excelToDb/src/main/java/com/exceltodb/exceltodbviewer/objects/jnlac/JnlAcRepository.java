package com.exceltodb.exceltodbviewer.objects.jnlac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JnlAcRepository extends JpaRepository<JnlAcEntity, Integer> {

    JnlAcEntity findByRefPiece(String refPiece);

}
