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

    public void list(TaskList taskList) {
        System.out.println("!plans:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i));
        }
    }

    public void count(int size) {
        System.out.println("Now you have " + size +
                ((size == 1) ? " item " : " items ") + "in the list.");
    }

    public void unknownInput() {
        System.out.println("fence is programmed to track your tasks and has long lost all ability " +
                "to do other things ");
    }

    public void todoWrongFormat() {
        System.out.println("doing nothing");
    }

    public void unknownDate() {
        System.out.println("fence only understands yyyy-mm-dd");
    }

    public void loadingError() {
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
