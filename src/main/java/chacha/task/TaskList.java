package chacha.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.exception.EventTimeException;
import chacha.exception.WrongDateFormatException;
import chacha.exception.WrongTimeFormatException;
import chacha.parser.DateParser;
import chacha.parser.TimeParser;

/**
 * Represents a list of tasks that the users wants to keep track of.
 *
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int getTotalNumber() {
        return this.tasks.size();
    }

    /**
     * Returns the Task at the index.
     *
     * @param index Index.
     * @return Task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns ToDoTask to be added in the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return ToDoTask.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task addToDo(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 5) {
            throw new ChaChaException();
        } else {
            String description = cmd.substring(5);
            Task task = new ToDoTask(description, false);
            this.tasks.add(task);
            storage.writeFile(ui.printToDo(description));
            return task;
        }
    }

    /**
     * Returns EventTask to be added in the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return EventTask.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task addEvent(String cmd, Ui ui, Storage storage) throws ChaChaException,
            EventTimeException, WrongTimeFormatException, WrongDateFormatException {
        if (cmd.length() <= 6) {
            throw new ChaChaException();
        }

        String temp = cmd.substring(6);
        String[] arr = temp.split(" /");
        if (arr.length < 4) {
            throw new ChaChaException();
        }

        String description = arr[0];
        if (!arr[2].startsWith("from")) {
            throw new EventTimeException("start");
        }
        if (!arr[3].startsWith("to")) {
            throw new EventTimeException("end");
        }

        LocalDate date = DateParser.parseDate(arr[1]);
        if (date == null) {
            throw new WrongDateFormatException();
        }

        LocalTime startTime = TimeParser.parseStringToTime(arr[2].substring(5));
        LocalTime endTime = TimeParser.parseStringToTime(arr[3].substring(3));
        if (startTime == null || endTime == null) {
            throw new WrongTimeFormatException();
        }

        Task task = new EventTask(description, false, date, startTime, endTime);
        this.tasks.add(task);

        storage.writeFile(ui.printEvent(description, date, startTime, endTime));

        return task;
    }

    /**
     * Returns DeadlineTask to be added in the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return DeadlineTask.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task addDeadline(String cmd, Ui ui, Storage storage) throws ChaChaException, EventTimeException {
        if (cmd.length() <= 9) {
            throw new ChaChaException();
        }

        String temp = cmd.substring(9);
        String[] arr = temp.split(" /");
        if (arr.length < 2) {
            throw new ChaChaException();
        }

        String description = arr[0];
        if (!arr[1].startsWith("by")) {
            throw new EventTimeException("deadline");
        }

        LocalDate date = DateParser.parseDate(arr[1].substring(3));
        if (date == null) {
            throw new WrongDateFormatException();
        }
        Task task = new DeadlineTask(description, false, date);
        this.tasks.add(task);
        storage.writeFile(ui.printDeadline(description, date));
        return task;
    }

    /**
     * Returns the string representation of the list of tasks.
     *
     * @param ui Ui.
     * @return String representation.
     */
    public String printList(Ui ui) {
        if (this.tasks.isEmpty()) {
            return "You currently have no task. ";
        }
        return ui.printList(this.tasks, "Here are the tasks in your list: \n");
    }

    /**
     * Returns Task that is deleted from the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return Task deleted.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task deleteTask(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 7) {
            throw new ChaChaException();
        }

        int index = Integer.parseInt(cmd.substring(7));
        Task deletedTask = this.tasks.remove(index - 1);
        storage.overwriteFile(this);
        return deletedTask;
    }

    /**
     * Returns Task that is marked done in the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return Task marked done.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task markDone(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 5) {
            throw new ChaChaException();
        }

        int index = Integer.parseInt(cmd.substring(5));
        Task markedTask = this.tasks.get(index - 1).markDone();
        storage.overwriteFile(this);
        return markedTask;
    }

    /**
     * Returns Task that is marked undone in the list.
     *
     * @param cmd Command.
     * @param ui Ui.
     * @param storage Storage.
     * @return Task marked undone.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public Task markUndone(String cmd, Ui ui, Storage storage) throws ChaChaException {
        if (cmd.length() <= 7) {
            throw new ChaChaException();
        }

        int index = Integer.parseInt(cmd.substring(7));
        Task unmarkedTask = this.tasks.get(index - 1).markUndone();
        storage.overwriteFile(this);
        return unmarkedTask;
    }

    /**
     * Checks through the list of tasks to filter out the ones that contain the keyword given.
     * Returns the Tasks in ArrayList.
     *
     * @param cmd Command
     * @param ui UI
     * @return List of Task.
     * @throws ChaChaException if the command is not inputted correctly.
     */
    public ArrayList<Task> find(String cmd, Ui ui) throws ChaChaException {
        if (cmd.length() <= 5) {
            throw new ChaChaException();
        }

        String inputText = cmd.substring(5);
        ArrayList<Task> listOfResult = new ArrayList<>();
        for (int i = 0; i < this.getTotalNumber(); i++) {
            Task task = this.getTask(i);
            if (task.compareText(inputText)) {
                listOfResult.add(task);
            }
        }
        return listOfResult;
    }
}
