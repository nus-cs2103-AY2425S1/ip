package duch;

import java.util.ArrayList;

import duch.history.AddTaskHistory;
import duch.history.DeleteTaskHistory;
import duch.history.History;
import duch.history.MarkHistory;
import duch.history.NoChangeHistory;
import duch.history.UnmarkHistory;
import duch.task.Deadline;
import duch.task.Event;
import duch.task.Task;
import duch.task.Todo;

/**
 * Parse commands received from the CLI.
 * 
 */
public class Parser {
    ArrayList<History> historyList = new ArrayList<History>();
    TaskList tasks = new TaskList();
    Ui ui = new Ui();

    public Parser(TaskList lst) {
        tasks = lst;
    }

    /**
     * Concat strings in indexes [s, e), with spaces between the words.
     * 
     * @param arr the array.
     * @param s start index.
     * @param e end index.
     * @return concated string.
     */
    public String concat(String[] arr, int s, int e) {
        String out = "";
        for (int i = s; i < e; i ++) {
            if (out.equals("")) {
                out += arr[i];
            } else {
                out += " " + arr[i];
            }
        }
        return out;
    }
    
    private int find(String[] arr, String s) {
        for (int i = 0; i < arr.length; i ++) {
            if (arr[i].equals(s)) return i;
        }
        return -1;
    }

    private String printAddTask(Task t) {   
        assert t != null;
        return ui.print("Got it. I've added this task\n  " + t.toString() + "\nNow you have " + tasks.size() + " tasks in the list\n");
    }

    private String printMark(Task t) {
        assert t != null;
        return ui.print("Nice! I have marked this task as done:\n  " + t.toString()+ "\n");
    }

    private String printUnmark(Task t) {
        assert t != null;
        return ui.print("OK, I've marked this task as not done yet:\n  " + t.toString() + "\n");
    }

    private String printDelete(Task t) {
        assert t != null;
        return ui.print("Noted. I have removed this task\n  " + t.toString() + "\n");
    }

    /**
     * Parse a todo command
     * 
     * @param arr The splitted arr.
     * @throws DuchException If command is invalid.
     */
    public String todo(String[] arr) throws DuchException {
        if (arr.length == 1) {
            throw new DuchException("The description of todo cannot be empty");
        }
        
        Todo t = new Todo(concat(arr, 1, arr.length));
        tasks.addTask(t);
        
        historyList.add(new AddTaskHistory(tasks));
        
        return printAddTask(t);
    }

    /**
     * parse a deadline command 
     * 
     * @param arr The splitted arr.
     * @throws DuchException If command is invalid.
     */
    public String deadline(String[] arr) throws DuchException {
        int byIdx = find(arr, "/by");
        if (byIdx == -1) {
            throw new DuchException("/by date is not found");
        }
        
        String task = concat(arr, 1, byIdx);
        String by = concat(arr, byIdx + 1, arr.length);
        if (task.equals("")) {
            throw new DuchException("The description of deadline cannot be empty");
        }
        
        Task t = new Deadline(task, by);
        tasks.addTask(t);
        
        historyList.add(new AddTaskHistory(tasks));
        
        return printAddTask(t);
    }

    /**
     * Parse an event command
     * 
     * @param arr The splitted arr.
     * @throws DuchException If command is invalid.
     */
    public String event(String[] arr) throws DuchException {
        int fromIdx = find(arr, "/from");
        int toIdx = find(arr, "/to");
        if (fromIdx == -1 || toIdx == -1) {
            throw new DuchException("/from date (and/or) /to date is not found");
        }

        String task = concat(arr, 1, fromIdx);
        String from = concat(arr, fromIdx + 1, toIdx);
        String to = concat(arr, toIdx + 1, arr.length);
        if (task.equals("")) {
            throw new DuchException("The description of event cannot be empty");
        }
        
        Event t = new Event(task, from, to);
        tasks.addTask(t);
        
        historyList.add(new AddTaskHistory(tasks));
        
        return printAddTask(t);
    }

    /**
     * Parse a list command
     * 
     * @param arr The splitted arr.
     */
    public String list(String[] arr) {
        String out = "";
        for (int i = 0; i < tasks.size(); i ++) {
            out += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        
        historyList.add(new NoChangeHistory());
        
        return ui.print(out);
    }

    /**
     * Parse a mark command
     * 
     * @param arr The splitted arr.
     */
    public String mark(String[] arr) throws DuchException {
        int idx = Integer.parseInt(arr[1]) - 1;
        Task t = tasks.get(idx);
        if (t.getIsDone()) {
            throw new DuchException("The task is marked already");
        }
        tasks.markTask(idx);
        
        historyList.add(new MarkHistory(t));
        
        return printMark(t);
    }

    /**
     * Parse a unmark command.
     * 
     * @param arr The splitted command.
     */
    public String unmark(String[] arr) throws DuchException {
        int idx = Integer.parseInt(arr[1]) - 1;
        Task t = tasks.get(idx);
        if (!t.getIsDone()) {
            throw new DuchException("The task is unmarked already");
        }
        tasks.unmarkTask(idx);
        
        historyList.add(new UnmarkHistory(t));
        
        return printUnmark(t);
    }

    /**
     * Parse a delete command.
     * 
     * @param arr The splitted command.
     */
    public String delete(String[] arr) {
        int idx = Integer.parseInt(arr[1]) - 1;
        Task t = tasks.get(idx);
        tasks.deleteTask(idx);
        
        System.out.println(idx);
        historyList.add(new DeleteTaskHistory(tasks, t, idx));
        
        return printDelete(t);
    }

    public String find(String[] arr) throws DuchException {
        if (arr.length == 1) {
            throw new DuchException("The keyword for find cannot be empty");
        }

        String out = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i ++) {
            Task task = tasks.get(i);
            if (task.getTask().contains(arr[1])) {
                out += (i + 1) + "." + task.toString() + "\n";
            }
        }

        historyList.add(new NoChangeHistory());
        
        return ui.print(out); 
    }

    /**
     * Undo the last executed command.
     * 
     * @return the updated list of tasks, in a string to be displayed.
     * @throws DuchException
     */
    public String undo() throws DuchException {
        if (historyList.size() == 0) {
            throw new DuchException("No commands to undo");
        }

        History hist = historyList.remove(historyList.size() - 1);
        hist.undo();

        if (hist instanceof NoChangeHistory) {
            return "Undo successful\nNo change to your task list\n";
        }

        String out = "Undo successful\nHere are your updated task\n";
        for (int i = 0; i < tasks.size(); i ++) {
            out += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }

        return out;
    }

    /**
     * Returns the string of the result after processing the command.
     * 
     * @param cmd 
     * @return 
     * @throws DuchException
     */
    public String handleCommand(String cmd) throws DuchException {
        String[] splitted = cmd.split(" ");
        String action = splitted[0];
        if (action.equals("list")) {
            return list(splitted);
        } else if (action.equals("todo")) {
            return todo(splitted);
        } else if (action.equals("deadline")) {
            return deadline(splitted);
        } else if (action.equals("event")) {
            return event(splitted);
        } else if (action.equals("mark")) {
            return mark(splitted);
        } else if (action.equals("unmark")) {
            return unmark(splitted);
        } else if (action.equals("delete")) {
            return delete(splitted);
        } else if (action.equals("find")) {
            return find(splitted);
        } else if (action.equals("undo")) {
            return undo();
        } else {
            throw new DuchException("Invalid Command. Please re-enter command");
        }
    }
}
