package sigmabot.util;

import sigmabot.task.Task;
import sigmabot.task.Todo;
import sigmabot.task.Deadline;
import sigmabot.task.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ListReader {

    /**
     * Reads tasks from a file and parses them into a Map.
     *
     * @param filePath The path to the file containing the tasks.
     * @return A map where the key is the task name and the value is the Task object.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public Map<String, Task> readTasksFromFile(String filePath) throws IOException {
        Map<String, Task> taskList = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();  // Remove leading and trailing whitespace
                if (line.isEmpty()) {
                    continue;  // Skip empty lines
                }

                if (line.startsWith("[T]")) {
                    Task task = parseTodoTask(line, br);
                    taskList.put(task.getName(), task);
                } else if (line.startsWith("[D]")) {
                    Task task = parseDeadlineTask(line, br);
                    taskList.put(task.getName(), task);
                } else if (line.startsWith("[E]")) {
                    Task task = parseEventTask(line, br);
                    taskList.put(task.getName(), task);
                } else {
                    System.out.println("Unknown task format: " + line);
                }
            }
        }
        return taskList;
    }

    /**
     * Parses a Todo task from the input line.
     *
     * @param line The line containing the task type and status.
     * @param br   The BufferedReader to read the next line for the description.
     * @return A Todo task.
     * @throws IOException If an I/O error occurs reading from the BufferedReader.
     */
    private Task parseTodoTask(String line, BufferedReader br) throws IOException {
        String[] parts = line.split(" ", 4);
        boolean isDone = parts[2].equals("[X]");
        String name = parts[3].trim();  // Get task name

        // Read the description line
        String descriptionLine = br.readLine().trim();
        String description = descriptionLine.replaceFirst("Description: ", "").trim();  // Remove "Description: " prefix

        Task task = new Todo(name, description);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Parses a Deadline task from the input line.
     *
     * @param line The line containing the task type and status.
     * @param br   The BufferedReader to read the next lines for the description and byTime.
     * @return A Deadline task.
     * @throws IOException If an I/O error occurs reading from the BufferedReader.
     */
    private Task parseDeadlineTask(String line, BufferedReader br) throws IOException {
        String[] parts = line.split(" ", 4);
        boolean isDone = parts[2].equals("[X]");
        String name = parts[3].trim();

        String descriptionLine = br.readLine().trim();
        String description = descriptionLine.replaceFirst("Description: ", "").trim();

        String byTimeLine = br.readLine().trim();
        String byTimeStr = byTimeLine.replaceFirst("By: ", "").trim();

        // Use DateParser to convert the date format
        String formattedByTimeStr = DateParser.parseDate(byTimeStr);
        LocalDate byTime = LocalDate.parse(formattedByTimeStr);

        Task task = new Deadline(name, description, byTime);
        if (isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Parses an Event task from the input line.
     *
     * @param line The line containing the task type and status.
     * @param br   The BufferedReader to read the next lines for the description, startTime, endTime, and location.
     * @return An Event task.
     * @throws IOException If an I/O error occurs reading from the BufferedReader.
     */
    private Task parseEventTask(String line, BufferedReader br) throws IOException {
        String[] parts = line.split(" ", 4);
        boolean isDone = parts[2].equals("[X]");
        String name = parts[3].trim();

        String descriptionLine = br.readLine().trim();
        String description = descriptionLine.replaceFirst("Description: ", "").trim();

        String startTimeLine = br.readLine().trim();
        String startTimeStr = startTimeLine.replaceFirst("Start Time: ", "").trim();
        String formattedStartTimeStr = DateParser.parseDate(startTimeStr);
        LocalDate startTime = LocalDate.parse(formattedStartTimeStr);

        String endTimeLine = br.readLine().trim();
        String endTimeStr = endTimeLine.replaceFirst("End Time: ", "").trim();
        String formattedEndTimeStr = DateParser.parseDate(endTimeStr);
        LocalDate endTime = LocalDate.parse(formattedEndTimeStr);

        String locationLine = br.readLine().trim();
        String location = locationLine.replaceFirst("Location: ", "").trim();

        Task task = new Event(name, description, startTime, endTime, location);
        if (isDone) {
            task.markDone();
        }
        return task;
    }
}
