package sinatra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles the reading and writing of tasks to a file.
 */
public class Storage {

    private String fileName = "";
    private boolean storageLoadStatus = true;

    /**
     * Constructs a new Storage instance with the specified file name.
     *
     * @param fileName the name of the file to store tasks
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Checks if a line is in the storage.
     *
     * @param line the line to check
     * @return true if the line is in the storage, false otherwise
     */

    public boolean isLineInStorage(String line) {
        try {
            File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                if (data.equals(line)) {
                    scanner.close();
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not created");
            File file = new File(this.fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    public boolean isStorageLoadOk() {
        return storageLoadStatus;
    }

    /**
     * Appends a line to the text file.
     *
     * @param line the line to append to the file
     * @return
     */
    public void appendLineToTxtFile(String line) throws SinatraException {
        if (isLineInStorage(line)) {
            throw new SinatraException("Line already in storage");
        }
        System.out.println("appending line");
        File f = new File(this.fileName);
        assert f.exists() : this.fileName + " does not exist";
        try {
            FileWriter file = new FileWriter(this.fileName, true);
            file.write(line);
            file.write("\n");
            file.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of tasks loaded from the file
     */
    public ArrayList<Task> loadTasksFromFile() throws IllegalArgumentException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(":");
                if (data.length == 0) {
                    break;
                } else if (data.length == 1) {
                    handleFileCorruption();
                    return new ArrayList<>();
                }

                String className = data[0];

                try {
                    if (className.equals("Sinatra.ToDo")) {
                        tasks.add(ToDo.newObjectFromData(data[1]));
                    } else if (className.equals("Sinatra.Event")) {
                        tasks.add(Event.newObjectFromData(data[1]));
                    } else if (className.equals("Sinatra.Deadline")) {
                        tasks.add(Deadline.newObjectFromData(data[1]));
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("error ");
                    handleFileCorruption();
                    return new ArrayList<>();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not created");
            File file = new File(this.fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(tasks);
        return tasks;
    }

    /**
     * Delete all tasks from the file.
     */
    public void deleteAllTasks() {
        try {
            FileWriter file = new FileWriter(this.fileName);
            file.write("");
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Clears the file corruption
     */
    public void handleFileCorruption() {
        deleteAllTasks();
        storageLoadStatus = false;
    }


    /**
     * Deletes a line from the file.
     *
     * @param row the line to delete
     */
    public void deleteLineFromFile(int row) {
        try {
            File file = new File(this.fileName);
            Scanner myReader = new Scanner(file);
            ArrayList<String> lines = new ArrayList<>();
            while (myReader.hasNextLine()) {
                lines.add(myReader.nextLine());
            }
            myReader.close();
            FileWriter fileWriter = new FileWriter(this.fileName);
            for (int i = 0; i < lines.size(); i++) {
                if (i != row) {
                    fileWriter.write(lines.get(i) + "\n");
                }
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not created");
            File file = new File(this.fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Clears the file.
     */
    public void clear() {
        try {
            FileWriter file = new FileWriter(this.fileName);
            file.write("");
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

