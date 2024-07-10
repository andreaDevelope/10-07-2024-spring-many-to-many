package org.java.spring_many_to_many;

import java.util.List;
import java.util.Optional;

import org.java.spring_many_to_many.db.pojo.Employee;
import org.java.spring_many_to_many.db.pojo.Task;
import org.java.spring_many_to_many.db.service.EmployeeService;
import org.java.spring_many_to_many.db.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringManyToManyApplication implements CommandLineRunner {

	@Autowired
	EmployeeService es;

	@Autowired
	TaskService ts;

	public static void main(String[] args)  throws Exception {
		SpringApplication.run(SpringManyToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testNoRelations();
		testRelationsManyToMany();
		System.out.println("END");
	}

	public void testNoRelations(){
		Employee e1 = new Employee("Rollo Pollo","rolloNumerOne", 1500);
		Employee e2 = new Employee("Carlo Magno","meMagnoTutto", 2000);

		Task t1 = new Task("fe", "sviluppo del fe", 2);

		es.save(e1);
		es.save(e2);
		ts.save(t1);

		es.getAll().forEach(System.out::println);
		ts.getAll().forEach(System.out::println);

		System.out.println("--------------------------------------------");

		es.delete(e1);
		ts.delete(t1);

		es.getAll().forEach(System.out::println);
		ts.getAll().forEach(System.out::println);
	}

	public void testRelationsManyToMany(){
		// CREO LA MIA LISTA DI EMPLOYEE
		Employee e3 = new Employee("Maldi Testa", "cioMalDiTesta", 3000);

		es.save(e3);

		// INIZIO testRelationsManyToMany IN TERMINALE
		System.out.println("-------------------------------------------------------------");

		List<Employee> employees = es.getAll();
		for (Employee employee : employees) {
			System.out.println(employee);
		}	
		
		// CREO LA MIA LISTA DI TASK
		Task t2 = new Task("be", "sviluppo del be", 4);
		Task t3 = new Task("full-stack", "full-stack developer", 5);

		ts.save(t2);
		ts.save(t3);

		System.out.println("----------------------------------------------------------------");

		List<Task> tasks = ts.getAll();
		for (Task task : tasks) {
			System.out.println(task);
		}

		// RELAZIONO LA TABELLA PADRE EMPLOYEE A TASK
		Optional<Employee> opt2 = es.getByIdWithTasks(2);

		Optional<Employee> opt3 = es.getByIdWithTasks(3);

		if (opt2.isEmpty()) {
			System.out.println("employee con id: 2 non trovato");
			return;
		}
		
		if (opt3.isEmpty()) {
			System.out.println("employee con id: 3 non trovato");
			return;
		}

		System.out.println("-------------------------------------");

		Employee e2 = opt2.get();
		e2.addTask(t2);
		es.save(e2);

		e3 = opt3.get();
		e3.addTask(t3);
		es.save(e3);

		//RIMUOVO LA RELAZIONE TRA EMPLOYEE E TASK 
		e2.removeTask(t2);
		es.save(e2);

		//FINALMENTE POSSO CANCELLARE IL TASK
		ts.delete(t2);

		System.out.println(t2 + " | taask cancellato");
	}
}
