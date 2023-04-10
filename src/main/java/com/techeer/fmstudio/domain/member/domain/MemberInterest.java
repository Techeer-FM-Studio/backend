package com.techeer.fmstudio.domain.member.domain;

public enum MemberInterest implements EnumModel{
    BACKEND("Backend Engineering"),
    FRONTEND("Frontend Engineering");

    private String desc;

    MemberInterest(String desc){
        this.desc = desc;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return desc;
    }
}
