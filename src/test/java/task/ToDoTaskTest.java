package task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {
    static ToDoTask toDoTaskOne;
    static ToDoTask toDoTaskTwo;

    /**
     * Initial set up of test conditions
     */
    @BeforeAll
    public static void setUp() {
        toDoTaskOne = new ToDoTask("test");
        toDoTaskTwo = new ToDoTask("get apples");

        toDoTaskOne.setTaskDone(true);
    }

    /**
     * Test for formatting of createSaveData
     */
    @Test
    public void test_CreateSaveData() {
        assertEquals("T|1|test", toDoTaskOne.createSaveData());
        assertEquals("T|0|get apples", toDoTaskTwo.createSaveData());
    }

    /**
     * Test for formatting of getTaskInformation
     */
    @Test
    public void test_GetTaskInformation() {
        assertEquals("[T] [X] test", toDoTaskOne.getTaskInformation());
        assertEquals("[T] [ ] get apples", toDoTaskTwo.getTaskInformation());
    }
}
