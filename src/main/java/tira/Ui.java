package tira;
import tira.task.TaskList;
import tira.task.Event;
import tira.task.Task;
import tira.task.ToDo;
import tira.task.Deadline;

import java.util.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
/*
Ui class
Handles the user's commands, including reading the user commands
Handles printing of all the errors.
Used as an input for TaskList, Storage, and Tira Main.
 */
public class Ui {
    private final Scanner scanner;
    private final PrintWriter printer = new PrintWriter(System.out);

    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String read() {
        return scanner.nextLine();
    }
    public void welcome() {
        System.out.println("MIAO! I'm TIRAMISU THE CAT (TIRA)\n" +
                "What can I do for you today, miao?\n");
    }

    public void bye() {
        System.out.println("Bye. Come back with treats, MIAO!");
    }
    /*
    Solution below inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/hana/Ui.java
     */
    public void showTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        printer.println("HERE ARE THE CURRENT TASKS:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            printer.print((i + 1) + ". " + currTask + "\n");
        }
        printer.flush();
    }

    public void showMarkTask(Task task) {
        printer.println("NYA! Good job on this task:" + "\n" +
                task + "\n");
        printer.flush();
    }

    public void showUnmarkTask(Task task) {
        printer.println("MRAWWW! Don't forget to return to this task:" + "\n" +
                task + "\n");
        printer.flush();
    }

    public void showAddTask(Task task, int taskSize) {
        printer.println("Miao! Got it. I've added this task to my cat brain:\n" +
                task + "\nNow you have " + taskSize + " task(s) in the list!");
        printer.flush();
    }

    public void showDelete(Task task, int taskSize) {
        printer.println("Noted, Miao! I've removed this task:\n" + task +
                "\nNow you have " + taskSize + " task(s) in the list!");
        printer.flush();
    }
    public void showLoadingError() {
        System.out.println("Oh no... There is an error while loading the file! ");
    }

    public void showNoMatchingTask() {
        printer.println("Miao... No such task... Sorry!");
        printer.flush();
    }

    public void showMatchingTasks(ArrayList<Task> tasks) {
        printer.println("Miao!!!! I found the tasks in my cat brain! They are:");
        for(Task task: tasks) {
            printer.println(task);
        }
        printer.flush();
    }
}
