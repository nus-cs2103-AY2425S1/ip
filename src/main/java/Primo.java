import java.util.ArrayList;
import java.util.Scanner;

public class Primo {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean ended = false;
    private static ArrayList<Task> list = new ArrayList<>();

    private static void printList() {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            String output = String.valueOf(i + 1) + "." + list.get(i);
            System.out.println(output);
        }
    }

    private static void sayBye() {
        String byeMessage = "\nEl Primo:\n" +
                            "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
        ended = true;
    }

    public static void assessInput(String input) throws PrimoException{
        String[] words = input.split(" ");
        if (words[0].equals("bye")) {
            sayBye();
        } else if (words[0].equals("list")) {
            System.out.println("\nEl Primo:");
            System.out.println("Here are the tasks in your list:");
            printList();
        } else if (words[0].equals("mark")) {
            try {
                Integer.valueOf(words[1]);
            } catch (NumberFormatException e) {
                throw new PrimoException("mark <integer> expected");
            }
            int index = Integer.valueOf(words[1]) - 1;
            if (index >= list.size()) {
                throw new PrimoException("You don't have that many tasks!");
            }
            list.get(index).markAsDone();
            System.out.println("\nEl Primo:");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index));
        } else if (words[0].equals("unmark")) {
            try {
                Integer.valueOf(words[1]);
            } catch (NumberFormatException e) {
                throw new PrimoException("unmark <integer> expected");
            }
            int index = Integer.valueOf(words[1]) - 1;
            if (index >= list.size()) {
                throw new PrimoException("You don't have that many tasks!");
            }
            list.get(index).markAsUndone();
            System.out.println("\nEl Primo:");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list.get(index));
        } else if (words[0].equals("todo")) {
            int fromIndex = input.indexOf("todo ") + 5;
            String description = input.substring(fromIndex);
            if (description.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected: todo <string>");
            }
            Task newTask = new ToDoTask(description);
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else if (words[0].equals("deadline")) {
            if (!input.contains("/by")) {
                throw new PrimoException("Invalid parameters! Expected: deadline <string> /by <string>");
            }
            int fromIndex = input.indexOf("deadline ") + 9;
            int toIndex = input.indexOf("/by ");
            String description = input.substring(fromIndex, toIndex).trim();
            if (description.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
            }
            String dueTime = input.substring(toIndex + 4);
            if (dueTime.isEmpty()) {
                throw new PrimoException("deadline time cannot be empty! Expected deadline <string> /by <string>");
            }
            Task newTask = new DeadlineTask(description, dueTime);
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else if (words[0].equals("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new PrimoException("Invalid parameters! Expected: event <string> /from <string> /to <string>");
            }
            int fromIndex = input.indexOf("event ") + 6;
            int toIndex = input.indexOf("/from ");
            int finalIndex = input.indexOf("/to ");
            String description = input.substring(fromIndex, toIndex).trim();
            if (description.isEmpty()) {
                throw new PrimoException("Description cannot be empty! Expected deadline <string> /by <string>");
            }
            String from = input.substring(toIndex + 6, finalIndex).trim();
            if (from.isEmpty()) {
                throw new PrimoException("'From' parameter cannot be empty! Expected deadline <string> /by <string>");
            }
            String to = input.substring(finalIndex + 4);
            Task newTask = new EventTask(description, from, to);
            if (to.isEmpty()) {
                throw new PrimoException("'To' parameter cannot be empty! Expected deadline <string> /by <string>");
            }
            list.add(newTask);
            System.out.println("\nEl Primo:");
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list.%n", list.size());
        } else {
            throw new PrimoException(
                    "Invalid command!\n(Expected Commands: todo, deadline, event, mark, unmark, list, bye)");
        }
    }
    public static void readInput() {
        System.out.println("\nMe:");
        String input = scanner.nextLine();
        try {
            assessInput(input);
        } catch (PrimoException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("""
                El Primo:
                Hello! I'm El Primo!!
                What can I do for you?""");

        while (!ended) {
            readInput();
        }
    }
}
