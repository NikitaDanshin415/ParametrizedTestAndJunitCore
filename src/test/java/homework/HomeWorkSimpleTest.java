package homework;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Simple test")
public class HomeWorkSimpleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Call before all method");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("    Call before each method");
    }

    @DisplayName("Ожидаемый зеленый тест")
    @Test
    void simpleGreenTest() {
        assertTrue(3 > 2);
    }

    @DisplayName("Ожидаемый красный тест")
    @Test
    void simpleRedTest() {
        assertTrue(3 < 2);
    }

    @Test
    @Disabled("Bug: jira-1234")
    void simpleBrokenTest() {
        throw new IllegalStateException("Broken:(");
    }


    @AfterAll
    static void AfterAll() {
        System.out.println("Call after all method");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("    Call after Each method");
    }
}
