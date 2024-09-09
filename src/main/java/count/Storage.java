package count;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import count.exception.IncorrectFormatException;
import count.exception.InvalidTimelineException;
import count.task.Deadline;
import count.task.Event;
import count.task.Task;
import count.task.ToDo;



/**
 * The Storage class deals with reading any existing Count.txt files for Tasks
 * in the specified file path
 * @author Kieran Koh Jun Wei
 */
public class Storage {
    protected File file;
    protected String filePath;
    protected Scanner sc;
    protected DateTimeFormatter readerFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");

    /**
     * Constructor for Storage class
     * @param filePath String filePath in which Storage will read from
     */

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * The load method checks if there is any Count.txt files in the specified file path
     * It will then read the file and add the Tasks to an ArrayList to return.
     * @return ArrayList&lt;Task&gt; with Tasks read from Count.txt
     * @throws IncorrectFormatException if the String format of the read file does not match the format reader
     * @throws FileNotFoundException if no Count.txt file can be found
     */
    public ArrayList<Task> load() throws IncorrectFormatException, FileNotFoundException, InvalidTimelineException {
        this.sc = new Scanner(this.file);
        ArrayList<Task> listOfTasksRead = new ArrayList<>();
        while (sc.hasNextLine()) {
            String taskString = sc.nextLine();

            try {
                String type = taskString.substring(3, 4);
                String completion = taskString.substring(6, 7);
                if (!completion.equals(" ") && !completion.equals("X")) {
                    throw new IncorrectFormatException("Incorrect format for Task completion scanned in Count.txt!");
                }

                boolean isComplete = completion.equals("X");
                String desc = taskString.split("] ", 2)[1];

                switch (type) {
                case "T":
                    listOfTasksRead.add(new ToDo(desc, isComplete));
                    break;
                case "D":
                    String[] deadlineInfo = desc.split(" \\(by: ", 2);
                    LocalDate byTime = LocalDate.parse(deadlineInfo[1].substring(0, deadlineInfo[1].length() - 1),
                            readerFormat);
                    listOfTasksRead.add(new Deadline(deadlineInfo[0], byTime, isComplete));
                    break;
                case "E":
                    String[] eventInfo = desc.split(" \\(from: ", 2);
                    String[] eventTimeInfo = eventInfo[1].split(" to: ", 2);
                    LocalDate fromTime = LocalDate.parse(eventTimeInfo[0], readerFormat);
                    LocalDate toTime = LocalDate.parse(eventTimeInfo[1].substring(0, eventTimeInfo[1].length() - 1),
                            readerFormat);
                    listOfTasksRead.add(new Event(eventInfo[0], toTime, fromTime, isComplete));
                    break;
                default:
                    throw new IncorrectFormatException("Incorrect format for Task types"
                            + " scanned in count.Count.txt!");
                }

            } catch (StringIndexOutOfBoundsException e) {
                throw new IncorrectFormatException("Incorrect format for Task types "
                        + "scanned in count.Count.txt!");
            } catch (PatternSyntaxException e) {
                throw new IncorrectFormatException("Incorrect format for Task description"
                        + " scanned in count.Count.txt!");
            }

        }
        return listOfTasksRead;
    }
}
