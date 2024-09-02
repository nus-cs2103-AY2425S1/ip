package fence.ui;

import fence.task.Task;
import fence.tasklist.TaskList;

public class Ui {
    public void greet() {
        System.out.println("nihao! I'm Fence |=|=|=|=|=|");
    }

    public void exit() {
        System.out.println("have good day :)");
    }

    public void list(TaskList tasks) {
        System.out.println("!plans:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i));
        }
    }

    public void count(int size) {
        System.out.println("Now you have " + size +
                ((size == 1) ? " item " : " items ") + "in the list.");
    }

    public void printUnknownCommand() {
        System.out.println("fence is programmed to track your tasks and has long lost all ability " +
                "to do other things ");
    }

    public void printMissingFieldError() {
        System.out.println("doing nothing (field missing/invalid format)");
    }

    public void printInvalidDateError() {
        System.out.println("fence only understands yyyy-mm-dd");
    }

    public void printInvalidNumberError() {
        System.out.println("fence has only learnt numerical integers");
    }

    public void printLoadingError() {
        System.out.println("Data file corrupted");
    }

    public void add(Task task) {
        System.out.println("added: " + task);
    }

    public void mark(Task task) {
        System.out.println("good job");
        System.out.println(task);
    }

    public void unmark(Task task) {
        System.out.println("huh?");
        System.out.println(task);
    }

    public void delete(Task task) {
        System.out.println("removed: " + task);
        System.out.println("(we never make plans)");
    }
}
