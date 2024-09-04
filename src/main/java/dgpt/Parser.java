package dgpt;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

import dgpt.exception.IncorrectInputException;
import dgpt.exception.TaskNotFoundException;
import dgpt.task.Task;
import dgpt.task.TaskList;

/**
 * The Parser class is responsible for interpreting user input and converting it into commands
 * that interact with the TaskList and Ui components of the Dgpt application.
 * It parses the input string, identifies the command, and performs the corresponding action
 * on the task list while providing feedback to the user interface.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command on the task list.
     * This method identifies the command type and performs the appropriate action,
     * such as marking or unmarking tasks, adding new tasks, or deleting tasks.
     * It also handles exceptions related to incorrect input and task operations.
     *
     * @param text The user input string that contains the command and its parameters.
     * @param taskList The TaskList instance where tasks are managed.
     * @return A string representing the response given by Dgpt after taking in an input.
     * @throws IncorrectInputException If the input format is incorrect or missing required parameters.
     * @throws TaskNotFoundException If the command refers to a task that cannot be found.
     */

    public static String parse(String text, TaskList taskList, Storage storage) throws IncorrectInputException,
            TaskNotFoundException {

        StringBuilder output = new StringBuilder();
        String[] inputs = text.split(" ", 2);

        switch (inputs[0]) {
        case "list" -> {
            if (inputs.length == 1) {
                output.append("Here are the following items in your list:\n");
                int index = 1;
                for (Task t : taskList.getTaskList()) {
                    output.append(index).append(".").append(t.toString()).append("\n");
                    index++;
                }
            } else {
                throw new IncorrectInputException("You should not have anything after your request. "
                        + "(e.g. \"list\")");
            }
        }
        case "mark" -> {
            if (inputs.length == 2) {
                output.append("Nice! I've marked this task as done:\n");
                output.append(taskList.markTask(Integer.parseInt(inputs[1]) - 1).toString()).append("\n");
            } else {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"mark 1\")");
            }
        }
        case "unmark" -> {
            if (inputs.length == 2) {
                output.append("Dgpt> OK, I've marked this task as not done yet:\n");
                output.append(taskList.unmarkTask(Integer.parseInt(inputs[1]) - 1)).append("\n");
            } else {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"unmark 1\")");
            }
        }
        case "todo" -> {
            if (inputs.length == 2) {
                output.append("Got it. I've added this task:\n");
                output.append(taskList.addToDoToList(inputs[1]).toString()).append("\n");
                output.append("Now you have ").append(taskList.getSize()).append(" tasks in the list.\n");
            } else {
                throw new IncorrectInputException("You should have a description after your request. "
                        + "(e.g. \"todo your_description\")");
            }
        }
        case "deadline" -> {
            try {
                if (inputs.length == 2) {
                    String[] parts = inputs[1].split(" /by ");

                    output.append("Got it. I've added this task:\n");
                    output.append(taskList.addDeadlineToList(parts[0], parts[1]).toString()).append("\n");
                    output.append("Now you have ").append(taskList.getSize()).append(" tasks in the list.\n");
                } else {
                    throw new IncorrectInputException("You should have a description after your request. "
                            + "(e.g. \"todo your_description /by your_deadline\")");
                }
            } catch (DateTimeParseException e) {
                output.append(e.getMessage()).append("\n");
            }
        }
        case "event" -> {
            try {
                if (inputs.length == 2) {
                    String[] parts = inputs[1].split(" /");

                    output.append("Got it. I've added this task:\n");
                    output.append(taskList.addEventToList(parts[0], parts[1].substring(5),
                            parts[2].substring(3))).append("\n");
                    output.append("Now you have ").append(taskList.getSize()).append(" tasks in the list.\n");
                } else {
                    throw new IncorrectInputException("You should have a description after your request. "
                            + "(e.g. \"todo your_description /from your_start_time /to your_end_time\")");
                }
            } catch (DateTimeParseException e) {
                output.append(e.getMessage()).append("\n");
            }
        }
        case "delete" -> {
            if (inputs.length == 2) {
                int size = taskList.getSize();

                output.append("OK, I've removed this task from the list:\n");
                output.append(taskList.deleteTask(Integer.parseInt(inputs[1]) - 1)).append("\n");
                output.append("Now you have ").append(taskList.getSize()).append(" tasks in the list.\n");
            } else {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"delete 1\")");
            }
        }

        case "find" -> {
            if (inputs.length == 2) {
                List<Task> tasks = taskList.findTasks(inputs[1]);
                int index = 1;

                output.append("Here are the matching tasks in your list:\n");

                for (Task t : tasks) {
                    output.append(index).append(".").append(t.toString()).append("\n");
                    index++;
                }
            } else {
                throw new IncorrectInputException("You should input what you're searching for after \"find\""
                        + " (e.g. \"find task\")");
            }
        }

        case "save" -> {
            try {
                storage.save(taskList);
                output.append("successfully saved!");
            } catch (IOException e) {
                output.append(e.getMessage()).append("\n");
            }
        }
        default -> {
            throw new TaskNotFoundException("I do not recognise that request. These are the "
                    + "following requests that are supported:\n"
                    + "-list\n"
                    + "-mark\n"
                    + "-unmark\n"
                    + "-todo\n"
                    + "-deadline\n"
                    + "-event\n"
                    + "-delete\n"
                    + "-find\n"
                    + "-save");
        }
        }

        return output.toString();
    }
}
