package org.java.spring_many_to_many.db.repo;

import org.java.spring_many_to_many.db.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
