package meowmeow;

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
public class Storage {

    private Path saveFilePath;
    private String pathName;
    private File file;

    private TaskList tasks;

    public Storage(String pathName) {
        this.pathName = pathName;
        this.saveFilePath = Path.of(pathName);
        this.tasks = new TaskList();
    }

    /**
     * Reads the save file and fills the TaskList with the Tasks from the save file.
     *
     * @param filePath File path of the save file.
     * @throws FileNotFoundException If the file cannot be found at that file path.
     */
    public void load(String filePath) throws FileNotFoundException {
        assert filePath != null && !filePath.isEmpty() : "File path should not be null or empty";

        this.file = new File(filePath);
        assert this.file.exists() : "File should exist at the specified path";

        Scanner scanner = new Scanner(this.file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            assert parts.length >= 3 : "Line should have at least 3 parts after splitting";

            switch (parts[0]) {
                //Help I changed the setting to make the indents go away for case but it's not working.
                case "T":
                    ToDo todo = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markDone();
                    }
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markDone();
                    }
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markDone();
                    }
                    tasks.add(event);
                    break;
                default:
                    assert false : "Unknown task type in save file: " + parts[0];
            }
        }
        scanner.close();
    }

    /**
     * Loads the data from the file path if a save file exists at that path.
     * If the save file does not exist at that path, new directories and a file are created at that file path.
     *
     * @throws IOException If an I/O error occurs when creating new directories or file.
     */
    public void getData() throws IOException {
        assert pathName != null && !pathName.isEmpty() : "Path name should not be null or empty";
        try {
            load(pathName);
            //System.out.println("loaded file");
            //System.out.println("full path: " + file.getAbsolutePath());
        } catch (FileNotFoundException e) {
            assert !Files.exists(saveFilePath) : "File should not exist before creation";

            Files.createDirectories(saveFilePath.getParent());
            Files.createFile(saveFilePath);
            this.file = new File(pathName);
            //System.out.println("created file");
            assert this.file.exists() : "File should be created at the specified path";
        }
    }

    /**
     * Writes each Task in the TaskList into the save file at pathName.
     *
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void saveData() throws IOException {
        assert tasks != null : "Task list should not be null";
        try {
            FileWriter fw = new FileWriter(this.pathName);
            for (Task task : tasks) {
                fw.write(task.convertToFileFormat() + "\n");
            }
            fw.close();
            //System.out.println("saved tasks");
        } catch (IOException e) {
            assert false : "An error occurred while saving tasks.";        }
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
        return this.tasks;
    }
}