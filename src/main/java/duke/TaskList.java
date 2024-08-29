package duke;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> toDoList = new ArrayList<Task>();
    private static int counter = 1;

    public static void mark(int index) {
        Task task = toDoList.get(index - 1);
        task.markAsDone();
        Storage.replaceLineInFile(index - 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmark(int index) {
        Task task = toDoList.get(index - 1);
        task.unmarkAsUndone();
        Storage.replaceLineInFile(index - 1);
        System.out.println("Ok! I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void delete(int index) {
        Task task = toDoList.get(index - 1);
        toDoList.remove(index - 1);
        Storage.deleteLineFromFile(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        counter -= 1;
        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
    }

    public static void load(Task task) {
        toDoList.add(task);
        counter += 1;
    }

    public static void add(Task task) {
        toDoList.add(task);
        Storage.writeToFile(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        counter += 1;
    }

    static void setToDoList(ArrayList<Task> tasks) {
        toDoList = tasks;
        // Update the counter based on the new list size
        counter = tasks.size() + 1;
    }

    public static int getSize() {
        return toDoList.size();
    }

    public static Task getTask(int index) {
        return toDoList.get(index);
    }
}
