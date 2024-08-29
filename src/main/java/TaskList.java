import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    public void addToList(Task task) {
        printLine();
        System.out.println("    The task hath been added");
        System.out.println("      " + task);
        list.add(task);
        if (list.size() == 1) {
            System.out.println("    thou now hath " + list.size() + " task to complete");
        } else {
            System.out.println("    thou now hath " + list.size() + " tasks to complete");
        }
        printLine();
        System.out.println();
    }

    public Task getTask(int index) throws Elseption {
        Task task;

        try {
            task = list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new Elseption();
        }

        return task;
    }

    public boolean markTaskAsDone(int index) throws Elseption {
        Task task = getTask(index);

        return task.markAsDone();
    }

    public boolean unmarkTaskAsUndone(int index) throws Elseption {
        Task task = getTask(index);

        return task.unmarkAsUndone();
    }

    public void deleteTask(int index) {
        printLine();
        Task task;

        try {
            task = list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Ain't no such task in the middle of these woods");
            printLine();
            return;
        }

        System.out.println("    Your bidding has been done, removed:");
        System.out.println("      " + task.toString());
        if (list.size() == 1) {
            System.out.println("    thou now hath " + list.size() + " task to complete");
        } else if (list.isEmpty()) {
            System.out.println("    thou hath no tasks to be completed");
        } else {
            System.out.println("    thou now hath " + list.size() + " tasks to complete");
        }

        printLine();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public String printString() {
        StringBuilder returnStr = new StringBuilder();

        if (list.isEmpty()) {
            returnStr.append("    No tasks to do? that's pretty goooood.");

        } else {
            returnStr.append("    So here's the tasks in your list, you should proooobably do them\n");
            for (int i = 0; i < list.size(); i++) {
                returnStr.append("    ").append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
        }

        return returnStr.toString().stripTrailing();
    }

    public String fileString() {
        StringBuilder returnStr = new StringBuilder();

        for (Task task : list) {
            returnStr.append(task.toFileString()).append(System.lineSeparator());
        }

        return returnStr.toString();
    }
}
