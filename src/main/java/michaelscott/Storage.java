package michaelscott;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import michaelscott.task.Deadline;
import michaelscott.task.Task;
import michaelscott.task.Event;
import michaelscott.task.TaskList;
import michaelscott.task.Todo;

public class Storage {
    private final String filePath;

    public Storage(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("./userdata"));
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
        this.filePath = "./userdata/tasks.txt";
        loadTasks(taskList);
    }

    public Storage() {
        this.filePath = "./";
    }

    /**
     * Reads the task from file and updates the task list.
     *
     * @param todo the task list.
     */
    public void saveTasks(ArrayList<Task> todo) throws MichaelScottException {
        File file = new File(this.filePath);
        FileWriter fw;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            for (int i = 0; i < todo.toArray().length; i++) {
                fw.write(todo.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new MichaelScottException("Error saving data: " + e.getMessage());
        }
    }

    public void loadTasks(TaskList taskList) {
        File file = new File(this.filePath);
        Scanner sc;
        String line;
        try {
            file.createNewFile();
            sc = new Scanner(file);
            while (sc.hasNext()) {
                line = sc.nextLine();
                taskList.addTask(parseTask(line));
            }
            sc.close();
        } catch (IOException | MichaelScottException e ) {
            Ui.showError(e.getMessage());
        }
    }

    public Task parseTask(String line) throws MichaelScottException {
        String[] split = line.split(" \\| ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Task task;
        int status = Integer.parseInt(split[1]);
        String name = split[2];
        String taskType = split[0].trim();
        if (taskType.equals("T")) {
            task = new Todo(name);
        } else if (taskType.equals("D")) {
            task = new Deadline(name, LocalDateTime.parse(split[3], formatter));
        } else if (taskType.equals("E")) {
            task = new Event(name, LocalDateTime.parse(split[3], formatter), LocalDateTime.parse(split[4], formatter));
        } else {
            throw new MichaelScottException("Wrong stuff");
        }

        if (status == 1) {
            task.completeTask();
        } else {
            task.undoTask();
        }

        return task;
    }
}
