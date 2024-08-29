package fridayproject;

import java.util.Scanner;

public class Friday {
    public static void main(String[] args) throws FridayException {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in); 
        Tasks[] tasks = new Tasks[100];
        int taskAdded = 0;
        String line = "_________________________________________";
        System.out.println(greeting + line);

        while (true) {
            String inputString = scanner.nextLine().trim();
            System.out.println(line);
            try {
                System.out.println(line);
                if (inputString.equals("bye")) {
                    System.out.println(farewell);
                    System.out.println(line);
                    break;
                } else if (inputString.startsWith("todo ")) {
                    if (inputString.length() < 6) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    tasks[taskAdded++] = new Todo(inputString.substring(5));
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskAdded - 1].getTypeIcon() 
                    + tasks[taskAdded - 1].toString() + "\nNow you have " + taskAdded + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("deadline")) {
                    if (inputString.length() < 10) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String remainingInput = inputString.substring(inputString.indexOf(" ") + 1);
                    String[] deadlineParts = remainingInput.substring(taskAdded).split(" /by ");
                    tasks[taskAdded++] = new Deadline(deadlineParts[0] , deadlineParts[1]);
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskAdded - 1].getTypeIcon() 
                    + tasks[taskAdded - 1].toString() + "\nNow you have " + taskAdded + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("event")) {
                    if (inputString.length() < 7) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String[] eventParts = inputString.substring(6).split(" /from | /to ");
                    tasks[taskAdded++] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskAdded - 1].getTypeIcon() 
                    + tasks[taskAdded - 1].toString() + "\nNow you have " + taskAdded + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.length; i++) {
                        if (tasks[i] != null) {
                            System.out.println((i + 1) + ". " + tasks[i].getTypeIcon() + tasks[i]);
                        }
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(5)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.length && tasks[taskNum] != null) {
                        tasks[taskNum].markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  [X] " + tasks[taskNum].description);
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.length && tasks[taskNum] != null) {
                        tasks[taskNum].markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + tasks[taskNum].description);
                    }
                    System.out.println(line);
                } else {
                    throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                }
                System.out.println(line);
            } catch (FridayException e) {
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        scanner.close();
    }
}