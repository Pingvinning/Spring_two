package spring_two.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_two.entity.Employee;
import spring_two.exeption_handling.EmployeeIncorrectData;
import spring_two.exeption_handling.NoSuchEmployeeException;
import spring_two.exeption_handling.NoSuchEmployeeException;
import spring_two.service.EmployeeService;
import spring_two.service.EmployeeServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee>  employees = employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable("id") int id){
        Employee employee = employeeService.getEmployee(id);

        if(employee == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id+ " in Database.");
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        EmployeeService.saveEmployee(employee);
        return employee;
    }

}
