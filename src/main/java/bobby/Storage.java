package bobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bobby.exception.BobbyException;
import bobby.exception.EmptyDescriptionException;

/**
 * The Storage class a storage where users can store their tasks.
 * It enables users to save and load their tasks.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage instance
     *
     * @param filePath The path of the file where the tasks are going to be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks in the file.
     *
     * @param listOfTasks The list of tasks that is in Bobby.
     */
    public void saveData(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

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
    public void processFileLines(ArrayList<Task> listOfTasks, String line) throws EmptyDescriptionException {
        String[] lineParts = line.split(" \\| ");
        switch (lineParts[0]) {
        case "T":
            Task t = Todo.createTodo("todo" + lineParts[2]);
            if (lineParts[1].equals("X")) {
                t.indIncomplete();
            }
            listOfTasks.add(t);
            break;
        case "D":
            Task d = Deadline.createDeadline("deadline" + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                d.indIncomplete();
            }
            listOfTasks.add(d);
            break;
        case "E":
            Task e = Event.createEvent("event " + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                e.indIncomplete();
            }
            listOfTasks.add(e);
            break;
        }
    }

    /**
     * Loads the data from the file to Bobby.
     *
     * @param listOfTasks The list of tasks in Bobby.
     */
    public void loadData(ArrayList<Task> listOfTasks) throws BobbyException {
        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                processFileLines(listOfTasks, s.nextLine());
            }
        } catch (FileNotFoundException e) {
           throw new BobbyException("The file cannot be found, please check again.");
        }
    }

    /**
     * Returns an ArrayList of Task.
     * If there was no file containing the tasks, a new file will be created and empty ArrayList would be returned.
     *
     * @return ArrayList of Task
     */
    public ArrayList<Task> returnTaskList() throws IOException, BobbyException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (f.createNewFile()) {
            System.out.println(filePath + " not found, allow me to create it for you");
            return tasks;
        } else {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) {
                processFileLines(tasks, s.nextLine());
            }
            return tasks;
        }
    }


}
