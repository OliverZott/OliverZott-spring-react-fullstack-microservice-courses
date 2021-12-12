package com.velo.microserviceusermanagement.service;

import com.velo.microserviceusermanagement.model.Course;
import com.velo.microserviceusermanagement.model.Transaction;
import com.velo.microserviceusermanagement.repository.CourseRepository;
import com.velo.microserviceusermanagement.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final TransactionRepository transactionRepository;

    public CourseServiceImpl(CourseRepository courseRepository, TransactionRepository transactionRepository) {
        this.courseRepository = courseRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCurseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Transaction> findTransactionsOfUser(Long userId) {
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> findTransactionsOfCourse(Long courseId) {
        return transactionRepository.findAllByCourseId(courseId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
