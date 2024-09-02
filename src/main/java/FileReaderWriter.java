import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;

public class FileReaderWriter {
    java.nio.file.Path path;
    TaskList taskList;

    public FileReaderWriter(TaskList taskList) {
        path = java.nio.file.Paths.get("data.txt");
        this.taskList = taskList;
    }

    public String createFile() {
        String output = "";
        try {
            File myObj = new File(String.valueOf(path));
            if (myObj.createNewFile()) {
                output = ("I created " + myObj.getName() + " for you~");
            }
        } catch (IOException e) {
            output = "Aww something went wrong :(\n";
        }
        return output;
    }

    public String writeFile() {
        String output = "";
        try {
            Files.writeString(path, taskList.toFile(), StandardCharsets.UTF_8);
            output = "Saving all your tasks for you~";
        } catch (IOException e) {
            output = "Aww something went wrong :(\n";
        }
        return output;
    }

    public String readFile() {
        String output = "";
        File file = new File(path.toString());
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String taskInput = sc.nextLine();
                String[] taskParts = taskInput.split("\\|");
                switch(taskParts[0]) {
                case "T":
                    Todo newTodo = new Todo(taskParts[2]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newTodo.updateStatus(true);
                    }
                    taskList.addTask(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(taskParts[2], taskParts[3]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newDeadline.updateStatus(true);
                    }
                    taskList.addTask(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(taskParts[2], taskParts[3], taskParts[4]);
                    if (Objects.equals(taskParts[1], "1")) {
                        newEvent.updateStatus(true);
                    }
                    taskList.addTask(newEvent);
                    break;
                }
            }
            output = "I loaded your past data! Aren't I amazing? Come on praise me!";
        } catch (FileNotFoundException ignored) {}
        return output;
    }
}
