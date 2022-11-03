package com.quiz.license.community.domain.dto;

import com.quiz.license.common.vo.PagingVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

@Getter
@Setter
@ToString
public class CommunitySearchDto extends PagingVo {

    private Long seq;
    private String title;
    private String content;
    private String accountId;
    private String searchTxt;
    private String searchType;

}
