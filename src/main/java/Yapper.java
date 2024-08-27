import java.util.Scanner;
import java.util.ArrayList;

public class Yapper {
    public static ArrayList<Task> listOfTask = new ArrayList<>();
    public static void main(String[] args) {
        // greeting message
        System.out.println("Hello! I'm Yapper\n" +
                "What can I do for you?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            read(text);
        }
    }

    // reads commands
    public static void read(String command)
    {
        try {
            if (command.equals("bye")) {
                exit();
                System.exit(0);
            } else if (command.equals("list")) {
                returnList();
            } else if (command.startsWith("mark")) {
                mark(command);
            } else if (command.startsWith("unmark")) {
                unmark(command);
            } else if (command.startsWith("todo")) {
                addToDo(command);
            } else if (command.startsWith("deadline")) {
                addDeadline(command);
            } else if (command.startsWith("event")) {
                addEvent(command);
            } else if (command.startsWith("delete")) {
                deleteTask(command);
            } else {
                throw new YapperException("Yapper don't know this command :(");
            }
        } catch (YapperException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String command) throws YapperException
    {
        if (command.length() == 6) { // If command is only "delete"
            throw new YapperException("Task Number cannot be empty!");
        }

        String input = command.substring(7);
        int order = Integer.parseInt(input);

        if (order <= 0) { // If Task Number is less than 1
            throw new YapperException("Task Number cannot be less than 1!");
        } else if (order > listOfTask.size()) { // If Task Number if greater than the size of the list
            throw new YapperException("Task Number cannot be more than size of list!");
        } else {
            Task task = listOfTask.get(order - 1);
            listOfTask.remove(order - 1);
            System.out.println("Noted. I've removed this task: \n" +
                    task + "\nNow you have " + listOfTask.size() + " tasks in the list");;
        }
    }

    public static void addToDo(String command) throws YapperException
    {
        if (command.length() == 4) { // If command is only "todo"
            throw new YapperException("Description for ToDo cannot be empty!");
        }
        String input = command.substring(5);
        ToDo todo = new ToDo(input);
        addTask(todo);
    }

    public static void addDeadline(String command) throws YapperException
    {
        if (command.length() == 8) { // If command is only "deadline"
            throw new YapperException("Description for Deadline cannot be empty!");
        }
        String input = command.substring(9);
        String[] split = input.split("/by ");
        if (split.length == 1) { // If command is deadline not followed /by
            throw new YapperException("Deadline require /by command with Deadline Time");
        } else if (split[0].isEmpty()) { // If command is "deadline /by something"
            throw new YapperException("Deadline Task is empty!");
        } else if (split[1].isEmpty()) { // If command is "deadline something /by"
            throw new YapperException("Deadline Time is empty!");
        } else {
            Deadline deadline = new Deadline(split[0], split[1]);
            addTask(deadline);
        }
    }

    public static void addEvent(String command) throws YapperException
    {
        if (command.length() == 5) { // If command is only "event"
            throw new YapperException("Event Task cannot be empty!");
        }
        String input = command.substring(6);
        String[] split = input.split("/from ");
        String[] split2 = split[1].split("/to ");
        if (split.length == 1) { // If command does not have /from
            throw new YapperException("Event require /from command with Start Time");
        } else if (split2.length == 1) { // If command does not have /to
            throw new YapperException("Event require /to command with End Time");
        } else if (split[0].isEmpty()) { // If command does not have a Task
            throw new YapperException("Event Task is empty!");
        } else if (split2[0].isEmpty()) { // If command does not have a Start Time
            throw new YapperException("Event Start Time is empty!");
        } else if (split2[1].isEmpty()) { // If command does not have an End Time
            throw new YapperException("Event End Time is empty!");
        } else {
            Event event = new Event(split[0], split2[0], split2[1]);
            addTask(event);
        }
    }

    // add a new task into the list
    public static void addTask(Task task)
    {
        listOfTask.add(task);
        System.out.println("Got it. I've added this task:\n" +
                task + "\nNow you have " + listOfTask.size() + " tasks in the list");
    }

    // mark a task as done
    public static void mark(String command) throws YapperException
    {
        if (command.length() == 4) { // If command is only "mark"
            throw new YapperException("Task Number cannot be empty!");
        }

        String input = command.substring(5);
        int order = Integer.parseInt(input);
        if (order <= 0) { // If Task Number is less than 1
            throw new YapperException("Task Number cannot be less than 1!");
        } else if (order > listOfTask.size()) { // If Task Number is greater than size of the list
            throw new YapperException("Task Number cannot be more than size of list!");
        } else {
            Task taskToMark = listOfTask.get(order - 1);
            taskToMark.setDone(true);
            System.out.println("Nice! I've marked this task as done: \n" +
                    taskToMark);
        }
    }

    // umark a task as undone
    public static void unmark(String command) throws YapperException
    {
        if (command.length() == 6) { // If command is only "unmark"
            throw new YapperException("Task Number cannot be empty!");
        }

        String input = command.substring(7);
        int order = Integer.parseInt(input);
        if (order <= 0) { // If Task Number is less than 1
            throw new YapperException("Task Number cannot be less than 1!");
        } else if (order > listOfTask.size()) { // If Task Number is greater than size of the list
            throw new YapperException("Task Number cannot be more than size of list!");
        } else {
            Task taskToUnmark = listOfTask.get(order - 1);
            taskToUnmark.setDone(false);
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    taskToUnmark);
        }
    }

    // return list
    public static void returnList()
    {
        System.out.println("Here are the tasks in your list: ");
        int order = 1;
        for (Task task : listOfTask) {
            System.out.println(order + "." + task);
            order++;
        }
    }

    // exits when the user types the command "bye"
    public static void exit()
    {
        System.out.println("Yapper shall yap next time!");
    }
}
