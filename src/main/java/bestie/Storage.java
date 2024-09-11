package bestie;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bestie.task.*;

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
        assert filePath != null && !filePath.isEmpty(): "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Writes tasks in the TaskList to the bestie.txt file.
     *
     * @param tasks contains tasks to be saved into the bestie.txt file.
     */
    public void saveTasksToFile(TaskList tasks) {

        // want to save tasks to bestie.txt file
        assert this.filePath != null && !filePath.isEmpty(): "Filepath has not been initialised";
        try {
            File f = new File(this.filePath);
            FileWriter fw = new FileWriter(f);

            for (Task task: tasks.getTasks()) {
                /** Stores each task in its correct format for saving */
                assert task != null: "Task in task list is null";
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
            // Call close() method of filewriter object for writing operation to be completed
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to save tasks to file.");
        }

    }

    /**
     * Loads tasks from bestie.txt file into an ArrayList.
     *
     * @return list of tasks already stored in the bestie.txt file.
     */
    public ArrayList<Task> loadTasksFromFile() {

        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        try {
            // creates new file if and only if file does not yet exist
            f.createNewFile();
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                // Load the next task in the file in its stored format
                String nextTask = sc.nextLine();
                assert nextTask != "" : "Next task is empty";
                String[] parts = nextTask.split(" \\| ");
                String taskType = parts[0]; // either T, D, or E, depending on task
                
              // Checks whether task had been completed
                assert taskType.equals("T") || taskType.equals("D") || taskType.equals("E"):
                        "Invalid task type";
                boolean isCompleted = parts[1].equals("1");
                String description = parts[2]; // description of task
                Task newTask = null;

                switch (taskType) {

                case ("T"): // next task is a todo
                    Priority todoPriority = Priority.valueOf(parts[3]);
                    newTask = new Todo(description, todoPriority);
                    tasks.add(newTask);
                    break;

                case ("D"): // next task is a deadline
                    String deadline = parts[3];
                    Priority daedlinePriority = Priority.valueOf(parts[4]);
                    newTask = new Deadline(description, deadline, daedlinePriority);
                    tasks.add(newTask);
                    break;

                case ("E"): // next task is an event
                    String start = parts[3];
                    String end = parts[4];
                    Priority eventPriority = Priority.valueOf(parts[5]);
                    newTask = new Event(description, start, end, eventPriority);
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
