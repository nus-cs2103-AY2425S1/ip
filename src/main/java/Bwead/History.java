package Bwead;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class History {

    private File saved;
    private static ArrayList<Task> historyList;

    public History(String filePath) {
        this.saved = new File(filePath);
        historyList = new ArrayList<>();
    }

    public File getSaved() {
        return saved;
    }

    public ArrayList<Task> load() throws BweadException, IOException {

        if (!saved.exists()) {
            System.out.println("Please create historyFile.txt at this file path: ./src/main/java/historyFile.txt");
        }
        if (saved.canRead()) {
            Scanner s = new Scanner(saved);
            while (s.hasNext()) {
                historyList.add(getTaskfromString(s.nextLine()));
            }
            return historyList;
        } else {
            throw new BweadException("no saved tasks found");
        }
    }

    public void updateFile(ArrayList<Task> tasklist) throws IOException {
        FileWriter fw = new FileWriter(saved);
        for (int i = 1; i <= tasklist.size(); i++) {
            Task task = tasklist.get(i-1);
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

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
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d uuuu"));
            return new Deadline(text1, date);
        } else if (type == 'E') {
            String text = string.substring(7);
            String text1 = text.split("from: ")[0];
            text1 = text1.substring(0, text1.length() - 2);
            String dates = text.split("from: ")[1];
            String startString = dates.split(" to: ")[0];
            String endString = dates.split(" to: ")[1];
            endString = endString.substring(0, endString.length() - 1);
            return new Event(text1 + " ", LocalDate.parse(startString, DateTimeFormatter.ofPattern("MMM d uuuu"))
                    , LocalDate.parse(endString, DateTimeFormatter.ofPattern("MMM d uuuu")));
        } else {
            return new Task("dh");
        }
    }

}
