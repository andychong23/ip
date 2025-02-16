package action;

import data.DataHandler;
import task.DeadLineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static java.lang.Integer.parseInt;

/**
 * Class to handle actions
 */
public class ActionHandler {
    private RandomGenerator randomGenerator = new Random();

    /**
     * Returns List of Strings that will be output to the user
     * This method processes the actions that the user inputs accordingly
     * Precondition: actionString is a valid input by the user
     *
     * @param actionString Valid Input String provided by the user
     * @param user        User class that indicates the current user
     * @return List of Strings that indicates what needs to be output to the console
     */
    public List<String> processAction(String actionString, User user) throws IOException {
        List<String> outputMessages = new ArrayList<>();

        List<String> actionStringTokens = Arrays.asList(actionString.split(" "));

        if (actionStringTokens.getFirst().equalsIgnoreCase(Action.LIST.toString())) {
            processListAction(user, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.MARK.toString())) {
            processMarkAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.UNMARK.toString())) {
            processUnmarkAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.TODO.toString())) {
            processToDoAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.DEADLINE.toString())) {
            processDeadlineAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.EVENT.toString())) {
            processEventAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.DELETE.toString())) {
             processDeleteAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.FIND.toString())) {
            processFindAction(user, actionStringTokens, outputMessages);
        } else if (actionStringTokens.getFirst().equalsIgnoreCase(Action.CHEER.toString())) {
            processCheerAction(outputMessages);
        }

        updateSaveFile(user, false);

        return outputMessages;
    }

    /**
     * Adds a cheer message to outputMessages
     * @param outputMessages List of Strings that will be shown to the user
     * @throws IOException File read fails for the cheer messages
     */
    private void processCheerAction(List<String> outputMessages) throws IOException {
        outputMessages.add(getCheerMessage());
    }

    /**
     * Find the tasks that has the relevant keyword
     * If tasks are found, add a numbered task list to outputMessages
     * Note: Numbered task list provided here does not correspond to the actual task number
     *
     * @param user User that is requesting the find action
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processFindAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        List<Task> foundTasks = user.findTaskWithKeyWord(
                String.join(" ", actionStringTokens.subList(1, actionStringTokens.size())));

        if (foundTasks.isEmpty()) {
            outputMessages.add("There are not tasks with that keyword in your list D:");
        }
        else {
            outputMessages.add("Here are the matching tasks in your list:");
            outputMessages.add(user.getTaskList(foundTasks));
        }
    }

    /**
     * Delete the task number specified by the user
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processDeleteAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("Noted. I've removed this task:");
        outputMessages.add(user.deleteTask(parseInt(actionStringTokens.get(1)) - 1));
        outputMessages.add("Now you have %s tasks in your list".formatted(user.getNumberOfTasks()));
    }

    /**
     * Adds an event task to the task list associated with the user
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processEventAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("Got it. I've added this event:");
        Task createdTask = createTask(
                Action.EVENT,
                actionStringTokens.subList(1, actionStringTokens.size())
        );
        user.addTask(createdTask);
        outputMessages.add(createdTask.getTaskInformation());
        outputMessages.add("Now you have %s tasks in your list".formatted(user.getNumberOfTasks()));
    }

    /**
     * Adds a deadline task to the task list associated with the user
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processDeadlineAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("Got it. I've added this deadline:");
        Task createdTask = createTask(
                Action.DEADLINE,
                actionStringTokens.subList(1, actionStringTokens.size())
        );
        user.addTask(createdTask);
        outputMessages.add(createdTask.getTaskInformation());
        outputMessages.add("Now you have %s tasks in your list".formatted(user.getNumberOfTasks()));
    }

    /**
     * Adds a todo task to the task list associated with the user
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processToDoAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("Got it. I've added this todo task:");
        Task createdTask = createTask(
                Action.TODO,
                actionStringTokens.subList(1, actionStringTokens.size())
        );
        user.addTask(createdTask);
        outputMessages.add(createdTask.getTaskInformation());
        outputMessages.add("Now you have %s tasks in your list".formatted(user.getNumberOfTasks()));
    }

    /**
     * Unmarks a task status
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processUnmarkAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("OK, I've marked this task as not done yet:");
        outputMessages.add(user.markTaskAsNotDone(parseInt(actionStringTokens.get(1)) - 1));
    }

    /**
     * Marks a task status
     * @param user User
     * @param actionStringTokens List of inputs provided by the user, split by *space*
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processMarkAction(User user, List<String> actionStringTokens, List<String> outputMessages) {
        outputMessages.add("Nice! I've marked this task as done:");
        outputMessages.add(user.markTaskAsDone(parseInt(actionStringTokens.get(1)) - 1));
    }

    /**
     * Add the user's task list to outputMessages
     * @param user User
     * @param outputMessages List of Strings that will be shown to the user
     */
    private void processListAction(User user, List<String> outputMessages) {
        outputMessages.add("Here are the tasks in your list: ");
        outputMessages.add(user.getTaskList());
    }

    /**
     * Writes the user's current task list into memory
     * @param user User
     * @param isAppend boolean that indicates if we should append the data of rewrite the file
     * @throws IOException When the file does not exist
     */
    private void updateSaveFile(User user, boolean isAppend) throws IOException {
        try {
            DataHandler.writeFile(user.getDataFilePath(), user.createSaveData(), isAppend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a cheer message to outputMessages
     * @return String of cheer message
     * @throws IOException when the file containing the cheer messages does not exist
     */
    private String getCheerMessage() throws IOException{
        List<String> cheerMessages = DataHandler.readFile(DataHandler.cheerPath);
        return cheerMessages.get(randomGenerator.nextInt(cheerMessages.size()));
    }

    /**
     * Method to create a TODO/DEADLINE/EVENT task
     *
     * @param action      Action enum field that indicates what is the action to do
     * @param taskDetails Valid input string provided by the user
     * @return Task that encapsulates the required information to create TODO/DEADLINE/EVENT task
     */
    public static Task createTask(Action action, List<String> taskDetails) {
        if (action.equals(Action.TODO)) {
            return new ToDoTask(String.join(" ", taskDetails));
        } else if (action.equals(Action.DEADLINE)) {
            int deadLineIndex = taskDetails.indexOf("/by");

            return new DeadLineTask(
                    String.join(" ", taskDetails.subList(0, deadLineIndex)),
                    String.join(" ", taskDetails.subList(deadLineIndex + 1, taskDetails.size()))
            );
        } else if (action.equals(Action.EVENT)) {
            int fromIndex = taskDetails.indexOf("/from");
            int toIndex = taskDetails.indexOf("/to");

            return new EventTask(
                    String.join(" ", taskDetails.subList(0, fromIndex)),
                    String.join(" ", taskDetails.subList(fromIndex + 1, toIndex)),
                    String.join(" ", taskDetails.subList(toIndex + 1, taskDetails.size()))
            );
        }
        return null;
    }

    /**
     * Create Task
     *
     * @param taskDetail String representation for the details of the task
     * @return Task created
     */
    @Deprecated
    private Task createTask(String taskDetail) {
        return new Task(taskDetail);
    }

    public enum Action {
        LIST,
        MARK,
        UNMARK,
        BYE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        CHEER,
        DEFAULT
    }
}
