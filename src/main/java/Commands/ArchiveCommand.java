package Commands;

import task.Task;
import task.TaskList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static prince.Prince.TASKS_FILE_PATH;

public class ArchiveCommand{

    ArrayList<Task> lst;
    public ArchiveCommand() {
        this.lst = TaskList.getList();
    }

    public void commandAction() {
        archiveTasks(this.lst);
        TaskList.clearTaskList();
        ArrayList<Task> emptyList = new ArrayList<>();
        saveTasks(emptyList);
    }

    private void archiveTasks(ArrayList<Task> lst) {
        try(PrintWriter printwriter = new PrintWriter(new FileWriter("archive.txt", true))) {
            for (Task task : lst) {
                printwriter.println(task.printFileFormat()); // Or task.toString(), depending on your need
            }

            System.out.println("Tasks archived successfully.");
        } catch (IOException e) {
            System.out.println("There was an error archiving tasks.");
            e.printStackTrace();
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(TASKS_FILE_PATH))) {
            System.out.println("Tasks are cleared saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

}
