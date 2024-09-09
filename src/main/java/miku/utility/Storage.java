package miku.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import miku.exception.DataCorruptionException;
import miku.task.Deadline;
import miku.task.Event;
import miku.task.Task;
import miku.task.Todo;


/**
 * Stores the data into a txt file.
 */
public class Storage {
    /**
     * Default filePath where the information is stored
     */
    private String filePath = "src/main/resources/data.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
    }

    /**
     * Save to the designated location from a taskList.
     *
     * @param itemList the taskList that provides the data.
     */
    public void saveFromTaskList(TaskList itemList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
            for (Task item : itemList.getTasks()) {
                //System.out.println(item.stringValue());
                writer.write(item.storeValue());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File Access Error:" + e.getMessage());
        }
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Initialises a taskList using the stored information.
     *
     * @param tasks the taskList to be populated.
     */
    public void init(TaskList tasks) {
        File file = new File("src/main/resources/data.txt");
        File dir = new File("src/main/resources");
        checkDir(dir);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created: " + e.getMessage());
            }
        } else {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String[] dataLine = sc.nextLine().split(" \\| ");
                    try {
                        if (dataLine[0].equals("D")) {
                            tasks.initAdd(new Deadline(dataLine[2], dataLine[3], Boolean.parseBoolean(dataLine[1])));
                        } else if (dataLine[0].equals("T")) {
                            tasks.initAdd(new Todo(dataLine[2], Boolean.parseBoolean(dataLine[1])));
                        } else if (dataLine[0].equals("E")) {
                            tasks.initAdd(new Event(dataLine[2], dataLine[3],
                                    dataLine[4], Boolean.parseBoolean(dataLine[1])));
                        } else {
                            throw new DataCorruptionException("The file is corrupted,"
                                    + " the file would be deleted...\nA empty tasklist will be created.");
                        }
                    } catch (DataCorruptionException e) {
                        System.out.println(e.getMessage() + "DataCorruption!");
                        file.delete();
                        tasks.clear();
                    }
                }
                System.out.println("Init Successful! no of elements: " + tasks.size());
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage() + " File not found!");
            }

        }
    }

    private static void checkDir(File dir) {
        if (!dir.exists()) {
            boolean dirCreated = dir.mkdirs();
            if (dirCreated) {
                System.out.println("Directory created\n");
            } else {
                System.out.println("Directory not created\n");
            }
        }
    }
}
