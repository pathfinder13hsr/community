package com.quiz.license.notice.domain.dto;

import com.quiz.license.common.vo.PagingVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeSearchDto extends PagingVo {
    private Long seq;
    private String title;
    private String content;
    private String createId;
    private String searchTxt;
    private String searchType;
}
