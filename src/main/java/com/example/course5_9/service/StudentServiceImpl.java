package com.example.course5_9.service;

import com.example.course5_9.Student;
import com.example.course5_9.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student getById(Integer studentid) {
        return studentDao.getById(studentid);
    }
}
