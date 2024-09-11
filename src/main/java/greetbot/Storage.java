package greetbot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class which manages database of the chatbot.
 */
public class Storage {
    private String filePath;
    private String workingDir = System.getProperty("user.dir");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Solution for load and readEntry is adapted from
    // https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Storage.java

    /**
     * Returns a task array which represents the current material in the database.
     * @return Task array which represents the current database.
     */
    public Task[] load() {
        Path dataPath = java.nio.file.Paths.get(this.workingDir, this.filePath);
        File database = new File(dataPath.toString());
        File dir = new File(dataPath.getParent().toString());
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            dir.mkdir();
            if (!Files.exists(dataPath)) {
                database.createNewFile();
                assert database.exists();
            } else {
                Scanner fileReader = new Scanner(database);
                while (fileReader.hasNextLine()) {
                    readEntry(fileReader.nextLine(), taskList);

                }
                fileReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EmptyDescriptionException | RandomInputException e) {
            System.out.println(e);
        }


        return taskList.toArray(new Task[0]);
    }

    private void readEntry(String entry, ArrayList<Task> taskList) throws
            EmptyDescriptionException, RandomInputException {
        final int descriptionPart = 2;
        final int isDonePart = 1;
        final String doneString = "1";

        String[] fields = entry.split("\\|");
        char typeOfTask = fields[0].charAt(0);
        if (typeOfTask == 'T') {
            String command = "todo" + fields[descriptionPart];
            Task.decideTaskFromDatabase(command, taskList);
            if (fields[isDonePart].trim().equals(doneString)) {
                taskList.get(taskList.size() - 1).mark();
            }
        } else if (typeOfTask == 'D') {
            final int byPart = 3;
            String command = "deadline" + fields[descriptionPart] + "/by" + fields[byPart];
            Task.decideTaskFromDatabase(command, taskList);
            if (fields[isDonePart].trim().equals(doneString)) {
                taskList.get(taskList.size() - 1).mark();
            }
        } else if (typeOfTask == 'E') {
            final int fromPart = 3;
            final int toPart = 4;
            String command = "event" + fields[descriptionPart] + "/from" + fields[fromPart] + "/to" + fields[toPart];
            Task.decideTaskFromDatabase(command, taskList);
            if (fields[isDonePart].trim().equals(doneString)) {
                taskList.get(taskList.size() - 1).mark();
            }
        }
    }

    /**
     * Stores the information in the program back to the database.
     * @param taskList Tasks information when program runs.
     */
    public void saveData(TaskList taskList) {
        Path dataPath = java.nio.file.Paths.get(this.workingDir, this.filePath);
        File database = new File(dataPath.toString());
        assert database.exists();
        try (FileWriter databaseWriter = new FileWriter(dataPath.toString(), false)) {
            for (int i = 0; i < taskList.getLength(); i++) {
                databaseWriter.write(taskList.get(i).transferToDatabaseString());
                databaseWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
