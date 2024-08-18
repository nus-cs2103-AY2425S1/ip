import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    private static final String BYE = "bye";
    private static String Logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> allTasks = new ArrayList<>();

    public static void dashline() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Send  greet message to users
     */
    public static void greet() {
        System.out.println("Hello from\n" + Logo);
        dashline();
        System.out.println("Hello! I'm EchoBot");
        System.out.println("What can I do for you?");
        dashline();
    }

    /**
     * List all tasks in the task list of EchoBot
     */
    public static void listAllTask() {
        dashline();
        String tasks = "";
        for (int i = 0; i < allTasks.size(); i++) {
            tasks += (i + 1) + ". " + allTasks.get(i).toString();
            tasks += (i == allTasks.size() - 1) ? "" : "\n";
        }
        System.out.println(tasks);
        dashline();
    }

    public static void deleteTask(int deleteIdx) {
        dashline();
        System.out.println("Noted. I've removed this task:");
        System.out.println(allTasks.get(deleteIdx));
        allTasks.remove(deleteIdx);
        System.out.println("Now you have " + allTasks.size() +" tasks in the list.");
        dashline();
    }


    public static void bye() {
        dashline();
        System.out.println("Bye. Hope to see you again soon!");
        dashline();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EchoBot.greet();
        while (true) {
            String userInput = scanner.nextLine();
            String[] cmdParts = userInput.split(" ");
            String CMD = cmdParts[0].toUpperCase();
            try {
                switch (Command.valueOf(CMD)) {
                    case BYE:
                        EchoBot.bye();
                        return;
                    case LIST:
                        EchoBot.listAllTask();
                        break;
                    case MARK:
                        dashline();
                        int markIdx = Integer.parseInt(cmdParts[1]) - 1;
                        Task markTask = allTasks.get(markIdx);
                        markTask.mark();
                        dashline();
                        break;
                    case UNMARK:
                        dashline();
                        int unmarkIdx = Integer.parseInt(cmdParts[1]) - 1;
                        Task unmarkTask = allTasks.get(unmarkIdx);
                        unmarkTask.unmark();
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
            } catch (IndexOutOfBoundsException e) {
                String msg = "Input Error: " + e.getMessage();
                System.out.println(msg);
                System.exit(0);
            } catch (ClassCastException e) {
                String msg = "Input Error: " + e.getMessage();
                System.out.println(msg);
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
