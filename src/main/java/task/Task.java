package task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import storage.Storage;
import system.DateTimeSystem;
import system.Ui;

/**
 * Represents an abstract task that can be managed in a task management system.
 * This class provides methods to initialize, add, delete, find, mark, unmark, and list tasks.
 * It also defines the common properties and
 * behaviors for different types of tasks such as ToDos, Deadlines, and Events.
 * Tasks have a name, status, and a tag.
 */
public abstract class Task {
    protected static Ui ui = new Ui();
    protected static DateTimeSystem dateTimeSystem = new DateTimeSystem();
    protected static Storage storage;

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String name;

    /**
     * Represents the status of a task.
     */
    public enum Status {
        MARKED, UNMARKED
    }

    private Status currentStatus;
    private String tag;

    /**
     * Constructs a new Task with the specified name and tag.
     * The task is initialised with the status UNMARKED.
     *
     * @param name Name of the task.
     * @param tag  Tag of the task.
     */
    public Task(String name, String tag) {
        this.name = name;
        currentStatus = Status.UNMARKED;
        this.tag = tag;
    }

    /**
     * Initialises the task list by reading from storage and populating the task list with tasks.
     *
     * @throws IOException If an I/O error occurs while reading from storage.
     */
    public static void init_list() throws IOException {
        TaskList.getTasks().clear();
        StringBuilder sb = storage.read();
        String[] lines = sb.toString().split("\n");
        for (String s : lines) {
            if (s.contains("[T]")) {
                Task t = initTodo(s);
                TaskList.addTasks(t);
            } else if (s.contains("[D]")) {
                Task d = initDeadline(s);
                TaskList.addTasks(d);
            } else if (s.contains("[E]")) {
                Task e = initEvent(s);
                TaskList.addTasks(e);
            }
        }
    }

    private static Task initTodo(String s) throws IOException {
        String[] tokens = s.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 2; i < tokens.length; i++) {
            name.append(tokens[i]).append(" ");
        }


        Task t = new ToDos(name.toString());
        if (s.contains("[X]")) {
            t.setCurrentStatus(Status.MARKED);
        } else {
            t.setCurrentStatus(Status.UNMARKED);
        }

        return t;
    }

    private static Task initDeadline(String s) throws IOException {
        String[] tokens = s.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder fullDate = new StringBuilder();
        boolean isName = true;
        for (int i = 2; i < tokens.length; i++) {
            if (isName) {
                if (tokens[i].equals("(by:")) {
                    isName = false;
                } else {
                    name.append(tokens[i]).append(" ");
                }
            } else {
                fullDate.append(tokens[i]).append(" ");
            }
        }

        LocalDateTime ldt = processInitEventDateTime(fullDate);

        Task d = new Deadlines(name.toString(), ldt);
        if (s.contains("[X]")) {
            d.setCurrentStatus(Status.MARKED);
        } else {
            d.setCurrentStatus(Status.UNMARKED);
        }

        return d;
    }

    private static Task initEvent(String s) throws IOException {
        String[] tokens = s.split(" ");
        StringBuilder name = new StringBuilder();
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        boolean isName = true;
        boolean isEnd = false;
        for (int i = 2; i < tokens.length; i++) {
            if (isEnd) {
                end.append(tokens[i]).append(" ");
            }
            if (isName) {
                if (tokens[i].equals("(from:")) {
                    isName = false;
                } else {
                    name.append(tokens[i]).append(" ");
                }
            } else {
                if (tokens[i].equals("to:")) {
                    isEnd = true;
                } else {
                    start.append(tokens[i]).append(" ");
                }
            }
        }

        LocalDateTime ldtStart = processInitEventDateTime(start);
        LocalDateTime ldtEnd = processInitEventDateTime(end);

        Task e = new Events(name.toString(), ldtStart, ldtEnd);
        if (s.contains("[X]")) {
            e.setCurrentStatus(Status.MARKED);
        } else {
            e.setCurrentStatus(Status.UNMARKED);
        }

        return e;
    }

    private static LocalDateTime processInitEventDateTime(StringBuilder s) {
        String[] fullDateToken = s.toString().split(" ");
        String[] dateToken = fullDateToken[0].split("-");
        String[] timeToken = fullDateToken[1].split(":");

        LocalDateTime localDateTime =
                dateTimeSystem.createDateTime(dateToken[0], dateToken[1], dateToken[2],
                        timeToken[0], timeToken[1]);

        return localDateTime;
    }

    /**
     * Deletes a task at the specified index from the task list.
     *
     * @param index Index of the task to be deleted (1-based).
     * @return Response message indicating the result of the deletion.
     * @throws IOException If an I/O error occurs while accessing the storage.
     */
    public static String deleteTask(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.getTasks();
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
            return response;
        }

        if (temporaryTaskList.size() < index) {
            response = ui.doesNotExist();
            return response;
        }

        Task temp = temporaryTaskList.get(index - 1);
        temporaryTaskList.remove(temp);
        storage.delete(index);
        response = ui.delete_message(temp);
        return response;
    }
    /**
     * Searches for tasks that match the given input in the stored task list and formats them for display.
     * This method reads the task list from storage, splits it into individual lines,
     * and filters tasks that contain the specified input.
     *
     * @param input The string to search for within the task list.
     * @return A formatted string containing the numbered tasks that match the input.
     * @throws IOException If the file containing the task list cannot be found.
     */
    public static String viewTask(String input) throws IOException {
        StringBuilder sb = storage.read();
        String temp = sb.toString();
        String[] lineTokens = temp.split("\n");
        StringBuilder finalSb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < lineTokens.length; i++) {
            if (lineTokens[i].contains(input)) {
                finalSb.append(count).append(". ").append(lineTokens[i].substring(3)).append("\n");
                count++;
            }
        }

        return ui.viewTaskMessage(input, finalSb);
    }

    /**
     * Finds tasks that match the specified input string.
     *
     * @param input String to search for in task names.
     * @return String containing the matched tasks.
     * @throws IOException If the storage file is not found.
     */
    public static String findTask(String input) throws IOException {
        StringBuilder sb = storage.read();
        String temp = sb.toString();
        String[] lineTokens = temp.split("\n");
        StringBuilder finalSb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < lineTokens.length; i++) {
            String name = "";
            if (lineTokens[i].contains("(")) {
                String[] charTokens = lineTokens[i].split("\\(");
                name = charTokens[0].substring(10);
            } else {
                name = lineTokens[i].substring(10);
            }

            if (name.contains(input)) {
                count++;
                finalSb.append(count).append(". ").append(lineTokens[i].substring(3)).append("\n");
            }
        }

        if (count == 0) {
            return ui.noTaskFound();
        }

        return ui.findTaskMessage(finalSb);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return String containing all tasks.
     * @throws IOException If the storage file is not found.
     */
    public static String list_task() throws IOException {
        StringBuilder temp = storage.read();
        if (temp.toString().equals("")) {
            return ui.emptyList();
        }
        return ui.list_task_message(String.valueOf(temp));
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index Index of the task to be marked (1-based).
     * @return Response message indicating the result of the operation.
     * @throws IOException If an I/O error occurs while accessing the storage.
     */
    public static String mark_task(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.getTasks();
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
        } else {
            if (temporaryTaskList.size() >= index) {
                Task temp = temporaryTaskList.get(index - 1);
                System.out.println("CURRENT STATUS IS : " + temp.getCurrentStatus());
                if (temp.getCurrentStatus() == Status.MARKED) {
                    response = ui.alreadyMarked();
                } else {
                    temp.setCurrentStatus(Status.MARKED);
                    storage.mark(index);
                    response = ui.mark_message(temp.getName());
                }
            } else {
                response = ui.doesNotExist();
            }
        }

        return response;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index Index of the task to be marked (1-based).
     * @return Response message indicating the result of the operation.
     * @throws IOException If an I/O error occurs while accessing the storage.
     */
    public static String unmark_task(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.getTasks();
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
        } else {
            if (temporaryTaskList.size() >= index) {
                Task temp = temporaryTaskList.get(index - 1);
                if (temp.getCurrentStatus() == Status.UNMARKED) {
                    response = ui.alreadyUnmarked();
                } else {
                    temp.setCurrentStatus(Status.UNMARKED);
                    storage.unmark(index);
                    response = ui.unmark_message(temp.getName());
                }
            } else {
                response = ui.doesNotExist();
            }
        }

        return response;
    }

    public String getName() {
        return name;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getTag() {
        return tag;
    }

    public int get_list_size() {
        return TaskList.getTasks().size();
    }

    public abstract LocalDateTime getDate();

    public abstract LocalDateTime getStart();

    public abstract LocalDateTime getEnd();


}
