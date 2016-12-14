package com.example.bbs.controller;

import com.example.bbs.repository.BbsEntity;
import com.example.bbs.repository.BbsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <pre>
 * com.example.bbs.controller
 *      BbsController
 *
 * Class 설명을 입력하세요.
 *
 * </pre>
 *
 * @author junypooh
 * @see
 * @since 2016-11-29 오후 5:30
 */
@Slf4j
@RestController
public class BbsController {

    @Autowired
    private BbsRepository bbsRepository;

    @RequestMapping("save")
    public String save() {

        log.debug("save");

        BbsEntity bbsEntity = new BbsEntity();
        bbsEntity.setTitle("제목입니다.");
        bbsEntity.setContents("내용입니다.");
        bbsEntity.setCretDt(new Date());

        bbsRepository.save(bbsEntity);

        return "OK!";
    }

    @RequestMapping("select")
    public Object select() {

        log.debug("select");

        return bbsRepository.findByTitleIgnoreCaseContaining("제목");
    }
}
