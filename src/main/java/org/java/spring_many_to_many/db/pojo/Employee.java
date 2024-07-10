package org.java.spring_many_to_many.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 36, nullable = false)
    private String name;

    @Column(length = 36, nullable = false, unique = true)
    private String surname;

    @Column(nullable = false)
    private int salary;

    @ManyToMany
    private List<Task> tasks;

    public Employee(){

    }

    public Employee(String name, String surname, int salary){
        setId(id);
        setName(name);
        setSurname(surname);
        setSalary(salary);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) throws IllegalArgumentException{
        if (salary < 1) {
            throw new IllegalArgumentException("questo campo non può essere negativo");
        }
        this.salary = salary;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks = tasks.stream().filter(t -> t.getId() != task.getId()).toList();
    }

    @Override
    public String toString() {
        return "EMPLOYEE ID: [ " + getId() + " ] | name: " + getName() + " | surname: " + getSurname() + " | salary: " + getSalary();
    }
}
