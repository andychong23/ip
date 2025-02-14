package task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    static EventTask dateEventTaskOne;
    static EventTask dateEventTaskTwo;
    static EventTask dateTimeEventTaskOne;
    static EventTask dateTimeEventTaskTwo;

    /**
     * Initial set up of test conditions
     */
    @BeforeAll
    public static void setUp() {
        dateEventTaskOne = new EventTask("test", "12/12", "14/12");
        dateEventTaskTwo = new EventTask("test", "12/12/2026", "14/12/2026 14:00");
        dateTimeEventTaskOne = new EventTask("test", "12/12 12:00", "14/12 09:00");
        dateTimeEventTaskTwo = new EventTask("test", "12/12 09:00", "14/12/2026");

        dateEventTaskOne.setTaskDone(true);
    }

    /**
     * Tests for formatting of getDeadLine
     */
    @Test
    public void test_GetDeadline() {
        assertEquals("14 Dec 2025", dateEventTaskOne.getDeadLine());
        assertEquals("14 Dec 2026 02:00pm", dateEventTaskTwo.getDeadLine());
        assertEquals("14 Dec 2025 09:00am", dateTimeEventTaskOne.getDeadLine());
        assertEquals("14 Dec 2026", dateTimeEventTaskTwo.getDeadLine());
    }

    /**
     * Tests for formatting of getStartDateTime
     */
    @Test
    public void test_GetStartDateTime() {
        assertEquals("12 Dec 2025", dateEventTaskOne.getStartDateTime());
        assertEquals("12 Dec 2026", dateEventTaskTwo.getStartDateTime());
        assertEquals("12 Dec 2025 12:00pm", dateTimeEventTaskOne.getStartDateTime());
        assertEquals("12 Dec 2025 09:00am", dateTimeEventTaskTwo.getStartDateTime());
    }

    /**
     * Tests for formatting of createSaveData
     */
    @Test
    public void test_CreateSaveData() {
        assertEquals("E|1|test|/from 12/12/2025 /to 14/12/2025", dateEventTaskOne.createSaveData());
        assertEquals("E|0|test|/from 12/12/2026 /to 14/12/2026 14:00", dateEventTaskTwo.createSaveData());
        assertEquals("E|0|test|/from 12/12/2025 12:00 /to 14/12/2025 09:00", dateTimeEventTaskOne.createSaveData());
        assertEquals("E|0|test|/from 12/12/2025 09:00 /to 14/12/2026", dateTimeEventTaskTwo.createSaveData());
    }

    /**
     * Tests for formatting of getTaskInformation
     */
    @Test
    public void test_GetTaskInformation() {
        assertEquals("[E] [X] test (from: 12 Dec 2025 to: 14 Dec 2025)", dateEventTaskOne.getTaskInformation());
        assertEquals("[E] [ ] test (from: 12 Dec 2026 to: 14 Dec 2026 02:00pm)", dateEventTaskTwo.getTaskInformation());
        assertEquals("[E] [ ] test (from: 12 Dec 2025 12:00pm to: 14 Dec 2025 09:00am)", dateTimeEventTaskOne.getTaskInformation());
        assertEquals("[E] [ ] test (from: 12 Dec 2025 09:00am to: 14 Dec 2026)", dateTimeEventTaskTwo.getTaskInformation());
    }
}
