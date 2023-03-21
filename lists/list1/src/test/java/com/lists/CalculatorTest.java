package com.lists;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calc;

    @BeforeEach
    public void initialize() {
        this.calc = new Calculator();
    }

    @Test
    public void nullValue() {
        int rEsp = 0;
        int rObs = calc.evaluate("0+0");
        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void borderLowPositiveValue() {
        int rEsp = 1;
        int rObs = calc.evaluate("0+1");
        Assertions.assertEquals(rEsp, rObs);
    }

    @Test
    public void borderMaxPositiveValue() {
        int rEsp = 2147483647;
        int rObs = calc.evaluate("0+1+2147483646");
        Assertions.assertEquals(rEsp, rObs);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test(expected = NumberFormatException.class)
    public void positeveResultWithNegativeSummationValue() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("NumberFormat For input string");
        calc.evaluate("0-21+22");
    }

    @Test(expected = NumberFormatException.class)
    public void nullResultWithNegativeSummationValue() {    
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("NumberFormat For input string");
        calc.evaluate("0-22+22");    
    }

    @Test(expected = NumberFormatException.class)
    public void improperNegativeResultTestValue() {
        exceptionRule.expect(NumberFormatException.class);
        exceptionRule.expectMessage("NumberFormat For input string");
        calc.evaluate("0-23+22");
    }
}