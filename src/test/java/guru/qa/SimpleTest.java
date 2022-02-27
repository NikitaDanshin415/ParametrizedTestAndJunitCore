package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Simple test")
public class SimpleTest {
    @DisplayName("Ожидаемый зеленый тест")
    @Test
    void simpleGreenTest(){
        assertTrue(3 > 2);
    }

    @DisplayName("Ожидаемый красный тест")
    @Test
    void simpleRedTest(){
        assertTrue(3 < 2);
    }

    @Test
    @Disabled("Bug: jira-1234")
    void simpleBrokenTest(){
        throw new IllegalStateException("Broken:(");
    }
}
