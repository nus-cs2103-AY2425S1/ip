package ps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    private static final Pattern TODO_PATTERN
            = Pattern.compile("\\[T\\]\\[( |X)\\] (.+)");
    private static final Pattern DEADLINE_PATTERN
            = Pattern.compile("\\[D\\]\\[( |X)\\] (.+) \\(by: (.+)\\)");
    private static final Pattern EVENT_PATTERN
            = Pattern.compile("\\[E\\]\\[( |X)\\] (.+) \\(from: (.+), to: (.+)\\)");

    private File taskFile;

    public Storage(String filePath) {
        this.taskFile = new File(filePath);
    }

    /**
     * Takes each line from the task file and parses it using regex.
     * 
     * @param taskLine the line from the text file.
     * @return a Task object representing the task in the line.
     */
    private Task processTaskLine(String taskLine) {
         // try to match the task as a todo
        Matcher todoMatcher = TODO_PATTERN.matcher(taskLine);

        if (todoMatcher.find()) {
            Todo todo = new Todo(todoMatcher.group(2));
            if (todoMatcher.group(1).equals("X")) {
                todo.markAsCompleted();
            }
            return todo;
        }

        // otherwise try to match task as a deadline
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(taskLine);

        if (deadlineMatcher.find()) {
            Deadline deadline = new Deadline(
                deadlineMatcher.group(2),
                LocalDateTime.parse(deadlineMatcher.group(3))
            );
            if (deadlineMatcher.group(1).equals("X")) {
                deadline.markAsCompleted();
            }
            return deadline;
        }

        // finally try to match task as an event
        Matcher eventMatcher = EVENT_PATTERN.matcher(taskLine);

        if (eventMatcher.find()) {
            Event event = new Event(
                eventMatcher.group(2),
                LocalDateTime.parse(eventMatcher.group(3)),
                LocalDateTime.parse(eventMatcher.group(4))
            );
            if (eventMatcher.group(1).equals("X")) {
                event.markAsCompleted();
            }
            return event;
        }

        // if all fails, return null and handle exception
        return null;
    }

    /**
     * Loads the tasks as saved in the task file.
     * If the file does not exist, it is created
     * and an empty list is returned instead.
     * 
     * @return an arraylist of tasks.
     */
    public ArrayList<Task> loadTasksFromMemory() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner taskFileReader = new Scanner(this.taskFile);
            while (taskFileReader.hasNextLine()) {
                String data = taskFileReader.nextLine();
                if (!data.equals("----TASKS----")) {
                    taskList.add(processTaskLine(data));
                }
            }

            taskFileReader.close();
        } catch (FileNotFoundException e) {
            try {
                taskFile.createNewFile();
            } catch (IOException | DateTimeException f) {
                f.printStackTrace();
                System.out.println("Error with task file. Please delete the task file and try again.");
            }
        }

        return taskList;
    }

    /**
     * Writes the tasks to the given text file at the end of the program.
     * 
     * @param taskList the list of tasks in directly writeable string format.
     */
    public void writeTasksToMemory(String taskList) {
        try {
            FileWriter taskFileWriter = new FileWriter(this.taskFile);
            taskFileWriter.write(taskList);
            taskFileWriter.close();
            assert this.taskFile.exists() : "File does not exist but it still wrote the tasks somewhere";
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops! There was an error when trying to store your tasks.");
        }
    }
}
