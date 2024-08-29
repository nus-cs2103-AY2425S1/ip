package pixel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pixel.task.Deadline;
import pixel.task.Event;
import pixel.task.Task;
import pixel.task.TaskList;
import pixel.task.TaskType;
import pixel.task.Todo;

import java.io.File;
import java.io.FileWriter;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

            }
            line = br.readLine();
        }
        br.close();
        return taskList;
    }

    public void writeFile(TaskList taskList) throws IOException {
        File file = new File(this.filePath);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < taskList.size(); i++) {
            writer.write(taskList.getTaskAtIndex(i).getData() + "\n");
        }
        writer.close();
    }
}
