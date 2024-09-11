package Johnson.storage;

import Johnson.task.*;

import java.io.*;

/**
 * Handles the storage and retrieval of user data, specifically tasks lists.
 * This class is responsible for saving tasks lists to a file and loading tasks lists from a file.
 */
public class UserData {

    private TaskList tasks;
    private String defaultFileDirectory = "data/testSaveFile.txt";
    private String saveFileDirectory;

    /**
     * Constructs a UserData object with a specified directory.
     *
     * @param directory the directory where tasks will be saved and loaded from.
     */
    public UserData(String directory) {
        saveFileDirectory = directory;
        this.tasks = loadTasks(directory);
        assert tasks != null : "Tasks list should not be null";
    }

    /**
     * Constructs a UserData object with the default directory.
     */
    public UserData() {
        saveFileDirectory = defaultFileDirectory;
        this.tasks = loadTasks(saveFileDirectory);
        assert tasks != null : "Tasks list should not be null";
    }

    public TaskList getTasks() {
        return tasks;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Saves the list of tasks to the instance's save file directory.
     */
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

    /**
     * Loads the list of tasks from the specified file directory.
     *
     * @param fileName the name of the file to load tasks from.
     * @return the list of tasks loaded from the file.
     */
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
