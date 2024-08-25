package reo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/** Supports the required file operations for reo.Reo. */
public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Returns true if the parameter is 1, and false otherwise.
     *
     * @param i The integer used to represent true or false in the saved file.
     * @return Boolean representation of i.
     */
    public static boolean convertIntToBool(int i) {
        return i == 1;
    }

    /**
     * Reads the saved file, and interprets its contents to create
     * an ArrayList of reo.Task objects represented by the file contents.
     *
     * @return An ArrayList of Tasks interpreted from the file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dir = new File("./data");
            if (!dir.exists()) {
                dir.mkdir();
                System.out.println("Created new directory to store data.");
            }
            File f = new File(filePath);
            if (f.createNewFile()) {
                System.out.println("Created new file to store list.");
                return tasks;
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] split = line.split("\\|");

                switch (split[0].trim()) {
                    case "T":
                        boolean isDoneT = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameT = split[2].trim();
                        tasks.add(new Todo(nameT, isDoneT));
                        break;
                    case "E":
                        boolean isDoneE = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameE = split[2].trim();
                        String from = split[3].trim();
                        String to = split[4].trim();
                        tasks.add(new Event(nameE, isDoneE, to, from));
                        break;
                    case "D":
                        boolean isDoneD = convertIntToBool(Integer.parseInt(split[1].trim()));
                        String nameD = split[2].trim();
                        String deadline = split[3].trim();
                        tasks.add(new Deadline(nameD, isDoneD, deadline));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Ensure the file path starts with ./data");
        } catch (IOException e) {
            System.out.println("IO Error! Ensure the file path starts with ./data");
        }

        return tasks;
    }

    /**
     * Writes to the saved file by appending the file representation
     * of t to the end of the file.
     *
     * @param t The task to be converted to file representation and appended.
     */
    public void writeFile(Task t) {
        if (t == null) {
            throw new NullPointerException();
        }
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String text = t.toFileString() + System.lineSeparator();
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the specified line i from the saved file.
     *
     * @param lineNumber The line number (1-indexed) to be removed.
     */
    public void removeLine(int lineNumber) {
        try {
            File f = new File(filePath);
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }
    }

    /**
     * Edits the specified line i to change the recorded completion status.
     *
     * @param lineNumber The line number (1-indexed) to be edited.
     * @param cmd The action to be performed on the completion status.
     */
    public void editLine(int lineNumber, String cmd) {
        try {
            File f = new File(filePath);
            File temp = new File("./data/temp.txt");
            FileWriter tw = new FileWriter(temp, false);
            Scanner s = new Scanner(f);
            int lineCount = 1;

            while (s.hasNext()) {
                String line = s.nextLine();
                if (lineNumber != lineCount) {
                    tw.write(line + System.lineSeparator());
                } else {
                    String[] split = line.split("\\|");
                    if (cmd == "mark") {
                        split[1] = " 1 ";
                    } else {
                        split[1] = " 0 ";
                    }

                    String newLine = String.join("|", split);
                    tw.write(newLine + System.lineSeparator());
                }
                lineCount ++;
            }
            tw.close();
            s.close();

            Files.move(temp.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO Exception!");
        }

    }
}
