package cow.filesaver;

import cow.message.Message;
import cow.message.Messages;
import cow.exceptions.CowExceptions;
import cow.exceptions.MissingParametersException;
import cow.exceptions.UnknownCommandException;
import cow.tasks.Deadlines;
import cow.tasks.Event;
import cow.tasks.Task;
import cow.tasks.Todo;
import cow.todoList.TodoList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSaver {
    private String filePath;
    private FileWriter fw;

    public FileSaver(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the items in the TodoList.TodoList into the file
     *
     * @throws CowExceptions if the file or path has an issue
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
     * Loads the data from the file into the TodoList.TodoList
     *
     * @return The loaded todolist
     * @throws IOException   if the file does not exist
     */
    public TodoList loadData() throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        TodoList td = new TodoList();
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
                td.add(t);
            } catch (UnknownCommandException | ArrayIndexOutOfBoundsException | MissingParametersException e) {
                Message.printCorruptionDetected();
            }
        }
        return td;
    }

}
