package com.quiz.license.sqld.domain.repository;

import com.quiz.license.sqld.domain.entity.SqldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // JPA repository 를 extends 받아서 안에 쓸 수 있는 함수는 찾아보면 금방 나옴.
public interface SqldRepository extends JpaRepository<SqldEntity, Long> {
    // jpa 자주쓰는 함수.
    // select - find, findAll 등등 여기에 컬럼명 붙여서 사용
    // insert - save, saveAll
    // update - save, saveAll(insert 와 사용하는 함수가 같음)
    // delete - deleteById
}
