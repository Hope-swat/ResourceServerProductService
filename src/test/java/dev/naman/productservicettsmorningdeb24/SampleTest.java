package dev.naman.productservicettsmorningdeb24;

import dev.naman.productservicettsmorningdeb24.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SampleTest {
    @Test
    void testAddAndMultiplyFunctionalities() {
        //Arrange + Act
        int x = 1 + 1;
        int y = 2 * 2;

//        assert x == 2;
//
//        assert y == 4;

        assertEquals(5, x);
        assertEquals(3, y);
    }
}

/*
A test case is nothing but a method.

AAA
Arrange -> Creating the input params
Act -> calling the required functions.
Assert ->  Checking against the expected value.

In one test case, we can have multiple assertions.
A test case will pass if all of its assertion will pass.
 */
