package com.example.course5_9.bo;

public class UserBo {
    //test 1
    private String id;
    private String dpt;

    // 無參構造器
    public UserBo() {
    }

    public UserBo(String id, String dpt) {
        this.id = id;
        this.dpt = dpt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    @Override
    public String toString() {
        return "UserVo [id= " + id + ", dpt=" + dpt + "]";
    }
}
