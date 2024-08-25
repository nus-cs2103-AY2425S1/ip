import java.util.Scanner;
import java.util.ArrayList;

public class Glados {
    private static final String HORIZONTAL_LINE = "\n" 
        + "-----------------------------------------------------------------------------\n";
    private static final String LOGO = "\n"
            + " ░▒▓██████▓▒░░▒▓█▓▒░       ░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \n"
            + "░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░      ░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + "░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░ \n"
            + " ░▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  \n"
            + "\n";
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else {
                try {
                    String query = input.split(" ")[0];
                    switch (query) {
                    case "echo":
                        echo(input.substring(4, input.length()));
                        break;
                    case "todo":
                        add(TaskType.TODO, input.substring(4, input.length()));
                        break;
                    case "deadline":
                        add(TaskType.DEADLINE, input.substring(8, input.length()));
                        break;
                    case "event":
                        add(TaskType.EVENT, input.substring(5, input.length()));
                        break;
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(Integer.valueOf(input.substring(5, input.length())));
                        break;
                    case "unmark":
                        unmark(Integer.valueOf(input.substring(7, input.length())));
                        break;
                    case "delete":
                        delete(Integer.valueOf(input.substring(7, input.length())));
                        break;
                    default:
                        throw new CommandNotFoundException();
                    
                    }
                } catch (GladosException e) {
                    error(e);
                }
            }
        }
        sc.close();
    }

    public static void greet() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nHello, welcome to the Aperture Science computer-aided enrichment center! My name is:\n"
            + LOGO
            + "What task would you like me to perform today?\n"
            + HORIZONTAL_LINE
        );
    }

    public static void echo(String input) {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: " + input.trim() + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void error(GladosException e) {
        System.out.println(
            HORIZONTAL_LINE
            + "\n" + e.getMessage() + "\n"
            + HORIZONTAL_LINE
        );
    }

    public static void add(TaskType taskType, String input) throws GladosException {
        String[] res = taskManager.add(taskType, input);
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have added the following task to the list...\n"
            + "\n" + res[0] + "\n"
            + "\nNow you have " + res[1] + (Integer.parseInt(res[1]) == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }

    public static void delete(int index) throws TaskNotFoundException{
        String[] res = taskManager.delete(index);

        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I have removed the following task from the list...\n"
            + "\n" + res[0] + "\n"
            + "\nNow you have " + res[1] + (Integer.parseInt(res[1]) == 1 ? " task.\n" : " tasks.\n")
            + HORIZONTAL_LINE
        );
    }

    public static void list() {
        ArrayList<Task> res = taskManager.list();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("GLaDOS: Here is the list...\n");
        for (int i = 0; i < res.size(); i++) {
            System.out.println(i + 1 + ". " + res.get(i).toString()); 
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(int index) {
        String res = taskManager.mark(index);
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: I've marked this task as done...\n"
            + "\n" + res + "\n"
            + HORIZONTAL_LINE);
    }

    public static void unmark(int index) {
        String res = taskManager.unmark(index);

        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Oops, looks like this task is no longer done...\n"
            + "\n" + res + "\n"
            + HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(
            HORIZONTAL_LINE
            + "\nGLaDOS: Goodbye, user.\n"
            + HORIZONTAL_LINE
        );
    }

}
