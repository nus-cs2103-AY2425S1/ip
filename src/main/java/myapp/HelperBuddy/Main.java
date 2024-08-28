package myapp.helperbuddy;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final String HOME = System.getProperty("user.home");

    private static final String DIRECTORY_PATH = HOME + "/Documents/";

    private static final String FILE_PATH = DIRECTORY_PATH + "TaskInfo.txt";

    public static void main(String[] args) {
        Ui screen = new Ui();
        screen.showWelcomeMessage();
        TaskList taskList = new TaskList();
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + DIRECTORY_PATH);
            } else {
                System.out.println("Failed to create directory: " + DIRECTORY_PATH);
                return;
            }
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + FILE_PATH);
                } else {
                    System.out.println("Failed to create file: " + FILE_PATH);
                    return;
                }
            } catch (IOException e) {
                System.out.println("________________________________________________");
                System.out.println("An error occurred while creating the file.");
                System.out.println("________________________________________________");
                return;
            }
        }
        Storage storage = new Storage(FILE_PATH);
        storage.loadTasks(taskList.getTasks());
        while (true) {
            String taskDescription = screen.readUserInput();
            if (taskDescription.isEmpty()) {
                break;
            } else if (taskDescription.equals("bye")) {
                break;
            } else if (taskDescription.equals("list")) {
                screen.showTaskList(taskList);
            } else if (taskDescription.startsWith("delete")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task removedTask = taskList.deleteTask(taskLabel);
                screen.showTaskRemoved(removedTask, taskList);
            } else if (taskDescription.startsWith("mark")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task currentTask = taskList.getTask(taskLabel);
                screen.showTaskMarked(currentTask);
            } else if (taskDescription.startsWith("unmark")) {
                int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                Task currentTask = taskList.getTask(taskLabel);
                screen.showTaskUnmarked(currentTask);
            } else if (taskDescription.startsWith("find")) {
                String keyword = taskDescription.substring(7).trim();
                List<Task> searchResults = taskList.searchTasks(keyword);
                screen.showSearchResults(searchResults);
            } else {
                Task currentTask = Parser.parseCommand(taskDescription);
                if (currentTask != null) {
                    taskList.addTask(currentTask);
                    screen.showTaskAdded(currentTask, taskList.size());
                }
            }
        }
        storage.saveTasks(taskList.getTasks());
        screen.close();
        screen.showGoodbyeMessage();
    }
}
