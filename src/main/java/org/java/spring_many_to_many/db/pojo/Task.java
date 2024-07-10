package org.java.spring_many_to_many.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 34, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int level;

    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employees;

    public Task(){

    }

    public Task(String title, String description, int level){
        setId(id);
        setTitle(title);
        setDescription(description);
        setLevel(level);
        setEmployees(employees);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws IllegalArgumentException{
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("questo campo deve essere compreso tra 1 e 5");
        }
        this.level = level;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public String toString() {
        return "TASK ID: [" + getId() + "] | title: " + getTitle() + " | description: " + getDescription() + " | level: " + getLevel();
    }
}
