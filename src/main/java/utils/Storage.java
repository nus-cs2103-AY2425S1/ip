package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import chatterboxerrors.ChatterBoxDataFileError;
import chatterboxerrors.ChatterBoxError;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;



/**
 * Representation of a Storage for Chatterbox.
 */
public class Storage {
    private final Path saveFilePath;
    private final Path saveDirectoryPath;
    private final TextUi textUi = new TextUi();
    private final StoredList saveList = new StoredList();

    /**
     * Initialisation of a Storage given the save file.
     * @param fileName The string path to a save file.
     */
    public Storage(String fileName) {
        String currentDirectory = System.getProperty("user.dir");
        saveFilePath = Paths.get(currentDirectory, fileName);
        saveDirectoryPath = saveFilePath.getParent();
    }

    /**
     * Reads from a save file and processes it into the StoredList.
     */
    public void readFromSave() throws ChatterBoxDataFileError {
        try {
            Files.createDirectories(saveDirectoryPath);
        } catch (IOException e) {
            System.out.println("Could not create directory: " + saveDirectoryPath);
        }
        File saveFile = new File(String.valueOf(saveFilePath));
        try {
            Scanner fileReader = new Scanner(saveFile);
            int taskCount = 0;
            while (fileReader.hasNextLine()) {
                try {
                    String[] commands = Parser.processSaveInput(fileReader.nextLine());
                    doCommand(commands[0]);
                    taskCount++;
                    if (commands[1] != null) {
                        doCommand(commands[1] + taskCount);
                    }
                } catch (ChatterBoxError e) {
                    textUi.printMessage(e.getMessage());
                }
            }
            fileReader.close();
            textUi.printMessage("Save file " + saveFilePath + " loaded");
        } catch (FileNotFoundException e) {
            try {
                saveFile.createNewFile();
                assert saveFilePath != null;
            } catch (IOException e1) {
                textUi.printMessage("Error Reading Chatterbox save file");
            }
            throw new ChatterBoxDataFileError();
        }
    }

    public StoredList getSaveList() {
        return saveList;
    }

    /**
     * Writes the given task list into a save file.
     * @param taskList The current task list of the running Chatterbox.
     */
    public void writeToSave(StoredList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(saveFilePath.toString());
            for (int i = 0; i < taskList.getSize(); i++) {
                fileWriter.write(taskList.getItem(i).storeTask());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error Writing Chatterbox save file");
        }
    }

    /**
     * Performs the command given to create a StoredList from the save file.
     * @param input The command from the user.
     * @throws ChatterBoxError For any ChatterBox related errors.
     */
    public void doCommand(String input) throws ChatterBoxError {
        String[] command = Parser.processInput(input);
        switch (Commands.valueOf(command[0].toUpperCase())) {
        case LIST:
            break;
        case MARK:
            saveList.getItem(Integer.parseInt(command[1])).setCompleted(true);
            break;
        case UNMARK:
            saveList.getItem(Integer.parseInt(command[1])).setCompleted(false);
            break;
        case TODO:
            saveList.addItem(new ToDo(command[1]));
            break;
        case DEADLINE:
            saveList.addItem(
                    new Deadline(command[1], Parser.processDateTime(command[2]))
            );
            break;
        case EVENT:
            saveList.addItem(
                    new Event(command[1], Parser.processDateTime(command[2]),
                            Parser.processDateTime(command[3]))
            );
            break;
        default:
            throw new ChatterBoxError();
        }
    }
}
