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
            throw new TuesdayException("No data in the file"); //check again
        }
        try {
            Scanner myReader = new Scanner(this.filePath);
            try {
                String[] userInputArr;
                String line;

                while (myReader.hasNextLine()) {
                    //check next line in the data file
                    line = myReader.nextLine();

                    // split by ' | ' using regex
                    userInputArr = line.split(" \\| ");

                    if (userInputArr[0].equals("T")){
                        ToDo taskItem = new ToDo(userInputArr[2], userInputArr[1].equals("1"));
                    } else if (userInputArr[0].equals("D")) {
                        Deadline deadlineItem = new Deadline(userInputArr[2],
                                userInputArr[3], userInputArr[1].equals("1"));
                    } else if (userInputArr[0].equals("E")) {
                        String[] userInputArrEvent = userInputArr[3].split(" ");
                        Event eventItem = new Event(userInputArr[2],
                                userInputArrEvent[0], userInputArrEvent[1], userInputArr[1].equals("1"));
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
}
