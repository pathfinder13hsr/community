package com.quiz.license.community.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.quiz.license.community.domain.dto.CommunityDto;
import com.quiz.license.community.domain.dto.CommunitySearchDto;
import com.quiz.license.community.domain.entity.QCommunityEntity;
import com.quiz.license.user.domain.entity.QAccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Repository
public class CommunityRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public CommunityRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<CommunityDto> getCommunityList(CommunitySearchDto communitySearchDto, int page, int pageSize) {

        QCommunityEntity qCommunityEntity = QCommunityEntity.communityEntity;
        QAccountEntity qAccountEntity = QAccountEntity.accountEntity;

        return jpaQueryFactory.select(Projections.constructor(CommunityDto.class, qCommunityEntity))
                .from(qCommunityEntity)
                .leftJoin(qAccountEntity).on(qCommunityEntity.account.seq.eq(qAccountEntity.seq))
                .where(
                          containsAll(communitySearchDto.getSearchTxt())
                        , containsTitle(communitySearchDto.getTitle())
                        , containsContent(communitySearchDto.getContent())
                        , containsUserId(communitySearchDto.getAccountId())
                )
                .orderBy(qCommunityEntity.createDatetime.desc())
                .offset((page - 1) * pageSize)
                .limit(pageSize)
                .fetch();
    }

    public Long getListCount(CommunitySearchDto communitySearchDto){

        QCommunityEntity qCommunityEntity = QCommunityEntity.communityEntity;
        QAccountEntity qAccountEntity = QAccountEntity.accountEntity;

        return jpaQueryFactory.select(qCommunityEntity.count())
                .from(qCommunityEntity)
                .leftJoin(qAccountEntity).on(qCommunityEntity.account.seq.eq(qAccountEntity.seq))
                .where(
                        containsAll(communitySearchDto.getSearchTxt())
                        , containsTitle(communitySearchDto.getTitle())
                        , containsContent(communitySearchDto.getContent())
                        , containsUserId(communitySearchDto.getAccountId())
                )
                .fetchOne();
    }

    private BooleanExpression containsAll(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return QCommunityEntity.communityEntity.title.contains(value)
                .or(QCommunityEntity.communityEntity.content.contains(value))
                .or(QCommunityEntity.communityEntity.account.userId.contains(value));
    }

    private BooleanExpression containsTitle(String title) {
        if (ObjectUtils.isEmpty(title)) {
            return null;
        }
        return QCommunityEntity.communityEntity.title.contains(title);
    }

    private BooleanExpression containsContent(String content) {
        if (ObjectUtils.isEmpty(content)) {
            return null;
        }
        return QCommunityEntity.communityEntity.content.contains(content);
    }


    private BooleanExpression containsUserId(String userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return null;
        }
        return QAccountEntity.accountEntity.userId.contains(userId);
    }
    
}
