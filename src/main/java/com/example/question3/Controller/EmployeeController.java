package com.example.question3.Controller;

import com.example.question3.Model.Employee;
import com.example.question3.Model.EmployeeResponse;
import com.example.question3.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("employees")
    @ResponseStatus(HttpStatus.OK)
    public Object getAllEmployee(){
       return this.employeeService.getAll();
    }

    @PostMapping("employee/new")
    @ResponseStatus(HttpStatus.OK)
    public Object getAllEmployee(@RequestBody Employee employee){
         this.employeeService.saveEmployee(employee);
         return this.employeeService.saveEmployee(employee);
    }

    @DeleteMapping("employee")
    @ResponseStatus(HttpStatus.OK)
    public Object deleteEmployee(@RequestParam int id){
        this.employeeService.deleteEmployee(id);
        return this.employeeService.deleteEmployee(id);
    }

    @PutMapping("employee")
    @ResponseStatus(HttpStatus.OK)
    public Object updateEmployee(@RequestParam int id, @RequestBody Employee employee){
        this.employeeService.updateEmployee(id,employee);
        return this.employeeService.updateEmployee(id,employee);
    }

}
