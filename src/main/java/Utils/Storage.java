package Utils;

import ChatterBoxErrors.ChatterBoxError;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private final Path saveFilePath;
    private final Path saveDirectoryPath;
    private final Ui ui = new Ui();
    private StoredList saveList = new StoredList();

    public Storage(String fileName) {
        String currentDirectory = System.getProperty("user.dir");
        saveFilePath = Paths.get(currentDirectory, fileName);
        saveDirectoryPath = saveFilePath.getParent();
    }

    public void readFromSave() {
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
                    ui.printMessage(e.getMessage());
                }
            }
            fileReader.close();
            ui.printMessage("Save file " + saveFilePath + " loaded");
        } catch (FileNotFoundException e) {
            try {
                saveFile.createNewFile();
                ui.printMessage("Save file " + saveFilePath + " cannot be found.");
            } catch (IOException e1) {
                ui.printMessage("Error Reading Chatterbox save file");
            }
        }
    }

    public StoredList getSaveList() {
        return saveList;
    }

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

    public void doCommand(String input) throws ChatterBoxError {
        String message;
        try {
            String[] command = Parser.processInput(input);
            switch (Commands.valueOf(command[0].toUpperCase())) {
            case LIST:
                break;
            case MARK:
                message = saveList.getItem(Integer.parseInt(command[1])).setCompleted(true);
                break;
            case UNMARK:
                message = saveList.getItem(Integer.parseInt(command[1])).setCompleted(false);
                break;
            case TODO:
                message = saveList.addItem(new ToDo(command[1]));
                break;
            case DEADLINE:
                message = saveList.addItem(
                        new Deadline(command[1], Parser.processDateTime(command[2]))
                );
                break;
            case EVENT:
                message = saveList.addItem(
                        new Event(command[1], Parser.processDateTime(command[2]),
                                Parser.processDateTime(command[3]))
                );
                break;
            }
        } catch (ChatterBoxError e) {
            throw e;
        }
    }
}
