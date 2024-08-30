package lutodo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

import lutodo.tasks.*;

public class Utility {

    public static final String FILE_PATH = "docs/taskListFile.txt";
    public static final String LINE = "____________________________________________________________\n";
    public static final Task EMPTY_TASK = new Task("default");

    protected static ArrayList<Task> toDo = new ArrayList<>();

    public static void greeting() {
        System.out.print(LINE + " Hello! I'm LuToDo \n" +
                "What can I do for you?\n" +
                 LINE);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void fileToTaskList(File file) throws FileNotFoundException {
        toDo.clear();
        Scanner s = new Scanner(file);
        try {
            while (s.hasNextLine()) {
                String taskMessage = s.nextLine();
                if (taskMessage.isEmpty()) {
                    continue;
                }
                Task task = EMPTY_TASK;
                switch (taskMessage.charAt(1)) {
                    case 'T':
                        task = new TodoTask(splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    case 'D':
                        task = new DeadlineTask(splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    case 'E':
                        task = new EventTask(splitTaskInfo(taskMessage)[1]);
                        toDo.add(task);
                        break;
                    default:
                        System.out.println("Cannot read: " + taskMessage);
                }
                if(taskMessage.charAt(4) == 'X') {
                    task.markAsDone();
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read file, so the task list may lost data. " + e.getMessage());
        }

    }

    public static void taskListToFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (int i = 1; i <= toDo.size(); i++) {
                fileWriter.write(toDo.get(i - 1) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                int taskIndex = parseInt(divideMessage(message)[1]) - 1;
                System.out.println("Noted. I've removed this task:\n" + toDo.get(taskIndex) +
                        "\nNow you have " + (toDo.size() - 1) + " tasks in the list.");
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
            System.out.println("Tips: Tasks marked as [X] are already completed ～(∠・ω< )⌒★");
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
                System.out.print("""
                        The description of the deadline task must include the ddl time, please try again.
                        Tips: use '/by' to enter the ddl date.
                        Supported format: yyyy-MM-dd
                         e.g. deadline quiz1 /by 2024-12-31
                        """);
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
                System.out.print("""
                        The description of the event task must include the start time and the end time, please try again.
                        Tips: use '/from' and '/to' to enter the start and end time.
                        Supported time format: HH:mm, HH:mm:ss
                        e.g. event meeting1 /from 12:00 /to 14:00""");
                return;
            }
        }

        System.out.println("Sorry to say that I don't know what does \"" + message + "\" means. Anyway, have a good day :)");
    }
}
