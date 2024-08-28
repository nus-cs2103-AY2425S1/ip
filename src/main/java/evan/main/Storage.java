package evan.main;

import evan.exception.EvanException;
import evan.task.Task;

import java.util.ArrayList;

public class Storage {
    private final String filePath; // Consider replacing String with another type that is specifically for paths

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws EvanException {
        return new ArrayList<>();
    }
}
