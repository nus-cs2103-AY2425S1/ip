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
            String input = command.substring(5);
            int order = Integer.parseInt(input);
            mark(order);
        }
        else if (command.startsWith("unmark"))
        {
            String input = command.substring(7);
            int order = Integer.parseInt(input);
            unmark(order);
        }
        else if (command.startsWith("todo"))
        {
            String input = command.substring(5);
            addToDo(input);
        }
        else if (command.startsWith("deadline"))
        {
            String input = command.substring(9);
            String[] split = input.split("/by ");
            addDeadline(split[0], split[1]);
        }
        else if (command.startsWith("event"))
        {
            String input = command.substring(6);
            String[] split = input.split("/from ");
            String[] split2 = split[1].split("/to ");
            addEvent(split[0], split2[0], split2[1]);
        }
        else
        {

        }
    }

    public static void addToDo(String toDoName)
    {
        ToDo todo = new ToDo(toDoName);
        addTask(todo);
    }

    public static void addDeadline(String deadlineName, String deadlineTime)
    {
        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        addTask(deadline);
    }

    public static void addEvent(String eventName, String eventFromTime, String eventToTime)
    {
        Event event = new Event(eventName, eventFromTime, eventToTime);
        addTask(event);
    }

    // add a new task into the list
    public static void addTask(Task task)
    {
        listOfTask.add(task);
        System.out.println("Got it. I've added this task: \n" +
                task + "\nNow you have " + listOfTask.size() + " tasks in the list");
    }

    // mark a task as done
    public static void mark(int taskNumber)
    {
        Task taskToMark = listOfTask.get(taskNumber - 1);
        taskToMark.setDone(true);
        System.out.println("Nice! I've marked this task as done: \n" +
                taskToMark);
    }

    // umark a task as undone
    public static void unmark(int taskNumber)
    {
        Task taskToUnmark = listOfTask.get(taskNumber - 1);
        taskToUnmark.setDone(false);
        System.out.println("OK, I've marked this task as not done yet: \n" +
                taskToUnmark);
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
