package com.example.demo.entity;

public class Partner {
    private int id;
    private String partnerName;
    private String partnerMqKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerMqKey() {
        return partnerMqKey;
    }

    public void setPartnerMqKey(String partnerMqKey) {
        this.partnerMqKey = partnerMqKey;
    }

    @Override
    public String toString() {
        return "partner{" +
                "id=" + id +
                ", partnerName='" + partnerName + '\'' +
                ", partnerMqKey='" + partnerMqKey + '\'' +
                '}';
    }
}
