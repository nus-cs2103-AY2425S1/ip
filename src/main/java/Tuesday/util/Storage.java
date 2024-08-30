package Tuesday.util;

import Tuesday.task.Deadline;
import Tuesday.task.Event;
import Tuesday.task.Task;
import Tuesday.task.ToDo;

import java.io.IOException;
import java.util.Scanner; // Import the Scanner class
import java.io.File;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save_to_datafile(Task taskItem) {
        File dataFile = new File(filePath);
        taskItem.write_to_datafile(dataFile);
    }

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
