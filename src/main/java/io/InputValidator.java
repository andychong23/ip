package io;

import action.ActionHandler;
import user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * InputValidator class that validates all user inputs
 */
public class InputValidator {
    public static final DateTimeFormatter DAY_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DAY_MONTH_YEAR_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Check if user input is valid
     *
     * @param userInput Input string provided by the user with no trailing or leading whitespaces
     * @return ValidationToken with isValid status and error message if it is not valid
     */
    public static ValidationToken isInputValid(String userInput, User user) {
        List<String> userInputTokens = Arrays.asList(userInput.split(" "));

        if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.LIST.toString())) {
            return isListInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.BYE.toString())) {
            return isByeInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.MARK.toString())) {
            return isMarkInputValid(userInputTokens, user);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.UNMARK.toString())) {
            return isUnmarkInputValid(userInputTokens, user);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.TODO.toString())) {
            return isToDoInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.DEADLINE.toString())) {
            return isDeadLineInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.EVENT.toString())) {
            return isEventInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.DELETE.toString())) {
            return isDeleteInputValid(userInputTokens, user);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.FIND.toString())) {
            return isFindInputValid(userInputTokens);
        } else if (userInputTokens.getFirst().equalsIgnoreCase(ActionHandler.Action.CHEER.toString())) {
            return isCheerInputValid(userInputTokens);
        }

        return new ValidationToken(false, ValidationToken.InputError.INVALID_COMMAND);
    }

    private static ValidationToken isCheerInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() >= 2) {
            return new ValidationToken(false, ValidationToken.InputError.CHEER_TOO_MANY_ARGUMENTS);
        }
        return new ValidationToken(true);
    }

    private static ValidationToken isFindInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(false, ValidationToken.InputError.FIND_TOO_LITTLE_ARGUMENTS);
        }
        return new ValidationToken(true);
    }

    private static ValidationToken isDeleteInputValid(List<String> userInputTokens, User user) {
        if (userInputTokens.size() > 2) {
            // too many arguments
            return new ValidationToken(false, ValidationToken.InputError.DELETE_TOO_MANY_ARGUMENTS);
        }

        try {
            Integer.parseInt(userInputTokens.get(1));
        } catch (NumberFormatException e) {
            // second argument not a number
            return new ValidationToken(false, ValidationToken.InputError.DELETE_INCORRECT_ARGUMENT_TYPE);
        }

        if (Integer.parseInt(userInputTokens.get(1)) <= 0 || Integer.parseInt(userInputTokens.get(1)) > user.getNumberOfTasks()) {
            return new ValidationToken(false, ValidationToken.InputError.DELETE_ARGUMENT_DOES_NOT_EXISTS);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isEventInputValid(List<String> userInputTokens) {
        if (!(userInputTokens.contains("/from") && userInputTokens.contains("/to"))) {
            return new ValidationToken(false, ValidationToken.InputError.EVENT_MISSING_FROM_TO_ARGUMENTS);
        }

        int fromIndex = userInputTokens.indexOf("/from");
        int toIndex = userInputTokens.indexOf("/to");

        if (fromIndex == 1) {
            return new ValidationToken(false, ValidationToken.InputError.EVENT_MISSING_EVENT_INFO);
        }

        String fromDateTime = String.join(" ",
                userInputTokens.subList(fromIndex + 1, toIndex));

        if (!(InputValidator.isValidDateTime(fromDateTime, false)
                || InputValidator.isValidDate(fromDateTime, false))) {
            return new ValidationToken(false, ValidationToken.InputError.EVENT_INVALID_DATETIME);
        }

        String toDateTime = String.join(" ", userInputTokens.subList(toIndex + 1, userInputTokens.size()));

        if (!(InputValidator.isValidDateTime(toDateTime, true)
                || InputValidator.isValidDate(fromDateTime, true))) {
            return new ValidationToken(false, ValidationToken.InputError.EVENT_INVALID_DATETIME);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isDeadLineInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() <= 2) {
            return new ValidationToken(false, ValidationToken.InputError.DEADLINE_TOO_LITTLE_ARGUMENTS);
        }

        if (!userInputTokens.contains("/by")) {
            return new ValidationToken(false, ValidationToken.InputError.DEADLINE_MISSING_BY);
        }

        int deadLineIndex = userInputTokens.indexOf("/by") + 1;

        if (deadLineIndex == userInputTokens.size()) {
            return new ValidationToken(false, ValidationToken.InputError.DEADLINE_MISSING_DEADLINE);
        }

        String stringDateTime = String.join(" ",
                userInputTokens.subList(deadLineIndex, userInputTokens.size()));

        if (!(InputValidator.isValidDateTime(stringDateTime, true)
                || InputValidator.isValidDate(stringDateTime, true))) {
            return new ValidationToken(false, ValidationToken.InputError.DEADLINE_INVALID_DATETIME);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isToDoInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(false, ValidationToken.InputError.TODO_TOO_LITTLE_ARGUMENTS);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isUnmarkInputValid(List<String> userInputTokens, User user) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(false, ValidationToken.InputError.UNMARK_TOO_LITTLE_ARGUMENTS);
        } else if (userInputTokens.size() > 2) {
            return new ValidationToken(false, ValidationToken.InputError.UNMARK_TOO_MANY_ARGUMENTS);
        }

        try {
            Integer.parseInt(userInputTokens.get(1));
        } catch (NumberFormatException e) {
            return new ValidationToken(false, ValidationToken.InputError.UNMARK_INCORRECT_ARGUMENT_TYPE);
        }

        if (Integer.parseInt(userInputTokens.get(1)) < 1
                || Integer.parseInt(userInputTokens.get(1)) > user.getNumberOfTasks()) {
            return new ValidationToken(false, ValidationToken.InputError.INVALID_TASK_NUMBER);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isMarkInputValid(List<String> userInputTokens, User user) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(false, ValidationToken.InputError.MARK_TOO_LITTLE_ARGUMENTS);
        } else if (userInputTokens.size() > 2) {
            return new ValidationToken(false, ValidationToken.InputError.MARK_TOO_MANY_ARGUMENTS);
        }

        try {
            Integer.parseInt(userInputTokens.get(1));
        } catch (NumberFormatException e) {
            return new ValidationToken(false, ValidationToken.InputError.MARK_INCORRECT_ARGUMENT_TYPE);
        }

        if (Integer.parseInt(userInputTokens.get(1)) < 1
                || Integer.parseInt(userInputTokens.get(1)) > user.getNumberOfTasks()) {
            return new ValidationToken(false, ValidationToken.InputError.INVALID_TASK_NUMBER);
        }

        return new ValidationToken(true);
    }

    private static ValidationToken isByeInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(true);
        }
        return new ValidationToken(false, ValidationToken.InputError.BYE_TOO_MANY_ARGUMENTS);
    }

    private static ValidationToken isListInputValid(List<String> userInputTokens) {
        if (userInputTokens.size() == 1) {
            return new ValidationToken(true);
        }
        return new ValidationToken(false, ValidationToken.InputError.LIST_TOO_MANY_ARGUMENTS);
    }

    /**
     * Method to check if the provided string follows the dd/MM or dd/MM/yyyy format
     * @param stringDate String to be checked
     * @param requiresFuture Indicates if stringDate needs to be in the future
     * @return boolean that indicates if it is a valid date format
     */
    public static boolean isValidDate(String stringDate, boolean requiresFuture) {
        try {
            LocalDate date = LocalDate.parse(stringDate + "/" + Year.now(), DAY_MONTH_YEAR_FORMATTER);

            if (requiresFuture) {
                return date.isAfter(LocalDate.now());
            }

            return true;
        } catch (DateTimeParseException e) {
            e.getMessage();
        }

        try {
            LocalDate date = LocalDate.parse(stringDate, DAY_MONTH_YEAR_FORMATTER);

            if (requiresFuture) {
                return date.isAfter(LocalDate.now());
            }

            return true;
        } catch (DateTimeParseException e) {
            e.getMessage();
        }

        return false;
    }

    /**
     * Method to check if the provided string follows the dd/MM H:m or dd/MM/yyyy H:m format
     *
     * @param stringDateTime String to be checked
     * @param requiresFuture Indicates if stringDateTime needs to be in the future
     * @return boolean that indicates if it is a valid time format
     */
    public static boolean isValidDateTime(String stringDateTime, boolean requiresFuture) {
        List<String> stringDateList = new ArrayList<>(List.of(stringDateTime.split(" ")));

        try {
            LocalDateTime dateTime = LocalDateTime.parse(String.join(" ", stringDateList),
                    DAY_MONTH_YEAR_TIME_FORMATTER);

            if (requiresFuture) {
                return dateTime.isAfter(LocalDateTime.now());
            }

            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

        try {
            String removedItem = stringDateList.removeFirst();
            stringDateList.addFirst(removedItem + "/" + Year.now());
            LocalDateTime dateTime = LocalDateTime.parse(String.join(" ", stringDateList),
                    DAY_MONTH_YEAR_TIME_FORMATTER);

            if (requiresFuture) {
                return dateTime.isAfter(LocalDateTime.now());
            }

            return true;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
