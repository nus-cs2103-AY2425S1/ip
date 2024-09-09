import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Task {
    private String tasktype = "T";
    private boolean mark;
    private final String description;
    public Task(boolean mark, String description) {
        this.mark = mark;
        this.description = description;
    }

    public void markDone() {
        this.mark = true;
        return;
    }

    public void markUndone() {
        this.mark = false;
        return;
    }

    public String getTask() {
        return this.tasktype;
    }

    public String getStatusIcon() {
        return (this.mark ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

class Todo extends Task {
    private final String tasktype = "T";


    public Todo(boolean mark, String val) {
        super(mark, val);
    }


    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString();
    }
}

class Deadline extends Task {
    private final LocalDateTime date;
    private final String tasktype = "D";

    public Deadline(boolean mark, String val, LocalDateTime date) {
        super(mark, val);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString()
                + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }
}

class Event extends Task {
    private final String from;
    private final String to;
    private final String tasktype = "E";

    public Event(boolean mark, String val, String from, String to) {
        super(mark, val);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}

class TimoException extends Exception {

    public TimoException(String errorMessage) {
        super(errorMessage);
    }

}

//Storage: beginning I read from the file, end I update the file
class Storage {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from a file specified by the `filepath` and returns them as a list of `Task` objects.
     * <p>
     * The tasks in the file are expected to be in a specific format, indicating the type of task
     * (Todo, Deadline, or Event) and its completion status.
     * </p>
     *
     * @return A `List` of `Task` objects representing the tasks stored in the file.
     *
     * @throws FileNotFoundException if the file at the specified `filepath` does not exist.
     *
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     */
    public List<Task> load() throws FileNotFoundException {
        File f = new File(this.filepath);

        //initialise array to store the values
        List<Task> arr = new ArrayList<Task>();

        //check if the file exists
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String tmp = s.nextLine();
                if (tmp.startsWith("[T]")) {
                    String[] a = tmp.split("] ", 2);
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Todo(true, a[1]));
                    } else {
                        arr.add(new Todo(false, a[1]));
                    }
                } else if (tmp.startsWith("[D]")) {
                    //remove the [D][?] from the line
                    String a = tmp.split("] ")[1];

                    //get the important values to create the Deadline
                    String[] b = a.split(" \\(by: |\\)");

                    LocalDateTime datetime = LocalDateTime.parse(b[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));


                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Deadline(true, b[0], datetime));
                    } else {
                        arr.add(new Deadline(false, b[0], datetime));
                    }
                } else {
                    assert tmp.startsWith("[E]") : "Error in program";
                    String details = tmp.split("] ", 2)[1];
                    //getting important values to create the Event
                    String[] split_up = details.split(" \\(from: | to: |\\)");

                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Event(true, split_up[0], split_up[1], split_up[2]));
                    } else {
                        assert Character.compare(tmp.charAt(4), ' ') == 0 : "Error in file";
                        arr.add(new Event(false, split_up[0], split_up[1], split_up[2]));
                    }
                }
            }
            return arr;
        } else {
            throw new FileNotFoundException("file not found!");
        }

    }

    /**
     * create the relevant Task and add it to the array
     * @param tmp the Task read from one line in text file
     * @param arr the array that will store all tasks
     */
    public void addTaskToArray(String tmp, List<Task> arr) {
        if (tmp.startsWith("[T]")) {
            String[] a = tmp.split("] ", 2);
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Todo(true, a[1]));
            } else {
                arr.add(new Todo(false, a[1]));
            }
        } else if (tmp.startsWith("[D]")) {
            //remove the [D][?] from the line
            String a = tmp.split("] ")[1];

            //get the important values to create the Deadline
            String[] b = a.split(" \\(by: |\\)");

            LocalDateTime datetime = LocalDateTime.parse(b[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));

            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Deadline(true, b[0], datetime));
            } else {
                arr.add(new Deadline(false, b[0], datetime));
            }
        } else {
            //removing the [E][?] from the line
            String details = tmp.split("] ", 2)[1];
            //getting important values to create the Event
            String[] split_up = details.split(" \\(from: | to: |\\)");

            //see if the task has been done or not
            if (Character.compare(tmp.charAt(4), 'X') == 0) {
                arr.add(new Event(true, split_up[0], split_up[1], split_up[2]));
            } else {
                arr.add(new Event(false, split_up[0], split_up[1], split_up[2]));
            }
        }
    }

    /**
     * Stores a list of tasks to a file specified by the `filepath`.
     * <p>
     * If the file does not exist, it will be created. The method overwrites any existing content
     * in the file and writes each task in the provided list to the file, with each task on a new line.
     * </p>
     *
     * @param arr A `List` of `Task` objects to be stored in the file.
     *
     * @see Task
     */
    public void store(List<Task> arr) {
        //create new file if file does not exist
        File file = new File(this.filepath);


        try {
            boolean filecreated = file.createNewFile();

            //delete all contents in the file
            FileWriter fil = new FileWriter(this.filepath);
            fil.write("");
            fil.close();


            //create FileWriter to append to file
            FileWriter fw = new FileWriter(this.filepath, true);
            for (Task i: arr) {
                fw.write(i + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

//TaskList: operations to add and delete tasks in the list
//has operations to return list
class TaskList {
    private final List<Task> arr;

    public TaskList(List<Task> arr) {
        this.arr = arr;
    }

    public TaskList() {

        this.arr = new ArrayList<Task>();
    }

    /**
     * adds a Task to the array
     * @param task a task
     */
    public void add(Task task) {

        this.arr.add(task);
    }

    /**
     * removes the Task given the Task number and returns the Task that is removed
     * @param number the task number
     * @return Task
     */
    public Task delete(int number) {
        return this.arr.remove(number);
    }

    /**
     * displays the Task in the array
     * @return list of tasks
     */
    public List<Task> showList() {
        return this.arr;
    }

    /**
     * Marks the Task given the Task number, and returns the Task
     * @param num the task number
     * @return Task
     */
    public Task mark(int num) {
        Task chosen = this.arr.get(num - 1);
        chosen.markDone();
        return chosen;
    }

    /**
     * Unmark the Task given the Task number, and returns the Task
     * @param num the task number
     * @return Task
     */
    public Task unmark(int num) {
        Task chosen = this.arr.get(num - 1);
        chosen.markUndone();;
        return chosen;
    }
}


class UI {

    /**
     * says goodbye to the user
     */
    public String bye() {
        return "----------------------------\n"
            + "Bye. Hope to see you again soon!\n"
            + "----------------------------";
    }

    /**
     * given a TaskList, prints our the list items
     * @param lst the tasklist
     *
     * @see TaskList
     */
    public String printList(TaskList lst) {

        StringBuilder listString = new StringBuilder();

        for (int i = 1; i <= lst.showList().size(); i++) {
            Task chosen = lst.showList().get(i - 1);
            listString.append(i).append(". ").append(chosen).append("\n");
        }
        return "----------------------------\n"
            + "Here are the tasks in your list:\n"
            + listString
            + "----------------------------";
    }


    /**
     * given Task, prints out that the Task is marked
     * @param chosen the task that is marked
     */
    public String printMark(Task chosen) {
        return "----------------------------\n"
            + "Nice! I've marked this task as done:\n"
            + chosen + "\n"
            + "----------------------------";
    }

    /**
     * given Task, prints out that the Task is unmarked
     * @param chosen the task that is unmarked
     */
    public String printUnmark(Task chosen) {
        return "----------------------------\n"
                + "Nice! I've marked this task as undone:\n"
                + chosen + "\n"
                + "----------------------------";
    }

    /**
     * given Todo and size, prints out that Todo task has been added, and prints out the total size of array
     * @param todo the todo task
     * @param size the size of the task list
     */
    public String printTodo(Task todo, int size) {
        return "----------------------------\n"
            + "Got it. I've added this task:\n"
            + todo + "\n"
            + "Now you have " + size + " tasks in the list.\n"
            + "----------------------------";
    }

    /**
     * given Deadline and size, prints out that Deadline task has been added, and prints out the total size of array
     * @param deadline the deadline task
     * @param size the size of the task list
     */
    public String printDeadline(Deadline deadline, int size) {
        return "----------------------------\n"
                + "Got it. I've added this task:\n"
                + deadline + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "----------------------------";
    }

    /**
     * prints our Deadline error
     */
    public String printDeadlineError() {
        return "----------------------------\n"
            + "deadline usage: deadline <task> /by yyyy-mm-dd <time/24hr format>\n"
            + "----------------------------";
    }

    /**
     * given Event task and size, prints out that Event task has been added, and prints out the total size of the array
     * @param event the event task
     * @param size the size of the task list
     */
    public String printEvent(Event event, int size) {
        return "----------------------------"
                + "Got it. I've added this task:\n"
                + event + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + "----------------------------";
    }

    /**
     * given the task and the size, prints out the task that was deleted and the size of the array
     * @param task the task
     * @param size the size of task list
     */
    public String printDelete(Task task, int size) {
        return "----------------------------\n"
            + "Got it. I've removed this task:\n"
            + task + "\n"
            + "Now you have " + size + " tasks in the list.\n"
            + "----------------------------";
    }

    /**
     * prints out the error
     * @param e the unknown command error
     */
    public String printUnknownCommandError(TimoException e) {
        return "----------------------------\n"
            + e + "\n"
            + "----------------------------";
    }

}


//Parser: deals with making sense of the commands
//parser deal, then send commands to ui
class Parser {
    private final UI ui;
    private final Storage storage;
    private final TaskList taskList;
    public Parser(UI ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Parses and executes a given command string.
     * <p>
     * The method interprets the command and performs the corresponding action, such as marking tasks,
     * adding tasks (Todo, Deadline, Event), deleting tasks, printing the task list, or exiting the application.
     * If the command is not recognized or lacks necessary arguments, a `TimoException` is thrown.
     * </p>
     *
     * @param command The command string input by the user.
     *
     * @throws TimoException If the command is invalid or does not include the necessary arguments.
     *
     * @see TaskList
     * @see Task
     * @see Todo
     * @see Deadline
     * @see Event
     * @see TimoException
     */
    public String parse(String command) throws TimoException {
        String cmd = command.split(" ", 2)[0];
        switch (cmd) {
<<<<<<< HEAD
=======
        case "bye":
            this.storage.store(this.taskList.showList());
            return this.ui.bye();

        case "list":
            return this.ui.printList(this.taskList);

        case "mark":
            String taskNumber = String.valueOf(command.charAt(command.length() - 1));


            //get the Task number to mark
            int markTarget = Integer.parseInt(taskNumber);


            //find the task to mark
            Task markedTask = this.taskList.mark(markTarget);
            return this.ui.printMark(markedTask);

        case "unmark":

            //get the Task number to unmark
            int unmarkTarget = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));

            //find the task to unmark
            Task unmarkedTask = this.taskList.unmark(unmarkTarget);
            return this.ui.printUnmark(unmarkedTask);

>>>>>>> master
        case "todo":
            String[] todoCommands = command.split(" ", 2);

            //checks if todo command is correct
            if (todoCommands.length != 2) {
                throw new TimoException("Usage todo: todo <task> (need argument)");
            }

            Todo task = new Todo(false, todoCommands[1]);
            this.taskList.add(task);
            return this.ui.printTodo(task, this.taskList.showList().size());

        case "deadline":
            String[] deadlineCommands = command.split("deadline |/by ");
            String todo = deadlineCommands[1];
            String datetime = deadlineCommands[2].trim();
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

            try {
                LocalDateTime date = LocalDateTime.parse(datetime, inputFormat);
                Deadline deadline = new Deadline(false, todo, date);
                this.taskList.add(deadline);
                return this.ui.printDeadline(deadline, this.taskList.showList().size());
            } catch (DateTimeException e) {
                return this.ui.printDeadlineError();
            }

        case "event":
            String[] eventCommands = command.split("event |/from |/to ");
            Event event = new Event(false, eventCommands[1], eventCommands[2], eventCommands[3]);
            this.taskList.add(event);
            return this.ui.printEvent(event, this.taskList.showList().size());

        case "mark":
            String taskNumberToMark = String.valueOf(command.charAt(command.length() - 1));

            //get the Task number to mark
            int markTarget = Integer.parseInt(taskNumberToMark);

            //find the task to mark
            Task markedTask = this.taskList.mark(markTarget);
            return this.ui.printMark(markedTask);

        case "unmark":

            String taskNumberToUnmark = String.valueOf(command.charAt(command.length() - 1));

            //get the Task number to unmark
            int unmarkTarget = Integer.parseInt(taskNumberToUnmark);

            //find the task to unmark
            Task unmarkedTask = this.taskList.unmark(unmarkTarget);
            return this.ui.printUnmark(unmarkedTask);

        case "list":
            return this.ui.printList(this.taskList);

        case "delete":
            //get the Task number to delete
            int deleteTarget = Integer.parseInt(String.valueOf(command.charAt(command.length() - 1)));

            // Task that is deleted
            Task deleteTask = this.taskList.delete(deleteTarget);
            return this.ui.printDelete(deleteTask, this.taskList.showList().size());

        case "find":
            String phrase = command.split(" ", 2)[1];

            // TaskList to print out
            TaskList temporaryList = new TaskList();

            for (Task currentTask: this.taskList.showList()) {
                if (currentTask.toString().contains(phrase)) {
                    temporaryList.add(currentTask);
                }
            }
            return this.ui.printList(temporaryList);

        case "bye":
            this.storage.store(this.taskList.showList());
            return this.ui.bye();

        default:
            return this.ui.printUnknownCommandError(new TimoException("I'm sorry, I do not know what that means"));
        }
    }
}

/**
 * main class Timo
 */
public class Timo {

    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    private final Parser parser;

    /**
     * initialises Timo with ui, storage and parser
     * @param filepath the path to store the data
     */
    public Timo(String filepath) {
        ui = new UI();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(ui, storage, tasks);
    }


    /**
     * get parser
     *
     */
    public Parser getParser() {
        return this.parser;
    }

    // public static void main(String[] args) {
    // new Timo("list.txt").run();
}

