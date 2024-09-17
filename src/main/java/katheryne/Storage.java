package katheryne;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import katheryne.exceptions.InvalidInputException;

/**
 * Handles the reading and writing of tasks from and to the local file.
 */
public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Save the tasks to a local file at the specified path.
     * @param l current task list
     */
    public void save(TaskList l) {
        try {
            String str = "";
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Task t : l.getTaskList()) {
                str = str + t.toSaveString() + '\n';
            }
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read in tasks from the specified file.
     */
    public TaskList load() throws FileNotFoundException, InvalidInputException {
        TaskList list = new TaskList();
        List<String> lines = new ArrayList<>();
        try {
            lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(path));
        } catch (IOException e) {
            return new TaskList();
        }
        try {
            for (String str : lines) {
                String[] fullCommand = str.split("\\|");
                if (fullCommand.length < 3) {
                    throw new InvalidInputException("Invalid task format: " + str);
                }
                Task task = parseTask(fullCommand);
                if (fullCommand[1].trim().equals("1")) {
                    task.mark();
                }
                list.addTask(task);
            }
        } catch (InvalidInputException e) {
            System.out.println("Invalid format: " + e.getMessage());
            return new TaskList();
        }
        return list;
    }

    private Task parseTask(String[] fullCommand) throws InvalidInputException {
        if (fullCommand.length < 3) {
            throw new InvalidInputException("Invalid task data format: " + String.join(" ", fullCommand));
        }

        String commandWord = fullCommand[0].trim();
        Task task = null;

        switch (commandWord) {
        case "D":
            if (fullCommand.length != 4) {
                throw new InvalidInputException("Invalid format for Deadline task.");
            }
            task = createDeadlineTask(fullCommand);
            break;
        case "T":
            if (fullCommand.length != 3) {
                throw new InvalidInputException("Invalid format for ToDo task.");
            }
            String description = fullCommand[2];
            task = new ToDo(description);
            break;
        case "E":
            if (fullCommand.length != 4) {
                throw new InvalidInputException("Invalid format for Event task.");
            }
            task = createEventTask(fullCommand);
            break;
        default:
            throw new InvalidInputException("Unknown task type: " + commandWord);
        }

        return task;
    }

    private Deadline createDeadlineTask(String[] fullCommand) throws InvalidInputException {
        if (fullCommand.length != 4) {
            throw new InvalidInputException("Invalid format for Deadline task.");
        }
        String description = fullCommand[2];
        String dueDate = fullCommand[3];
        String formattedDueDate = convertToExpectedDateFormat(dueDate);
        return new Deadline(description, formattedDueDate);
    }

    private String convertToExpectedDateFormat(String dueDate) throws InvalidInputException {
        dueDate = dueDate.trim();
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dueDate, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return date.format(outputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format: " + dueDate);
        }
    }




    private Event createEventTask(String[] fullCommand) throws InvalidInputException {
        String description = fullCommand[2];
        String timeDetails = fullCommand[3];
        String[] times = timeDetails.split("-", 2);
        if (times.length != 2) {
            throw new InvalidInputException("Invalid time format for event task.");
        }
        String from = times[0];
        String to = times[1];
        return new Event(description, from, to);
    }
}
