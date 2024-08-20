import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class YapBot {
    private static final String PREFIXLINE = "\n-------------------------------------------";
    private static final String POSTFIXLINE = "-------------------------------------------\n";
    private static ArrayList<Task> storedTasks = new ArrayList<>();

    public static void list() {
        Iterator<Task> iterateStored = storedTasks.iterator();
        System.out.println(PREFIXLINE + "\nRetrieving Tasks... \nSuccess \nCurrent Tasks:");
        int index = 1;
        while (iterateStored.hasNext()) {
            System.out.println("  " + index + "." + iterateStored.next());
            index++;
        }
        System.out.println(POSTFIXLINE);
    }

    public static void markOrUnmark(String input) {
        if (input.contains("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;

            if (index == -1 || index >= storedTasks.size()) {
                System.out.println(PREFIXLINE + "\nFinding Task... \nFailure \nError, requested Task does not exist.\n" + POSTFIXLINE);
                return;
            }

            Task task = storedTasks.get(index);
            task.changeDone(false);
            System.out.println(PREFIXLINE + "\nFinding Task... \nSuccess \nTask Incomplete:\n" + task + "\n" + POSTFIXLINE);
        } else {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index == -1 || index >= storedTasks.size()) {
                System.out.println(PREFIXLINE + "\nFinding Task... \nFailure \nError, requested Task does not exist.\n" + POSTFIXLINE);
                return;
            }
            Task task = storedTasks.get(index);
            task.changeDone(true);
            System.out.println(PREFIXLINE + "\nFinding Task... \nSuccess \nTask Completed:\n" + task + "\n" + POSTFIXLINE);
        }
    }

    public static void createTask(String input) {
        String taskType = input.substring(0, input.indexOf(" "));
        String taskDetails = input.substring(input.indexOf(" ") + 1);
        Task task = new Task("Dummy");

        switch (taskType) {
            case "todo" -> {
                task = new ToDo(taskDetails);
                storedTasks.add(task);
            }
            case "deadline" -> {
                String taskName = taskDetails.substring(0, taskDetails.indexOf("/by")).strip();
                String deadline = taskDetails.substring(taskDetails.indexOf("/by") + 3).strip();
                task = new Deadline(taskName, deadline);
                storedTasks.add(task);
            }
            case "event" -> {
                String taskName = taskDetails.substring(0, taskDetails.indexOf("/")).strip();
                String taskDeadlines = taskDetails.substring(taskDetails.indexOf("/"));
                String from = taskDeadlines.substring(taskDeadlines.indexOf("/from") + 5, taskDeadlines.indexOf("/to")).strip();
                String to = taskDeadlines.substring(taskDeadlines.indexOf("/to") + 3).strip();

                task = new Event(taskName, from, to);
                storedTasks.add(task);
            }
        }

        System.out.println(PREFIXLINE + "\nAdding Task... \nSuccess \nTask Added: \n" + "  " + task + "\n" + "Total tasks: " + storedTasks.size() +"\n" + POSTFIXLINE);
    }

    public static void main(String[] args) {

        System.out.println(PREFIXLINE + "\nPowering up... \nSystem booted successfully.  \nYapBot online.\n" + POSTFIXLINE);
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list();
                input = in.nextLine();
            } else if (input.contains("mark")) {
                markOrUnmark(input);
                input = in.nextLine();
            }  else {
                createTask(input);
                input = in.nextLine();
            }
        }

        in.close();
        System.out.println(PREFIXLINE + "\nShutting down... \nYapBot process terminated.\n" + POSTFIXLINE);
    }
}
