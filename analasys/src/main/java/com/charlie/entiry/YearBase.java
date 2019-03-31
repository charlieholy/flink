package com.charlie.entiry;

public class YearBase {
    public String getYeartype() {
        return yeartype;
    }

    public void setYeartype(String yeartype) {
        this.yeartype = yeartype;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    //年代类型
    private String yeartype;
    //数量
    private Long count;
}
