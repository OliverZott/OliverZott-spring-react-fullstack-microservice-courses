package com.velo.microserviceusermanagement.controller;

import com.velo.microserviceusermanagement.intercomm.UserClient;
import com.velo.microserviceusermanagement.model.Transaction;
import com.velo.microserviceusermanagement.service.CourseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    private final UserClient userClient;
    private final CourseService courseService;
    // list discoverable services and instances with eureka discovery service
    private final DiscoveryClient discoveryClient;
    // to display port number of service-instance and list all instances of user-service
    private final Environment environment;

    @Value("${spring.application.name}")
    private String serviceId;

    public CourseController(UserClient userClient, CourseService courseService, DiscoveryClient discoveryClient, Environment environment) {
        this.userClient = userClient;
        this.courseService = courseService;
        this.discoveryClient = discoveryClient;
        this.environment = environment;
    }

    @GetMapping("/service/port")
    public String getPort() {
        return "Hiho, service is working at port " + environment.getProperty("local.server.port");
    }

    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances() {
        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
    }

    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findTransactionsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(courseService.findTransactionsOfUser(userId));
    }

    @GetMapping("/service/all")
    public ResponseEntity<?> findAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/service/enroll")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        transaction.setDateOfIssue(LocalDateTime.now());
        transaction.setCourse(courseService.findCurseById(transaction.getCourse().getId()));
        return new ResponseEntity<>(courseService.saveTransaction(transaction), HttpStatus.OK);
    }

    @GetMapping("/service/course/{courseId}")
    public ResponseEntity<?> findStudentsOfCourse(@PathVariable Long courseId) {
        List<Transaction> transactions = courseService.findTransactionsOfCourse(courseId);
        if (CollectionUtils.isEmpty(transactions)) {
            return ResponseEntity.notFound().build();
        }
//        List<Long> userIdList = transactions.parallelStream().map(t -> t.getUserId()).collect(Collectors.toList());
        List<Long> userIdList = transactions.parallelStream().map(Transaction::getUserId).collect(Collectors.toList());
        List<String> students = userClient.getUserNames(userIdList);
        return ResponseEntity.ok(students);
    }


}











