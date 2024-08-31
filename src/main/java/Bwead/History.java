package Bwead;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles the saving of tasks in a file with a specific file path.
 * Loads tasks from the file and updates the file upon new commands.
 */
public class History {

    private File saved;
    private static ArrayList<Task> historyList;

    /**
     * Constructs a History instance using a file path which points to local file.
     * a new ArrayList will be created to store the current historyList.
     *
     * @param filePath file path of the local file which will save tasks.
     */
    public History(String filePath) {
        this.saved = new File(filePath);
        historyList = new ArrayList<>();
    }

    /**
     * Loads the saved tasks from the local file into a new ArrayList.
     *
     * @throws IOException when a failure occurs while performing read operations from scanning.
     * @throws BweadException when file is not found at the specified file path.
     */
    public ArrayList<Task> load() throws BweadException, IOException {
        if (saved.canRead()) {
            Scanner s = new Scanner(saved);
            while (s.hasNext()) {
                historyList.add(getTaskfromString(s.nextLine()));
            }
            return historyList;
        } else {
            throw new BweadException("no file found");
        }
    }

    /**
     * Returns local file where tasks are saved.
     *
     * @return File.
     */
    public File getSaved() {
        return saved;
    }

    /**
     * Updates local file to the current arrayList in TaskList.
     *
     * @param tasklist ArrayList with all the current tasks.
     * @throws IOException when a failure occurs while performing write operations.
     */
    public void updateFile(ArrayList<Task> tasklist) throws IOException {
        FileWriter fw = new FileWriter(saved);
        for (int i = 1; i <= tasklist.size(); i++) {
            Task task = tasklist.get(i-1);
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Converts a string from the local file into its equivalent task.
     *
     * @param string String from the local file.
     * @return Task task that is represented by the string.
     */
    public static Task getTaskfromString(String string) {
        char type = string.charAt(1);
        if (type == 'T') {
            String text = string.substring(7);
            return new Todo(text);
        } else if (type == 'D') {
            String text = string.substring(7);
            String text1 = text.split("by: ")[0];
            text1 = text1.substring(0, text1.length() - 2);
            String dateString = text.split("by: ")[1];
            dateString = dateString.substring(0, dateString.length() - 1);
            dateString = dateString.substring(0, dateString.length() - 7);
            String timeString = text.substring(text.length() - 6, text.length());
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d uuuu"));
            LocalTime time = LocalTime.parse(timeString.substring(0, 2) + ":" + timeString.substring(3, 5));
            return new Deadline(text1, date, time);
        } else if (type == 'E') {
            String text = string.substring(7);
            String text1 = text.split("from: ")[0];
            text1 = text1.substring(0, text1.length() - 2);
            String dates = text.split("from: ")[1];
            String startString = dates.split(" to: ")[0];
            String startDateString = startString.substring(0, startString.length() - 7);
            String startTimeString = startString.substring(startString.length() - 5, startString.length() - 3) +
                    startString.substring(startString.length() - 2, startString.length());
            String endString = dates.split(" to: ")[1];
            endString = endString.substring(0, endString.length() - 1);
            String endDateString = endString.substring(0, endString.length() - 7);
            String endTimeString = endString.substring(endString.length() - 5, endString.length() - 3) +
                    endString.substring(endString.length() - 2, endString.length());
            LocalTime startTime = LocalTime.parse(startTimeString.substring(0, 2) + ":" + startTimeString.substring(2,4));
            LocalTime endTime = LocalTime.parse(endTimeString.substring(0, 2) + ":" + endTimeString.substring(2, 4));
            return new Event(text1 + " ", LocalDate.parse(startDateString, DateTimeFormatter.ofPattern("MMM d uuuu"))
                    , LocalDate.parse(endDateString, DateTimeFormatter.ofPattern("MMM d uuuu")),
                    startTime, endTime);
        } else {
            return new Task("dh");
        }
    }

}
