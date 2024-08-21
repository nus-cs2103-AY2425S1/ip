import java.util.Scanner;
import java.util.ArrayList;

public class Molly {
    public static String name = "Molly";

    public Molly() {

    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + Molly.name);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void listToString(ArrayList<Task> botMemory) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < botMemory.size(); i++) {
            System.out.println((i + 1) + ". " + botMemory.get(i).toString());
        }
    }

    public static void assistUser() {
        ArrayList<Task> botMemory = new ArrayList<>();
        Scanner botScanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        String userInput = botScanner.nextLine();
        while (!userInput.toLowerCase().equals("bye")) {
            if (userInput.substring(0, userInput.length() - 2).equals("mark") || userInput.substring(0, userInput.length() - 2).equals("unmark")) {
                botMemory.get(Integer.parseInt(userInput.substring(userInput.length() - 1)) - 1).toggleTaskDone();
            }
             else if (!userInput.toLowerCase().equals("list")) {
                if (userInput.startsWith("todo")) {
                    if (userInput.equals("todo")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Yikes! Sorry, the description of a todo cannot be empty.");
                        System.out.println("____________________________________________________________");
                    } else {
                        Task newToDo = new Task(userInput);
                        botMemory.add(newToDo);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(" " + newToDo.toString());
                        System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.equals("deadline")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Yikes! Sorry, the description of a deadline cannot be empty.");
                        System.out.println("____________________________________________________________");
                    } else {
                        Deadline newDeadline = new Deadline(userInput.substring(9, userInput.indexOf("/") - 1), userInput.substring(userInput.indexOf("/") + 1));
                        botMemory.add(newDeadline);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(" " + newDeadline.toString());
                        System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                    } else if (userInput.startsWith("event")) {
                    if (userInput.equals("event")) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Yikes! Sorry, the description of an event cannot be empty.");
                        System.out.println("____________________________________________________________");
                    } else {
                        Event newEvent = new Event(userInput.substring(6, userInput.indexOf("/") - 1),
                                userInput.substring(userInput.indexOf("/") + 6, userInput.lastIndexOf("/") - 1),
                                userInput.substring(userInput.lastIndexOf("/") + 4));
                        botMemory.add(newEvent);
                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(" " + newEvent.toString());
                        System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    System.out.println("I'm sorry, I do not know how to respond to that. Please enter commands allowed by the software.");
                }

            } else {
                System.out.println("____________________________________________________________");
                Molly.listToString(botMemory);
                System.out.println("____________________________________________________________");
            }
            userInput = botScanner.nextLine();
        }
        Molly.sayBye();

    }


    public static void main(String[] args) {
        Molly.greetUser();
        Molly.assistUser();
    }
}
