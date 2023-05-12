package com.example.question3.Service;

import com.example.question3.Model.Employee;
import com.example.question3.Model.EmployeeResponse;
import com.example.question3.Model.ResponseMessage;
import com.example.question3.Repository.EmployeeRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public Object getAll(){
        List<Employee> employees = employeeRepository.findAll();
        HashMap object;
        object = new HashMap<>();
        object.put("responseCode","00");
        object.put("responseMessage","Employee list fetched successfully");
        object.put("data",employees.stream().map(this::mapToEmployeeResponse).toList());
        log.info("Request to fetch employee list");
        return object;
    }

    public Object saveEmployee(Employee employee){
        try {
            HashMap object;
            object = new HashMap<>();
            object.put("responseCode", "00");
            object.put("responseMessage","Employee saved successfully");
            log.info("New employee ===={}===== has been added",employee);
            employeeRepository.save(employee);
            return object;
        } catch (Exception e) {
            HashMap object;
            object = new HashMap<>();
            object.put("responseCode", "01");
            object.put("responseMessage","Couldn't save employee");
            log.info("Could not save new employee===={}=====",employee);
            //throw new RuntimeException(e);
            return object;
        }

    }

    public Object deleteEmployee(int id){
        try {
            HashMap object;
            object = new HashMap<>();
            object.put("responseCode", "00");
            object.put("responseMessage","Employee deleted successfully");
            log.info("Employee of ID ===={}===== has been deleted successfully",id);
            employeeRepository.deleteById(id);
            return object;
        } catch (Exception e) {
            HashMap object;
            object = new HashMap<>();
            object.put("responseCode", "01");
            object.put("responseMessage","Couldn't delete employee");
            log.info("Failed to delete employee of ID===={}=====",id);
            //throw new RuntimeException(e);
            return object;
        }

    }

    public Object updateEmployee(int id, Employee employee){
        try {
            HashMap object;
            object = new HashMap<>();
            if(checkIfEmployeeExist(id)){
                Query query = new Query().addCriteria(Criteria.where("id").is(id));
                Update update = new Update().set("dept", employee.getDept());
                Update updatedNo = new Update().set("employeeNumber", employee.getEmployeeNumber());
                Update updatedName = new Update().set("name", employee.getName());
                Update updatedPos = new Update().set("position", employee.getPosition());
                mongoTemplate.updateFirst(query,update,Employee.class);
                mongoTemplate.updateFirst(query, updatedName,Employee.class);
                mongoTemplate.updateFirst(query, updatedPos,Employee.class);
                mongoTemplate.updateFirst(query, updatedNo,Employee.class);
                object.put("responseCode", "00");
                object.put("responseMessage","Employee updated successfully");
                log.info("Request to update employee of ID===={}=====",id);
                return object;
            }
            else{
                object.put("responseCode", "01");
                object.put("responseMessage","Employee does not exist.");
                log.info("Request to update employee of ID===={}=====",id);
                return object;
            }

        } catch (Exception e) {
            HashMap object;
            object = new HashMap<>();
            object.put("responseCode", "01");
            object.put("responseMessage","Couldn't update employee");
            log.info("Failed to update employee of ID===={}=====",id);
            throw new RuntimeException(e);
//            return object;
        }

    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .dept(employee.getDept())
                .employeeNumber(employee.getEmployeeNumber())
                .position(employee.getPosition())
                .build();
    }

    private EmployeeResponse mapToResponseMessage(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .dept(employee.getDept())
                .employeeNumber(employee.getEmployeeNumber())
                .position(employee.getPosition())
                .build();
    }

    private boolean checkIfEmployeeExist(int id){
    Optional<Employee> result = employeeRepository.findById(id);
    log.info("Employee exist====={}===",result.isPresent());
    return result.isPresent();
    }
}
