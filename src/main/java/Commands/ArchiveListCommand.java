package Commands;

import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static storage.Storage.getTaskFromFile;

public class ArchiveListCommand {
    ArrayList<Task> archivedlst;
    public ArchiveListCommand() {
        String filePath = "archive.txt";
        this.archivedlst = new ArrayList<>();

    }

    public String commandAction() {
        getTasks();
        return Ui.listDisplay(archivedlst);
    }

    public void getTasks() {
        archivedlst = loadArchivedTasksFromFile();  // Load tasks from archive.txt
        if (this.archivedlst.isEmpty()) {
            System.out.println("No archived tasks found.");
        } else {
            System.out.println("Archived tasks:");
        }
    }

    public static ArrayList<Task> loadArchivedTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        String archiveFilePath = "archive.txt";

        try {
            File file = new File(archiveFilePath);
            if (!file.exists()) {
                System.out.println("No archive file found.");
                return tasks;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                Task task = getTaskFromFile(taskString);
                if (task != null) {
                    tasks.add(task);
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading archived tasks.");
            e.printStackTrace();
        }

        return tasks;
    }

}

//get the list of tasks from archive.txt
// then do this return Ui.listDisplay(TaskList.getList());
