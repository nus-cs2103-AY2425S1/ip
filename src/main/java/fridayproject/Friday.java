package fridayproject;

import java.util.Scanner;
import java.util.ArrayList;

public class Friday {
    public static void main(String[] args) throws FridayException {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        Scanner scanner = new Scanner(System.in); 
        ArrayList<Tasks> tasks = new ArrayList<>();
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
                    Tasks todo = new Todo(inputString.substring(5)); 
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:\n  " + todo.getTypeIcon() 
                    + todo.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("deadline")) {
                    if (inputString.length() < 10) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String remainingInput = inputString.substring(inputString.indexOf(" ") + 1);
                    String[] deadlineParts = remainingInput.substring(taskAdded).split(" /by ");
                    Tasks deadline = new Deadline(deadlineParts[0] , deadlineParts[1]);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:\n  " + deadline.getTypeIcon() 
                    + deadline.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("event")) {
                    if (inputString.length() < 7) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String[] eventParts = inputString.substring(6).split(" /from | /to ");
                    Tasks event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:\n  " + event.getTypeIcon() 
                    + event.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println((i + 1) + ". " + tasks.get(i).getTypeIcon() + tasks.get(i));
                        }
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(5)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        tasks.get(taskNum).markAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  [X] " + tasks.get(taskNum).description);
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        tasks.get(taskNum).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + tasks.get(taskNum).description);
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("delete")) {
                    int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        System.out.println("Noted. I've removed this task:\n  " + tasks.get(taskNum).getTypeIcon() + tasks.get(taskNum));
                        tasks.remove(taskNum);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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