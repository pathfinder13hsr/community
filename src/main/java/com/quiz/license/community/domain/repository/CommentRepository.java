package com.quiz.license.community.domain.repository;

import com.quiz.license.community.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    CommentEntity findBySeqAndDelYnAndAccount_Seq(Long seq, String delYn, Long accountSeq);
    CommentEntity findBySeqAndDelYn(Long seq, String delYn); // 관리자 삭제를 위해 accountSeq 는 조건에 넣지 않는다.
}
