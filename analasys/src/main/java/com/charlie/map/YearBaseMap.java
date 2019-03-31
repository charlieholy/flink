package com.charlie.map;

import com.charlie.entiry.YearBase;
import com.charlie.utils.DateUtils;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.util.StringUtils;

//近实时统计 每半个小时统计一次
public class YearBaseMap implements MapFunction<String,YearBase>{

    @Override
    public YearBase map(String value) throws Exception {
       if(org.apache.commons.lang3.StringUtils.isBlank(value)){
           return null;
       }
//        userid int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
////                username varchar(50) default NULL COMMENT '用户名',
////                password VARCHAR(50) default NULL COMMENT '密码',
////                sec int(1) default NULL COMMENT '性别',
////                telphone varchar(50) default NULL COMMENT '手机号',
////                email VARCHAR(50) default NULL COMMENT '邮箱地址',
////                age int(20) default NULL COMMENT '年龄',
////                idCard varchar(50) default NULL COMMENT '身份证',
////                registerTime timestamp default CURRENT_TIMESTAMP COMMENT '注册时间',
////                usetype int(1) default NULL COMMENT '用户类型',
        String[] userinfos = value.split(",");
       String userId = userinfos[0];
       String username = userinfos[1];
       String sex = userinfos[2];
       String tel = userinfos[3];
       String email = userinfos[4];
       String age = userinfos[5];
       String idCard = userinfos[6];
       String registerTime = userinfos[7];
       String usertype = userinfos[8];
        DateUtils.getYearbasebyAge(age);
       return null;
    }
}
