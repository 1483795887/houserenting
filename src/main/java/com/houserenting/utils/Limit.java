package com.houserenting.utils;

import java.util.Map;

public class Limit {
    private int page;
    private int size;

    private final static int DEFAULT_SIZE = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static Limit getFromMap(Map o){
        Limit limit = new Limit();
        try{
            limit.setPage((int)o.get("page"));
            limit.setSize((int)o.get("size"));
        }catch (NullPointerException e){
            limit.setPage(1);
            limit.setSize(DEFAULT_SIZE);
        }
        return limit;
    }

    public int getBegin(){
        return (page - 1) * size;
    }

    public boolean valid(){
        return page >= 1 && size >= 1;
    }
}
