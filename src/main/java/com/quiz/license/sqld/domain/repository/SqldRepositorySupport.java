package com.quiz.license.sqld.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository // jpa 로는 복잡한 select 문은 구현하기가 힘들어서 queryDsl 을 이용하려고 민든 클래스.
public class SqldRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public SqldRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
