package bestie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bestie.task.Deadline;
import bestie.task.Event;
import bestie.task.Task;
import bestie.task.Todo;

/**
 * Deals with loading objects from the file and saving tasks in the bestie.txt file.
 */
public class Storage {

    private String filePath;

    /**
     * Creates an instance of the storage class for user throughout the programme.
     *
     * @param filePath path of the bestie.txt file where users' tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes tasks in the TaskList to the bestie.txt file.
     *
     * @param tasks contains tasks to be saved into the bestie.txt file.
     */
    public void saveTasksToFile(TaskList tasks) {
        // want to save tasks to bestie.txt file
        try {
            File f = new File(this.filePath);
            FileWriter fw = new FileWriter(f);

            for (Task task: tasks.getTasks()) {
                // store each task in the save format
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            // must call close() method of filewriter object for writing operation to be completed
            fw.close();
        } catch (IOException e) {
            // must handle the checked exception from creating a new FileWriter instance, IOException
            System.out.println("An error occurred while attempting to save tasks to file.");
        }

    }

    /**
     * Loads tasks from bestie.txt file into an ArrayList.
     *
     * @return list of tasks already stored in the bestie.txt file.
     */
    public ArrayList<Task> loadTasksFromFile() {

        // create the array list of tasks that will be returned
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        try {
            // creates new file if and only if file does not yet exist
            f.createNewFile();
            Scanner sc = new Scanner(f); // create scanner using file as source

            while (sc.hasNextLine()) {
                // load the next task in the file in its stored format
                String nextTask = sc.nextLine();
                String[] parts = nextTask.split(" \\| ");
                String taskType = parts[0]; // either T, D, or E, depending on task
                // check if task is completed
                boolean isCompleted = parts[1].equals("1");
                String description = parts[2]; // description of task
                Task newTask = null;

                switch (taskType) {

                case ("T"): // next task is a todo
                    newTask = new Todo(description);
                    tasks.add(newTask);
                    break;

                case ("D"):

                    String deadline = parts[3];
                    newTask = new Deadline(description, deadline);
                    tasks.add(newTask);
                    break;

                case ("E"):
                    String start = parts[3];
                    String end = parts[4];
                    newTask = new Event(description, start, end);
                    tasks.add(newTask);
                    break;

                default:
                    newTask = null;
                }


                if (newTask != null) {
                    if (isCompleted) {
                        newTask.markTaskDone();
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("An error occured loading tasks from the file.");
        }
        return tasks;

    }
}
