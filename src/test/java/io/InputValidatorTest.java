package io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import user.User;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidatorTest {
    User user;

    /**
     * Set up a User that can be used to do testing
     * @throws IOException If data file does not exists
     */
    @BeforeEach
    public void setUp() throws IOException {
        user = new User("test");
    }

    /**
     * This test takes in multiple date inputs and tests whether isValidDate evaluates the dates correctly
     * @param input String of a potential date
     * @param requiresFuture boolean that indicates if the input has to be in the future
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "04/02, true, false",
            "12/12, true, true",
            "04/02, false, true",
            "04/02/2025, true, false",
            "12/12/2025, true, true",
            "04/02/2025, false, true",
            "abcd, true, false",
            "12/12/2025 14:00, false, false"
    })
    public void test_IsValidDate(String input, boolean requiresFuture, boolean expected) {
        assertEquals(expected, InputValidator.isValidDate(input, requiresFuture));
    }

    /**
     * This test takes in multiple datetime inputs and tests whether isValidDateTime evaluates the datetime correctly
     * @param input String of a potential datetime
     * @param requiresFuture boolean that indicates if the input has to be in the future
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "04/02, true, false",
            "12/12, true, false",
            "04/02 12:00, true, false",
            "04/02 12:00, false, true",
            "12/12/2025 12:00, true, true",
            "12/12/2025 80:00, true, false"
    })
    public void test_IsValidDateTime(String input, boolean requiresFuture, boolean expected) {
        assertEquals(expected, InputValidator.isValidDateTime(input, requiresFuture));
    }

    /**
     * This test takes in multiple mark/unmark inputs and tests whether the provided format is correct
     * @param userInput String of a potential mark/unmark input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "mark 2, false",
            "mark 1, true",
            "unmark 2, false",
            "unmark 1, true",
            " mark 2, false",
            " mark 1, true",
            "mark  1, false"
    })
    public void test_IsMarkInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }

    /**
     * This test takes in multiple deadline inputs and tests whether the provided format is correct
     * @param userInput String of a potential deadline input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "deadline return book /by 12/12, true",
            "deadline /by 04/02, false",
            "deadline return book 04/02, false",
            "DEADLINE return book /by 12/12, true",
            "DEADline return book /by 12/12, true"
    })
    public void test_IsDeadLineInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }

    /**
     * This test takes in multiple event inputs and tests whether the provided format is correct
     * @param userInput String of a potential event input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "event networking /from 12/12 /to 13/12, true",
            "event networking /from 11/11 11:00 /to 12/12 12:00, true",
            "event sleep /from 04/02 /to 04/02, false",
            "event sleep /from 04/02 /to 03/02, false",
            "event sleep /from 11/11 /to 12/12 12:00, true",
            "event /from 11/11 /to 12/12, true"
    })
    public void test_IsEventInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }

    /**
     * This test takes in multiple todo event inputs and tests whether the provided format is correct
     * @param userInput String of a potential todo input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "todo return book, true",
            "TOdo return book, true",
            " todo return book, true"
    })
    public void test_IsToDoInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }

    /**
     * This test takes in multiple bye inputs and tests whether the provided format is correct
     * @param userInput String of a potential bye input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "bye, true",
            " bye, true",
            "bye , true",
            " bye , true",
            "bye a, false"
    })
    public void test_IsByeInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }

    /**
     * This test takes in multiple delete inputs and tests whether the provided format is correct
     * @param userInput String of a potential delete input
     * @param expected Expected result of each test case
     */
    @ParameterizedTest
    @CsvSource({
            "delete 2, false",
            "delete 1, true",
            "delete 0, false",
            "delete 1 , true",
            " delete 1, true",
            "delete  1, true"
    })
    public void test_IsDeleteInputValid(String userInput, boolean expected) {
        assertEquals(expected, InputValidator.isInputValid(userInput, user).isValid());
    }
}
