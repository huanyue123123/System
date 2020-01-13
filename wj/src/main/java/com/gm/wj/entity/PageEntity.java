package com.gm.wj.entity;

public class PageEntity {

    private Integer pageNo;

    private Integer pageSize;

    public PageEntity(){
        this.pageNo = 1;
        this.pageSize = 10;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
