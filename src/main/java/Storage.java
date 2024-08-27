import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();

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
                        " | " + event.getFromTime() + "to" + event.getToTime() + "\n";
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
    public ArrayList<Task> readTasksFromFile() throws FileNotFoundException, TaskWithoutTypeException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskString = s.nextLine();
            if (taskString.isEmpty()) {
                continue;
            }

            Task.TASK_TYPE taskType;
            switch (taskString.split(" \\| ", 0)[0]) {
            case "T":
                taskType = Task.TASK_TYPE.TODO;
                break;
            case "D":
                taskType = Task.TASK_TYPE.DEADLINE;
                break;
            case "E":
                taskType = Task.TASK_TYPE.EVENT;
                break;
            default:
                throw new TaskWithoutTypeException();
            }

            boolean taskIsDone = taskString.split(" \\| ", 0)[1].equals("1");

            String taskName = taskString.split(" \\| ", 0)[2];

            switch (taskType) {
            case TODO:
                ToDo toDoTask = new ToDo(taskName);
                if (taskIsDone) {
                    toDoTask.mark();
                } else {
                    toDoTask.unmark();
                }
                taskList.add(toDoTask);
                break;
            case DEADLINE:
                String by = taskString.split(" \\| ")[3];
                Deadline deadlineTask = new Deadline(taskName, by);
                if (taskIsDone) {
                    deadlineTask.mark();
                } else {
                    deadlineTask.unmark();
                }
                taskList.add(deadlineTask);
                break;
            case EVENT:
                String dates = taskString.split(" \\| ")[3];
                String from = dates.split("to")[0];
                String to = dates.split("to")[1];
                Event eventTask = new Event(taskName, from, to);
                if (taskIsDone) {
                    eventTask.mark();
                } else {
                    eventTask.unmark();
                }
                taskList.add(eventTask);
                break;
            default:
                throw new TaskWithoutTypeException();
            }
        }

        return taskList;
    }
}
