package jude;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jude.task.Deadline;
import jude.task.Event;
import jude.task.Task;
import jude.task.Todo;

public class Storage {
    String filePath;
    Scanner fileReader;
    FileWriter writer;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws JudeException {
        File file = new File(filePath);
        List<Task> list = new ArrayList<>();
        String errorMessage = " error while loading the file. Starts with an empty list.";

        // Check if file exists
        if (!file.exists()) {
            return list;
        }

        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException fe) {
            throw new JudeException("file not found" + errorMessage);
        }


        while (fileReader.hasNextLine()) {
            String[] split = fileReader.nextLine().split(" \\| ");

            // Handles file format with no Type letter
            if (split.length < 3) {
                throw new JudeException("invalid file format" + errorMessage);
            }
            boolean isDone = split[1].equals("1");
            String description = split[2];

            switch (split[0]) {
            case "T":
                if (split.length != 3) {
                    throw new JudeException("invalid file format" + errorMessage);
                }
                list.add(new Todo(description, isDone));
                break;
            case "D":
                if (split.length != 4) {
                    throw new JudeException("invalid file format" + errorMessage);
                }
                list.add(new Deadline(description, split[3], isDone));
                break;
            case "E":
                if (split.length != 5) {
                    throw new JudeException("invalid file format" + errorMessage);
                }

                list.add(new Event(description, split[3], split[4], isDone));
                break;
            default:
                break;
            }
        }
        return list;
    }

    public void save(TaskList list) throws JudeException {
        File save = new File(filePath);

        // Check if file exists, create a file if it doesn't
        if (!save.exists()) {
            try {
                save.createNewFile();
            } catch (IOException ie) {
                throw new JudeException("An error has occurred while creating a save file.");
            }
        }

        // Write to the save file
        try {
            writer = new FileWriter(filePath);
            writer.write(list.toFileFormat());
            writer.close();
        } catch (IOException ie) {
            throw new JudeException("IOException has occurred while writing to a save file.");
        }
    }



}
