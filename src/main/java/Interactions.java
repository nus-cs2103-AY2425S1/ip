import java.util.ArrayList;

public class Interactions {

    static void printTask(ArrayList<Task> tasks) {
        System.out.println("_____________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("_____________________________________");
    }

    static void markTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markDone();
            System.out.println("_____________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index - 1));
        } else {
            System.out.println("_____________________________________");
            System.out.println("No Task Found");
        }
        System.out.println("_____________________________________");
    }

    static void unmarkTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markNotDone();
            System.out.println("_____________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index - 1));
        } else {
            System.out.println("_____________________________________");
            System.out.println("No Task Found");
        }
        System.out.println("_____________________________________");
    }

    static void deleteTask(ArrayList<Task> tasks, int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("_____________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("_____________________________________");
            System.out.println("No Task Found");
        }
        System.out.println("_____________________________________");
    }

    static void addTask(ArrayList<Task> tasks, Task.TaskType type, String desc, String... args) {
        Task newTask = switch (type) {
            case TODO -> new Task.ToDo(desc);
            case DEADLINE -> new Task.Deadline(desc, args[0]);
            case EVENT -> new Task.Event(desc, args[0], args[1]);
        };
        tasks.add(newTask);
        System.out.println("_____________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("_____________________________________");
    }

    static void defaultMsg(){
        System.out.println("_____________________________________");
        System.out.println("I'm sorry, but I don't know what that means");
        System.out.println("_____________________________________");
    }

    static void endMsg(){
        System.out.println("_____________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_____________________________________");
    }

    static void greetMsg(String logo){
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________");
    }

    static void blankMsg(String type){
        System.out.println("_____________________________________");
        switch (type){
            case "todo":
                System.out.println("The description of a " + type + " cannot be empty.");
                break;
            case "number":
                System.out.println("The task number cannot be empty.");
                break;
            default:
                System.out.println("The description and date of a " + type + " cannot be empty.");
                break;
        }
        System.out.println("_____________________________________");
    }

}
