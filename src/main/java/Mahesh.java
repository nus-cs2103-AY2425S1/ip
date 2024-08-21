import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class Mahesh {

    private static String LOGO = 
    "#     #                                       ######                               ###### \n"
  + "##   ##   ##   #    # ######  ####  #    #    #     #   ##   #      #              #      \n"
  + "# # # #  #  #  #    # #      #      #    #    #     #  #  #  #      #              #      \n"
  + "#  #  # #    # ###### #####   ####  ######    #     # #    # #      #      #####   #####  \n"
  + "#     # ###### #    # #           # #    #    #     # ###### #      #              #      \n"
  + "#     # #    # #    # #      #    # #    #    #     # #    # #      #              #      \n"
  + "#     # #    # #    # ######  ####  #    #    ######  #    # ###### ######         ###### \n";
    private static String DIVIDER = "-------------------------------------------------------";
    private static String INCOMPLETE_COMMAND_ERR = "The command is incomplete/incorrect. Please refer to the below usage for this command.";
    private static ArrayList<Task> list = new ArrayList<>();
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Mahesh Dall-E [but you can call me Mahesh ;)]");
        System.out.println("What can I do for you?\n");
        System.out.println(DIVIDER);
        
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
            System.out.println(DIVIDER);
            String command = tokenizedInput.nextToken();
            Task task;
            switch (command) {
                case "list":
                    try {
                        Mahesh.printList();
                    } catch (MaheshException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case "bye":
                    exit = true;
                    break;
                case "mark":
                    try {
                        task = list.get(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + task);
                    } catch (IndexOutOfBoundsException err) {
                        System.out.println("There is no such task. You currently have " + Mahesh.taskCount + " tasks.");
                        System.out.println("Use the \"list\" command to view all your tasks.");
                    }
                    break;
                case "unmark":
                    try {
                        task = list.get(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                        task.unmarkAsDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + task);
                    } catch (IndexOutOfBoundsException err) {
                        System.out.println("There is no such task. You currently have " + Mahesh.taskCount + " tasks.");
                        System.out.println("Use the \"list\" command to view all your tasks.");
                    }
                    break;
                case "todo":
                    try {
                        Mahesh.addToList(Mahesh.parseTodo(tokenizedInput));
                    } catch (NoSuchElementException err) {
                        System.out.println(INCOMPLETE_COMMAND_ERR);
                        System.out.println("todo task_description");
                    }
                    break;
                case "deadline":
                    try { 
                        Mahesh.addToList(Mahesh.parseDeadline(tokenizedInput));
                    } catch (NoSuchElementException err) {
                        System.out.println(INCOMPLETE_COMMAND_ERR);
                        System.out.println("deadline task_description /by task_deadline");
                    }
                    break;
                case "event": 
                    try {
                        Mahesh.addToList((Mahesh.parseEvent(tokenizedInput)));
                    } catch (NoSuchElementException err) {
                        System.out.println(INCOMPLETE_COMMAND_ERR);
                        System.out.println("event task_description /from event_start /to event_end");
                    }
                    break;
                case "delete":
                    try {
                        Mahesh.deleteFromList(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                    } catch (IndexOutOfBoundsException err) {
                        System.out.println("There is no such task. You currently have " + Mahesh.taskCount + " tasks.");
                        System.out.println("Use the \"list\" command to view all your tasks.");
                    }
                    break;
                default:
                    System.out.println("That is not a valid command. Use the \"bye\" command if you wish to exit the bot.");
                    break;
            }
            System.out.println(DIVIDER);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(DIVIDER);
        scan.close();
    }

    private static void addToList(Task task) {
        Mahesh.list.add(task);
        Mahesh.taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Mahesh.taskCount + " tasks in the list.");
    }

    private static void deleteFromList(int index) throws IndexOutOfBoundsException {
        Task task = list.get(index);
        Mahesh.list.remove(index);
        Mahesh.taskCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Mahesh.taskCount + " tasks in the list.");
    }

    private static void printList() throws MaheshException {
        if (Mahesh.taskCount == 0) {
            throw new MaheshException("You have no tasks! Add a few tasks (todo, deadine or event)");
        }
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }
}
