import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private String workingDir = System.getProperty("user.dir");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Solution for load and readEntry is adapted from https://github.com/david-eom/CS2103T-IP/blob/master/src/main/java/duke/Storage.java
    public Task[] load() {
        Path dataPath = java.nio.file.Paths.get(this.workingDir, this.filePath);
        File database = new File(dataPath.toString());
        File dir = new File(dataPath.getParent().toString());
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            dir.mkdir();
            System.out.println(dataPath.toString());
            if (!Files.exists(dataPath)) {
                 database.createNewFile();
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

        }


        return taskList.toArray(new Task[0]);
    }

    private void readEntry(String entry, ArrayList<Task> taskList) throws EmptyDescriptionException, RandomInputException {
        String[] fields = entry.split("\\|");
        char typeOfTask = fields[0].charAt(0);
        if (typeOfTask == 'T') {
            String command = "todo" + fields[2];
            Task.decideTask(command, taskList);
            if (fields[1].trim().equals("1")) {
                taskList.get(taskList.size() - 1).mark();
            }
        } else if (typeOfTask == 'D') {
            String command = "deadline" + fields[2] + "/by" + fields[3];
            Task.decideTask(command, taskList);
            if (fields[1].trim().equals("1")) {
                taskList.get(taskList.size() - 1).mark();
            }
        } else if (typeOfTask == 'E') {
            String command = "event" + fields[2] + "/from" + fields[3] + " /to" + fields[4];
            Task.decideTask(command, taskList);
            if (fields[1].trim().equals("1")) {
                taskList.get(taskList.size() - 1).mark();
            }
        }
    }

    public void saveData(TaskList taskList) {
        Path dataPath = java.nio.file.Paths.get(this.workingDir, this.filePath);
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