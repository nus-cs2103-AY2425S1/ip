import exceptions.MissingParametersException;
import exceptions.UnknownCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class FileSaver {
    private final TodoList todoList;
    private static final String FILE_PATH = "data/cow.txt";
    private FileWriter fw;
    public FileSaver(TodoList todoList) throws IOException {
        Path filePath = Paths.get("data/cow.txt");
        if (!Files.exists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
        this.todoList = todoList;
    }

    /**
     * Writes the items in the TodoList into the file
     * @throws IOException if the file or path has an issue
     */
    public void saveData() throws IOException {
        this.fw = new FileWriter(FILE_PATH);
        String saveData = todoList.getSaveData();
        fw.write(saveData);
        fw.close();
    }

    /**
     * Loads the data from the file into the TodoList
     * @throws FileNotFoundException if the file does not exist
     * @throws UnknownCommandException if the commands in the file are corrupted
     */
    public void loadData() throws FileNotFoundException, UnknownCommandException, MissingParametersException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] tokens = s.nextLine().split(" \\| ");
            Task t;
            try {
                switch (tokens[0]) {
                    case "T":
                        t = new Todo(tokens[1], tokens[2]);
                        break;
                    case "D":
                        t = new Deadlines(tokens[1], tokens[2], tokens[3]);
                        break;
                    case "E":
                        t = new Event(tokens[1], tokens[2], tokens[3], tokens[4]);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                todoList.add(t);

            } catch (UnknownCommandException e) {
                throw new RuntimeException(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                Message.printCorruptionDetected();
            }
        }
    }

}
