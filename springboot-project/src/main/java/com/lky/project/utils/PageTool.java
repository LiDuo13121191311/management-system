package com.lky.project.utils;

public class PageTool {
    private int totalCount;//总数据量
    private int currentPage;//当前页码
    private int pageCount;//总页数
    private int lastPage;//上一页页码
    private int nextPage;//下一页页码
    private int startIndex;//起始下标
    private int pageSize;//每页数据量

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageTool(int totalCount, String currentPage,int pageSize) {
        //初始化总数据量
        this.totalCount = totalCount;
        //初始化每页数据量
        this.pageSize = pageSize;
        //初始化当前页码
        if (currentPage == null) {
            this.currentPage = 1;
        } else {
            this.currentPage = Integer.parseInt(currentPage);
        }

        //初始化总页数
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);

        //上一页页码的初始化
        if (this.currentPage == 1) {
            lastPage = 1;
        } else {
            lastPage = this.currentPage - 1;
        }

        //下一页页码的初始化
        if (this.currentPage == pageCount) {
            nextPage = pageCount;
        } else {
            nextPage = this.currentPage + 1;
        }

        //起始下标初始化
        startIndex = (this.currentPage - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "PageTool{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", lastPage=" + lastPage +
                ", nextPage=" + nextPage +
                ", startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                '}';
    }
}
