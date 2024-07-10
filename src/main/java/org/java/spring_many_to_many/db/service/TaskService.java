package org.java.spring_many_to_many.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_many_to_many.db.pojo.Task;
import org.java.spring_many_to_many.db.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private TaskRepo tr;

    public List<Task> getAll(){
       return tr.findAll();
    }

    public Optional<Task> getEmployeeById(int id){
       return tr.findById(id);
    }

    public void save(Task e){
        tr.save(e);
    }

    public void delete(Task e){
        tr.delete(e);
    }

    @Transactional
    public Optional<Task> findByIdWithEmployee(int id) {
        Optional<Task> optTask = tr.findById(id);

        if (optTask.isEmpty())
            return Optional.empty();

        Task task = optTask.get();
        Hibernate.initialize(task.getEmployees());

        return Optional.of(task);
    }
}
