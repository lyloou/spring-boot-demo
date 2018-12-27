package com.lyloou.demo.pojo;

import java.util.List;

public class PageData<T> {
    private int total = 0;
    private int pageSize = 20;
    private int currentPage = 1;
    private int totalPage = 1;
    private List<T> rows = null;

    public PageData(int total, int pageSize, int currentPage, List<T> rows) {
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.rows = rows;

        this.totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}