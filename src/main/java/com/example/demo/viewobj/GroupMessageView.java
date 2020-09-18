package com.example.demo.viewobj;

import com.example.demo.domain.GroupMessage;
import com.example.demo.domain.GroupUserRelative;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Result;

import java.util.Date;
import java.util.List;
@Data
public class GroupMessageView {
    //content,picture,record_time,group_id,user_id,power
    String content;
    String picture;
    Date recordTime;
    Integer groupId;
    Integer userId;
    Integer power;
}
