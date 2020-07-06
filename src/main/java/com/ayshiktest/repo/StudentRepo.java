package com.ayshiktest.repo;

import java.util.List;

import com.ayshiktest.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

	List<Student> findByAddress(String add);

}