import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    TaskList tasks;

    public Storage(String filePath, TaskList tasks) throws WenJigglyBotException {
        File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("No saved tasks found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            System.out.println("Tasks loaded from " + file.getPath());
        } catch (IOException e) {
            throw new WenJigglyBotException("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }

    private static Task parseTask(String line) {
        // Example format: 1. [T][X] task description
        String[] parts = line.split(" ", 3);
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[1].substring(1, 2);
        boolean isDone = parts[1].charAt(3) == 'X';
        String description = parts[2];

        switch (taskType) {
        case "T": // ToDo task
            ToDoTask todo = new ToDoTask(description.split(" ")[1]);
            if (isDone) {
                todo.markTask();
            }
            return todo;
        case "D": // Deadline task
            // Format: [D][ ] description (by: date/time)
            String[] deadlineParts = description.split("\\(by: ");
            if (deadlineParts.length == 2) {
                String unprocessedTaskDescription = deadlineParts[0].trim();
                String[] splitted = unprocessedTaskDescription.split(" ");
                String taskDescription;
                if (splitted.length != 1) {
                    taskDescription = splitted[1];
                } else {
                    taskDescription = splitted[0];
                }
                String deadline = deadlineParts[1].replace(")", "").trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                DeadlineTask deadlineTask = new DeadlineTask(taskDescription, LocalDate.parse(deadline, formatter));
                if (isDone) {
                    deadlineTask.markTask();
                }
                return deadlineTask;
            }
            break;
        case "E": // Event task
            // Format: [E][ ] description (from: start time to: end time)
            String[] eventParts = description.split("\\(from: ");
            if (eventParts.length == 2) {
                String taskDescription = eventParts[0].trim();
                String[] timeParts = eventParts[1].split(" to: ");
                if (timeParts.length == 2) {
                    String startTime = timeParts[0].trim();
                    String endTime = timeParts[1].replace(")", "").trim();
                    EventTask eventTask = new EventTask(taskDescription, startTime, endTime);
                    if (isDone) {
                        eventTask.markTask();
                    }
                    return eventTask;
                }
            }
            break;
        }

        return null; // return null if the task could not be parsed
    }


}