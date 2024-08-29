import TaskObj.Task;
import TaskObj.TaskType;

import java.util.ArrayList;

public class Printer {
    public static final String hLine = "____________________________________________________________\n";
    // Method to print todolist
    public static void printList(ArrayList<Task> todoList) {
        System.out.print(hLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Task.taskNumber; i++) {
            System.out.println(i+1 + "." + todoList.get(i).toString());
        }
        System.out.println(hLine);
    }

    public static void printMark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    public static void printUnmark(Task curTask) {
        System.out.print(hLine);
        System.out.println("Ok, I've marked this as not done yet:");
        System.out.println("  " + curTask.toString());
        System.out.print(hLine);
    }

    public static void printDelete(Task curTask) {
        System.out.print(hLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + curTask.toString());
        int curTaskNumber = Task.taskNumber;
        if (curTaskNumber == 1) {
            System.out.println("Now you have " + curTaskNumber + " task in the list.");
        } else {
            System.out.println("Now you have " + curTaskNumber + " tasks in the list.");
        }
        System.out.print(hLine);
    }

    public static void printTask(Task curTask) {
        System.out.print(hLine);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + curTask.toString());
        int curTaskNumber = Task.taskNumber;
        if (curTaskNumber == 1) {
            System.out.println("Now you have " + curTaskNumber + " task in the list.");
        } else {
            System.out.println("Now you have " + curTaskNumber + " tasks in the list.");
        }
        System.out.print(hLine);
    }

    public static void printError(TaskType.taskType tasktype, String desc) {
        String oops = "OOPS!!! ";
        switch (tasktype) {
            case TODO:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case DEADLINE:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case EVENT:
                System.out.println(hLine + oops + desc + "\n" + hLine);
                break;
            case INVALID:
                String invalidMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
                System.out.println(hLine + invalidMessage + hLine);
                break;
            default:
                String defaultMessage = oops + "I'm sorry, I don't know what that means ;-;\n";
                System.out.println(hLine + defaultMessage + hLine);
        }
    }
}
