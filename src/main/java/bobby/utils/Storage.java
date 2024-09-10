package bobby.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bobby.exception.BobbyException;
import bobby.tasks.Deadline;
import bobby.tasks.Event;
import bobby.tasks.Task;
import bobby.tasks.Todo;

/**
 * The Storage class a storage where users can store their tasks.
 * It enables users to save and load their tasks.
 */
public class Storage {

    private final String filePath;
    private final String archivePath;

    /**
     * Constructs a Storage instance
     *
     * @param filePath The path of the file where the tasks are going to be stored.
     */
    public Storage(String filePath, String archivePath) {
        this.filePath = filePath;
        this.archivePath = "archive.txt";
    }

    /**
     * Saves the tasks in the file.
     *
     * @param listOfTasks The list of tasks that is in Bobby.
     */
    public void saveData(ArrayList<Task> listOfTasks, String path) throws IOException {
        FileWriter fw = new FileWriter(path);

        for (Task task : listOfTasks) {
            fw.write(task.taskInFile() + "\n");
        }
        fw.close();
    }

    /**
     * Processes the file containing the tasks line-by-line and creates the tasks.
     * The tasks are then added to the list of tasks into Bobby.
     *
     * @param listOfTasks The list of task that is in Bobby.
     * @param line Each line in the file.
     */
    public void processFileLines(ArrayList<Task> listOfTasks, String line) throws BobbyException {
        String[] lineParts = line.split(" \\| ");

        switch (lineParts[0]) {
        case "T":
            Task t = Todo.createTodo("todo" + lineParts[2]);
            if (lineParts[1].equals("X")) {
                t.indComplete();
            }
            listOfTasks.add(t);
            break;
        case "D":
            Task d = Deadline.createDeadline("deadline" + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                d.indComplete();
            }
            listOfTasks.add(d);
            break;
        case "E":
            Task e = Event.createEvent("event " + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                e.indComplete();
            }
            listOfTasks.add(e);
            break;
        default:
            throw new BobbyException("Error has occurred!");
        }
    }

    /**
     * Returns an ArrayList of Task.
     * If there was no file containing the tasks, a new file will be created and empty ArrayList would be returned.
     *
     * @return ArrayList of Task.
     */
    public ArrayList<Task> returnTaskList() throws IOException, BobbyException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (f.createNewFile()) {
            System.out.println(filePath + " not found, allow me to create it for you");
            assert f.exists() : "File does not exist!";
            return tasks;
        } else {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                processFileLines(tasks, s.nextLine());
            }
            assert f.exists() : "File does not exist!";
            return tasks;
        }
    }

    /**
     * Returns an ArrayList of archived tasks.
     * If there is no archive file, a new archive file will be created and empty ArrayList will be returned.
     *
     * @return ArrayList of archived tasks.
     */
    public ArrayList<Task> returnArchiveList() throws IOException, BobbyException {
        File archive = new File(this.archivePath);
        ArrayList<Task> archivedTasks = new ArrayList<>();

        if (!archive.createNewFile()) {
            Scanner s = new Scanner(archive);
            while (s.hasNextLine()) {
                processFileLines(archivedTasks, s.nextLine());
            }
        }
        return archivedTasks;
    }
}
