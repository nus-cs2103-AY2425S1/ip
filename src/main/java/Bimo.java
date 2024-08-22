import java.util.Scanner;
public class Bimo {
    public static String name = "Bimo";
    public static String line = "    " + "___________________________________";
    public static Scanner scanner = new Scanner(System.in);
    public static int length = 0;
    public static Task[] list = new Task[100];
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("    " + String.format("Hello! I'm %s", name));
        System.out.println("    " + "What can I do for you?");
        System.out.println(line);
        System.out.println();
        String command = scanner.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            System.out.println(line);
            String action = command.split(" ")[0].toLowerCase();
            if (action.equals("list") && length > 0) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    String message = String.format("    %d. %s", i + 1, list[i].toString());
                    System.out.println(message);
                }

            } else if (action.equals("list")) {
                System.out.println("    No tasks added");

            } else if (action.equals("mark")) {
                int index = Integer.valueOf(command.toLowerCase().split(" ")[1]) - 1;
                updateList(true, index);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("       " + list[index].toString());

            } else if (action.equals("unmark")) {
                int index = Integer.valueOf(command.toLowerCase().split(" ")[1]) - 1;
                updateList(false, index);
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("       " + list[index].toString());

            } else if (action.equals("todo") ||
                        action.equals("deadline") ||
                        action.equals("event") ) {
                System.out.println("    Got it. I've added this task:");
                if (action.equals("todo")) {
                    Task task = new ToDo(getDetails(command));
                    Bimo.list[length] = task;
                    length += 1;
                    System.out.println("        "+ task.toString());
                } else if (action.equals("deadline")) {
                    String[] temp = command.split("/");
                    String dueDate = processDate(temp[1]);
                    String details = getDetails(temp[0]);
                    Task task = new Deadline(details, dueDate);
                    Bimo.list[length] = task;
                    length += 1;
                    System.out.println("        "+ task.toString());
                } else {

                }
                String word = length == 1 ? "task" : "tasks";
                System.out.println(String.format("    Now you have %d %s in the list.", length, word));
            } else {
                System.out.println("    Invalid command");
            }
            System.out.println(line + "\n");
            command = scanner.nextLine();
        }

        System.out.println("    " +"Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void updateList(boolean complete, int index) {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException();
        } else if(complete) {
            list[index].markCompleted();
        } else {
            list[index].markUnCompleted();
        }
    }

    public static String getDetails(String mixed) {
        String[] temp = mixed.split(" ");
        temp[0] = "";
        return String.join(" ", temp);
    }

    public static String processDate(String invalid) {
        String[] temp = invalid.split(" ");
        temp[0] = "";
        return String.join(" ", temp);
    }

}
