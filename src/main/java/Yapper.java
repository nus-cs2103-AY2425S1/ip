import java.util.Scanner;
import java.util.ArrayList;

public class Yapper {
    public static ArrayList<Task> listOfTask = new ArrayList<Task>();
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
        else
        {
            int i = command.indexOf(' ');
            String comm = command.substring(0, i);
            if (comm.equals("mark")) {
                String input = command.substring(i + 1);
                int order = Integer.valueOf(input);
                mark(order);
            }
            else if (comm.equals("unmark")) {
                String input = command.substring(i + 1);
                int order = Integer.valueOf(input);
                unmark(order);
            }
            else {
                addToList(command);
            }
        }
    }
    public static void mark(int taskNumber)
    {
        Task taskToMark = listOfTask.get(taskNumber - 1);
        taskToMark.setDone(true);
        System.out.println("Nice! I've marked this task as done: \n" +
                taskToMark);
    }

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

    // add to List
    public static void addToList(String text)
    {
        Task newTask = new Task(text);
        listOfTask.add(newTask);
        System.out.println("Added: " + text);
    }

    // exits when the user types the command "bye"
    public static void exit()
    {
        System.out.println("Yapper shall yap next time!");
    }
}
