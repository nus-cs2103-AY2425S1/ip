package cow.filesaver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import cow.exceptions.CowExceptions;
import cow.exceptions.MissingParametersException;
import cow.exceptions.UnknownCommandException;
import cow.message.Messages;
import cow.tasks.Deadlines;
import cow.tasks.Event;
import cow.tasks.Task;
import cow.tasks.Todo;
import cow.todolist.TodoList;

/**
 * Creates a FileSaver Object.
 **/
public class FileSaver {
    private String filePath;
    private FileWriter fw;

    /**
     * Constructor to create a file saver.
     *
     * @param filePath of the data file.
     */
    public FileSaver(String filePath) {
        assert !filePath.isEmpty() : "File path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Writes the items in the TodoList into the file.
     *
     * @throws CowExceptions if the file or path has an issue.
     */
    public void saveData(TodoList todoList) throws CowExceptions {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            String saveData = todoList.getSaveData();
            fw.write(saveData);
            fw.close();
        } catch (IOException e) {
            throw new CowExceptions(Messages.FILE_ERROR);
        }

    }

    /**
     * Loads the data from the file into the TodoList.
     *
     * @return The loaded todolist.
     * @throws IOException if the file does not exist.
     */
    public TodoList loadData() throws IOException, CowExceptions {
        checkFilePath();
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        TodoList td = new TodoList();
        while (s.hasNext()) {
            loadData(s.nextLine(), td);
        }
        return td;
    }

    /**
     * Loads the data from each line of the data file to the todo list.
     *
     * @param nextLine is the next line of data to input.
     * @param td is the todo list to add the data to.
     * @throws CowExceptions if the data is malformed.
     */
    private static void loadData(String nextLine, TodoList td) throws CowExceptions {
        String[] tokens = nextLine.split(" \\| ");
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
            td.add(t);
        } catch (UnknownCommandException | ArrayIndexOutOfBoundsException | MissingParametersException e) {
            throw new CowExceptions(e.getMessage());
        }
    }

    /**
     * Checks if the file path exist, if not it will be created.
     *
     * @throws IOException if the file path is invalid.
     */
    private void checkFilePath() throws CowExceptions {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                throw new CowExceptions(Messages.FILE_ERROR);
            }

        }
    }

}
