package makima.io;

import makima.command.Makima;
import makima.task.Deadline;
import makima.task.Event;
import makima.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helper class to create and read files.
 */
public class FileManager {

    public static final String DATA_FOLDER = "./data";
    public static final String DATA_PATH = "./data/makima.txt";

    /**
     * Saves the current list of tasks to DATA_PATH.
     * Returns true if successful
     *
     * @param makima State of chatbot
     * @return outcome of save
     */
    public static boolean saveFile(Makima makima) {
        File file = new File(DATA_PATH);
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(makima.convertTaskstoFileString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
            return false;
        }
        return true;
    }

    /**
     * Loads the list of tasks saved in DATA_PATH
     * Returns true if successful
     *
     * @param makima State of chatbot
     * @return outcome of load
     */
    public static boolean loadFile(Makima makima) {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                File file = new File(DATA_PATH);
                try {
                    if (!file.createNewFile()) {
                        System.out.println("An unexpected error occurred.");
                        return false;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating a new data file!");
                    return false;
                }
            } else {
                System.out.println("An error occurred while creating a new data file!");
                return false;
            }
        }
        File file = new File(DATA_PATH);
        if (file.exists()) {
            ArrayList<String> lines = new ArrayList<>();

            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    lines.add(reader.nextLine());
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while reading the data file!");
                return false;
            }

            int lineNumber = 0;

            while (lineNumber < lines.size()) {
                String name;
                LocalDateTime startTime;
                LocalDateTime endTime;
                boolean done;

                String line = lines.get(lineNumber);
                switch (line) {
                case "E":
                    if (lineNumber + 4 >= lines.size()) {
                        System.out.println("The file is corrupted! Delete it before restarting the program!");
                        return false;
                    }

                    name = lines.get(lineNumber + 1);
                    done = Boolean.parseBoolean(lines.get(lineNumber + 2));
                    try {
                        startTime = LocalDateTime.parse(lines.get(lineNumber + 3));
                        endTime = LocalDateTime.parse(lines.get(lineNumber + 4));
                    } catch (DateTimeParseException e) {
                        System.out.println("The file is corrupted! Delete it before restarting the program!");
                        return false;
                    }

                    makima.addTask(new Event(name, startTime, endTime, done));
                    lineNumber += 5;
                    break;
                case "D":
                    if (lineNumber + 3 >= lines.size()) {
                        System.out.println("The file is corrupted! Delete it before restarting the program!");
                        return false;
                    }

                    name = lines.get(lineNumber + 1);
                    done = Boolean.parseBoolean(lines.get(lineNumber + 2));
                    try {
                        endTime = LocalDateTime.parse(lines.get(lineNumber + 3));
                    } catch (DateTimeParseException e) {
                        System.out.println("The file is corrupted! Delete it before restarting the program!");
                        return false;
                    }

                    makima.addTask(new Deadline(name, endTime, done));
                    lineNumber += 4;
                    break;
                case "T":
                    if (lineNumber + 2 >= lines.size()) {
                        System.out.println("The file is corrupted! Delete it before restarting the program!");
                        return false;
                    }

                    name = lines.get(lineNumber + 1);
                    done = Boolean.parseBoolean(lines.get(lineNumber + 2));

                    makima.addTask(new ToDo(name, done));
                    lineNumber += 3;
                    break;
                case "":
                    lineNumber++;
                    break;
                default:
                    System.out.println("The file is corrupted! Delete it before restarting the program!");
                    return false;
                }
            }

        } else {
            try {
                if (!file.createNewFile()) {
                    System.out.println("An unexpected error occurred.");
                    return false;
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating a new data file!");
                return false;
            }

        }
        return true;
    }

}
