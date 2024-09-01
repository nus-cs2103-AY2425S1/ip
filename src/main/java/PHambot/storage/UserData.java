package PHambot.storage;

import PHambot.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class UserData {

    private TaskList tasks;
    private String defaultFileDirectory = "data/testSaveFile.txt";
    private String saveFileDirectory;

    public UserData(String directory) {
        saveFileDirectory = directory;
        this.tasks = loadTasks(directory);
    }

    public UserData() {
        saveFileDirectory = defaultFileDirectory;
        this.tasks = loadTasks(saveFileDirectory);
    }

    public TaskList getTasks() {
        return this.tasks;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFileDirectory))) {
            for (int i = 0; i < tasks.taskCount(); i++) {
                Task task = tasks.getTask(i);
                if (task instanceof Deadline) {
                    writer.write("D" + '/' + task.getTaskStatus() + "/" + task.getTaskName() + "/" + ((Deadline) task).getDate().toString());
                    if (((Deadline) task).getTime() != null) {
                        writer.write("/" + ((Deadline) task).getTime().toString());
                    }
                }
                else if (task instanceof Event) {
                    writer.write("E" +"/" + task.getTaskStatus() + "/" +task.getTaskName() + "/" + ((Event) task).getDate());
                    if (((Event) task).getTime() != null) {
                        writer.write("/" + ((Event) task).getTime().toString());
                    }
                }
                else {
                    writer.write("T" + "/" + task.getTaskStatus() + "/" + task.getTaskName());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TaskList loadTasks(String fileName) {
        TaskList taskList = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String task;
            while ((task = reader.readLine()) != null) {
                String[] splitTask = task.split("/");
                if (splitTask[0].equals("T")) {
                    ToDo newToDo = new ToDo();
                    newToDo.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newToDo.setTaskName(splitTask[2]);
                    taskList.addTask(newToDo);
                }
                if (splitTask[0].equals("D")) {
                    Deadline newDeadline = new Deadline();
                    newDeadline.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newDeadline.setTaskName(splitTask[2]);
                    newDeadline.setDate(splitTask[3]);
                    if (splitTask.length > 4) {
                        newDeadline.setTime(splitTask[4]);
                    }
                    taskList.addTask(newDeadline);
                }
                if (splitTask[0].equals("E")) {
                    Event newEvent = new Event();
                    newEvent.setTaskStatus(Boolean.parseBoolean(splitTask[1]));
                    newEvent.setTaskName(splitTask[2]);
                    newEvent.setDate(splitTask[3]);
                    if (splitTask.length > 4) {
                        newEvent.setTime(splitTask[4]);
                    }
                    taskList.addTask(newEvent);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
