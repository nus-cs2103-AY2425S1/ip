import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String filePathWithoutFile;

    public Storage(String filePath, String filePathWithoutFile) {
        this.filePath = filePath;
        this.filePathWithoutFile = filePathWithoutFile;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            boolean directoriesCreated = new File(this.filePathWithoutFile).mkdirs();
            if (!f.createNewFile()) {
                Scanner fileReader = new Scanner(f);
                while (fileReader.hasNext()) {
                    // task is in format {type of task} | {0 if not completed, else 1} | {name of task}
                    // | {other task attributes}
                    String task = fileReader.nextLine();
                    try {
                        Task newTask = Storage.getTask(task);
                        tasks.add(newTask);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.print("Task is not in the expected format: " + task);
                        Ui.print("Removed from task list!");
                    }
                }
                fileReader.close();
            }
            return tasks;
        } catch (IOException e) {
            Ui.print("An error occurred: " + e.getMessage());
            Ui.print("Resetting to an empty task list for you!");
            return new ArrayList<>();
        }
    }

    private static Task getTask(String task) {
        String[] taskItems = task.split(" \\| ");
        String taskType = taskItems[0];
        String completed = taskItems[1];
        String taskDescription = taskItems[2];
        Task newTask;
        if (taskType.equals("T")) {
            newTask = new ToDo(taskDescription);
        } else if (taskType.equals("D")) {
            newTask = new Deadline(taskDescription, LocalDate.parse(taskItems[3]));
        } else {
            newTask = new Event(taskDescription, LocalDate.parse(taskItems[3]), LocalDate.parse(taskItems[4]));
        }
        if (completed.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    public void writeToFile(String formattedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(formattedTasks);
            fw.close();
        } catch (IOException e) {
            Ui.print("Something went wrong: " + e.getMessage());
        }
    }
}
