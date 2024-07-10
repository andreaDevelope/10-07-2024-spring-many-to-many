package org.java.spring_many_to_many.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_many_to_many.db.pojo.Employee;
import org.java.spring_many_to_many.db.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo er;

    public List<Employee> getAll(){
       return er.findAll();
    }

    public Optional<Employee> getEmployeeById(int id){
       return er.findById(id);
    }

    public void save(Employee e){
        er.save(e);
    }

    public void delete(Employee e){
        er.delete(e);
    }

    @Transactional
    public Optional<Employee> getByIdWithTasks(int id) {

        Optional<Employee> optEmployee = getEmployeeById(id);

        if (optEmployee.isEmpty())
            return Optional.empty();

        Employee employee = optEmployee.get();
        Hibernate.initialize(employee.getTasks());

        return Optional.of(employee);
    }
}
