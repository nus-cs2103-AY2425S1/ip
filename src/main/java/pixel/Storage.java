package pixel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.TaskList;
import pixel.task.TaskType;
import pixel.task.Todo;

/**
 * The Storage class handles the loading and writing of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path to load and write tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns a TaskList object containing the loaded
     * tasks.
     *
     * @return A TaskList object containing the loaded tasks.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();
        BufferedReader br = new BufferedReader(new FileReader(this.filePath));
        String line = br.readLine();
        while (line != null) {
            String[] taskRepresentation = line.split(",");
            TaskType taskType = TaskType.valueOf(taskRepresentation[0]);
            switch (taskType) {
            case E:
                Task eventTask = new Event(taskRepresentation[2], taskRepresentation[1]);
                taskList.addTask(eventTask);
                break;
            case D:
                Task deadlineTask = new Deadline(taskRepresentation[2], taskRepresentation[1]);
                taskList.addTask(deadlineTask);
                break;
            case T:
                Task todoTask = new Todo(taskRepresentation[2], taskRepresentation[1]);
                taskList.addTask(todoTask);
                break;
            default:
                break;
            }
            line = br.readLine();
        }
        br.close();
        return taskList;
    }

    /**
     * Writes the tasks from the TaskList object to the file.
     *
     * @param taskList The TaskList object containing the tasks to be written.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeFile(TaskList taskList) throws IOException {
        File file = new File(this.filePath);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < taskList.size(); i++) {
            writer.write(taskList.getTaskAtIndex(i).getData() + "\n");
        }
        writer.close();
    }
}
