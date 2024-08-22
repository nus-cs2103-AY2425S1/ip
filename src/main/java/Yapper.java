import java.util.Scanner;
import java.util.ArrayList;

public class Yapper {
    public static ArrayList<Task> listOfTask = new ArrayList<>();
    public static void main(String[] args) {
        // greeting message
        System.out.println("Hello! I'm Yapper \n" +
                "What can I do for you? \n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            read(text);
        }
    }

    // reads commands
    public static void read(String command)
    {
        if (command.equals("bye"))
        {
            exit();
            System.exit(0);
        }
        else if (command.equals("list"))
        {
            returnList();
        }
        else if (command.startsWith("mark"))
        {
            mark(command);
        }
        else if (command.startsWith("unmark"))
        {
            unmark(command);
        }
        else if (command.startsWith("todo"))
        {
            addToDo(command);
        }
        else if (command.startsWith("deadline"))
        {
            addDeadline(command);
        }
        else if (command.startsWith("event"))
        {
            addEvent(command);
        }
        else if (command.startsWith("delete"))
        {
            deleteTask(command);
        }
        else
        {
            throw new YapperException("Yapper don't know this command :(");
        }
    }

    public static void deleteTask(String command)
    {
        String input = command.substring(7);
        if (input.isEmpty())
        {
            throw new YapperException("Task Number cannot be empty!");
        }

        int order = Integer.parseInt(input);
        if (order <= 0)
        {
            throw new YapperException("Task Number cannot be less than 1!");
        }
        else if (order > listOfTask.size())
        {
            throw new YapperException("Task Number cannot be more than size of list!");
        }
        else {
            Task task = listOfTask.get(order - 1);
            listOfTask.remove(order - 1);
            System.out.println("Noted. I've removed this task: \n" +
                    task + "\nNow you have " + listOfTask.size() + " tasks in the list");;
        }
    }

    public static void addToDo(String command)
    {
        String input = command.substring(5);
        if (input.isEmpty())
        {
           throw new YapperException("Description for ToDo cannot be empty!");
        }
        ToDo todo = new ToDo(input);
        addTask(todo);
    }

    public static void addDeadline(String command)
    {
        String input = command.substring(9);
        if (input.isEmpty())
        {
            throw new YapperException("Description for Deadline cannot be empty!");
        }
        String[] split = input.split("/by ");
        if (split.length == 1)
        {
            throw new YapperException("Deadline require /by command with Deadline Time");
        }
        else if (split[0].isEmpty())
        {
            throw new YapperException("Deadline Task is empty!");
        }
        else if (split[1].isEmpty())
        {
            throw new YapperException("Deadline Time is empty!");
        }
        else
        {
            Deadline deadline = new Deadline(split[0], split[1]);
            addTask(deadline);
        }
    }

    public static void addEvent(String command)
    {
        String input = command.substring(6);
        if (input.isEmpty())
        {
            throw new YapperException("Event Task cannot be empty!");
        }
        String[] split = input.split("/from ");
        String[] split2 = split[1].split("/to ");
        if (split.length == 1)
        {
            throw new YapperException("Event require /from command with Start Time");
        }
        else if (split2.length == 1)
        {
            throw new YapperException("Event require /to command with End Time");
        }
        else if (split[0].isEmpty())
        {
            throw new YapperException("Event Task is empty!");
        }
        else if (split2[0].isEmpty())
        {
            throw new YapperException("Event Start Time is empty!");
        }
        else if (split2[1].isEmpty())
        {
            throw new YapperException("Event End Time is empty!");
        }
        else
        {
            Event event = new Event(split[0], split2[0], split2[1]);
            addTask(event);
        }
    }

    // add a new task into the list
    public static void addTask(Task task)
    {
        listOfTask.add(task);
        System.out.println("Got it. I've added this task: \n" +
                task + "\nNow you have " + listOfTask.size() + " tasks in the list");
    }

    // mark a task as done
    public static void mark(String taskNumber) throws YapperException
    {
        String input = taskNumber.substring(5);
        if (input.isEmpty())
        {
            throw new YapperException("Task Number cannot be empty!");
        }

        int order = Integer.parseInt(input);
        if (order <= 0)
        {
            throw new YapperException("Task Number cannot be less than 1!");
        }
        else if (order > listOfTask.size())
        {
            throw new YapperException("Task Number cannot be more than size of list!");
        }
        else {
            Task taskToMark = listOfTask.get(order - 1);
            taskToMark.setDone(true);
            System.out.println("Nice! I've marked this task as done: \n" +
                    taskToMark);
        }
    }

    // umark a task as undone
    public static void unmark(String taskNumber) throws YapperException
    {
        String input = taskNumber.substring(7);
        if (input.isEmpty())
        {
            throw new YapperException("Task Number cannot be empty!");
        }

        int order = Integer.parseInt(input);
        if (order <= 0)
        {
            throw new YapperException("Task Number cannot be less than 1!");
        }
        else if (order > listOfTask.size())
        {
            throw new YapperException("Task Number cannot be more than size of list!");
        }
        else {
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
        for (Task task : listOfTask)
        {
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
