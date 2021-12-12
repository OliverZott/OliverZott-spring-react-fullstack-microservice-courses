package com.velo.microserviceusermanagement.service;

import com.velo.microserviceusermanagement.model.Course;
import com.velo.microserviceusermanagement.model.Transaction;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course findCurseById(Long id);

    List<Transaction> findTransactionsOfUser(Long userId);

    List<Transaction> findTransactionsOfCourse(Long courseId);

    Transaction saveTransaction(Transaction transaction);
}
