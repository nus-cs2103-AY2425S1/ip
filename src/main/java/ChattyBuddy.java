import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ChattyBuddy {
    public static void main(String[] args) {
        ArrayList<Task> inputList = new ArrayList<>();
        String breakLine = "——————————————————————————";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(breakLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(breakLine);
        Scanner userInput = new Scanner(System.in);

        // user inputs something
        String response = userInput.nextLine();
        String[] slicedStr = response.split(" ");

        // checking against all possible outcomes
        while (!response.equals("bye")) {
            try {
                // when user enters "list"
                if (response.equals("list")) {
                    System.out.println(breakLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputList.size(); i++) {
                        System.out.printf("%d.[%s][%s] %s%n", i + 1,inputList.get(i).type,inputList.get(i).getStatusIcon(), inputList.get(i).description);
                    }
                    System.out.println(breakLine);

                    // when user enters "mark xxx"
                } else if (slicedStr.length == 2 && slicedStr[0].equals("mark")) {
                    try {
                        if (Integer.parseInt(slicedStr[1]) > inputList.size() || Integer.parseInt(slicedStr[1]) <= 0) {
                            throw new ChattyBuddyException();
                        }
                        Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                        target.setMarkStatus(true);
                        System.out.println(breakLine);
                        System.out.println("OK, I've marked this task as done:");
                        System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target.description);
                        System.out.println(breakLine);
                    } catch (ChattyBuddyException e) {
                        System.out.println(breakLine);
                        System.out.println("Oh no! No such task is found!");
                        System.out.println(breakLine);
                    }

                    // when user enters "unmark xxx"
                } else if (slicedStr.length == 2 && slicedStr[0].equals("unmark")) {
                    try {
                        if (Integer.parseInt(slicedStr[1]) > inputList.size() || Integer.parseInt(slicedStr[1]) <= 0) {
                            throw new ChattyBuddyException();
                        }
                            Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                            target.setMarkStatus(false);
                            System.out.println(breakLine);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target.description);
                            System.out.println(breakLine);
                    } catch (ChattyBuddyException e) {
                        System.out.println(breakLine);
                        System.out.println("Oh no! No such task is found!");
                        System.out.println(breakLine);
                    }

                    // users enters "todo xxx"
                } else if (slicedStr[0].equals("todo")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new ChattyBuddyException();
                        }
                        Todo newTodo = new Todo();
                        newTodo.convertStringToTask(slicedStr);
                        inputList.add(newTodo);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newTodo.type, newTodo.getStatusIcon(), newTodo.description);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (ChattyBuddyException e) {
                        System.out.println(breakLine);
                        System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(breakLine);
                    }


                    // users enters "deadline xxx"
                } else if (slicedStr[0].equals("deadline")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new ChattyBuddyException();
                        }
                        Deadline newDeadline = new Deadline();
                        newDeadline.convertStringToTask(slicedStr);
                        inputList.add(newDeadline);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newDeadline.type, newDeadline.getStatusIcon(), newDeadline.description);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (ChattyBuddyException e) {
                        System.out.println(breakLine);
                        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(breakLine);
                    }


                    // user enters "event xxx"
                } else if (slicedStr[0].equals("event")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new ChattyBuddyException();
                        }
                        Event newEvent = new Event();
                        newEvent.convertStringToTask(slicedStr);
                        inputList.add(newEvent);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newEvent.type, newEvent.getStatusIcon(), newEvent.description);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (ChattyBuddyException e) {
                        System.out.println(breakLine);
                        System.out.println("OOPS!!! The description of an event cannot be empty.");
                        System.out.println(breakLine);
                    }

                    // user enters otherwise
                } else {
                    throw new ChattyBuddyException();
                }
            } catch (ChattyBuddyException e) {
                System.out.println(breakLine);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(breakLine);
            }
            response = userInput.nextLine();
            slicedStr = response.split(" ");
        }
        System.out.println(breakLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(breakLine);
    }
}
