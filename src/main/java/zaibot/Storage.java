package zaibot;

import zaibot.exception.ZaibotException;
import zaibot.task.DeadlineTask;
import zaibot.task.EventTask;
import zaibot.task.Task;
import zaibot.task.ToDoTask;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Storage {

    private enum Status {
        INCOMPLETE(false),
        COMPLETE(true);

        private final boolean isComplete;

        Status(boolean status) {
            this.isComplete = status;
        }

        public boolean checkComplete() {
            return isComplete;
        }
    }

    private String link;

    public Storage(TaskList taskList) {
        try {
            Files.createDirectories(Paths.get("../data"));
        } catch (IOException e) {
            Ui.displayError(e);
        }
        this.link = "../data/tasks.txt";
        readFromFile(taskList);
    }

    /**
     * Saves the task list to the file on disk.
     *
     * @param taskList The list of tasks.
     */
    public void saveToFile(TaskList taskList) {
        File file = new File(link);
        BufferedWriter writer;

        try {
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            boolean first = true;
            for (Task task : taskList.retrieveTasks()) {
                if (!first) {
                    writer.newLine();
                }
                writer.write(convertTaskToString(task));
                first = false;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the tasks from the file on disk and updates the task list.
     *
     * @param taskList The task list.
     */
    public void readFromFile(TaskList taskList) {
        File file = new File(this.link);
        BufferedReader reader;

        try {
            file.createNewFile();
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                taskList.addTask(parseLine(line));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | ZaibotException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a line from the file in the format specified
     * Helper function for the readFromFile function.
     *
     * @param input A line from the file on disk
     * @return The Task object with data from the input line
     * @throws ZaibotException if the line is not formatted as expected.
     */
    public Task parseLine(String input) throws ZaibotException {
        String[] tokens = input.split(" \\| ");

        Task task;

        Status status = Status.valueOf(tokens[1].toUpperCase());
        String name = tokens[2];

        task = switch (tokens[0].trim()) {
            case "T" -> new ToDoTask(name);
            case "D" -> new DeadlineTask(name, LocalDateTime.parse(tokens[3]));
            case "E" -> new EventTask(name, LocalDateTime.parse(tokens[3]), LocalDateTime.parse(tokens[4]));
            default -> throw new ZaibotException("Saved file data not in expected format.");
        };

        task.setDone(status.checkComplete());

        return task;
    }

    public String convertTaskToString(Task task) {
        return task.toSaveString();
    }
}
