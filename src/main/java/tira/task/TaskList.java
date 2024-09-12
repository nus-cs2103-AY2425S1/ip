package tira.task;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tira.TiraException;
import tira.Ui;

/**
 * Manages list of tasks, including adding, deleting, marking, unmarking.
 */
public class TaskList {
    private static final DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ArrayList<Task> tasks;
    private final PrintWriter printer = new PrintWriter(System.out);
    private final Ui ui = new Ui();

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Initialises the taskList using an ArrayList of tasks.
     *
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter method to get the tasks in taskList.
     *
     * @return The list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the numbered task in the taskList.
     *
     * @param command The main command that the user input
     * @param splitCommand Command split using " "
     * @throws TiraException Custom Tira exception class
     */
    public void markTask(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2 && command.equals("mark")) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum).markStatus();
        Task currTask = tasks.get(currNum);
        ui.showMarkTask(currTask);
        printer.flush();
    }

    /**
     * Unmarks the numbered task in the taskList.
     *
     * @param command The main command that the user input
     * @param splitCommand Command split using " "
     * @throws TiraException Custom Tira exception class
     */
    public void unmarkTask(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2 && command.equals("unmark")) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum = Integer.parseInt(splitCommand[1]) - 1;
        tasks.get(currNum).unmarkStatus();
        Task currTask = tasks.get(currNum);
        ui.showUnmarkTask(currTask);
        printer.flush();
    }

    /**
     * Adds the todo task into the task List.
     *
     * @param command The command inputted by user
     * @param splitCommand User command split using " "
     * @throws TiraException Custom Tira Exception
     */
    public void addToDo(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2 && command.equals("ToDo")) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String description = "";
        for (int i = 1; i < splitCommand.length; i++) {
            description += (splitCommand[i]);
            if (i != splitCommand.length - 1) {
                description += " ";
            }
        }
        Task newTask = new ToDo(description);
        tasks.add(newTask);
        ui.showAddTask(newTask, tasks.size());
    }

    /**
     * Adds deadline task to the task list.
     *
     * @param command inputted by the user
     * @param splitCommand separated by the space
     * @throws TiraException custom Tira exception
     */
    public void addDeadline(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2 && command.equals("Deadline")) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands = command.split("/");
        try {
            LocalDate endDate = LocalDate.parse(dateCommands[1].substring(3).trim(), IN_FORMATTER);
            Task deadlineTask = new Deadline(dateCommands[0], endDate);
            tasks.add(deadlineTask);
            ui.showAddTask(deadlineTask, tasks.size());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Adds event task to the task list.
     *
     * @param command inputted by the user
     * @param splitCommand separated by the space
     * @throws TiraException custom Tira exception
     */
    public void addEvent(String command, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2 && command.equals("Event")) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands = command.split("/");
        try {
            LocalDate startDate = LocalDate.parse(dateCommands[1].substring(5).trim(), IN_FORMATTER);
            LocalDate endDate = LocalDate.parse(dateCommands[2].substring(3).trim(), IN_FORMATTER);
            Task eventTask = new Event(dateCommands[0].substring(6).trim(), startDate, endDate);
            tasks.add(eventTask);
            System.out.println("TESTING ADDDEADLINE, DESCRIPTION IS " + eventTask.getDescription());
            ui.showAddTask(eventTask, tasks.size());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes task of specific number in the task list.
     *
     * @param splitCommand separated by the space
     * @throws TiraException custom Tira exception
     */
    public void delete(String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int indexToDelete = Integer.parseInt(splitCommand[1]);
        Task taskToRemove = tasks.get(indexToDelete - 1);
        tasks.remove(indexToDelete - 1);
        ui.showDelete(taskToRemove, tasks.size());
        printer.flush();
    }

    /**
     * Finds a specific task.
     *
     * @param command user command.
     * @param splitCommand User command split by " ".
     * @throws TiraException Custom Tira exception
     */
    public void findTask(String command, String[] splitCommand) throws TiraException {
        ArrayList<Task> tasksThatMatch = new ArrayList<Task>();
        String description = "";
        for (int i = 1; i < splitCommand.length; i++) {
            if (splitCommand[i].equals("/from") || splitCommand[i].equals("/by")) {
                break;
            }
            description += (splitCommand[i]) + " ";
        }
        description = description.trim();
        for (Task task: tasks) {
            String currentTaskDescription = task.getDescription().trim();
            if (description.equals(currentTaskDescription)) {
                tasksThatMatch.add(task);
            }
        }
        if (tasksThatMatch.isEmpty()) {
            ui.showNoMatchingTask();
        } else {
            ui.showMatchingTasks(tasksThatMatch);
        }
    }

}
