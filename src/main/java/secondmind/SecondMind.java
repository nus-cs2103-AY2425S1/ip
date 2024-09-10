package secondmind;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Main class for the SecondMind application that manages tasks.
 * It initializes the required components and controls the main program flow.
 */
public class SecondMind {
    private static final String logo = "SecondMind";
    private static final String DATA_FILE_PATH = "./SecondMind.txt";
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    /**
     * Main method that serves as the entry point for the application.
     */
    public SecondMind() {
        this.storage = new Storage(DATA_FILE_PATH);
        this.taskList = null;
        try {
            this.taskList = new TaskList(storage.loadTaskList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.taskList == null) {
            this.taskList = new TaskList(new ArrayList<Task>());
        }
        this.parser = new Parser();
    }

    public String getResponse(String userInput) {
        String[] instruction = parser.processInput(userInput);
        String command = instruction[0];
        if (command.equals("bye")) {
            return "$$$EXIT_PROGRAM$$$";
        } else if (command.equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsDone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, true, taskList.getTaskCount());
                String message = "Well done! You have completed the following task:\n"
                        + taskList.getTask(taskNumber).toString();
                return message;
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are "
                        + taskList.getTaskCount() + " tasks in your task list.";
                return errorMessage;
            } catch (FileNotFoundException e) {
                return e.toString();
            } catch (IOException e) {
                return e.toString();
            }
        } else if (command.equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsUndone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, false, taskList.getTaskCount());
                String message = "I've marked the following task as incomplete:\n"
                        + taskList.getTask(taskNumber).toString();
                return message;
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are "
                        + taskList.getTaskCount() + " tasks in your task list.";
                return errorMessage;
            } catch (FileNotFoundException e) {
                return e.toString();
            } catch (IOException e) {
                return e.toString();
            }
        } else if (command.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                storage.delete(taskNumber, taskList.getTaskCount());
                String message = "I've removed the following task:\n"
                        + "\t" + taskList.getTask(taskNumber)
                        + "\nYou have a grand total of " + (taskList.getTaskCount()-1) + " task(s)";
                taskList.delete(taskNumber);
                return message;
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are "
                        + taskList.getTaskCount() + " tasks in your task list.";
                return errorMessage;
            } catch (FileNotFoundException e) {
                return e.toString();
            } catch (IOException e) {
                return e.toString();
            }
        } else if (command.equals("list")) {
            StringBuilder sb = new StringBuilder();
            ArrayList<Task> tl = this.taskList.getTaskList();
            for (Task task : tl) {
                sb.append(task.toString());
                sb.append("\n");
            }
            return sb.toString();
        } else if (command.equals("find")) {
            instruction[0] = "";
            String match = String.join(" ", instruction);
            ArrayList<Task> filteredTaskList = this.taskList.getMatchingTasks(match);
            StringBuilder sb = new StringBuilder();
            for (Task task : filteredTaskList) {
                sb.append(task.toString());
                sb.append("\n");
            }
            return sb.toString();
        } else {
            try {
                Task curr = taskList.addToTaskList(instruction[1]);
                if (curr == null) {
                    return "";
                } else {
                    try {
                        storage.appendToFile(curr.getStorageRepresentation(), taskList.getTaskCount());
                        String message = "Got it. I have added the following task:\n\t" + curr + "\n"
                                + "You have a grand total of " + this.taskList.getTaskCount() + " task(s)";
                        return message;
                    } catch (IOException e) {
                        return e.toString();
                    }
                }
            } catch (EmptyCommandException | EmptyToDoException | UnknownCommandException e) {
                return e.toString();
            } catch (DateTimeParseException e) {
                String errorMessage = "Warning! Invalid dateTime format detected!\n"
                        + "Please use the following representation for dateTime strings:\n"
                        + "\tyyyy-MM-ddTHH:mm:ss";
                return errorMessage;
            }
        }
    }
}
