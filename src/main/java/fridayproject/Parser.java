package fridayproject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /*
     * Parses the user input and executes the corresponding command.
     * @param input The user input.
     */
    public void parseCommand(String inputString) {
        try {
            if (inputString.equals("bye")) {
                ui.displayFarewell();
                System.exit(0);
            } else if (inputString.equals("list")) {
                listTasks();
            } else if (inputString.startsWith("mark ")) {
                markTask(inputString);
            } else if (inputString.startsWith("unmark ")) {
                unmarkTask(inputString);
            } else if (inputString.startsWith("delete ")) {
                deleteTask(inputString);
            } else if (inputString.startsWith("todo ")) {
                addTodo(inputString);
            } else if (inputString.startsWith("deadline ")) {
                addDeadline(inputString);
            } else if (inputString.startsWith("event ")) {
                addEvent(inputString);
            } else {
                ui.displayUnknownCommandError();
            }
        } catch (FridayException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }


    private void listTasks() {
        ui.displayTasks(taskList.getTasks());
    }

    private void markTask(String input) throws FridayException, IOException {
        if (input.length() < 6) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(5));
        taskList.markTaskAsDone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        ui.displayMessage("Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber).getTypeIcon() 
        + taskList.getTask(taskNumber).toString());
    }

    private void unmarkTask(String input) throws FridayException, IOException {
        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7));
        taskList.markTaskAsUndone(taskNumber);
        storage.saveTasksToFile(taskList.getTasks());
        ui.displayMessage("OK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber).getTypeIcon() 
        + taskList.getTask(taskNumber).toString());
    }

    private void deleteTask(String input) throws FridayException, IOException {
        if (input.length() < 8) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task number.");
        }
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < taskList.size() && taskList.getTask(taskNumber) != null) {
            Tasks deletedTask = taskList.deleteTask(taskNumber);
            ui.displayMessage("Noted. I've removed this task:\n  " + deletedTask.getTypeIcon() + deletedTask.toString() 
            + "\nNow you have " + taskList.size() + " tasks in the list.");
            storage.saveTasksToFile(taskList.getTasks());
        }
    }

    private void addTodo(String input) throws FridayException, IOException {
        if (input.length() < 6) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
        }
        Tasks todo = new Todo(input.substring(5).trim());
        taskList.addTask(todo);
        storage.saveTasksToFile(taskList.getTasks());
        ui.displayMessage("Got it. I've added this task:\n  " + todo.getTypeIcon() 
        + todo.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private void addDeadline(String input) throws FridayException, IOException {
        if (input.length() < 10) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
        }
        String remainingInput = input.substring(input.indexOf(" ") + 1);
        String[] deadlineParts = remainingInput.split(" /by ");

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(deadlineParts[1], formatter);
            Tasks deadline = new Deadline(deadlineParts[0] , date.toString());
            taskList.addTask(deadline);
            storage.saveTasksToFile(taskList.getTasks());
            ui.displayMessage("Got it. I've added this task:\n  " + deadline.getTypeIcon() 
            + deadline.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new FridayException("Invalid date format! Please enter in yyyy-MM-dd.");
        }
    }

    private void addEvent(String input) throws FridayException, IOException {
        if (input.length() < 7) {
            throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
        }
        String remainingInput = input.substring(input.indexOf(" ") + 1);
        String[] eventParts = remainingInput.split(" /at ");
        String[] startEnd = eventParts[1].split(" to ");
        Tasks event = new Event(eventParts[0], startEnd[0], startEnd[1]);
        taskList.addTask(event);
        storage.saveTasksToFile(taskList.getTasks());
        ui.displayMessage("Got it. I've added this task:\n  " + event.getTypeIcon() 
        + event.toString() + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
