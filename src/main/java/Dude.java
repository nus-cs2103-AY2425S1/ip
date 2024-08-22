import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Dude class is a chatbot, whose name is Dude, that can help you manage your tasks.
 */
public class Dude {

    /**
     * The taskList is an ArrayList that stores all the tasks that the user has added.
     */
    private static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * The TaskType enum controls the different types of tasks that the user can add.
     */
    public enum TaskType {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        /**
         * The type is a String that represents the type of task.
         */
        private final String type;

        /**
         * The TaskType constructor creates a new TaskType object.
         *
         * @param type The type of task.
         */
        TaskType(String type) {
            this.type = type;
        }

        /**
         * The getType method returns the type of task.
         *
         * @return The type of task.
         */
        public String getType() {
            return this.type;
        }
    }

    /**
     * The addTask method adds a task to the taskList.
     *
     * @param input The user's input.
     * @throws DudeException If the user's input is invalid.
     */
    public static void addTask(String input) throws DudeException{
        if (input.startsWith(TaskType.TODO.getType() + " ")) {
            if (input.substring(5).isEmpty()) {
                throw new DudeException("The description of a todo cannot be empty!");
            }
            taskList.add(new Todo(input.substring(5)));
        } else if (input.startsWith(TaskType.DEADLINE.getType() + " ")) {
            String[] arr = input.substring(9).split(" /by ");
            if (arr[0].isEmpty()) {
                throw new DudeException("The description of a deadline cannot be empty!");
            } else if (arr.length < 2) {
                throw new DudeException("The deadline of a deadline cannot be empty!");
            }
            taskList.add(new Deadline(arr[0], arr[1]));
        } else if (input.startsWith(TaskType.EVENT.getType() + " ")) {
            String[] arr = input.substring(6).split(" /");
            if (arr[0].isEmpty()) {
                throw new DudeException("The description of an event cannot be empty!");
            } else if (arr.length < 3 || !arr[1].startsWith("from ") || !arr[2].startsWith("to ")) {
                throw new DudeException("The format of timings of the event is wrong!");
            }
            taskList.add(new Event(arr[0], arr[1].substring(5), arr[2].substring(3)));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * The deleteTask method deletes a task from the taskList.
     *
     * @param index The index of the task to be deleted.
     * @throws DudeException If the task does not exist.
     */
    public static void deleteTask(int index) throws DudeException {
        if (index > taskList.size() || index < 1) {
            throw new DudeException("This task does not exist!");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.get(index - 1));
            taskList.remove(index - 1);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    /**
     * The markTaskAsDone method marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws DudeException If the task does not exist or is already marked as done.
     */
    public static void markTaskAsDone(int index) throws DudeException {
        if (index > taskList.size() || index < 1) {
            throw new DudeException("This task does not exist!");
        } else if (taskList.get(index - 1).isDone) {
            throw new DudeException("This task is already marked as done!");
        } else {
            taskList.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.get(index - 1));
        }
    }

    /**
     * The markTaskAsUndone method marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws DudeException If the task does not exist or is already marked as not done.
     */
    public static void markTaskAsUndone(int index) throws DudeException {
        if (index > taskList.size() || index < 1) {
            throw new DudeException("This task does not exist!");
        } else if (!taskList.get(index - 1).isDone) {
            throw new DudeException("This task is already marked as not done!");
        } else {
            taskList.get(index - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(taskList.get(index - 1));
        }
    }

    /**
     * The printList method prints all the tasks in the taskList.
     * If the taskList is empty, it will print a message saying that there are no tasks in the list.
     */
    public static void printList() {
        if (taskList.isEmpty()) {
            System.out.println("There are no tasks in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i));
            }
        }
    }

    /**
     * The action method performs an action based on the user's input.
     * Supported actions include adding a task,
     * deleting a task, marking a task as done, marking a task as not done, and listing all tasks.
     *
     * @param input The user's input.
     * @throws DudeException If the user's input is invalid.
     */
    public static void action(String input) throws DudeException {
        if (input.startsWith("mark ")) {
            try {
                markTaskAsDone(Integer.parseInt(input.substring(5)));
            } catch (NumberFormatException e) {
                throw new DudeException("Please enter a valid number!");
            }
        } else if (input.startsWith("unmark ")) {
            try {
                markTaskAsUndone(Integer.parseInt(input.substring(7)));
            } catch (NumberFormatException e) {
                throw new DudeException("Please enter a valid number!");
            }
        } else if (input.startsWith("delete ")) {
            try {
                deleteTask(Integer.parseInt(input.substring(7)));
            } catch (NumberFormatException e) {
                throw new DudeException("Please enter a valid number!");
            }
        } else if (input.equals("list")) {
            printList();
        } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
            addTask(input);
        } else {
            throw new DudeException("I'm sorry, but I don't know what that means :(");
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Dude!\nWhat can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line);
            try {
                action(input);
            } catch (DudeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again!");
        System.out.println(line);
    }
}
