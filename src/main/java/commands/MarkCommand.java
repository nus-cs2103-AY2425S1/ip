//package commands;
//
//import exceptions.CenaInvalidTaskIndexException;
//import storage.Storage;
//import tasks.Task;
//
//import java.util.ArrayList;
//
//public class MarkCommand implements Command{
//
//    private ArrayList<Task> tasks;
//    private int taskIndex;
//
//    public MarkCommand(ArrayList<Task> tasks, int taskIndex) {
//        this.tasks = tasks;
//        this.taskIndex = taskIndex;
//    }
//
//
//    @Override
//    public void execute() {
//        try {
//            if (taskIndex < 0 || taskIndex >= tasks.size()) {
//                throw new CenaInvalidTaskIndexException("The task index is invalid.");
//            }
//            tasks.get(taskIndex).markAsDone();
//            Storage.saveTasks(tasks);
//            System.out.println("____________________________________________________________");
//            System.out.println(" Nice! I've marked this task as done:");
//            System.out.println("   " + tasks.get(taskIndex));
//            System.out.println("____________________________________________________________");
//        } catch (CenaInvalidTaskIndexException e) {
//            System.out.println("Error: " + e.getMessage());
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("The task index must be a number.", e);
//        }
//    }
//
//}


package commands;

import exceptions.CenaInvalidTaskIndexException;
import storage.Storage;
import tasks.Task;

import java.util.ArrayList;

public class MarkCommand implements Command {
    private ArrayList<Task> tasks;
    private int taskIndex;

    public MarkCommand(ArrayList<Task> tasks, int taskIndex) {
        this.tasks = tasks;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws CenaInvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CenaInvalidTaskIndexException("The task index is invalid.");
        }
        tasks.get(taskIndex).markAsDone();
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks.get(taskIndex));
        System.out.println("____________________________________________________________");
    }
}
