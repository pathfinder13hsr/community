package com.quiz.license.user.domain.repository;

import com.quiz.license.user.domain.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAll();
    boolean existsByUserId(String userId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);
    AccountEntity findBySeq(Long seq);
    AccountEntity findByUserId(String userId);



}
