package Bot;

import TaskType.TaskBuilder;

import java.util.Objects;
import java.util.Scanner;

//
public class Duke {
    private static String name = "Bot.Duke";
    private ListManager DukeManager = new ListManager();
    // Possible use of Task
    public enum TaskType {
        TODO,EVENT,DEADLINE
    }


    private void exit() {
        System.out.println("Bye! Hope to see you again my G");
    }

    private void greet() {
        boolean endChat = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + " aka ChatGPT on Crack!\nWhat assistance are you in need of today?");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String commandLowerCase = command.toLowerCase();
            String[] parts = command.split(" ");
            String firstWord = parts[0];

            if (commandLowerCase.equals("bye")) {
                System.out.println("See you loser");
            } else if (commandLowerCase.equals("list")) {
                System.out.println(DukeManager.listItems());

            } else if (parts.length == 2 && (parts[0].toLowerCase().equals("mark") || parts[0].toLowerCase().equals("unmark"))) {
                String action = parts[0].toLowerCase();
                String numberStr = parts[1];
                 try {
                    int number = Integer.parseInt(numberStr);
                    // Check for valid index (e.g., positive integers only)
                    if (number > 0) {
                        if (action.equals("mark")) {
                            DukeManager.setDone(true, number);
                            System.out.println("Nice! I've marked this task as done:\n" + DukeManager.getItem(number));

                        } else {
                            DukeManager.setDone(false, number);
                            System.out.println("OK, I've marked this task as not done yet:\n" + DukeManager.getItem(number));
                        }
                    } else {
                        System.out.println("Give me a positive number man");
                    }
                 } catch (NumberFormatException e) {
                     System.out.println("You didnt even type a number !!.");
                 }

            } else if (Objects.equals(firstWord, "deadline")) {
                // Remove the word 'deadline' and split by '/by'
                String[] part = command.replaceFirst("deadline ", "").split("/by", 2);
                // Get description part
                String description = part[0].trim();
                // Get 'by' part
                String by = part.length > 1 ? part[1].trim() : "";

                TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.DEADLINE);
                DukeManager.createItem(taskBuilder.by(by));

            } else if (Objects.equals(firstWord, "todo")) {
                String description = command.substring("todo".length()).trim();
                TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.TODO);
                DukeManager.createItem(taskBuilder);

            } else if (Objects.equals(firstWord, "event")) {
                // Remove the word 'event' and split by '/from'
                String[] part = command.replaceFirst("event ", "").split("/from", 2);
                // The description part
                String description = part[0].trim();
                // The remaining part
                String remaining = part.length > 1 ? part[1].trim() : "";
                // Split the remaining part by '/to'
                String[] dateParts = remaining.split("/to", 2);
                // The 'from' part
                String from = dateParts[0].trim();
                // The 'to' part
                String to = dateParts.length > 1 ? dateParts[1].trim() : "";

                TaskBuilder taskBuilder = new TaskBuilder(description, TaskType.EVENT);
                DukeManager.createItem(taskBuilder.from(from).to(to));

            } else if (Objects.equals(firstWord, "delete")) {
                String index = command.substring("delete".length()).trim();
                String tempTask = DukeManager.getItem(Integer.parseInt(index));
                DukeManager.delete(Integer.parseInt(index));
                System.out.println("I have your ass covered! " + tempTask + " is now deleted. You have"
                + DukeManager.getItemSize() + " items left todo lazy boy");


            } else {
                System.out.println("I dont get your command man");

            }
        }


    }

    public static void main(String[] args) {
        Duke MrDuke = new Duke();
        MrDuke.greet();

    }
}
