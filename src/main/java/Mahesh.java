import java.lang.IndexOutOfBoundsException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class Mahesh {

    /**
     * The logo to be displayed when the application starts.
     */
    private static String LOGO = 
      "#     #                                       ######                               ###### \n"
    + "##   ##   ##   #    # ######  ####  #    #    #     #   ##   #      #              #      \n"
    + "# # # #  #  #  #    # #      #      #    #    #     #  #  #  #      #              #      \n"
    + "#  #  # #    # ###### #####   ####  ######    #     # #    # #      #      #####   #####  \n"
    + "#     # ###### #    # #           # #    #    #     # ###### #      #              #      \n"
    + "#     # #    # #    # #      #    # #    #    #     # #    # #      #              #      \n"
    + "#     # #    # #    # ######  ####  #    #    ######  #    # ###### ######         ###### \n";

    /**
     * Divider line used for separating sections in the console output.
     */
    private static String DIVIDER = "-------------------------------------------------------";

    /**
     * Error message for incomplete or incorrect commands.
     */
    private static String INCOMPLETE_COMMAND_ERR = "The command is incomplete/incorrect. Please refer to the below usage for this command.";

    /**
     * List to store tasks.
     */
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * Counter to keep track of the number of tasks.
     */
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
            String commandString = tokenizedInput.nextToken();
            Task task;
            try {
                Command command = Command.fromString(commandString);
                switch (command) {
                    case LIST:
                        try {
                            Mahesh.printList();
                        } catch (MaheshException err) {
                            System.out.println(err.getMessage());
                        }
                        break;
                    case BYE:
                        exit = true;
                        break;
                    case MARK:
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
                    case UNMARK:
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
                    case TODO:
                        try {
                            Mahesh.addToList(Todo.parseTodo(tokenizedInput));
                        } catch (NoSuchElementException err) {
                            System.out.println(INCOMPLETE_COMMAND_ERR);
                            System.out.println("todo task_description");
                        }
                        break;
                    case DEADLINE:
                        try { 
                            Mahesh.addToList(Deadline.parseDeadline(tokenizedInput));
                        } catch (NoSuchElementException err) {
                            System.out.println(INCOMPLETE_COMMAND_ERR);
                            System.out.println("deadline task_description /by task_deadline");
                        }
                        break;
                    case EVENT: 
                        try {
                            Mahesh.addToList((Event.parseEvent(tokenizedInput)));
                        } catch (NoSuchElementException err) {
                            System.out.println(INCOMPLETE_COMMAND_ERR);
                            System.out.println("event task_description /from event_start /to event_end");
                        }
                        break;
                    case DELETE:
                        try {
                            Mahesh.deleteFromList(Integer.parseInt(tokenizedInput.nextToken()) - 1);
                        } catch (IndexOutOfBoundsException err) {
                            System.out.println("There is no such task. You currently have " + Mahesh.taskCount + " tasks.");
                            System.out.println("Use the \"list\" command to view all your tasks.");
                        }
                        break;
                    }
                } catch (MaheshException err) {
                    System.out.println(err.getMessage());
                }
            System.out.println(DIVIDER);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(DIVIDER);
        scan.close();
    }

    /**
     * Adds a task to the list and increments the task count.
     *
     * @param task The task to be added to the list.
     */
    private static void addToList(Task task) {
        Mahesh.list.add(task);
        Mahesh.taskCount++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Mahesh.taskCount + " tasks in the list.");
    }
    
    /**
     * Deletes a task from the list at the specified index and decrements the task count.
     *
     * @param index The index of the task to be removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    private static void deleteFromList(int index) throws IndexOutOfBoundsException {
        Task task = list.get(index);
        Mahesh.list.remove(index);
        Mahesh.taskCount--;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Mahesh.taskCount + " tasks in the list.");
    }
    
    /**
     * Prints all tasks in the list. Throws an exception if the list is empty.
     *
     * @throws MaheshException If there are no tasks in the list.
     */
    private static void printList() throws MaheshException {
        if (Mahesh.taskCount == 0) {
            throw new MaheshException("You have no tasks! Add a few tasks (todo, deadline or event)");
        }
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }
}
