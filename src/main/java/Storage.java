import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Task.Task;
import Task.TaskList;
import Task.Deadline;
import Task.Event;
import Task.ToDo;

import exception.CitadelException;
import exception.CitadelTaskNoInput;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File db = new File(filePath);
        try {
            if (db.createNewFile()) {
                System.out.println("File created: " + db.getName());
            } else {
                System.out.println("File already exists. " + db.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public TaskList getTasks() {
        TaskList tasks = new TaskList();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String taskType = line.substring(1, 2);
                String taskString = line.substring(7);
                if (taskType.equals("T")) {
                    tasks.add(createTodo(taskString));
                } else if (taskType.equals("D")) {
                    tasks.add(createDeadline(taskString));
                } else if (taskType.equals("E")) {
                    tasks.add(createEvent(taskString));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public void saveData(TaskList tasks) throws IOException {
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < tasks.size(); i++) {
                bufferedWriter.write(tasks.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
            }

    private static Task createDeadline(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" \\(by: ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }
        String task = words[0].trim();
        String deadline = words[1].substring(0, words[1].length() - 1).trim();

        if (task.isEmpty() || deadline.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime deadlineFormatted = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Deadline(task, deadlineFormatted);
        return t;
    }

    private static Task createEvent(String input) throws CitadelException {
        Task t;
        String[] words = input.split(" \\(from: ");

        if (words.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String task = words[0].trim();
        String[] timeline = words[1].split(" to: ");

        if (timeline.length < 2) {
            throw new CitadelTaskNoInput();
        }

        String from = timeline[0].trim();
        String to = timeline[1].substring(0, timeline[1].length() - 1).trim();

        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CitadelTaskNoInput();
        }

        LocalDateTime fromFormatted = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        LocalDateTime toFormatted = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        t = new Event(task, fromFormatted, toFormatted);
        return t;
    }

    private static Task createTodo(String input) throws CitadelException {
        Task t;
        String todo = input.trim();
        if (todo.isEmpty()) {
            throw new CitadelTaskNoInput();
        }
        t = new ToDo(todo);
        return t;
    }
}
