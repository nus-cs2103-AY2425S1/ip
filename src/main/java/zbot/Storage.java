package zbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

import zbot.task.Deadline;
import zbot.task.Task;
import zbot.task.ToDo;
import zbot.task.Event;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            String folderPath = filePath.substring(0, filePath.lastIndexOf("/"));
            File folder = new File(folderPath);
            folder.mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("I/O error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(String text) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("I/O error occurred.");
            e.printStackTrace();
        }
    }

    // Save tasks to file using "," as delimiter
    public void save(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            // Append task type, status, and description, separated by ","
            char taskType = task instanceof ToDo ? 'T' : task instanceof Deadline ? 'D' : 'E';
            int taskStatus = task.getStatus() ? 1 : 0;
            sb.append(taskType).append(",").append(taskStatus)
                    .append(",").append(task.getDescription());

            // Append task-specific details
            if (taskType == 'D') {
                sb.append(",").append(Parser.formatDateTimeToInput(((Deadline) task).getDueDate()));
            } else if (taskType == 'E') {
                sb.append(",").append(Parser.formatDateTimeToInput(((Event) task).getStartDate()))
                        .append(",").append(Parser.formatDateTimeToInput(((Event) task).getEndDate()));
            }
            sb.append("\n");
        }

        writeToFile(sb.toString());
    }

    // Load tasks from file using "," as delimiter
    public ArrayList<Task> load() throws ZBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                String[] taskParts = sc.nextLine().split(",");
                Task task;
                if (taskParts[0].equals("T")) {
                    task = new ToDo(taskParts[2]);
                } else if (taskParts[0].equals("D")) {
                    task = new Deadline(taskParts[2], Parser.parseDateTime(taskParts[3]));
                } else {
                    task = new Event(taskParts[2],
                            Parser.parseDateTime(taskParts[3]),
                            Parser.parseDateTime(taskParts[4]));
                }
                if (taskParts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new ZBotException("No saved data found.");
        } catch (DateTimeParseException e) {
            System.out.println(
                    "Please enter a valid date and time format (dd/MM/yyyy HHmm, dd/MM/yyyy)!\n");
        }
        return tasks;
    }
}
