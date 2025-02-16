package bob;

import action.ActionHandler;
import io.InputValidator;
import io.UI;
import io.ValidationToken;
import user.User;

import java.io.IOException;
import java.util.List;

/**
 * Chatbot class
 */
public class Bob {
    private static final String CHATBOT_NAME = "Bob";
    private final UI uI = new UI(Bob.getCHATBOT_NAME());
    private User user;
    private final ActionHandler actionHandler = new ActionHandler();

    public Bob() throws IOException {
        user = new User("bob");
    }

    public Bob(String userName) throws IOException {
        user = new User(userName);
    }

    public static String getCHATBOT_NAME() {
        return CHATBOT_NAME;
    }

    public String getWelcomeMessage() throws IOException {
        return String.join(" ", uI.getWelcomeMessage());
    }

    public String getExitMessage() throws IOException {
        return String.join(" ", uI.getExitMessage());
    }

    public String parseInput(String input) throws IOException {
        ValidationToken validationToken = InputValidator.isInputValid(input, user);
        if (validationToken.isValid()) {
            return String.join("\n", actionHandler.processEvent(input, user));
        } else {
            return validationToken.getErrorMessage();
        }
    }
}
