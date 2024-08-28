import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = storage.loadFromFile();
        } catch (BrunoException e) {
            Ui.showLoadingError();
            tasks = new ArrayList<Task>();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public void addTask(String str, Bruno.TaskType type) throws BrunoException {
        if (str.trim().isEmpty()) {
            throw new EmptyTaskException();
        }

        Task task = null;
        boolean recognized = true;
        if (type.equals(Bruno.TaskType.TODO)) {
            task = new ToDo(str);
        } else if (type.equals(Bruno.TaskType.DEADLINE)) {
            if (!str.contains("/by")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/by")).trim();
            String byString = str.substring(str.indexOf("/by") + 4).trim();
            if (description.isEmpty()) {
                throw new EmptyTaskException();
            }
            try {
                LocalDateTime by = LocalDateTime.parse(byString, formatter1);
                task = new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else if (type.equals(Bruno.TaskType.EVENT)) {
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/from")).trim();
            String fromString = str.substring(str.indexOf("/from") + 6, str.indexOf("/to")).trim();
            String toString = str.substring(str.indexOf("/to") + 4).trim();
            if (description.isEmpty()) {
                throw new EmptyTaskException();
            }
            try {
                LocalDateTime from = LocalDateTime.parse(fromString, formatter1);
                LocalDateTime to = LocalDateTime.parse(toString, formatter1);
                task = new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else {
            recognized = false;
        }

        if (recognized) {
            tasks.add(task);
            storage.updateFile(this.tasks);
            Ui.printAddedTask(task, tasks.size());
        } else {
            throw new UnknownCommandException();
        }
    }
    public void markTask(String num) throws BrunoException {
        try {
            Task task = tasks.get(Integer.parseInt(num) - 1);
            task.complete();
            storage.updateFile(this.tasks);
            Ui.printMarkedTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public void unmarkTask(String num) throws BrunoException {
        try {
            Task task = tasks.get(Integer.parseInt(num) - 1);
            task.uncomplete();
            storage.updateFile(this.tasks);
            Ui.printUnmarkedTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    public void deleteTask(String num) throws BrunoException {
        try {
            Task task = tasks.remove(Integer.parseInt(num) - 1);
            storage.updateFile(this.tasks);
            Ui.printDeletedTask(task, tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }
}
