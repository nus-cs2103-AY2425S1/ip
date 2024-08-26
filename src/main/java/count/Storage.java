package count;

import count.task.*;
import count.exception.IncorrectFormatException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;
public class Storage {
    protected File file;
    protected String filePath;
    protected Scanner sc;
    protected DateTimeFormatter readerFormat = DateTimeFormatter.ofPattern("LLLL dd yyyy");

    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IncorrectFormatException, FileNotFoundException {
        this.sc = new Scanner(this.file);
        ArrayList<Task> ls = new ArrayList<>();
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
                        ls.add(new ToDo(desc, isComplete));
                        break;
                    case "D":
                        String[] deadlineInfo = desc.split(" \\(by: ", 2);
                        LocalDate byTime = LocalDate.parse(deadlineInfo[1].substring(0, deadlineInfo[1].length() - 1), readerFormat);
                        ls.add(new Deadline(deadlineInfo[0], byTime, isComplete));
                        break;
                    case "E":
                        String[] eventInfo = desc.split(" \\(from: ", 2);
                        String[] eventTimeInfo = eventInfo[1].split(" to: ",2);
                        LocalDate fromTime = LocalDate.parse(eventTimeInfo[0], readerFormat);
                        LocalDate toTime = LocalDate.parse(eventTimeInfo[1].substring(0, eventTimeInfo[1].length() - 1), readerFormat);
                        ls.add(new Event(eventInfo[0], toTime, fromTime, isComplete));
                        break;
                    default:
                        throw new IncorrectFormatException("Incorrect format for Task.Task types scanned in count.Count.txt!");
                }

            } catch (StringIndexOutOfBoundsException e) {
                throw new IncorrectFormatException("Incorrect format for Task.Task types scanned in count.Count.txt!");
            } catch (PatternSyntaxException e) {
                throw new IncorrectFormatException("Incorrect format for Task.Task description scanned in count.Count.txt!");
            }

        }
        return ls;
    }
}
