package tuesday.util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class

import tuesday.task.Deadline;
import tuesday.task.Event;
import tuesday.task.Task;
import tuesday.task.ToDo;

/**
 * The Storage class handles reading from and writing to a local file that stores tasks, allowing for tasks
 */
public class Storage {
    // variable
    private String filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath File directory for data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Used to load all the data from the datafile
     *
     * @return Empty String
     * @throws TuesdayException Throws when the program does not understand the data
     */
    public String load() throws TuesdayException {
        assert this.filePath != null : "The constructor should be used first";

        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            throw new TuesdayException("No data in the file");
        }
        try {
            Scanner myReader = new Scanner(dataFile);
            try {
                String[] userInputArr;
                String line;

                label:
                while (myReader.hasNextLine()) {
                    //check next line in the data file
                    line = myReader.nextLine();
                    // split by ' | ' using regex
                    userInputArr = line.split(" \\| ");

                    switch (userInputArr[0]) {
                    case "T":
                        ToDo taskItem = new ToDo(userInputArr[2], userInputArr[1].equals("1"));
                        break;
                    case "D":
                        Deadline deadlineItem = new Deadline(userInputArr[2],
                                    userInputArr[3], userInputArr[1].equals("1"));
                        break;
                    case "E":
                        String[] userInputArrEvent = userInputArr[3].split("-");
                        Event eventItem = new Event(userInputArr[2],
                                    userInputArrEvent[0], userInputArrEvent[1], userInputArr[1].equals("1"));
                        break;
                    default:
                        break label;
                    }
                }
            } finally {
                myReader.close();
            }
        } catch (Exception e) {
            System.out.println("An error occurred. " + e);
        }
        return "";
    }

    /**
     * Saves the data into the data file
     *
     * @param taskItem Task added to data file
     */

    public void saveToDatafile(Task taskItem) {
        assert this.filePath != null : "The constructor should be used first";

        File dataFile = new File(filePath);
        taskItem.writeToDatafile(dataFile);
    }

    /**
     * Creates a new data file if one does not exist
     */
    public void createNewDatafile() {
        try {
            assert this.filePath != null : "The constructor should be used first";

            File dataFile = new File(filePath);
            dataFile.getParentFile().mkdirs(); // Make the directory
            dataFile.createNewFile(); // Make the file
        } catch (IOException e) {
            System.out.println("    An error occurred.");
        }
    }
}
