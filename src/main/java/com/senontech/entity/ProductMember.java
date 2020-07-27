package com.senontech.entity;

import java.util.List;

public class ProductMember {

    private String  productName;
    private List<Member> memberList;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public List<Member> getMemberList() {
        return memberList;
    }
}
