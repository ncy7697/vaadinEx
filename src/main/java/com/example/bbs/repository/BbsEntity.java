package com.example.bbs.repository;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * <pre>
 * com.example.bbs.repository
 *      BbsEntity
 *
 * Class 설명을 입력하세요.
 *
 * </pre>
 *
 * @author junypooh
 * @see
 * @since 2016-11-29 오후 4:56
 */
@Entity
@Data
public class BbsEntity {

    @Id // 주키생성
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_sequence")
    @SequenceGenerator(name="default_sequence", sequenceName="default_sequence", allocationSize=1)
    private Long id;

    private String title;

    private String contents;

    @Temporal(TemporalType.DATE)
    private Date cretDt;



}
