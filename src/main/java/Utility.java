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
        if (message.isBlank() ||
                message.equalsIgnoreCase("Hi") ||
                message.equalsIgnoreCase("Hello") ||
                message.equalsIgnoreCase("Ciallo")) {
            System.out.println("Ciallo～(∠・ω< )⌒★");
            return;
        }
        if (taskType.equals("delete")) {
            try {
                int taskIndex = divideMessage(message)[1].charAt(0) - 49;
                System.out.println("Noted. I've removed this task:\n" + toDo.get(taskIndex) +
                        "\nNow you have " + toDo.size() + " tasks in the list.\n");
                toDo.remove(taskIndex);
                return;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("The task you want to delete is not in task list, please try again.");
                return;
            }
        }
        if (taskType.equals("list")) {
            if (toDo.isEmpty()) {
                System.out.println("You don't have any task now ~(∠・ω< )⌒★");
                return;
            }
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= toDo.size(); i++) {
                System.out.println(i + "." + toDo.get(i - 1));
            }
            return;
        }
        if (taskType.equals("mark")) {
            try {
                int taskIndex = divideMessage(message)[1].charAt(0) - 49;
                toDo.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + toDo.get(taskIndex));
                return;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("The task you want to mark is not in task list, please try again.");
                return;
            }

        }
        if (taskType.equals("unmark")) {
            try {
                int taskIndex = divideMessage(message)[1].charAt(0) - 49;
                toDo.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + toDo.get(taskIndex));
                return;
            } catch(IndexOutOfBoundsException e) {
                System.out.println("The task you want to unmark is not in task list, please try again.");
                return;
            }
        }
        if (taskType.equals("todo")) {
            try {
                String taskInfo = splitTaskInfo(message)[1];
                TodoTask task = new TodoTask(taskInfo);
                toDo.add(task);
                System.out.print("Got it. I've added this task:\n  " +
                        task.toString() +
                        "\nNow you have " + toDo.size() + " tasks in the list.\n");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the todo task cannot be empty, please try again.");
                return;
            }
        }
        if (taskType.equals("deadline")) {
            try {
                String taskInfo = splitTaskInfo(message)[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the deadline task cannot be empty, please try again.");
                return;
            }
            String taskInfo = splitTaskInfo(message)[1];
            try {
                DeadlineTask task = new DeadlineTask(taskInfo);
                toDo.add(task);
                System.out.print("Got it. I've added this task:\n  " +
                        task.toString() +
                        "\nNow you have " + toDo.size() + " tasks in the list.\n");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("The description of the deadline task must include the ddl time, please try again.\n" +
                        "Tips: use /by to enter the ddl time.\n e.g. deadline quiz1 /by today 23:59\n");
                return;
            }
        }
        if (taskType.equals("event")) {
            try {
                String taskInfo = splitTaskInfo(message)[1];
            } catch (IndexOutOfBoundsException e) {
                System.out.println("The description of the event task cannot be empty, please try again.");
                return;
            }
            String taskInfo = splitTaskInfo(message)[1];
            try {
                EventTask task = new EventTask(taskInfo);
                toDo.add(task);
                System.out.print("Got it. I've added this task:\n  " +
                        task.toString() +
                        "\nNow you have " + toDo.size() + " tasks in the list.\n");
                return;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("The description of the event task must include" +
                        " the start time and the end time, please try again.\n" +
                        "Tips: use /from and /to to enter the start and end time.\n" +
                        " e.g. event meeting1 /from today 12:00 /to 14:00\n");
                return;
            }
        }

        System.out.println("Sorry to say that I don't know what does \"" + message + "\" means. Anyway, have a good day :)");
    }
}
