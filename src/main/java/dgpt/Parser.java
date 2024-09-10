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
        String command = inputs[0];

        switch (command) {
        case "list" -> {
            if (inputs.length != 1) {
                throw new IncorrectInputException("You should not have anything after your request. "
                        + "(e.g. \"list\")");
            }

            output.append("Here are the following items in your list:\n");
            int index = 1;
            for (Task t : taskList.getTaskList()) {
                output.append(index).append(".").append(t).append("\n");
                index++;
            }
        }
        case "mark" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"mark 1\")");
            }

            int indexOfTask = Integer.parseInt(inputs[1]) - 1;
            Task currTask = taskList.markTask(indexOfTask);

            output.append("Nice! I've marked this task as done:\n");
            output.append(currTask).append("\n");
        }
        case "unmark" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"unmark 1\")");
            }

            int indexOfTask = Integer.parseInt(inputs[1]) - 1;
            Task currTask = taskList.unmarkTask(indexOfTask);

            output.append("Dgpt> OK, I've marked this task as not done yet:\n");
            output.append(currTask).append("\n");
        }
        case "todo" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have a description after your request. "
                        + "(e.g. \"todo your_description\")");
            }

            String description = inputs[1];
            Task addedTask = taskList.addToDoToList(description);
            int sizeOfList = taskList.getSize();

            output.append("Got it. I've added this task:\n");
            output.append(addedTask).append("\n");
            output.append("Now you have ").append(sizeOfList).append(" tasks in the list.\n");
        }
        case "deadline" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have a description after your request. "
                        + "(e.g. \"todo your_description /by your_deadline\")");
            }

            try {
                String[] parts = inputs[1].split(" /by ");
                String description = parts[0];
                String deadline = parts[1];

                Task addedTask = taskList.addDeadlineToList(description, deadline);
                int sizeOfList = taskList.getSize();

                output.append("Got it. I've added this task:\n");
                output.append(addedTask).append("\n");
                output.append("Now you have ").append(sizeOfList).append(" tasks in the list.\n");
            } catch (DateTimeParseException e) {
                output.append(e.getMessage()).append("\n");
            }
        }
        case "event" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have a description after your request. "
                        + "(e.g. \"todo your_description /from your_start_time /to your_end_time\")");
            }
            try {
                String[] parts = inputs[1].split(" /");
                String description = parts[0];
                String startTime = parts[1].substring(5);
                String endTime = parts[2].substring(3);

                Task addedTask = taskList.addEventToList(description, startTime, endTime);
                int sizeOfList = taskList.getSize();


                output.append("Got it. I've added this task:\n");
                output.append(addedTask).append("\n");
                output.append("Now you have ").append(sizeOfList).append(" tasks in the list.\n");
            } catch (DateTimeParseException e) {
                output.append(e.getMessage()).append("\n");
            }
        }
        case "delete" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should have only 1 number after your request. "
                        + "(e.g. \"delete 1\")");
            }

            int indexOfTask = Integer.parseInt(inputs[1]) - 1;
            Task deletedTask = taskList.deleteTask(indexOfTask);
            int size = taskList.getSize();

            output.append("OK, I've removed this task from the list:\n");
            output.append(deletedTask).append("\n");
            output.append("Now you have ").append(size).append(" tasks in the list.\n");
        }

        case "find" -> {
            if (inputs.length != 2) {
                throw new IncorrectInputException("You should input what you're searching for after \"find\""
                        + " (e.g. \"find task\")");
            }

            String keyword = inputs[1];
            List<Task> matchingTasks = taskList.findTasks(keyword);
            int index = 1;

            output.append("Here are the matching tasks in your list:\n");

            for (Task t : matchingTasks) {
                output.append(index).append(".").append(t.toString()).append("\n");
                index++;
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
