import java.util.Scanner;
import java.util.Arrays;

public class Elliot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        TaskList taskList = new TaskList();

        introSay();
        while(running) {
            System.out.print("> ");
            String userInput = captureUserInput(scanner).strip();
            String[] command = stripStrArray(userInput.toLowerCase().split(" ", 2));
            say("");
            switch (command[0]) {
                case "mark":
                case "unmark":
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
                            }
                        } else {
                            say("No such task!\n");
                        }
                    } catch (NumberFormatException e) {
                        taskIndex = -1;
                        say("No such task!\n");
                    }
                    break;
                case "list":
                    say(taskList.toString());
                    break;
                case "bye":
                    byeSay();
                    running = false;
                    break;
                case "todo":
                case "deadline":
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
                                taskToAdd = new DeadlineTask(commandOptions[0]);
                            } else {
                                taskToAdd = new DeadlineTask(commandOptions[0], commandOptions[1]);
                            }
                            break;
                        case "event":
                            commandOptions = stripStrArray(command[1].split("/from|/to"));
                            if (commandOptions.length == 0 || commandOptions[0] == "") {
                                say("describe your task\n");
                                continue;
                            }
                            if (commandOptions.length < 3) {
                                taskToAdd = new EventTask(commandOptions[0]);
                            } else {
                                taskToAdd = new EventTask(commandOptions[0], commandOptions[1], 
                                        commandOptions[2]);
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
}
