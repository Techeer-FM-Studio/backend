package com.techeer.fmstudio.domain.member.domain;

public enum MemberStatus implements EnumModel {
    NORMAL("NORMAL_STATUS");

    private String memberStatus;

    MemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return memberStatus;
    }
}
