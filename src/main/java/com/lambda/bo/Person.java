package com.lambda.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private int age;
}
