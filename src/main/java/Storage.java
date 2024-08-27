import java.io.FileWriter;

import java.io.IOException;

import java.util.ArrayList;

/**
 * Represents a Storage object that handles writing and reading tasks from a local file.
 * @author Lee Ze Hao (A0276123J)
 */

public class Storage {
    private String filePath;

    /**
     * Creates a new Storage object.
     * @param filePath The file path data is stored to and read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Takes a list of tasks and writes them to a text file at the filePath given during object creation.
     * @param taskList The list of tasks to be written to the file.
     */
    public void writeTasksToFile(ArrayList<Task> taskList) throws IOException, TaskException {
        FileWriter fw = new FileWriter(this.filePath);
        String textToWrite = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            Task.TASK_TYPE taskType = task.getTaskType();
            String isDoneMark = task.getIsDone() ? "1" : "0";

            switch (taskType) {
            case TODO:
                textToWrite = textToWrite + "T | " + isDoneMark + " | " + task.getTaskName() + "\n";
                break;
            case DEADLINE:
                // Note: taskType only set to DEADLINE when Deadline class is created, and is final, so this is safe
                Deadline deadline = (Deadline) task;
                textToWrite = textToWrite + "D | " + isDoneMark + " | " + deadline.getTaskName() +
                        " | " + deadline.getByTime() + "\n";
                break;
            case EVENT:
                // Note: taskType only set to EVENT when Event class is created, and is final, so this is safe
                Event event = (Event) task;
                textToWrite = textToWrite + "E | " + isDoneMark + " | " + event.getTaskName() +
                        " | " + event.getFromTime() + "-" + event.getToTime() + "\n";
                break;
            default:
                throw new TaskWithoutTypeException();
            }
        }

        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Reads a list of tasks from a text file at the filePath given during object creation.
     * @return ArrayList<Task> List of tasks stored in the text file.
     */
    public ArrayList<Task> readTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        return taskList;
    }
}
