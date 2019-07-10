package com.team.house.util;

public class passParam extends PageParam{
    private Integer floorage;//面积
    private Long price;//价格
    private Integer ispass;//是否审核

    public Integer getIspass() {
        return ispass;
    }

    public void setIspass(Integer ispass) {
        this.ispass = ispass;
    }

    public Integer getFloorage() {
        return floorage;
    }

    public void setFloorage(Integer floorage) {
        this.floorage = floorage;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
