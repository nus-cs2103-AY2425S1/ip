import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFile {
    private static final String DATA_FOLDER_PATH = "./data";
    private static final String FILE_PATH = DATA_FOLDER_PATH + "/bobbybot.txt";

    protected static Task[] getTasksFromFile() throws IOException {
        File taskListFile = getTaskListFile();
        Scanner fileScanner = new Scanner(taskListFile);
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String task = fileScanner.nextLine();
            String[] taskDetails = task.split(" \\| ");
            String taskType = taskDetails[0].trim();
            boolean isDone = taskDetails[1].trim().equals("1");
            String description = taskDetails[2].trim();
            Task newTask;
            switch (taskType) {
            case "T":
                newTask = new ToDo(description);
                break;
            case "D":
                newTask = new Deadline(description, taskDetails[3]);
                break;
            case "E":
                newTask = new Event(description, taskDetails[3], taskDetails[4]);
                break;
            default:
                continue;
            }
            newTask.setIsDone(isDone);
            tasks.add(newTask);
        }
        return tasks.toArray(new Task[0]);
    }

    protected static void saveTasksToFile(Task[] tasks) throws IOException {
        File taskListFile = getTaskListFile();
        FileWriter fileWriter = new FileWriter(taskListFile);
        for (Task task : tasks) {
            String taskFileString = String.format(
                    "%s | %d | %s",
                    task.getTaskType(),
                    task.getIsDone() ? 1 : 0,
                    task.getDescription()
            );
            fileWriter.write(taskFileString + "\n");
        }
        fileWriter.close();
    }

    private static File getTaskListFile() throws IOException {
        // Create data folder if it does not exist
        new File(DATA_FOLDER_PATH).mkdirs();
        File taskList = new File(FILE_PATH);
        taskList.createNewFile();

        return taskList;
    }

}
