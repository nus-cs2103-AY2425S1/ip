package duke;

import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String STATE_FILE_DIRECTORY = "./data";
    private static final String STATE_FILE = "save.txt";

    public static List<Task> loadStateFileToTasksList() throws IOException {
        Path dirPath = Paths.get(Storage.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(Storage.STATE_FILE);

        if (Files.notExists(dirPath)) { // create the SAVE_DIRECTORY folder if it doesn't exist
            Files.createDirectories(dirPath);
        }

        if (Files.notExists(filePath)) { // create an empty file initially
            Files.createFile(filePath);
        }

        List<String> lines = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath);
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // convert the state file `lines` to an arraylist of tasks
        List<Task> tasks = new ArrayList<>();
        for (String state : lines) {
            tasks.add(Storage.convertStateToTask(state));
        }

        return tasks;
    }

    private static Task convertStateToTask(String state) {
        // format of state file below
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th 2-4pm
        String[] taskInformation = state.split(" \\| ");
        String description = taskInformation[2];
        Task task;

        if (taskInformation[0].equals("T")) {
            task = new ToDos(description);
        } else if (taskInformation[0].equals("D")) {
            String by = taskInformation[3];

            try {
                task = new Deadline(description, by);
            } catch (InvalidDeadlineException e) {
                System.out.println(e.getMessage() + "error converting task back to deadline");
                return null;
            };
        } else {
            String[] times = taskInformation[3].split("-");
            System.out.println(times[0] + " " + times[1]);
            String from = times[0];
            String to = times[1];

            try {
                task = new Event(description, from, to);
            } catch (InvalidEventException e) {
                System.out.println(e.getMessage() + "error converting task back to event");
                return null;
            };
        }

        if (taskInformation[1].equals("1")) {
            task.setDone();
        }

        return task;
    }

    public static void saveTasksListToStateFile (List<Task> tasks) throws IOException {
        Path dirPath = Paths.get(Storage.STATE_FILE_DIRECTORY);
        Path filePath = dirPath.resolve(Storage.STATE_FILE);

        BufferedWriter writer = Files.newBufferedWriter(filePath);
        List<String> stateFile = new ArrayList<>();

        for (Task task : tasks) {
            stateFile.add(Storage.convertTaskToState(task));
        }

        for (String line : stateFile) {
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    private static String convertTaskToState(Task task) {
        StringBuilder str = new StringBuilder();
        str.append(task.getStatusIcon().equals("X") ? "| 1 " : "| 0 ");
        str.append("| ");
        str.append(task.getDescription());
        str.append(" ");

        if (task instanceof ToDos) {
            str.insert(0, "T ");
        } else if (task instanceof Deadline deadline) {
            str.insert(0, "D ");
            str.append("| ");
            str.append(deadline.getBy());
        } else if (task instanceof Event event) {
            str.insert(0, "E ");
            str.append("| ");
            str.append(event.getFrom());
            str.append("-");
            str.append(event.getTo());
        }

        return str.toString();
    }
}
