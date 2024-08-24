import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SavedTasks {
    /**
     * The path of the file to save the list of tasks.
     */
    private String path;

    /**
     * Constructor for a saved list of tasks.
     *
     * @param path The path of the file to save the list of tasks.
     */
    public SavedTasks(String path) {
        this.path = path;
    }

    /**
     * Add a task to the list of saved tasks.
     *
     * @param task The task to add.
     * @throws IOException If an I/O error occurs.
     */
    public void add(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.path, true);
        fw.write(task.toString() + System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Update a task in the list of saved tasks by item number.
     *
     * @param itemNum The item number of the task to update.
     * @param task The updated task.
     * @throws IOException If an I/O error occurs.
     */
    public void update(int itemNum, Task task) throws IOException {
        File inputFile = new File(this.path);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineNum = 1;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if (lineNum == itemNum) {
                writer.write(task.toString() + System.getProperty("line.separator"));
            } else {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            lineNum++;
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    /**
     * Delete a task in the list of saved tasks by item number.
     *
     * @param itemNum The item number of the task to delete.
     * @throws IOException If an I/O error occurs.
     */
    public void delete(int itemNum) throws IOException {
        File inputFile = new File(this.path);
        File tempFile = new File("temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        int lineNum = 1;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if (lineNum != itemNum) {
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            lineNum++;
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }

    /**
     * Parses and adds the tasks from a file.
     *
     * @throws IOException If an I/O error occurred.
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(this.path);
        f.createNewFile();

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                Task task = this.parseTask(line);
                tasks.add(task);
            } catch (InvalidInputException e) {

            }
        }

        return tasks;
    }

    /**
     * Parses a task string, creates the task and returns it.
     *
     * @param taskString The task string to parse.
     * @return The task created from the specified task string.
     * @throws InvalidInputException If the task string could not be parsed.
     */
    private Task parseTask(String taskString) throws InvalidInputException {
        Matcher todoMatcher = Pattern.compile("^\\[T]\\[([X ])] (.*)$").matcher(taskString);
        Matcher deadlineMatcher = Pattern.compile("^\\[D]\\[([X ])] (.*) \\(by: (.*)\\)$").matcher(taskString);
        Matcher eventMatcher = Pattern.compile("^\\[E]\\[([X ])] (.*) \\(from: (.*) to: (.*)\\)$").matcher(taskString);

        boolean isCompleted;
        Task task;
        if (todoMatcher.find()) {
            isCompleted = Objects.equals(todoMatcher.group(1), "X");
            String name = todoMatcher.group(2);

            task = new ToDo(name);
        } else if (deadlineMatcher.find()) {
            isCompleted = Objects.equals(deadlineMatcher.group(1), "X");
            String name = deadlineMatcher.group(2);
            String deadline = deadlineMatcher.group(3);

            task = new Deadline(name, deadline);
        } else if (eventMatcher.find()) {
            isCompleted = Objects.equals(eventMatcher.group(1), "X");
            String name = eventMatcher.group(2);
            String start = eventMatcher.group(3);
            String end = eventMatcher.group(4);

            task = new Event(name, start, end);
        } else {
            throw new InvalidInputException("Could not parse task.");
        }

        if (isCompleted) {
            task.mark(true);
        }

        return task;
    }
}
