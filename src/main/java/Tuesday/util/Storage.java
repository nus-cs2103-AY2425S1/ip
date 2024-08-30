package Tuesday.util;

import Tuesday.task.Deadline;
import Tuesday.task.Event;
import Tuesday.task.Task;
import Tuesday.task.ToDo;

import java.io.IOException;
import java.util.Scanner; // Import the Scanner class
import java.io.File;

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
                            //System.out.println("test "+ userInputArr[2]);
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

    public void save_to_datafile(Task taskItem) {
        File dataFile = new File(filePath);
        taskItem.write_to_datafile(dataFile);
    }

    /**
     * Creates a new data file if one does not exist
     */
    public void create_new_datafile() {
        try {
            File dataFile = new File(filePath);
            dataFile.getParentFile().mkdirs(); // Make the directory
            dataFile.createNewFile(); // Make the file
        } catch (IOException e) {
            System.out.println("    An error occurred.");
        }
    }
}
