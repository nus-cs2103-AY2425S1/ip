import java.util.ArrayList;

public class Utility {

    public static final String LINE = "____________________________________________________________\n";
    private static ArrayList<Task> toDo = new ArrayList<>();

    public static void greeting() {
        System.out.print(LINE + " Hello! I'm LuToDo \n" +
                "What can I do for you?\n" +
                 LINE);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addToDo(String s) {
        Task task = new Task(s);
        toDo.add(task);
        System.out.println("added: " + s);
    }

    public static String[] divideMessage(String message) {
        return message.trim().split("\\s+");
    }

    public static void handleMessage(String message) {
        if (message.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= toDo.size(); i++) {
                System.out.println(i + "." + toDo.get(i - 1));
            }
            return ;
        }
        if (divideMessage(message)[0].equals("mark")) {
            int taskIndex = divideMessage(message)[1].charAt(0) - 49;
            toDo.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + toDo.get(taskIndex));
            return ;
        }
        if (divideMessage(message)[0].equals("unmark")) {
            int taskIndex = divideMessage(message)[1].charAt(0) - 49;
            toDo.get(taskIndex).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + toDo.get(taskIndex));
            return ;
        }
        addToDo(message);

    }
}
