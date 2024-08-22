import java.util.Scanner;
import java.util.ArrayList;

public class Cypher {
    private static ArrayList<Task> taskList;
    private static void lineBreak() {
        System.out.println("-------------------------------------------------------");
    }

    private static void greet() {
        Cypher.lineBreak();
        System.out.println("Hello! I am Cypher \nWhat can I do for you!\n");
        Cypher.lineBreak();
    }

    private static void goodBye() {
        System.out.println("Bye. Hope to see you again soon!");
        Cypher.lineBreak();
    }

    private static void addToList(String description) {
        Task task = new Task(description);
        taskList.add(task);
        Cypher.lineBreak();
        System.out.println("Added: " + description);
        Cypher.lineBreak();
    }

    private static void printTaskList() {
        Cypher.lineBreak();
        System.out.println("Here are the items in your list: ");
        for (int i = 0; i < Cypher.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + Cypher.taskList.get(i));
        }
        Cypher.lineBreak();
    }

    private static void markTask(int i) {
        Task task = Cypher.taskList.get(i);
        task.completeTask();
        Cypher.lineBreak();
        System.out.println("Nice! I have marked this task as completed: \n" + task);
        Cypher.lineBreak();
    }

   private static void unmarkTask(int i) {
       Task task = Cypher.taskList.get(i);
       task.incompleteTask();
       Cypher.lineBreak();
       System.out.println("Ok! I have marked this task as incomplete: \n" + task);
       Cypher.lineBreak();
   }
    public static void main(String[] args) {
        Cypher.greet();
        Cypher.taskList = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your command: ");
            String input = scanner.nextLine();
            String[] command = input.split(" ");

            // Need to use switch cases
            if (command[0].equalsIgnoreCase("list")) {
                Cypher.printTaskList();
            } else if (command[0].equalsIgnoreCase("mark")) {
                Cypher.markTask(Integer.parseInt(command[1]) - 1);
            }else if (command[0].equalsIgnoreCase("unmark")) {
                Cypher.unmarkTask(Integer.parseInt(command[1]) -1);
            }else if (!command[0].equalsIgnoreCase("bye")) {
                Cypher.addToList(String.join(" ",command));
            } else {
                Cypher.lineBreak();
                break;
            }
        }

        Cypher.goodBye();
    }
}
