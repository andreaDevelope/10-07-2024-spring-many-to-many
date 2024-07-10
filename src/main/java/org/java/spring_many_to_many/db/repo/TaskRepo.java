package org.java.spring_many_to_many.db.repo;

import org.java.spring_many_to_many.db.pojo.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer>{

}
