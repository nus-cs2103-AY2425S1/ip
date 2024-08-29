import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;
import java.time.format.DateTimeParseException;

public class Elliot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        TaskList taskList;

        introSay();
        taskList = loadTaskList();
        while (isRunning) {
            System.out.print("> ");
            String userInput = captureUserInput(scanner).strip();
            String[] command = stripStrArray(userInput.toLowerCase().split(" ", 2));
            say("");
            switch (command[0]) {
            case "mark":
                // Fallthrough
            case "unmark":
                // Fallthrough
            case "delete":
                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(command[1]);
                    if (taskIndex <= taskList.size() && taskIndex > 0) {
                        switch(command[0]) {
                        case "mark":
                            taskList = taskList.markTaskAsDone(taskIndex - 1);
                            say("Nice! I've marked this task as done:\n"
                                    + taskList.get(taskIndex - 1).toString() + "\n");
                            break;
                        case "unmark":
                            taskList = taskList.markTaskAsUndone(taskIndex - 1);
                            say("OK, I've marked this task as not done yet:\n"
                                    + taskList.get(taskIndex - 1).toString() + "\n");
                            break;
                        case "delete":
                            say("Noted. I've removed this task:\n"
                                    + taskList.get(taskIndex - 1).toString() + "\n"
                                    + "Now you have " + (taskList.size() - 1) 
                                    + " tasks in the list.\n");
                            taskList = taskList.deleteTask(taskIndex - 1);
                        }
                    } else {
                        say("No such task!\n");
                    }
                } catch (NumberFormatException e) {
                    taskIndex = -1;
                    say("No such task!\n");
                }
                break;
            case "todo":
                // Fallthrough
            case "deadline":
                // Fallthrough
            case "event":
                String[] commandOptions;
                if (command.length == 1) {
                    say("describe your task\n");
                    break;
                }
                Task taskToAdd;
                switch (command[0]) {
                case "todo":
                    taskToAdd = new TodoTask(command[1]);
                    break;
                case "deadline":
                    commandOptions = stripStrArray(command[1].split("/by"));
                    if (commandOptions.length == 0 || commandOptions[0] == "") {
                        say("describe your task\n");
                        continue;
                    }
                    if (commandOptions.length < 2) {
                        say("when is this due by?\n");
                        continue;
                    }
                    try {
                        taskToAdd = new DeadlineTask(commandOptions[0], commandOptions[1]);
                    } catch (DateTimeParseException e) {
                        say("date format incorrect. try dd-MM-yyyy hhmm (24-hour)\n");
                        continue;
                    }
                    break;
                case "event":
                    commandOptions = stripStrArray(command[1].split("/from|/to"));
                    if (commandOptions.length == 0 || commandOptions[0] == "") {
                        say("describe your task\n");
                        continue;
                    }
                    if (commandOptions.length < 3) {
                        say("from when to when is this event?\n");
                        continue;
                    }
                    try {
                        taskToAdd = new EventTask(commandOptions[0], commandOptions[1], 
                                commandOptions[2]);
                    } catch (DateTimeParseException e) {
                        say("date format incorrect. try dd-MM-yyyy hhmm (24-hour)\n");
                        continue;
                    }
                    break;
                default:
                    taskToAdd = new Task(userInput);
                }
                taskList = taskList.addTask(taskToAdd);
                say("Got it. I've added this task:\n"
                        + taskToAdd.toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list.\n");
                break;
            case "list":
                say(taskList.toString());
                break;
            case "bye":
                saveTaskList(taskList);
                byeSay();
                isRunning = false;
                break;
            default:
                say("no such commands found\n");
            }
        }
    }

    private static void say(String textToSay) {
        String line = "____________________________________________________________\n";
        System.out.print(textToSay);
        System.out.print(line);
    }

    private static void introSay() {
        String logo = "    ________    __    ________  ______\n"
                + "   / ____/ /   / /   /  _/ __ \\/_  __/\n"
                + "  / __/ / /   / /    / // / / / / /\n"
                + " / /___/ /___/ /____/ // /_/ / / /\n"
                + "/_____/_____/_____/___/\\____/ /_/\n";
        String intro = "Hello! I'm\n" + logo
                + "What can I do for you?\n";
        say("");
        say(intro);
    }

    private static void byeSay() {
        String bye = "Bye. Hope to see you again soon!\n";
        say(bye);
    }

    private static String captureUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    private static String[] stripStrArray(String[] strArray) {
        return Arrays.stream(strArray)
                .map(String::strip)
                .toArray(String[]::new);
    }

    private static TaskList loadTaskList() {
        try {
            File file = new File("ElliotTaskList.ser");
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(file));
                say("task list save file loaded\n");
                return (TaskList) ois.readObject();
            } else {
                file.createNewFile();
                say("created new save file for task list\n");
                saveTaskList(new TaskList());
                return new TaskList();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new TaskList();
        }
    }

    private static void saveTaskList(TaskList taskList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("ElliotTaskList.ser"));
            oos.writeObject(taskList);
            say("task list saved to file!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
