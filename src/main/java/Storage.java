import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {

    private static final String folderName = "data";
    private static final String fileName = "tasks.txt";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> retrieveTasks() {
        File tasks = new File(folderName, fileName);
        ArrayList<Task> taskList = new ArrayList<>();
        int taskCount = 0;

        try {
            Scanner sc = new Scanner(tasks);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                char taskType = line.charAt(0);
                String[] parts = line.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                String taskNumber = parts[1];
                String taskDescription = parts[2];
                switch (taskType) {
                    case 'T' -> {
                        taskList.add(taskCount, new Todo(taskDescription));
                        if (taskNumber.equals("1")) {
                            taskList.get(taskCount).completeTask();
                        }
                        taskCount++;
                    }
                    case 'D' -> {
                        LocalDateTime taskDeadline = LocalDateTime.parse(parts[3]);
                        taskList.add(taskCount, new Deadline(taskDescription, taskDeadline));
                        if (taskNumber.equals("1")) {
                            taskList.get(taskCount).completeTask();
                        }
                        taskCount++;
                    }
                    case 'E' -> {
                        LocalDateTime taskStartTime = LocalDateTime.parse(parts[3]);
                        LocalDateTime taskEndTime = LocalDateTime.parse(parts[4]);
                        taskList.add(taskCount, new Event(taskDescription, taskStartTime, taskEndTime));
                        if (taskNumber.equals("1")) {
                            taskList.get(taskCount).completeTask();
                        }
                        taskCount++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Ui.showErrorMessage("File not found:" + tasks.getAbsolutePath());
        }
        return taskList;
    }

    public void markComplete(Task t) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            List<String> updatedLines = lines.stream().map(line -> {
                String[] parts = line.split("\\|");
                if (parts.length > 2 && parts[2].trim().equals(t.getName())) {
                    parts[1] = " 1 "; // Change the status from 0 to 1
                    return String.join("|", parts).trim();
                }
                return line;
            }).toList();

            // Write the updated lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void markIncomplete(Task t) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));
            List<String> updatedLines = lines.stream().map(line -> {
                String[] parts = line.split("\\|");
                if (parts.length > 2 && parts[2].trim().equals(t.getName())) {
                    parts[1] = " 0 "; // Change the status from 1 to 0
                    return String.join("|", parts).trim();
                }
                return line;
            }).toList();

            // Write the updated lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void deleteTask(Task t) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(this.filePath));

            // Filter out the line corresponding to the task to be deleted
            List<String> updatedLines = lines.stream()
                    .filter(line -> {
                        String[] parts = line.split("\\|");
                        return !(parts.length > 2 && parts[2].trim().equals(t.getName()));
                    })
                    .collect(Collectors.toList());

            // Write the remaining lines back to the file
            try (FileWriter writer = new FileWriter(this.filePath, false)) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }

    public void writeTodoToFile(Todo t) {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }
            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("T | 0 | " + t.getName() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void writeDeadlineToFile(Deadline t) {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }
            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("D | 0 | " + t.getName() + " | " + t.getTime() + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public void writeEventToFile(Event t) {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) {
                dataFolder.mkdirs(); // Create the folder if it doesn't exist
            }

            try (FileWriter writer = new FileWriter(this.filePath, true)) {
                writer.write("E | 0 | " + t.getName() + " | " + t.getStartTime() + " | " + t.getEndTime()
                        + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

}
