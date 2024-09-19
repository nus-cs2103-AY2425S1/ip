package darkpool.storage;

import static darkpool.storage.ValidateData.validateData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import darkpool.DarkpoolException;
import darkpool.task.Task;

/**
 * LoadData class is responsible for loading data from the file.
 */
public class LoadData {

    /**
     * Loads data from the file.
     *
     * @param filePath Path of the file to load data from.
     * @return ArrayList of tasks loaded from the file.
     * @throws DarkpoolException If file is not found.
     */
    public static ArrayList<Task> load(String filePath) throws DarkpoolException {
        ArrayList<Task> taskList = new ArrayList<>();
        String curTask;
        File dataFile = validateData(filePath);
        Scanner scanner;

        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DarkpoolException(e.getMessage());
        }

        while (scanner.hasNext()) {
            curTask = scanner.nextLine();
            taskList.add(ParseTask.parse(curTask));
        }

        scanner.close();
        return taskList;
    }
}
