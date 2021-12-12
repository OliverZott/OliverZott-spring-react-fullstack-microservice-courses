package com.velo.microserviceusermanagement.repository;

import com.velo.microserviceusermanagement.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
