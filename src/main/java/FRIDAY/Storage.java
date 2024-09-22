package FRIDAY;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the database stored on the computer's hard drive.
 *
 * <p>
 *     This class is responsible for reading and writing from a local database that stores the user's information.
 * </p>
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method reads the storage file then loads the contents into an ArrayList of tasks,
     * and finally returns the ArrayList.
     *
     * @return ArrayList of Tasks.
     * @throws FRIDAYException If the file type in the storage file is not recognized.
     * @throws FileNotFoundException If the storage file is unable to be found.
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

    /**
     * This method attempts to create a text file to store the task list locally.
     *
     * @throws FRIDAYException If the text file or its parent directories are unable to be created.
     */
    private void createDatabase() throws FRIDAYException {
        File db = new File(this.filePath);
        File dir = new File(db.getParent());
        // Check to see if parent folder("storage") exists. if it doesnt then create it
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new FRIDAYException("Failed to create parent folders");
            }
        }
        try {
            // Create the database file if it doesn't exist
            if (!db.exists()) {
                if (!db.createNewFile()) {
                    throw new FRIDAYException("Failed to create storage file");
                }
            }
        } catch (IOException e) {
            throw new FRIDAYException("Error occurred while creating storage file");
        }
    }

    /**
     * method to populate the program storage with data read from the hard drive.
     *
     * @param data String to represent all the details of the task, including its type and
     *             other miscellaneous details.
     * @param storage ArrayList representing the database of tasks.
     * @throws FRIDAYException If an error occurs while reading from the database.
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
     * This function writes the content of the task list to the storage file.
     *
     * @param taskList ArrayList representing a list of tasks.
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
