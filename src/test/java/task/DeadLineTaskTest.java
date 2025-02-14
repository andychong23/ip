package task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadLineTaskTest {
    static DeadLineTask dateDeadLineTaskOne;
    static DeadLineTask dateDeadLineTaskTwo;
    static DeadLineTask dateTimeDeadLineTaskOne;
    static DeadLineTask dateTimeDeadLineTaskTwo;

    /**
     * Initial set up of test conditions
     */
    @BeforeAll
    public static void setUp() {
        dateDeadLineTaskOne = new DeadLineTask("test", "12/12");
        dateDeadLineTaskTwo = new DeadLineTask("test", "12/12/2026");
        dateTimeDeadLineTaskOne = new DeadLineTask("test", "12/12 12:00");
        dateTimeDeadLineTaskTwo = new DeadLineTask("test", "12/12 09:00");

        dateDeadLineTaskOne.setTaskDone(true);
    }

    /**
     * Tests for formatting of getDeadLine
     */
    @Test
    public void test_GetDeadline() {
        assertEquals("12 Dec 2025", dateDeadLineTaskOne.getDeadLine());
        assertEquals("12 Dec 2026", dateDeadLineTaskTwo.getDeadLine());
        assertEquals("12 Dec 2025 12:00pm", dateTimeDeadLineTaskOne.getDeadLine());
        assertEquals("12 Dec 2025 09:00am", dateTimeDeadLineTaskTwo.getDeadLine());
    }

    /**
     * Tests for formatting of createSaveData
     */
    @Test
    public void test_CreateSaveData() {
        assertEquals("D|1|test|/by 12/12/2025", dateDeadLineTaskOne.createSaveData());
        assertEquals("D|0|test|/by 12/12/2026", dateDeadLineTaskTwo.createSaveData());
        assertEquals("D|0|test|/by 12/12/2025 12:00", dateTimeDeadLineTaskOne.createSaveData());
        assertEquals("D|0|test|/by 12/12/2025 09:00", dateTimeDeadLineTaskTwo.createSaveData());
    }

    /**
     * Tests for formatting of getTaskInformation
     */
    @Test
    public void test_GetTaskInformation() {
        assertEquals("[D] [X] test (by: 12 Dec 2025)", dateDeadLineTaskOne.getTaskInformation());
        assertEquals("[D] [ ] test (by: 12 Dec 2026)", dateDeadLineTaskTwo.getTaskInformation());
        assertEquals("[D] [ ] test (by: 12 Dec 2025 12:00pm)", dateTimeDeadLineTaskOne.getTaskInformation());
        assertEquals("[D] [ ] test (by: 12 Dec 2025 09:00am)", dateTimeDeadLineTaskTwo.getTaskInformation());
    }
}
