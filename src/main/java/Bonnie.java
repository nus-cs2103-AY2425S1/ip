import java.util.Scanner;
import java.util.ArrayList;
import src.main.java.Task;

public class Bonnie {

    // Variables that are
    private static String username;
    private static ArrayList<Task> tasklist = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner scanner_obj = new Scanner(System.in);
        String my_username = scanner_obj.nextLine();
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot!\n", my_username));
        System.out.println("What would you like to do next?\n" +
                "1. bye : leaves the conversation with Bonnie\n" +
                "2. mark/unmark {task number (accepts numerical input only)}: marks or unmarks that task as done\n" +
                "3. any other string : Bonnie adds that task into your task list\n");
        username = my_username;

        while (true) {
            Scanner repeated_scanner = new Scanner(System.in);
            String input = repeated_scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye " + username + "\n");
                return;
            } else if (input.equals("list")) {
                String list = "Your current tasks are\n";
                for (int i = 1; i <= tasklist.size(); i++) {
                    list += String.format("%d. %s\n", i, tasklist.get(i - 1));
                }
                System.out.println(list);
            } else if (check_mark_command(input)) {
                String[] arr = input.split(" ", 2);
                Integer taskNum = Integer.valueOf(arr[1]);
                if (arr[0].equals("mark")) {
                    // Mark task "taskNum" as done
                    // Note that the actual task is off by -1 in the list!
                    tasklist.get(taskNum - 1).markAsDone();
                } else if (arr[0].equals("unmark")) {
                    // Mark task "taskNum" as not done
                    tasklist.get(taskNum - 1).unmarkAsDone();
                }
            } else {
                // Want to add task to task_list
                tasklist.add(new Task(input));
                System.out.println(String.format("Hey %s, I have added \"%s\" into your task list!\n", username, input));
            }
        }
    }

    public static boolean check_mark_command(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum < tasklist.size() && taskNum >= 0) {
                    return true;
                } else {
                    System.out.println("You might have tried to mark or unmark a task that does not exist.\n" +
                                       "If so, please delete this wrongly added task using the delete command.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

}
