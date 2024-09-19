package darkpool.storage;

import darkpool.task.Task;
import darkpool.DarkpoolException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static darkpool.storage.ValidateData.validateData;

/**
 * LoadData class is responsible for loading data from the file.
 */
public class LoadData {
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
