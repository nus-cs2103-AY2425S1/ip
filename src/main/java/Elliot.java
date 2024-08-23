import java.util.Scanner;
import java.util.ArrayList;

public class Elliot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        TaskList taskList = new TaskList();

        introSay();
        while(running) {
            System.out.print("> ");
            String userInput = captureUserInput(scanner);
            say("");
            switch(userInput.toLowerCase().split(" ")[0]) {
                case "mark":
                case "unmark":
                    int taskIndex;
                    try {
                        taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                        if (taskIndex <= taskList.size() && taskIndex > 0) {
                            switch(userInput.toLowerCase().split(" ")[0]) {
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
                default:
                    taskList = taskList.addTask(new Task(userInput.strip()));
                    say("added: " + userInput.strip() + "\n");
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
}
