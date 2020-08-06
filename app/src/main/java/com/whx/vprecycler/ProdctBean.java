package com.whx.vprecycler;

class ProdctBean {
    private String name;
    private int url;

    public ProdctBean(String name, int url) {
        this.name = name;
        this.url = url;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
