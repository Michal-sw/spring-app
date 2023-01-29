package com.projekt.planLekcji.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Student id DB")
public class StudentNotFoundException extends RuntimeException {
}
