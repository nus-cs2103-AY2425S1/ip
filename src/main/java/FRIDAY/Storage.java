package FRIDAY;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * represents the database stored on the computer's hard drive
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method reads the storage file then loads the contents into an ArrayList of tasks, and finally returns the ArrayList
     * @return ArrayList of Tasks
     * @throws FRIDAYException exception is thrown when the file type in the storage file is not recognized
     * @throws FileNotFoundException exception thrown when the storage file is unable to be found
     */
    public ArrayList<Task> load() throws FRIDAYException {
        ArrayList<Task> tasks = new ArrayList<>();
        File storageFile = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(storageFile);
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                populateProgramMemory(data, tasks);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }
        return tasks;
    }

    private void createDatabase() throws FRIDAYException {
        File db = new File(this.filePath);
        File dir = new File(db.getParent());
        if (!dir.mkdir()) {
            throw new FRIDAYException("Failed to create database");
        }
        try {
            if (!db.createNewFile()) {
                throw new FRIDAYException("Failed to create database file");
            }
        } catch (IOException e) {
            System.out.println("Error occured creating database");
        }
    }

    /**
     * method to populate the program storage with data read from the hard drive
     *
     * @param data string to represent all the details of the task, including its type and other miscellaneous details
     */
    private void populateProgramMemory(String data, ArrayList<Task> storage) throws FRIDAYException {
        String[] taskElements = data.split("\\|");
        String taskType = taskElements[0].trim();
        int status = Integer.parseInt(taskElements[1].trim());
        String taskDesc = taskElements[2].trim();
        switch(taskType) {
        case("T"):
            Task toDo = new ToDo(taskDesc, status);
            storage.add(toDo);
            break;
        case("E"):
            String duration = taskElements[3].trim();
            System.out.println(duration);
            String[] startEnd = duration.split("from |to ");
            String start = startEnd[1].trim();
            String end = startEnd[2].trim();
            Task event = new Event(taskDesc, start, end, status);
            storage.add(event);
            break;
        case("D"):
            String taskDeadline = taskElements[3];
            Task deadline = new Deadline(taskDesc, taskDeadline, status);
            storage.add(deadline);
            break;
        default:
            throw new FRIDAYException("Encountered unrecognizable task type");
        }
    }

    /**
     * This function writes the content of the task list to the storage file
     * @param taskList List of tasks stored within an ArrayList
     */
    public void updateStorage(ArrayList<Task> taskList) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            taskList.forEach((task) -> {
                try {
                    writer.write(task.storageDisplay() + "\n");
                } catch (IOException e) {
                    System.out.println("Error writing to file");
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to storage");
        }
    }
}
