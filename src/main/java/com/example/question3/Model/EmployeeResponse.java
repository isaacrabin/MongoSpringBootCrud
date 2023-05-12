package com.example.question3.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeResponse {
    int id;

    String name;

    String dept;

    String employeeNumber;

    String position;
}