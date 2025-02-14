package io;

/**
 * ValidationToken class that indicates validity of inputs
 */
public class ValidationToken {

    private boolean isValid;
    private InputError errorMessage;

    /**
     * Constructor for inputs with no errors
     *
     * @param isValid boolean that indicates that it is valid
     */
    public ValidationToken(boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * Constructor for inputs with errors
     *
     * @param isValid      boolean that indicates that it is not valid
     * @param errorMessage InputError enum that encapsulates information about the error
     */
    public ValidationToken(boolean isValid, InputError errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getErrorMessage() {
        return errorMessage.getErrorMessage();
    }

    /**
     * Enum class that indicates all the types of InputErrors
     * enum field follows the format <action_name>_<problem_information>
     */
    public enum InputError {
        LIST_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "list expects 0 argument but received more than 0 arguments";
            }
        },
        BYE_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "bye expects 0 argument but received more than 0 arguments";
            }
        },
        MARK_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "mark expects 1 argument but received more than 1 argument";
            }
        },
        MARK_INCORRECT_ARGUMENT_TYPE() {
            @Override
            public String getErrorMessage() {
                return "mark expects 1 argument that is an integer but received an non-integer argument";
            }
        },
        MARK_TOO_LITTLE_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "mark expects 1 argument but received 0 arguments";
            }
        },
        UNMARK_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "unmark expects 1 argument but received more than 1 argument";
            }
        },
        UNMARK_INCORRECT_ARGUMENT_TYPE() {
            @Override
            public String getErrorMessage() {
                return "unmark expects 1 argument that is an integer but received an non-integer argument";
            }
        },
        UNMARK_TOO_LITTLE_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "unmark expects 1 argument but received 0 arguments";
            }
        },
        TODO_TOO_LITTLE_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "todo expects at least 1 argument but received 0 arguments";
            }
        },
        DEADLINE_TOO_LITTLE_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "deadline expects at least 2 arguments";
            }
        },
        DEADLINE_MISSING_BY {
            @Override
            public String getErrorMessage() {
                return "deadline expects '/by' as one of its arguments";
            }
        },
        DEADLINE_MISSING_DEADLINE {
            @Override
            public String getErrorMessage() {
                return "deadline expects at least 1 argument after '/by' but received none";
            }
        },
        DEADLINE_INVALID_DATETIME {
            @Override
            public String getErrorMessage() {
                return "deadline expects a datetime argument in the format dd/mm or dd/MM HH:mm or Mon " +
                        "but received something different";
            }
        },
        EVENT_MISSING_FROM_TO_ARGUMENTS {
            public String getErrorMessage() {
                return "event expects both /from and /to as arguments but did not receive it";
            }
        },
        EVENT_INVALID_DATETIME {
            @Override
            public String getErrorMessage() {
                return "event expects a datetime argument in the format dd/mm or dd/MM HH:mm or Mon " +
                        "after /from and /to but received something different";
            }
        },
        DELETE_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "delete expects 1 argument but received more than 1 argument";
            }
        },
        DELETE_INCORRECT_ARGUMENT_TYPE {
            @Override
            public String getErrorMessage() {
                return "delete expects an integer argument but received a non-integer argument";
            }
        },
        DELETE_ARGUMENT_DOES_NOT_EXISTS {
            @Override
            public String getErrorMessage() {
                return "task number does not exists";
            }
        },
        INVALID_TASK_NUMBER() {
            @Override
            public String getErrorMessage() {
                return "task number provided does not exist";
            }
        },
        INVALID_COMMAND {
            @Override
            public String getErrorMessage() {
                return "command listed does not exists. use todo, mark, unmark, deadline, event, delete and find instead";
            }
        },
        FIND_TOO_LITTLE_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "find expects at least 1 argument but received 0 arguments";
            }
        },
        CHEER_TOO_MANY_ARGUMENTS() {
            @Override
            public String getErrorMessage() {
                return "cheer expects 0 argument but received at least 1 argument";
            }
        };

        public abstract String getErrorMessage();
    };
}
