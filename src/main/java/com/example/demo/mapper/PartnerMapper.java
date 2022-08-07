package com.example.demo.mapper;

import com.example.demo.entity.Partner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PartnerMapper {

    @Select("select id, " +
            "partner_name as partnerName, " +
            "partner_mq_key as partnerMqKey " +
            "from a_partners")
    List<Partner> getPartnerList();

    @Select("select partner_mq_key as partnerMqKey from a_partners where partner_name = #{partnerName}")
    String getKeyByName(String partnerName);
}