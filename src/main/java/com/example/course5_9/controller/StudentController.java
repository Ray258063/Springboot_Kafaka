package com.example.course5_9.controller;

import com.example.course5_9.Student;
import com.example.course5_9.bo.UserBo;
import com.example.course5_9.config.KafkaConfig;
import com.example.course5_9.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("kafka")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private KafkaTemplate<String, UserBo> kafkaTemplate;
    @GetMapping("/students")
    public String test(){
        return "test sucess";
    }

    @GetMapping("/students/{studentId}")
    public Student select(@PathVariable Integer studentId){
        return studentService.getById(studentId);
    }

    @GetMapping("/students/{dpt}/{id}")
    public String postUser(@PathVariable final String dpt, @PathVariable final String id){
        UserBo userBo = new UserBo(dpt,id);
        kafkaTemplate.send(KafkaConfig.JSON_TOPIC,userBo);
        return "Published done";
    }

}
