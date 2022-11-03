package com.quiz.license.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PagingVo {
    /** 한 페이지당 게시글 수 **/
    private int pageSize = 10;

    /** 한 블럭 사이즈 **/
    private int blockSize = 10;

    /** 현재 페이지 **/
    private int page = 1;

    /** 현재 블럭(range) **/
    private int currentBlock = 1;

    /** 총 게시글 수 **/
    private Long totalCount;

    /** 총 페이지 수 **/
    private int totalPage;

    /** 총 블럭 수 **/
    private int totalBlockCount;

    /** 시작 페이지 **/
    private int startPage = 1;

    /** 끝 페이지 **/
    private int endPage;

    /** 시작 index **/
    private int startIndex = 0;

    /** 이전 페이지 **/
    private int previousPage;

    /** 다음 페이지 **/
    private int nextPage;

    public PagingVo() {
    }

    public PagingVo(int page, int pageSize, Long totalCount){
        setTotalCount(page, pageSize, totalCount);
    }

    public void setTotalCount(int page, int pageSize, Long totalCount){
        this.page = page;
        this.pageSize = pageSize;
        this.startPage = page - ((page - 1) % blockSize);
        setTotalCount(totalCount);
    }

    /** 페이징 처리 **/
    public void setTotalCount(Long totalCount) {
        if(totalCount == null || totalCount <= 0){
            totalCount = 1L;
        }
        /** 총 게시물 수 **/
        this.totalCount = totalCount;

        /** 총 페이지 수 **/
        setTotalPageCount(totalCount);

        /** 총 블럭 수 **/
        setBlockCount(totalPage);

        /** 블럭 설정 **/
        blockSetting(page);

        /** DB 질의를 위한 startIndex 설정 **/
        setStartIndex(page);
    }

    private void setTotalPageCount(Long listCount) {
        this.totalPage = (int) Math.ceil(listCount*1.0/pageSize);
    }

    private void setBlockCount(int pageCount) {
        this.totalBlockCount = (int) Math.ceil(pageCount*1.0/blockSize);
    }

    private void blockSetting(int page){
        setCurrentBlock(page);
        this.endPage = startPage + blockSize - 1;
        if(endPage > totalPage){
            this.endPage = totalPage;
        }
        this.previousPage = page - 1;
        this.nextPage = page + 1;
    }
    private void setCurrentBlock(int page) {
        this.currentBlock = (int)((page-1)/blockSize) + 1;
    }
    private void setStartIndex(int page) {
        this.startIndex = (page-1) * pageSize;
    }
}
