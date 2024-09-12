package MeowMeow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;

/**
 * Represents a saving and loading system
 */
public class Saving {

    private Path saveFilePath;
    private String pathName;
    private File file;

    private TaskList taskList;

    public Saving(String pathName) {
        this.pathName = pathName;
        this.saveFilePath = Path.of(pathName);
        this.taskList = new TaskList();
    }

    /**
     * Reads the save file and fills the TaskList with the Tasks from the save file.
     *
     * @param filePath File path of the save file.
     * @throws FileNotFoundException If the file cannot be found at that file path.
     */
    public void load(String filePath) throws FileNotFoundException {
        this.file = new File(filePath);
        Scanner s = new Scanner(this.file);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            switch (parts[0]) {
                case "T":
                    ToDo todo = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markDone();
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markDone();
                    }
                    taskList.add(event);
                    break;
            }
        }
        s.close();
    }

    /**
     * Loads the data from the file path if a save file exists at that path.
     * If the save file does not exist at that path, new directories and a file are created at that file path.
     *
     * @throws IOException If an I/O error occurs when creating new directories or file.
     */
    public void getData() throws IOException {
        try {
            load(pathName);
            //System.out.println("loaded file");
            //System.out.println("full path: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            Files.createDirectories(saveFilePath.getParent());
            Files.createFile(saveFilePath);
            this.file = new File(pathName);
            //System.out.println("created file");
        }
    }

    /**
     * Writes each Task in the TaskList into the save file at pathName.
     *
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void saveData() throws IOException {
        try {
            FileWriter fw = new FileWriter(this.pathName);
            for (Task task : taskList) {
                fw.write(task.toFileFormat() + "\n");
            }
            fw.close();
            //System.out.println("saved tasks");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    /**
     * Returns the TaskList which has no Tasks if there are no saved Tasks
     * or if load has not been called.
     * Returns the TaskList which contains the saved Tasks if load has run
     * successfully and the save file was not empty.
     *
     * @return TaskList.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}