import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class SecondMind {
    private static final String logo = "SecondMind";
    private static final String DATA_FILE_PATH = "../../../SecondMind.txt";
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private UI ui;

    public SecondMind() {
        this.ui = new UI();
        this.storage = new Storage(DATA_FILE_PATH);
        this.taskList = null;
        try {
            this.taskList = new TaskList(storage.loadTaskList());
        } catch (FileNotFoundException e) {
            this.ui.output(e.toString());
        } catch (IOException e) {
            this.ui.output(e.toString());
        }
        if (this.taskList == null) {
            this.taskList = new TaskList(new ArrayList<Task>());
        }
        this.parser = new Parser();
    }

    private void greetUser() {
        String greetings = "Hello from\n" + logo
                + "\n" + "What can I do for you?";
        this.ui.output(greetings);
    }

    private void execute(String[] instruction) {
        String command = instruction[0];
        if (command.equals("mark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsDone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, true, taskList.getTaskCount());
                String message = "Well done! You have completed the following task:\n"
                        + taskList.getTask(taskNumber).toString();
                this.ui.output(message);
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are " 
                        + taskList.getTaskCount() + " tasks in your task list.";
                this.ui.output(errorMessage);
                return;
            } catch (FileNotFoundException e) {
                this.ui.output(e.toString());
            } catch (IOException e) {
                this.ui.output(e.toString());
            }
        } else if (command.equals("unmark")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                taskList.markAsUndone(taskNumber);
                storage.updateTaskInDataFile(taskNumber, false, taskList.getTaskCount());
                String message = "I've marked the following task as incomplete:\n"
                        + taskList.getTask(taskNumber).toString();
                this.ui.output(message);
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are " 
                        + taskList.getTaskCount() + " tasks in your task list.";
                this.ui.output(errorMessage);
                return;
            } catch (FileNotFoundException e) {
                this.ui.output(e.toString());
            } catch (IOException e) {
                this.ui.output(e.toString());
            }
        } else if (command.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(instruction[1]);
                storage.delete(taskNumber, taskList.getTaskCount());
                String message = "I've removed the following task:\n"
                        + "\t" + taskList.getTask(taskNumber)
                                + "\nYou have a grand total of " + (taskList.getTaskCount()-1) + " task(s)";
                this.ui.output(message);
                taskList.delete(taskNumber);
            } catch (InvalidTaskNumberException e) {
                String errorMessage = e.toString() + "\nThere are " 
                        + taskList.getTaskCount() + " tasks in your task list.";
                this.ui.output(errorMessage);
                return;
            } catch (FileNotFoundException e) {
                this.ui.output(e.toString());
            } catch (IOException e) {
                this.ui.output(e.toString());
            }
        } else if (command.equals("list")) {
            this.ui.printTaskList(this.taskList.getTaskList(), this.taskList.getTaskCount());
        } else {
            try {
                Task curr = taskList.addToTaskList(instruction[1]);
                if (curr == null) {
                    return;
                } else {
                    try {
                        storage.appendToFile(curr.getStorageRepresentation(), taskList.getTaskCount());
                        System.out.println("here");
                        String message = "Got it. I have added the following task:\n\t" + curr + "\n"
                                + "You have a grand total of " + this.taskList.getTaskCount() + " task(s)";
                        this.ui.output(message);
                    } catch (IOException e) {
                        this.ui.output(e.toString());
                    }
                }
            } catch (EmptyCommandException | EmptyToDoException | UnknownCommandException e) {
                this.ui.output(e.toString());
            } catch (DateTimeParseException e) {
                String errorMessage = "Warning! Invalid dateTime format detected!\n"
                        + "Please use the following representation for dateTime strings:\n"
                        + "\tyyyy-MM-ddTHH:mm:ss";
                this.ui.output(errorMessage);
            }
        }
    }

    private void run() {
        while (true) {
            String input = ui.getInput();
            try {
                String[] instruction = parser.processInput(input);
                if (instruction[0].equals("bye")) {
                    break;
                } else {
                    execute(instruction);
                }
            } catch (NumberFormatException e) {
                this.ui.output("Warning! Invalid number format has been detected!");
            }
        }
    }

    private void exitProgram() {
        String exitMessage = "Bye. Hope to see you again soon!";
        this.ui.output(exitMessage);
    }

    public static void main(String[] args) {
        SecondMind sm = new SecondMind();
        sm.greetUser();
        sm.run();
        sm.exitProgram();
    }
}
