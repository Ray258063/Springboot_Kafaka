package com.example.course5_9;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student=new Student();
        student.setId(resultSet.getInt("id"));//取得id那個column的數據
        student.setName(resultSet.getString("name"));  //取得name那個column的數據
        return student;
    }
}
