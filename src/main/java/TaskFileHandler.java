import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskFileHandler {
    private String filePath;

    public TaskFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void createFileIfDoesNotExist() {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Hey man, something went wrong when I tried creating the file or directory.");
        }
    }

    private static void writeToFile(String filePath, String textToAdd, boolean append) throws IOException {
        FileWriter fw = new FileWriter(filePath, append);
        fw.write(textToAdd);
        fw.close();
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try {
            writeToFile(filePath, "", false);
            for (Task task : tasks) {
                String taskString;
                // String format will be Type | isDone | Description | Time Constraint
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    taskString = "Deadline | " + deadline.getIsDoneString() + " | "
                            + deadline.getTaskDescription() + " | "
                            + deadline.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    taskString = "Event | " + event.getIsDoneString() + " | "
                            + event.getTaskDescription() + " | "
                            + "from " + event.getStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                            + " to " + event.getEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                } else {
                    taskString = "Todo | " + task.getIsDoneString() + " | " + task.getTaskDescription() + " | No time constraint";
                }
                writeToFile(filePath, taskString + "\n", true);
            }
        } catch (IOException e) {
            System.out.println("Hey man an error occured while saving tasks!");
        }
    }

    public ArrayList<Task> loadTasks(ArrayList<Task> tasks) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length < 4) {
                    System.out.println("Skipping malformed line: " + line);
                    continue; // Skip lines that do not have enough parts
                }

                String type = parts[0].trim();
                String isDoneString = parts[1].trim();
                String description = parts[2].trim();
                String timeConstraint = parts[3].trim();

                Task task;
                try {
                    switch (type) {
                        case "Todo":
                            task = new Todo(description);
                            break;
                        case "Deadline":
                            String deadlineDateTimeString = timeConstraint.replace("by:", "").trim();
                            LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeString, formatter);
                            task = new Deadline(description, deadlineDateTime);
                            break;
                        case "Event":
                            String[] timeParts = timeConstraint.split(" to ");
                            if (timeParts.length < 2) {
                                throw new IllegalArgumentException("Event time constraint is malformed: " + timeConstraint);
                            }
                            LocalDateTime start = LocalDateTime.parse(timeParts[0].replace("from ", "").trim(), formatter);
                            LocalDateTime end = LocalDateTime.parse(timeParts[1].trim(), formatter);
                            task = new Event(description, start, end);
                            break;
                        default:
                            throw new IllegalArgumentException("Unknown task type: " + type);
                    }

                    if (isDoneString.equals("X")) {
                        task.setIsDone(true);
                    }

                    tasks.add(task);
                } catch (DateTimeParseException | IllegalArgumentException e) {
                    System.out.println("Skipping malformed task due to error: " + e.getMessage());
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (DateTimeException e) {
            System.out.println("DateTime error while loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while loading tasks: " + e.getMessage());
        }

        return tasks;
    }

}
