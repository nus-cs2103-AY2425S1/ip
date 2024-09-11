package buddybot;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for accessing contents within the txt file
 */
public class FileStorage {

    public String filePath;

    /**
     * Constructor for FilePath
     * @param filePath
     */
    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the contents within the file and converts the String into an ArrayList of Tasks
     * @return Content of file
     * @throws BuddyBotException
     */
    public ArrayList<Task> readFileContents() throws BuddyBotException {
        ArrayList<Task> contents = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                contents.add(readEntry(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new BuddyBotException("File not found!t");
        }
        return contents;
    }

    /**
     * Converts Strings to Tasks
     * @param entry
     * @return Task to add
     */
    private Task readEntry(String entry) {
        String trimmed = entry.trim().trim();
        String[] fields = trimmed.split("\\|");
        //  System.out.println(Arrays.toString(fields));  // debug
        Task taskToAdd;
//        for (String field: fields) {
//            System.out.println(field);
//        }
        switch (fields[0]) {
            case "E":
                taskToAdd = new Event(fields[2], LocalDate.parse(fields[3]), LocalDate.parse(fields[4]));
                break;
            case "D":
                taskToAdd = new Deadline(fields[2], LocalDate.parse(fields[3]));
                break;
            default: // case "T":
                taskToAdd = new Todo(fields[2]);
                break;
        }
        if ((fields[1]).equals("X")) {
            taskToAdd.mark();
        }
        return taskToAdd;
    }

    /**
     * Writes Strings to the txt file
     * @param myTasks
     */
    public void writeToTxt(String myTasks) { //Using this method
        try  {
            File file = new File(this.filePath);
            File directory = new File(file.getParent());
            boolean dirCreated = directory.mkdirs();
            FileWriter fileWriter = new FileWriter(this.filePath, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
           bufferedWriter.write(myTasks);
           bufferedWriter.close();
           fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
