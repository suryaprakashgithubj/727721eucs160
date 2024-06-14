package com.Calculator.demo.controller;

import com.Calculator.demo.model.NumberResponse;
import com.Calculator.demo.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping("/{numberId}")
    public NumberResponse getNumbers(@PathVariable String numberId) {
        return numberService.getNumbers(numberId);
    }
}
