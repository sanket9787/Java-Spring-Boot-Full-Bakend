package com.UnitTest.UnitTest;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class SetupExampleTest {
    int count;

    @BeforeAll
    void setupBeforeAl(){
        System.out.println("Executing once before all tests start");
    }

    @AfterAll
    void setupAfterAll(){
        System.out.println("Executing once after all tests ends");
    }
    @BeforeEach
    void setup(){
        count = 0;
        System.out.println("Before each test");
    }

    @AfterEach
    void tearDown(){
        System.out.println("After each test");
    }

    @Test
    void testCountIncrement(){
        count++;
        System.out.println(count);
        assertEquals(1, count);
    }

    @Test
    void testCountIncrement2(){
        count++;
        System.out.println(count);
        assertEquals(1, count);
    }
}
