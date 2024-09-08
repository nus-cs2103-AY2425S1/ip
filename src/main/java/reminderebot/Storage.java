package reminderebot;

import reminderebot.task.Task;
import reminderebot.task.ToDo;
import reminderebot.task.Deadline;
import reminderebot.task.Event;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Storage represents the storage manager for Reminderebot.
 */
public class Storage {
    static String filePath;

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Reads File and returns contents as an arraylist of Strings.
     * @return an arraylist of Tasks
     * @throws ReminderebotException
     */
    public ArrayList<Task> readFileContents() throws ReminderebotException {
        ArrayList<Task> contents = new ArrayList<>();
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                contents.add(readEntry(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new ReminderebotException("File not found!");
        }
        return contents;
    }

    /**
     * Read the string entry and return a task
     * @param entry
     * @return Task corresponding to string
     */
    private Task readEntry(String entry) {
        String[] fields = entry.split("\\|");
        //  System.out.println(Arrays.toString(fields));  // debug
        Task taskToAdd;

        // @@author david-eom
        // Reused from https://github.com/david-eom/CS2103T-IP
        // with minor modifications
        switch (fields[0]) {
        case "E":
            taskToAdd = new Event(fields[2], LocalDateTime.parse(fields[3]), LocalDateTime.parse(fields[4]));
            break;
        case "D":
            taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3]));
            break;
        default: // case "T":
            taskToAdd = new ToDo(fields[2]);
            break;
        }
        if ((fields[1]).equals("X")) {
            taskToAdd.markAsDone();
        }
        return taskToAdd;
    }

    /**
     * Save data from TaskList to Storage
     * @param tasklist
     */

    public void saveData(TaskList tasklist) {
        try {
//            System.out.println("Added tasklist to" + filePath);
            File file = new File(filePath);
//
//            // This storage location is relative: If run from .bat script,
//            // the txt file will be stored at ip/text-ui-test/data/
//            // On the other hand, if Reminderebot.java is run directly,
//            // the txt file will be stored at ip/data
            File dir = new File(file.getParent());

            boolean dirCreated = dir.mkdirs();
//            System.out.println(dirCreated);
            // @@author david-eom
            // Reused from https://github.com/david-eom/CS2103T-IP
            // with minor modifications
            FileWriter fw = new FileWriter(filePath, false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < tasklist.length(); i++) {
                bw.write(tasklist.getTask(i).toFile());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException("This should never happen", e);
        }
    }


//        /**
//          * Write to File, also Handles I/O exception
//          * @param textToAdd
//          */
//    public void writeToFile(String textToAdd) {
//        try {
//            System.out.println("Added"+ textToAdd + "to" + filePath);
//            File file = new File(filePath);
//
//            // This storage location is relative: If run from .bat script,
//            // the txt file will be stored at ip/text-ui-test/data/
//            // On the other hand, if Reminderebot.java is run directly,
//            // the txt file will be stored at ip/data
//            File dir = new File(file.getParent());
//
//            boolean dirCreated = dir.mkdirs();
//            System.out.println(dirCreated);
//            if (file.createNewFile()) {
//                FileWriter fw = new FileWriter(filePath);
//                fw.write(textToAdd);
//                fw.close();
//            } else {
//                FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
//                fw.write("\n" + textToAdd);
//                fw.close();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("This should never happen", e);
//        }
//    }

//    /**
//     * Prints file contents.
//     * @param filePath
//     * @throws FileNotFoundException
//     */
//    public void printFileContents(String filePath) throws FileNotFoundException {
//        File f = new File(filePath); // create a File for the given file path
//        Scanner s = new Scanner(f); // create a Scanner using the File as the source
//        while (s.hasNext()) {
//            System.out.println(s.nextLine());
//        }
//    }
}
