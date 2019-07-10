package com.team.house.util;

public class ListParam extends PageParam {
    private String title;
    private Integer typeId;
    private Integer did;//区域编号
    private Integer streetId;//街
    private Long startprcie;
    private Long endprcie;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Long getStartprcie() {
        return startprcie;
    }

    public void setStartprcie(Long startprcie) {
        this.startprcie = startprcie;
    }

    public Long getEndprcie() {
        return endprcie;
    }

    public void setEndprcie(Long endprcie) {
        this.endprcie = endprcie;
    }
}
