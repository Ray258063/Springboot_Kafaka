package com.example.course5_9.dao;

import com.example.course5_9.Student;
import com.example.course5_9.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//變成一個bean讓別人注入
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Student getById(Integer studentId) {
        String sql="Select id, name From Member where id= :studentid";

        Map<String,Object> map= new HashMap<>();
        map.put("studentid",studentId);
        List<Student> list=namedParameterJdbcTemplate.query(sql,map,new StudentRowMapper());
        if (list.size()>0){
            return list.get(0);
        }
        else{
            return null;
        }
    }
}
