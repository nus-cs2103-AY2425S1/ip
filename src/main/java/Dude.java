import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Dude class is a chatbot, whose name is Dude, that can help you manage your tasks.
 */
public class Dude {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter initFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    /**
     * The TaskType enum controls the different types of tasks that the user can add.
     */
    public enum TaskType {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

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
            try {
                taskList.add(new Deadline(arr[0], LocalDateTime.parse(arr[1], formatter)));
            } catch (Exception e) {
                throw new DudeException("The format of the deadline is wrong! Try using the format: yyyy-mm-dd HH:mm");
            }
        } else if (input.startsWith(TaskType.EVENT.getType() + " ")) {
            String[] arr = input.substring(6).split(" /");
            if (arr[0].isEmpty()) {
                throw new DudeException("The description of an event cannot be empty!");
            } else if (arr.length < 3 || !arr[1].startsWith("from ") || !arr[2].startsWith("to ")) {
                throw new DudeException("The format of timings of the event is wrong!");
            }
            try {
                taskList.add(new Event(arr[0], LocalDateTime.parse(arr[1].substring(5), formatter),
                            LocalDateTime.parse(arr[2].substring(3), formatter)));
            } catch (Exception e) {
                throw new DudeException("The format of the timings is wrong! Try using the format: yyyy-mm-dd HH:mm");
            }
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

    private static void saveTasks() {
        try {
            File file = new File("data.txt");
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    private static void initTasks(String data) {
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] arr = line.split("]");
            String type = arr[0].substring(1);
            boolean markedDone = arr[1].substring(1).equals("X");
            switch(type) {
            case "T":
                taskList.add(new Todo(arr[2].substring(1)));
                if (markedDone) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
                break;
            case "D":
                String[] deadlineArr = arr[2].split(" \\(by: ");
                taskList.add(new Deadline(deadlineArr[0].substring(1),
                            LocalDateTime.parse(deadlineArr[1].substring(0, deadlineArr[1].length() - 1), initFormatter)));
                if (markedDone) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
                break;
            case "E":
                String[] eventArr = arr[2].split(" \\(from: ");
                String[] eventArrDetails = eventArr[1].split(" to: ");
                taskList.add(new Event(eventArr[0].substring(1),
                        LocalDateTime.parse(eventArrDetails[0], initFormatter),
                        LocalDateTime.parse(eventArrDetails[1].substring(0, eventArrDetails[1].length() - 1),
                                    initFormatter)));
                if (markedDone) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
                break;
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
        try {
            File file = new File("data.txt");
            Scanner reader = new Scanner(file);
            StringBuilder data = new StringBuilder();
            while (reader.hasNextLine()) {
                data.append(reader.nextLine()).append("\n");
            }
            reader.close();
            if (data.isEmpty()) {
                throw new IOException();
            }
            initTasks(data.toString());
        } catch (IOException e) {
            System.out.println("Since you have no save files, you are starting with an empty list!");
        }

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
        saveTasks();
    }
}
