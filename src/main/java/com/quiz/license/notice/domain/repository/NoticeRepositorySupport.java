package com.quiz.license.notice.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.domain.dto.CommunitySearchDto;
import com.quiz.license.community.domain.entity.QCommunityEntity;
import com.quiz.license.notice.domain.dto.NoticeDto;
import com.quiz.license.notice.domain.dto.NoticeSearchDto;
import com.quiz.license.notice.domain.entity.QNoticeEntity;
import com.quiz.license.user.domain.entity.QAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Repository
public class NoticeRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public NoticeRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<NoticeDto> getNoticeList(NoticeSearchDto noticeSearchDto, int page, int pageSize) {

        QNoticeEntity qNoticeEntity = QNoticeEntity.noticeEntity;
        QAccountEntity qAccountEntity = QAccountEntity.accountEntity;

        System.out.println("page : " + page);
        System.out.println("pageSize : " + pageSize);
        System.out.println("(page - 1) * pageSize : " + (page - 1) * pageSize);

        // TODO. 페이징 내용 추가 해야함.
        return jpaQueryFactory.select(Projections.constructor(NoticeDto.class, qNoticeEntity, qAccountEntity))
                .from(qNoticeEntity)
                .leftJoin(qAccountEntity).on(qNoticeEntity.account.seq.eq(qAccountEntity.seq))
                .where(
                          containsTitle(noticeSearchDto.getTitle())
                        , containsContent(noticeSearchDto.getContent())
                        , containsUserId(noticeSearchDto.getCreateId())
                )
                .orderBy(qNoticeEntity.createDatetime.desc())
                .offset((page - 1) * pageSize)
                .limit(pageSize)
                .fetch();
    }

    public Long getListCount(NoticeSearchDto noticeSearchDto) {

        QNoticeEntity qNoticeEntity = QNoticeEntity.noticeEntity;
        QAccountEntity qAccountEntity = QAccountEntity.accountEntity;

        return jpaQueryFactory.select(qNoticeEntity.count())
                .from(qNoticeEntity)
                .leftJoin(qAccountEntity).on(qNoticeEntity.account.seq.eq(qAccountEntity.seq))
                .where(
                        containsTitle(noticeSearchDto.getTitle())
                        , containsContent(noticeSearchDto.getContent())
                        , containsUserId(noticeSearchDto.getCreateId())
                )
                .fetchOne();
    }

    public NoticeDto getNotice(Long seq) {

        QNoticeEntity qNoticeEntity = QNoticeEntity.noticeEntity;
        QAccountEntity qAccountEntity = QAccountEntity.accountEntity;

        // TODO. 페이징 내용 추가 해야함.
        return jpaQueryFactory.select(Projections.constructor(NoticeDto.class, qNoticeEntity, qAccountEntity))
                .from(qNoticeEntity)
                .leftJoin(qAccountEntity).on(qNoticeEntity.account.seq.eq(qAccountEntity.seq))
                .where(
                        qNoticeEntity.seq.eq(seq)
                )
                .fetchOne();
    }

    private BooleanExpression containsTitle(String title) {
        if (ObjectUtils.isEmpty(title)) {
            return null;
        }
        return QNoticeEntity.noticeEntity.title.contains(title);
    }

    private BooleanExpression containsContent(String content) {
        if (ObjectUtils.isEmpty(content)) {
            return null;
        }
        return QNoticeEntity.noticeEntity.content.contains(content);
    }


    private BooleanExpression containsUserId(String userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return null;
        }
        return QAccountEntity.accountEntity.userId.contains(userId);
    }

}
