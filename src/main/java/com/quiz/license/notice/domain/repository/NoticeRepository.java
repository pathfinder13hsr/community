package com.quiz.license.notice.domain.repository;

import com.quiz.license.notice.domain.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
//    List<NoticeEntity> findAll();
    NoticeEntity findBySeq(Long seq);

//    @Transactional
//    void deleteBySeq(Long seq);
    // java.lang.integer is in module java.base of loader 'bootstrap' 에러 NoticeEntity->Long으로 바꾸니까 해결됨 왜지



}
