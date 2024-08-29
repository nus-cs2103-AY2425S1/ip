package storage;

import exception.PrimoException;
import tasks.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    // Deals with loading tasks from the file and saving tasks in the file
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Task[] load() throws PrimoException, IOException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Path filePath = Paths.get(this.filePath);
            List<String> lines = Files.readAllLines(filePath);
            for (String s : lines) {
                String[] words = s.split(" ");
                boolean isDone;
                String name = "";
                String deadline = "";
                String from = "";
                String to = "";
                switch (words[0].charAt(3)) {
                case 'T':
                    int todoFromIndex = 9;
                    String todoDescription = s.substring(todoFromIndex).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newToDoTask = new ToDoTask(todoDescription);
                    if (isDone) {
                        newToDoTask.markAsDone();
                    }
                    list.add(newToDoTask);
                    break;
                case 'D':
                    int deadlineFromIndex = 9;
                    int deadlineToIndex = s.indexOf("(by:");
                    String deadlineDescription = s.substring(deadlineFromIndex, deadlineToIndex).trim();
                    String dueTime = s.substring(deadlineToIndex + 4, s.indexOf(')')).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newDeadlineTask = new DeadlineTask(deadlineDescription, dueTime);
                    if (isDone) {
                        newDeadlineTask.markAsDone();
                    }
                    list.add(newDeadlineTask);
                    break;
                case 'E':
                    String eventDescription = s.substring(9, s.indexOf("(from:")).trim();
                    String eventFromTime = s.substring(s.indexOf("from: ") + 6, s.indexOf("to: ")).trim();
                    String eventToTime = s.substring(s.indexOf("to: ") + 4, s.indexOf(")")).trim();
                    isDone = s.charAt(6) == 'X';
                    Task newEventTask = new EventTask(eventDescription, eventFromTime, eventToTime);
                    if (isDone) {
                        newEventTask.markAsDone();
                    }
                    list.add(newEventTask);
                    break;
                }
            }
            return list.toArray(new Task[0]);
        } catch (IOException e) {
            Path directoryPath = Paths.get("./data");
            Path filePath = directoryPath.resolve("data.txt");
            Files.createDirectories(directoryPath);
            Files.createFile(filePath);
        }
        return null; // shouldn't reach here
    }

    public void updateStorage(TaskList taskList) {
        ArrayList<Task> list = taskList.getTasks();
        int len = list.size();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String output = i + 1 + "." + list.get(i);
            data.append(output);
            data.append("\n");
        }
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            writer.write(data.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
