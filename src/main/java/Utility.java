import java.util.ArrayList;

public class Utility {

    public static final String LINE = "____________________________________________________________\n";
    protected static ArrayList<Task> toDo = new ArrayList<>();

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

    public static String[] splitTaskInfo(String message) {
        return message.trim().split("\\s+", 2);
    }

    public static void handleMessage(String message) {
        String taskType = splitTaskInfo(message)[0];
        if (taskType.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= toDo.size(); i++) {
                System.out.println(i + "." + toDo.get(i - 1));
            }
            return ;
        }
        if (taskType.equals("mark")) {
            int taskIndex = divideMessage(message)[1].charAt(0) - 49;
            toDo.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + toDo.get(taskIndex));
            return ;
        }
        String taskInfo = splitTaskInfo(message)[1];
        if (taskType.equals("unmark")) {
            int taskIndex = divideMessage(message)[1].charAt(0) - 49;
            toDo.get(taskIndex).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + toDo.get(taskIndex));
            return ;
        }
        if (taskType.equals("todo")) {
            TodoTask task = new TodoTask(taskInfo);
            toDo.add(task);
            System.out.print("Got it. I've added this task:\n  " +
                    task.toString() +
                    "\nNow you have " + toDo.size() + " tasks in the list.\n");
        }
        if (taskType.equals("deadline")) {
            DeadlineTask task = new DeadlineTask(taskInfo);
            toDo.add(task);
            System.out.print("Got it. I've added this task:\n  " +
                    task.toString() +
                    "\nNow you have " + toDo.size() + " tasks in the list.\n");
        }
        if (taskType.equals("event")) {
            EventTask task = new EventTask(taskInfo);
            toDo.add(task);
            System.out.print("Got it. I've added this task:\n  " +
                    task.toString() +
                    "\nNow you have " + toDo.size() + " tasks in the list.\n");
        }
    }
}
