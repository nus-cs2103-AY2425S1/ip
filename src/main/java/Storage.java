import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            //Load file if file exists
            if (file.exists()) {
                Scanner s = new Scanner(file); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] stringArray = line.split("\\|");
                    char type = line.charAt(0);
                    if (type == 'T') {
                        Todo t = new Todo(stringArray[2]);
                        if (stringArray[1].equals("X")) {
                            t.markTaskAsDone();
                        }
                        list.add(t);
                    } else if (type == 'D') {
                        Deadline d = new Deadline(stringArray[2], LocalDateTime.parse(stringArray[3], fileFormatter));
                        if (stringArray[1].equals("X")) {
                            d.markTaskAsDone();
                        }
                        list.add(d);
                    } else if (type == 'E') {
                        Event e = new Event(stringArray[2], LocalDateTime.parse(stringArray[3], fileFormatter),
                                LocalDateTime.parse(stringArray[4], fileFormatter));
                        if (stringArray[1].equals("X")) {
                            e.markTaskAsDone();
                        }
                        list.add(e);
                    } else {
                        throw new CorruptedFileException("File is corrupted.");
                    }
                }
                System.out.println("Existing data file found. Data has been loaded.");
            } else {
                if (file.getParentFile().exists()) {
                    file.createNewFile();
                } else {
                    file.getParentFile().mkdir();
                    file.createNewFile();
                }
                System.out.println("No existing data file found. New data file \"dash.txt\" has been created.");
            }
        } catch (IOException e) {
            System.out.println("I/O error has occurred when creating new file: " + e.getMessage());
        } catch (CorruptedFileException e) {
            System.out.println("Please erase data in file: " + e.getMessage());
        } finally {
            Ui.insertLine();
        }
        return list;
    }

    /**
     *
     * @param textToAppend String text to be added to file
     */
    public void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(this.file.getPath(), true); // create a FileWriter in append mode
            fw.write(textToAppend + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Append to file failed: " + e.getMessage());
        }
    }

    /**
     *
     * @param textToAdd String text to be written to file
     */
    public void writeToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(this.file.getPath());
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Write to file failed: " + e.getMessage());
        }
    }

    public void markTask(int markNum, TaskList tasks) {
        int num = tasks.size();
        Task t = tasks.get(markNum - 1);
        t.markTaskAsDone();
        writeToFile(tasks.get(0).toFile());
        for (int i = 1; i < num; i++) {
            appendToFile(tasks.get(i).toFile());
        }
        Ui.insertLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        Ui.insertLine();
    }

    public void unmarkTask(int unmarkNum, TaskList tasks) {
        int num = tasks.size();
        Task t = tasks.get(unmarkNum - 1);
        t.unmarkTask();
        writeToFile(tasks.get(0).toFile());
        for (int i = 1; i < num; i++) {
            appendToFile(tasks.get(i).toFile());
        }
        Ui.insertLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        Ui.insertLine();
    }

    public void deleteTask(int deleteNum, TaskList tasks) {
        int num = tasks.size();
        Task t = tasks.removeTask(deleteNum - 1);
        Ui.insertLine();
        System.out.println("Noted. I've removed this task.");
        System.out.println(t);
        System.out.println("Now you have " + (num - 1) + " tasks in the list.");
        if (!tasks.isEmpty()) {
            writeToFile(tasks.get(0).toFile());
            if (tasks.size() > 1) {
                for (int i = 1; i < num - 1; i++) {
                    appendToFile(tasks.get(i).toFile());
                }
            }
        } else {
            writeToFile("");
        }
        Ui.insertLine();
    }
}
