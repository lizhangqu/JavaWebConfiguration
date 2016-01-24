package cn.edu.zafu.dao;

import java.util.List;

/**
 * Created by lizhangqu on 16/1/24.
 */
public class PaginationResult<T> {

    int total;

    int pageSize;

    int page;

    List<T> list;

    public PaginationResult(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        int s = (getPage() - 1) * pageSize;
        if (s > total) {
            s = 0;
            page = 1;
        }
        return s;
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

    public int getPage() {
        return page < 1 ? 1 : page;
    }

    public int getPageTotal() {
        return total <= 0 ? 1 : ((total - 1) / pageSize) + 1;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PaginationResult{" +
                "list=" + list +
                ", total=" + total +
                ", pageSize=" + pageSize +
                ", page=" + page +
                '}';
    }
}
