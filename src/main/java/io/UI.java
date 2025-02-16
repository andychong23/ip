package io;

import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UI class to handle tasks related to the User Interface
 */
public class UI {
    private Input INPUT;
    private Output OUTPUT;
    private final String DIVIDER = "_______________";
    private final String CHATBOT_NAME;

    /**
     * Constructor
     */
    public UI(String botName, Input input, Output output) {
        this.CHATBOT_NAME = botName;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    public UI(String botName) {
        this.CHATBOT_NAME = botName;
    }

    /**
     * Get valid user input from console
     *
     * @return User input String
     */
    public String getValidUserInput(User user) throws IOException {
        boolean isUserInputValid = false;

        String userInput;

        do {
            userInput = INPUT.getUserInput();
            ValidationToken validationToken = InputValidator.isInputValid(userInput, user);
            if (validationToken.isValid()) {
                isUserInputValid = true;
            } else {
                List<String> outputMessage = new ArrayList<>();
                outputMessage.add(validationToken.getErrorMessage());
                displayMessageWithDivider(outputMessage);
            }
        }
        while (!isUserInputValid);

        return userInput;
    }

    /**
     * Displays custom message to the console with dividers
     *
     * @param messages list of messages to output to console
     * @throws IOException When IO fails
     */
    public void displayMessageWithDivider(List<String> messages) throws IOException {
        messages.addFirst(this.DIVIDER);
        messages.add(this.DIVIDER);
        OUTPUT.printOutput(messages, "\n", "\n");
    }

    /**
     * Display custom message to the console without dividers
     *
     * @param messages list of messages to output to console
     * @throws IOException When IO fails
     */
    public void displayMessage(List<String> messages) throws IOException {
        OUTPUT.printOutput(messages, "\n", "\n");
    }

    /**
     * Display welcome message to the console
     *
     * @throws IOException When IO fails
     */
    public void displayWelcomeMessage() throws IOException {
        List<String> welcomeMessages = new ArrayList<>();
        welcomeMessages.add(this.DIVIDER);
        welcomeMessages.add(String.format("Hello! I'm %s", this.CHATBOT_NAME));
        welcomeMessages.add("What can I do for you?");
        welcomeMessages.add(this.DIVIDER);
        OUTPUT.printOutput(welcomeMessages, "\n", "\n");
    }

    /**
     * Display exit message to the console
     *
     * @throws IOException When IO fails
     */
    public void displayExitMessage() throws IOException {
        List<String> exitMessages = new ArrayList<>();
        exitMessages.add(this.DIVIDER);
        exitMessages.add("Bye. Hope to see you again soon!");
        exitMessages.add(this.DIVIDER);
        OUTPUT.printOutput(exitMessages, "\n", "\n");
    }

    public List<String> getWelcomeMessage() throws IOException {
        List<String> welcomeMessages = new ArrayList<>();
        welcomeMessages.add(String.format("Hello! I'm %s", this.CHATBOT_NAME));
        welcomeMessages.add("What can I do for you?");
        return welcomeMessages;
    }

    public List<String> getExitMessage() throws IOException {
        List<String> exitMessages = new ArrayList<>();
        exitMessages.add("Bye. Hope to see you again soon!");
        return exitMessages;
    }
}
