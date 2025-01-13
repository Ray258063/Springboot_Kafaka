package com.example.course5_9.controller;

import com.example.course5_9.Student;
import com.example.course5_9.bo.UserBo;
import com.example.course5_9.config.KafkaConfig;
import com.example.course5_9.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;


@RestController

public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
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
        ListenableFuture<SendResult<String,UserBo>> future = kafkaTemplate.send(KafkaConfig.JSON_TOPIC,userBo);

        future.addCallback(new ListenableFutureCallback<SendResult<String, UserBo>>() {
            @Override
            public void onFailure(Throwable throwable) {
               LOGGER.error("fail send message! Do something....");
            }

            @Override
            public void onSuccess(SendResult<String, UserBo> stringUserBoSendResult) {
                LOGGER.info("sucess send message{} with offset:{}", userBo, stringUserBoSendResult.getRecordMetadata().offset());
            }
        });
        return "Published done";
    }

}
