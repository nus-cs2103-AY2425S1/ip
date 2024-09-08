package jackson.utils;

import java.util.ArrayList;

import jackson.tasks.Task;

/**
 * Class to handle UI interaction.
 * Includes printing most exceptions and displaying task lists, etc.
 */
public class Ui {

    /**
     * Constructs Ui instance.
     */
    public Ui() { }

    /**
     * Returns welcome message at the start of the chatbot program.
     * @return String representation of welcome.
     */
    public String printWelcome() {
        return "Oi! I'm Jackson!\nWhat you want me do today ah?\n";
    }

    /**
     * Returns goodbye message at the end of the chatbot program.
     * @return String representation of goodbye.
     */
    public String printGoodbye() {
        return "K k bye lah!\n";
    }

    /**
     * Returns command list during an unrecognised command.
     * @return String response.
     */
    public String printCommandList() {
        return """
            Harh? What you talking about?
            Try entering a recognized command:
            1. todo [task-name]
            2. deadline [task-name] /by [due-date]
            3. event [task-name] /from [start-date] /to [end-date]
            4. mark [index]
            5. unmark [index]
            6. list
            7. find
            8. bye
            """;
    }

    /**
     * Returns format of command based on which command user incorrectly enters.
     * @param category Command category that is entered incorrectly.
     * @return String response.
     */
    public String printFormatGuide(String category) {
        String output = """
            Wrong format leh...
            Try formatting your command as such:
            """;

        // category is the type of command that the user incorrectly enters the format of
        // print the correct command format based on the user input
        switch (category) {
        case "todo":
            output += "todo [task-name]";
            break;
        case "deadline":
            output += """
            deadline [task-name] /by [due-date]
            All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""";
            break;
        case "event":
            output += """
            event [task-name] /from [start-date] /to [end-date]
            All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""";
            break;
        case "list":
            output += "list";
            break;
        case "mark":
            output += "mark [index]";
            break;
        case "unmark":
            output += "unmark [index]";
            break;
        case "delete":
            output += "delete [index]";
            break;
        case "find":
            output += "find [keywords]";
            break;
        case "bye":
            output += "bye";
            break;
        default:
            output += "Unknown error...";
            break;
        }
        return output + "\n";
    }

    /**
     * Returns valid indices when invalid index is entered.
     * @param taskList {@code OutOfListException} exception encountered.
     * @return String response.
     */
    public String printIndexGuide(TaskList taskList) {
        int size = taskList.getSize();
        String output = String.format("Alamak, you got %d items on the list only leh...\n", size);

        // different responses based on taskList size
        if (size == 0) {
            output += "You've got no items in the list! Add some stuff first!";
        } else if (size == 1) {
            output += "Enter 1 to mark/unmark/delete the task in the list!";
        } else {
            output += String.format("Enter a number between 1 and %d when marking/unmarking/deleting tasks!\n", size);
        }
        return output + "\n";
    }

    /**
     * Returns generic error message when other Exceptions occur.
     * @param e {@code Exception} encountered during runtime.
     * @return String response.
     */
    public String printUnknownError(Exception e) {
        return String.format("Oops! Something went wrong!\n%s\n", e.toString());
    }

    /**
     * Returns chatbot response when adding task to list.
     * @param task {@code Task} object to be added to taskList.
     * @param taskList {@code TaskList} object task is to be added to.
     * @return String response.
     */
    public String printAfterAddList(Task task, TaskList taskList) {
        String output = "";
        output += "Ya la, adding this task to your list!\n";
        output += String.format("\t%s\n", task);
        output += String.format("You now got %s tasks in your list leh\n", taskList.getSize());
        return output;
    }

    /**
     * Returns chatbot response when deleting task from list.
     * @param task {@code Task} object to be deleted from taskList.
     * @param taskList {@code TaskList} object task is to be deleted from.
     * @return String response.
     */
    public String printAfterDeleteList(Task task, TaskList taskList) {
        String output = "";
        output += "Deleting now hor!\n";
        output += String.format("\t%s\n", task);
        output += String.format("You now got %s tasks in your list leh\n", taskList.getSize());
        return output;
    }

    /**
     * Returns chatbot response when finding tasks containing keyword.
     * @param tasks {@code ArrayList} of tasks that contain keyword.
     * @param keyword String keyword.
     * @return String response.
     */
    public String printAfterFindList(ArrayList<Task> tasks, String keyword) {
        String output = "";
        output += "Ok ok, find for you already...\n";
        if (tasks.isEmpty()) {
            output += String.format("Don't have tasks that match '%s' leh....\n", keyword);
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                output += String.format("%d. %s\n", i + 1, tasks.get(i));
            }
        }
        return output;
    }

    /**
     * Returns chatbot response when marking task in list.
     * @param task {@code Task} object to be marked.
     * @return String response.
     */
    public String printAfterMark(Task task) {
        String output = "";
        output += "Solid lah, marked already\n";
        output += String.format("\t%s\n", task);
        return output;
    }

    /**
     * Returns chatbot response when unmarking task in list.
     * @param task {@code Task} object to be unmarked.
     * @return String response.
     */
    public String printAfterUnmark(Task task) {
        String output = "";
        output += "Walao, ok la I unmark already...\n";
        output += String.format("\t%s\n", task);
        return output;
    }

    /**
     * Returns tasks stored in the task list.
     * @param listString {@code String} representation of tasks in the list.
     * @return String response.
     */
    public String printList(String listString) {
        return listString + "\n";
    }
}
