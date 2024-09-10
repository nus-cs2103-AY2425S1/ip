package ai;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import ai.exception.AiException;
import ai.task.Deadline;
import ai.task.Event;
import ai.task.Task;
import ai.task.ToDo;

/**
 * Deals with file writing and reading.
 */
public class Storage {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file using the given filePath and scans for each line.
     *
     * @return ArrayList of type Task.
     * @throws IOException if .txt file cannot be created successfully.
     * @throws AiException if the line in the .txt file cannot be read.
     */
    public ArrayList<Task> load() throws IOException, AiException {
        Path path = Paths.get(filePath);
        Path directory = path.getParent();

        File dir = new File(directory.toString());

        dir.mkdirs();

        if (!dir.exists()) {
            System.out.println("Path created");
        }

        File f = new File(filePath);
        Scanner s = new Scanner(f);


        f.createNewFile();
        // Check if file exists
        if (!f.exists()) {
            // Create file
            System.out.println("File created");
        }

        // File exists, read from file
        while (s.hasNext()) {
            tasks.add(readFileLine(s.nextLine()));
        }

        s.close();
        return tasks;
    }

    /**
     * Reads the given input, parses it into the correct format to initialise the Task to be returned.
     *
     * @param input String of a single line in the .txt file.
     * @return Task in the correct type.
     * @throws AiException if the lines cannot be read.
     */
    public Task readFileLine(String input) throws AiException {
        try {
            String[] parsedInput = input.split(" \\| ", 3);
            String taskType = parsedInput[0];
            Boolean isDone = parsedInput[1].equals("1");
            String taskDesc = parsedInput[2];

            if (taskType.equals("T")) {
                return new ToDo(taskDesc, isDone);
            } else if (taskType.equals("D")) {
                String[] parsedDateDesc = taskDesc.split(" \\| ", 2);
                String descDeadline = parsedDateDesc[0];
                String date = parsedDateDesc[1];

                return new Deadline(descDeadline, date, isDone);
            } else if (taskType.equals("E")) {
                String[] parsedFromToDesc = taskDesc.split(" \\| ", 2);
                String descEvent = parsedFromToDesc[0];
                String fromTo = parsedFromToDesc[1];

                String[] parsedDate = fromTo.split(" - ", 2);
                String from = parsedDate[0];
                String to = parsedDate[1];

                return new Event(descEvent, from, to, isDone);
            } else {
                throw new AiException("Unexpected input, oh dearr");
            }
        } catch (Exception e) {
            throw new AiException("Ahh dearr, I think your lines might be a teeny tiny buggyyy :p");
        }
    }
}
