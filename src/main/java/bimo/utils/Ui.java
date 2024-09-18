package bimo.utils;

import java.util.ArrayList;
import java.util.stream.IntStream;

import bimo.tasks.Task;


/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Creates introduction message when chatbot is first opened.
     *
     * @param name Name of chatbot.
     * @return Response message when chatbot starts.
     */
    public String printUserIntroduction(String name) {
        String introduction = String.format("Good day! I'm %s.", name)
                + " What can I do for you? \n \n"
                + "(Type help to see the list of available commands)";
        return introduction;
    }

    /**
     * Retrieves the list of commands currently available.
     *
     * @return list of commands.
     */
    public String getListOfCommands() {
        String list = "Available commands:\n\n"
                + "1. todo <task>\n\n"
                + "2. deadline <task> /by yyyy-mm-dd\n\n"
                + "3. event <task> /from yyyy-mm-dd /to yyyy-mm-dd\n\n"
                + "4. mark <task number>\n\n"
                + "5. unmark <task number>\n\n"
                + "6. delete <task number>\n\n"
                + "7. find <keyword keyword keyword>\n\n"
                + "8. list\n\n"
                + "9. set <task number> <high, medium, low>\n\n"
                + "10. help\n\n"
                + "11. bye\n";
        return list;
    }

    /**
     * Displays error message for invalid tasks index.
     */
    public String showTaskNotFoundError() {
        String message = "Task not found in list...";
        return message;
    }

    /**
     * Creates response to addition of tasks.
     *
     * @param size Number of tasks inside tasklist.
     * @param task Task that is added.
     * @return Response message to add command.
     */
    public String sendAddTaskMessage(int size, Task task) {
        String word = wordFormat(size);
        String message = "Roger that. I've added this task for you\n" + "    " + task.toString()
                + "\n" + String.format("Now you have %d %s in the list.",
                size, word);
        return message;
    }

    /**
     * Creates response to deletion of tasks.
     *
     * @param size  Number of tasks inside tasklist.
     * @param task Task that is added.
     * @return Response message to delete command.
     */
    public String sendDeleteTaskMessage(int size, Task task) {
        String word = wordFormat(size);
        String message = "Roger that. I've removed this task for you\n    "
                + task.toString() + String.format("\nNow you have %d %s in the list.",
                size, word);
        return message;
    }
    /**
     * Creates a response to the Bye command.
     *
     * @return Response to bye command.
     */
    public String sendExitMessage() {
        return "Bye Bye!!! Have a good day!";
    }

    /**
     * Creates String representation of list of tasks.
     *
     * @param tasks List of tasks.
     * @return Response to List command.
     */
    public String printListOfTasks(TaskList tasks) {
        String response = "Have a look at the list of tasks:";
        for (int i = 0; i < tasks.getLength(); i++) {
            String message = String.format("\n    %d. %s", i + 1,
                    tasks.getTask(i).toString());
            response += message;
        }
        return response;
    }

    /**
     * Creates response for marking task as completed.
     *
     * @param task Task marked as completed.
     * @return Response to Mark command.
     */
    public String printTaskMarked(Task task) {
        String response = "Wow! Good job! I've crossed out this task for you!\n"
                + "    " + task.toString();
        return response;
    }

    /**
     * Creates response for unmarking tasks.
     *
     * @param task Task marked as uncompleted.
     * @return Response to Unmark command.
     */
    public String printTaskUnmarked(Task task) {
        String response = "OOPS! I've unticked this task for you :(\n"
                + "    " + task.toString();
        return response;
    }


    /**
     *  Checks if task exists in the list.
     *
     * @param index Index of task to find.
     * @param tasks List of tasks.
     * @return True if task is inside list.
     */
    public boolean isTaskInList(int index, TaskList tasks) {
        boolean isExceedSize = index >= tasks.getLength();
        boolean isNegative = index < 0;
        if (isExceedSize || isNegative) {
            return false;
        }
        return true;
    }

    /**
     * Formats word grammatically based on number of tasks in the list.
     *
     * @param size The number of tasks.
     * @return "task" if there is only one task in list else "tasks".
     */
    public String wordFormat(int size) {
        String word = size == 1 ? "task" : "tasks";
        return word;
    }

    /**
     * Represents response for the help command.
     *
     * @return Response for help command.
     */
    public String printHelpCommandMessage() {
        String response = "Take a look at what I can do!\n\n"
                + getListOfCommands();
        return response;
    }

    /**
     * Creates String representation of list of tasks containing
     * specified words.
     *
     * @param results List of specified tasks.
     * @return String representation of tasks with numbering.
     */
    public String printResultsList(ArrayList<Task> results) {
        String message = "Here are the matching tasks you requested:";
        String response = IntStream.range(1, results.size() + 1)
                .mapToObj(index -> String.format("\n    %d. %s", index,
                        results.get(index - 1).toString()))
                .reduce(message, (res, next) -> res + next);
        return response;
    }

    /**
     * Creates response for setting of task priority.
     *
     * @param task Task to have priority level set.
     * @return Response to set command.
     */
    public String printPrioritisedTaskMessage(Task task) {
        String response = "OKAYY I have set the priority level for this task!\n"
                + "    " + task.toString();
        return response;
    }

    /**
     * Creates response for invalid commands.
     *
     * @return Response to invalid user inputs.
     */
    public String printUnknownCommandMessage() {
        String response = "ERROR 101: No such command!\n\n"
                + this.getListOfCommands();
        return response;
    }
}
