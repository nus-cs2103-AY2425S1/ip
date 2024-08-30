package Timo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.time.format.DateTimeFormatter;

class Task {
    private String taskype = "T";
    private boolean mark;
    private String val;
    public Task(boolean mark, String val) {
        this.mark = mark;
        this.val = val;
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
        return "[" + getStatusIcon() + "] " + this.val;
    }
}

class Todo extends Task {
    private String tasktype = "T";


    public Todo(boolean mark, String val) {
        super(mark, val);
    }


    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString();
    }
}

class Deadline extends Task {
    private LocalDateTime date;
    private String tasktype = "D";

    public Deadline(boolean mark, String val, LocalDateTime date){
        super(mark, val);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + this.tasktype + "]" + super.toString() +
                " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;
    private String tasktype = "E";

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

//Timo.Storage: beginning I read from the file, end I update the file
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

                    //get the important values to create the Timo.Deadline
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
                    //getting important values to create the Timo.Event
                    String[] split_up = details.split(" \\(from: | to: |\\)");

                    //see if the task has been done or not
                    if (Character.compare(tmp.charAt(4), 'X') == 0) {
                        arr.add(new Event(true, split_up[0], split_up[1], split_up[2]));
                    } else {
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


            //create filewriter to append to file
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

//Timo.TaskList: operations to add and delete tasks in the list
//has operations to return list
class TaskList {
    private List<Task> arr;

    public TaskList(List<Task> arr) {
        this.arr = arr;
    }

    public TaskList() {

        this.arr = new ArrayList<Task>();
    }

    /**
     * adds a Task to the array
     * @param task
     */
    public void add(Task task) {

        this.arr.add(task);
    }

    /**
     * removes the Task given the Task number and returns the Task that is removed
     * @param number
     * @return Task
     */
    public Task delete(int number) {
        return this.arr.remove(number);
    }

    /**
     * displays the Task in the array
     * @return List<Task>
     */
    public List<Task> showList() {
        return this.arr;
    }

    /**
     * Marks the Task given the Task number, and returns the Task
     * @param num
     * @return Task
     */
    public Task mark(int num) {
        Task chosen = this.arr.get(num - 1);
        chosen.markDone();
        return chosen;
    }

    /**
     * Unmark the Task given the Task number, and returns the Task
     * @param num
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
     * greets the user
     */
    public void greet() {
        System.out.println("----------------------------");
        System.out.println("Hello! I'm Timo.\nWhat can I do for you?");
        System.out.println("----------------------------");
    }

    /**
     * says goodbye to the user
     */
    public void bye() {
        System.out.println("----------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------");
    }

    /**
     * given a TaskList, prints our the list items
     * @param lst
     *
     * @see TaskList
     */
    public void printList(TaskList lst) {
        System.out.println("----------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= lst.showList().size(); i++) {
            Task chosen = lst.showList().get(i - 1);
            System.out.println(i + ". " + chosen);
        }
        System.out.println("----------------------------");
    }

    /**
     * reads user input, and returns it
     * @return String
     */
    public String readCommand() {
        Scanner echo = new Scanner(System.in);
        return echo.nextLine();
    }

    /**
     * given Task, prints out that the Task is marked
     * @param chosen
     */
    public void printMark(Task chosen) {
        System.out.println("----------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(chosen);
        System.out.println("----------------------------");
    }

    /**
     * given Task, prints out that the Task is unmarked
     * @param chosen
     */
    public void printUnmark(Task chosen) {
        System.out.println("----------------------------");
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(chosen);
        System.out.println("----------------------------");
    }

    /**
     * given Todo and size, prints out that Todo task has been added, and prints out the total size of array
     * @param todo
     * @param size
     */
    public void printTodo(Task todo, int size) {
        System.out.println("----------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("----------------------------");
    }

    /**
     * given Deadline and size, prints out that Deadline task has been added, and prints out the total size of array
     * @param deadline
     * @param size
     */
    public void printDeadline(Deadline deadline, int size) {
        System.out.println("----------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("----------------------------");
    }

    /**
     * prints our Deadline error
     */
    public void printDeadlineError() {
        System.out.println("----------------------------");
        System.out.println("deadline usage: deadline <task> /by yyyy-mm-dd <time/24hr format>");
        System.out.println("----------------------------");
    }

    /**
     * given Event task and size, prints out that Event task has been added, and prints out the total size of the array
     * @param event
     * @param size
     */
    public void printEvent(Event event, int size) {
        System.out.println("----------------------------");
        System.out.println("Got it. I've added this task:");
        System.out.println(event.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("----------------------------");
    }

    /**
     * given the task and the size, prints out the task that was deleted and the size of the array
     * @param task
     * @param size
     */
    public void printDelete(Task task, int size) {
        System.out.println("----------------------------");
        System.out.println("Got it. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("----------------------------");
    }

    /**
     * prints out the error
     * @param e
     */
    public void printUnknownCommandError(TimoException e) {
        System.out.println("----------------------------");
        System.out.println(e);
        System.out.println("----------------------------");
    }

}


//Timo.Parser: deals with making sense of the commands
//parser deal, then send commands to ui
class Parser {
    private UI ui;
    private Storage storage;
    private TaskList taskList;
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
    public void parse(String command) throws TimoException {
        switch (command) {
            case "bye":
                this.ui.bye();
                this.storage.store(this.taskList.showList());
                break;

            case "list":
                this.ui.printList(this.taskList);
                break;

            default:
                String s = String.valueOf(command.charAt(command.length() - 1));
                if (command.startsWith("mark")) {
                    //get the Timo.Task number to mark
                    String num = s;
                    int target = Integer.parseInt(num);

                    //find the task to mark
                    Task chosen = this.taskList.mark(target);
                    this.ui.printMark(chosen);
                } else if (command.startsWith("unmark")) {
                    //get the Timo.Task number to unmark
                    String num = s;
                    int target = Integer.parseInt(num);

                    //find the task to unmark
                    Task chosen = this.taskList.unmark(target);
                    this.ui.printUnmark(chosen);
                } else if (command.startsWith("todo")) {
                        String[] tmp = command.split(" ", 2);
                        if (tmp.length != 2) {
                            throw new TimoException("Usage todo: todo <task> (need argument)");
                        }
                        Todo task = new Todo(false, tmp[1]);
                        this.taskList.add(task);
                        this.ui.printTodo(task, this.taskList.showList().size());
                } else if (command.startsWith("deadline")) {
                    String[] tmp = command.split("deadline |/by ");
                    String todo = tmp[1];
                    String datetime = tmp[2].trim();
                    DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                    try {
                        LocalDateTime date = LocalDateTime.parse(datetime, a);
                        System.out.println(date);
                        Deadline task = new Deadline(false, todo, date);
                        this.taskList.add(task);
                        this.ui.printDeadline(task, this.taskList.showList().size());
                    } catch (DateTimeException e) {
                        this.ui.printDeadlineError();
                    }
                } else if (command.startsWith("event")) {
                    String[] tmp = command.split("event |/from |/to ");
                    Event task = new Event(false, tmp[1], tmp[2], tmp[3]);
                    this.taskList.add(task);
                    this.ui.printEvent(task, this.taskList.showList().size());
                } else if (command.startsWith("delete")) {
                    String num = s;
                    int target = Integer.parseInt(num);

                    // Timo.Task that is deleted
                    Task task = this.taskList.delete(target);
                    this.ui.printDelete(task, this.taskList.showList().size());
                } else if (command.startsWith("find")) {
                    String phrase = command.split(" ", 2)[1];

                    // TaskList to print out
                    TaskList lst = new TaskList();

                    for (Task task: this.taskList.showList()) {
                        if (task.toString().contains(phrase)) {
                            lst.add(task);
                        }
                    }
                    this.ui.printList(lst);

                } else {
                    throw new TimoException("I'm sorry, I do not know what that means");
                }
        }
    }
}


public class Timo {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    private Parser parser;


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

    public void run() {
        //welcome
        ui.greet();

        //print list initially
        ui.printList(this.tasks);

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals("bye")) {
                    isExit = true;
                }
                parser.parse(fullCommand);
            } catch (TimoException e) {
                this.ui.printUnknownCommandError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Timo("list.txt").run();
    }
}
