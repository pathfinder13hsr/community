package com.quiz.license.sqld.service;

import com.quiz.license.sqld.domain.entity.SqldEntity;
import com.quiz.license.sqld.domain.repository.SqldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class) // 서비스에서 로직들을 처리하고 있기 때문에 오류나면 rollback 진행하라는 의미의 Annotation 을 달았다.
public class SqldService {

    private final SqldRepository sqldRepository;

    @Autowired
    public SqldService(SqldRepository sqldRepository) {
        this.sqldRepository = sqldRepository;
    }

    public List<SqldEntity> getSqldQuizList() {
        return sqldRepository.findAll(); // findAll 하면 전체가 나옴.
    }

    public void update() {
        SqldEntity sqldEntity = sqldRepository.findById(1L).orElseThrow(() -> new RuntimeException());
        sqldEntity.setContent("안녕하세요");
    }

}
