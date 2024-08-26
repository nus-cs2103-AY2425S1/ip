import java.util.Scanner;
import java.util.HashMap;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SaveManager {
    protected Path savePath;
    protected Path saveFilePath;
    protected String saveFileName;
    
    protected static HashMap<Character, Boolean> IS_DONE = new HashMap<>() {{
        put('0', false);
        put('1', true);
    }};

    public SaveManager(Path rootPath, String saveFileName) {
        this.savePath = rootPath.resolve("data");
        this.saveFileName = saveFileName;
        this.saveFilePath = savePath.resolve(saveFileName + ".txt");
    }

    public void createSave() {
        try {
            Files.createDirectory(savePath);
            Files.createFile(saveFilePath);
        } catch (IOException e) {
            // File already exists, don't need to do anything
        }
    }

    public TaskList loadSave() {
        try (Scanner sc = new Scanner(saveFilePath.toFile())) {
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                try {
                Task task = stringToTask(sc.nextLine());
                tasks.addTask(task);
                } catch (StringIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
                    System.out.println("Data is corrupted: " + e.toString());
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    private Task stringToTask(String input) throws StringIndexOutOfBoundsException, 
            NumberFormatException, IllegalArgumentException, DateTimeParseException{

        Boolean isDone = IS_DONE.get(input.charAt(2));
        if (isDone == null) {
            throw new IllegalArgumentException();
        }
        if (input.startsWith("T")) {
            String description = input.substring(input.indexOf(" /d") + 3).trim();

            return Task.toDo(description, isDone);
        } else if (input.startsWith("D")) {
            int startOfDesc = input.indexOf(" /d");
            int startOfTime = input.indexOf(" /t");

            String description = input.substring(startOfDesc + 3, startOfTime);
            LocalDate time = LocalDate.parse(input.substring(startOfTime + 3).trim());

            return Task.deadline(description, isDone, time);
        } else if (input.startsWith("E")) {
            int startOfDesc = input.indexOf(" /d");
            int startOfStart = input.indexOf(" /b");
            int startOfEnd = input.indexOf(" /e");

            String description = input.substring(startOfDesc + 3, startOfStart);
            LocalDate start = LocalDate.parse(input.substring(startOfStart + 3, startOfEnd));
            LocalDate end = LocalDate.parse(input.substring(startOfEnd + 3).trim());

            return Task.event(description, isDone, start, end);
        } else {
            throw new IllegalArgumentException("Unsupported task type.");
        }
    }

    public void writeToSave(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(saveFilePath.toString());
        String[] tasksToAdd = tasks.toSave();
        for (String task : tasksToAdd) {
            fw.write(task + System.lineSeparator());
        }
        fw.close();
    }

}
