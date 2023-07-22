package com.brazil.erudio.controllers;

import com.brazil.erudio.controllers.simples.SimpleMath;
import com.brazil.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.brazil.erudio.converters.NumberConverter.convertToDouble;
import static com.brazil.erudio.converters.NumberConverter.isNumeric;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MathController {

    private final SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = GET)
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.subtraction(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = GET)
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.multiplication(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = GET)
    public Double division(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.division(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = GET)
    public Double mean(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) {

        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.mean(convertToDouble(numberOne), convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/squareRoot/{number}", method = GET)
    public Double squareRoot(@PathVariable(value = "number") String number) {

        if (!isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return math.squareRoot(convertToDouble(number));
    }

}
