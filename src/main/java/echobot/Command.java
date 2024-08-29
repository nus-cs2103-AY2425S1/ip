package echobot;
import echobot.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Command {
    private final String[] inputParts;
    private final String command;
    private final TaskList tasks;
    public Command(String[] inputParts, TaskList tasks) {
        this.inputParts = inputParts;
        this.command = inputParts[0];
        this.tasks = tasks;
    }

    public String getCommand() {
        return this.command;
    }

    public boolean isExit() {
        return this.command.equals("bye");
    }

    public void run() {
        switch (this.command) {
            case "bye":
                break;
            case "list":
                listTasks();
                break;
            case "mark": {
                handleMarkCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "unmark": {
                handleUnmarkCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "todo": {
                handleTodoCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "deadline": {
                try {
                    handleDeadlineCommand(inputParts);
                    Storage.saveTasksToFile(tasks);
                } catch (IllegalArgumentException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println(" Please enter date in the format dd/MM/yyyy?");
                    System.out.println("____________________________________________________________");
                }
                break;
            }
            case "event": {
                try {
                    handleEventCommand(inputParts);
                    Storage.saveTasksToFile(tasks);
                } catch (IllegalArgumentException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" " + e.getMessage());
                    System.out.println(" Please enter date in the format dd/MM/yyyy?");
                    System.out.println("____________________________________________________________");
                }
                break;
            }
            case "delete": {
                handleDeleteCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "findbydate": {
                handleFindByDateCommand(inputParts);
                Storage.saveTasksToFile(tasks);
                break;
            }
            case "find" : {
                handleFindCommand(inputParts);
                break;
            }
            default:
                System.out.println("____________________________________________________________");
                System.out.println(" I'm sorry, I don't recognize that command.");
                System.out.println("____________________________________________________________");
                break;
        }
    }
    private void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }
    private void handleMarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to mark as done.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsDone();
                Storage.saveTasksToFile(tasks);
                System.out.println("____________________________________________________________");
                System.out.println(" Great! I've marked this task as done:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private void handleUnmarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to unmark.");
            System.out.println("____________________________________________________________");
        } else {
            int taskNumber = Integer.parseInt(inputParts[1]) - 1;
            if (taskNumber < tasks.size() && taskNumber >= 0) {
                tasks.get(taskNumber).markAsNotDone();
                Storage.saveTasksToFile(tasks);
                System.out.println("____________________________________________________________");
                System.out.println(" OK! I've marked this task as not done yet:");
                System.out.println("   " + tasks.get(taskNumber));
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! That task number doesn't exist.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private void handleTodoCommand(String[] inputParts) {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a todo cannot be empty.");
            System.out.println("____________________________________________________________");
        } else {
            tasks.addTask(new Todo(inputParts[1]));
            System.out.println("____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks.get(tasks.size() - 1));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        }
    }

    private void handleDeadlineCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/by ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of a deadline must include a due date.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /by ");
            if (details.length < 2 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                tasks.addTask(new Deadline(details[0], details[1]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private void handleEventCommand(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/from ") || !inputParts[1].contains("/to ")) {
            System.out.println("____________________________________________________________");
            System.out.println(" The description of an event must include start and end times.");
            System.out.println("____________________________________________________________");
        } else {
            String[] details = inputParts[1].split(" /from | /to ");
            if (details.length < 3 || details[0].trim().isEmpty()) {
                System.out.println("____________________________________________________________");
                System.out.println(" The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } else {
                tasks.addTask(new Event(details[0], details[1], details[2]));
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks.get(tasks.size() - 1));
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private void handleDeleteCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please specify the task number to delete.");
            System.out.println("____________________________________________________________");
        } else {
            try {
                int taskNumber = Integer.parseInt(inputParts[1]) - 1;
                if (taskNumber < tasks.size() && taskNumber >= 0) {
                    Task removedTask = tasks.removeTask(taskNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Oops! That task number doesn't exist.");
                    System.out.println("____________________________________________________________");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter an item number to delete!");
                System.out.println("____________________________________________________________");
            }
        }
    }
    private void handleFindByDateCommand(String[] inputParts) {
        System.out.println("____________________________________________________________");
        if (inputParts.length < 2) {
            System.out.println("Please specify a date in the format yyyy-mm-dd.");
            System.out.println("____________________________________________________________");
            return;
        }

        String dateString = inputParts[1];  // Assuming the date is the second element
        try {
            LocalDate queryDate = LocalDate.parse(dateString);
            System.out.println("Tasks occurring on " + queryDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            boolean hasTasks = false;
            for (Task task : tasks.getTasks()) {
                if (task instanceof Deadline && ((Deadline) task).by.equals(queryDate)) {
                    System.out.println(task);
                    hasTasks = true;
                } else if (task instanceof Event && (((Event) task).from.equals(queryDate) || ((Event) task).to.equals(queryDate))) {
                    System.out.println(task);
                    hasTasks = true;
                }
            }
            if (!hasTasks) {
                System.out.println("No tasks found on this date.");
            }
            System.out.println("____________________________________________________________");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            System.out.println("____________________________________________________________");
        }
    }

    private void handleFindCommand(String[] inputParts) {
        System.out.println("____________________________________________________________");
        // Check if the command includes a keyword to search
        if (inputParts.length < 2) {
            System.out.println("Please specify a keyword to search for.");
            System.out.println("____________________________________________________________");
            return;
        }

        // Extract the keyword, assuming it is the second element
        String keyword = inputParts[1].trim();
        System.out.println("Searching for tasks with keyword: " + keyword);

        // Flag to check if any tasks match the keyword
        boolean hasMatchingTasks = false;

        // Iterate over the tasks to find matches
        for (Task task : tasks.getTasks()) {
            // Check if the task description contains the keyword
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(task);
                hasMatchingTasks = true;
            }
        }

        // If no tasks match the keyword, display an appropriate message
        if (!hasMatchingTasks) {
            System.out.println("No tasks found containing the keyword: " + keyword);
        }
        System.out.println("____________________________________________________________");
    }
}
