package com.example.course5_9.dao;

import com.example.course5_9.Student;

public interface StudentDao {
    //根據id的值去查詢
    Student getById(Integer studentId);
}
