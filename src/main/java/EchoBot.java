import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    private static final String BYE = "bye";
    private static String Logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> allTasks = Data.getData();

    public static void dashline() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Sends a greet message to the user.
     *
     */
    public static void greet() {
        System.out.println("Hello from\n" + Logo);
        dashline();
        System.out.println("Hello! I'm EchoBot");
        System.out.println("What can I do for you?");
        dashline();
    }

    /**
     * Returns all tasks in the task list of EchoBot.
     *
     */
    public static void listAllTask() {
        dashline();
        String tasks = "";

        // Get task from task list
        for (int i = 0; i < allTasks.size(); i++) {
            tasks += (i + 1) + ". " + allTasks.get(i).toString();
            tasks += (i == allTasks.size() - 1) ? "" : "\n";
        }

        System.out.println(tasks);
        dashline();
    }

    /**
     * Deletes a task from task list.
     * Deletes the target task from task list according to the index
     * and print the current list size.
     *
     * @param deleteIdx index of the task to be deleted.
     * @throws IllegalArgumentException If the index is out of bounds.
     */
    public static void deleteTask(int deleteIdx) throws IllegalArgumentException {
        dashline();
        System.out.println("Noted. I've removed this task:");
        System.out.println(allTasks.get(deleteIdx));

        // Remove the task from list and return the size of list
        allTasks.remove(deleteIdx);
        System.out.println("Now you have " + allTasks.size() +" tasks in the list.");

        dashline();
    }

    /**
     * Sends goodbye message to user and exits.
     *
     */
    public static void bye() {
        dashline();
        System.out.println("Bye. Hope to see you again soon!");
        dashline();
    }

    public static void main(String[] args) {
        // Create a scanner to get user input
        Scanner scanner = new Scanner(System.in);

        // Initialize data.txt
        Data.init();

        // Send greet message to user
        EchoBot.greet();

        // Echos commands entered by the user
        while (true) {
            String userInput = scanner.nextLine();
            String[] cmdParts = userInput.split(" ");
            String CMD = cmdParts[0].toUpperCase();

            try {
                switch (Command.valueOf(CMD)) {
                case BYE:
                    EchoBot.bye();
                    // When the user input "bye", the bot exits
                    return;
                case LIST:
                    EchoBot.listAllTask();
                    break;
                case MARK:
                    dashline();
                    int markIdx = Integer.parseInt(cmdParts[1]) - 1;
                    Task markTask = allTasks.get(markIdx);
                    markTask.setMark();
                    dashline();
                    break;
                case UNMARK:
                    dashline();
                    int unmarkIdx = Integer.parseInt(cmdParts[1]) - 1;
                    Task unmarkTask = allTasks.get(unmarkIdx);
                    unmarkTask.setUnmark();
                    dashline();
                    break;
                case DELETE:
                    int deleteIdx = Integer.parseInt(cmdParts[1]) - 1;
                    EchoBot.deleteTask(deleteIdx);
                    break;
                default:
                    dashline();
                    Task task = Task.creatTask(userInput);
                    allTasks.add(task);
                    System.out.println(task);
                    System.out.println("Now you have " + allTasks.size() +" tasks in the list.");
                    dashline();
                }
                Data.setData(allTasks);
            } catch (IndexOutOfBoundsException e) {
                String msg = "Input Error: " + e.getMessage();
                System.out.println(msg);
                System.exit(0);
            } catch (ClassCastException e) {
                String msg = "Input Error: " + e.getMessage();
                System.out.println(msg);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command entered. " + e.getMessage());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
    }
}
