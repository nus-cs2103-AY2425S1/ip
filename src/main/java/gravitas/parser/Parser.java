package gravitas.parser;

import gravitas.exception.DukeException;
import gravitas.task.Deadline;
import gravitas.task.Event;
import gravitas.task.Task;
import gravitas.task.Todo;
import gravitas.tasklist.TaskList;

public class Parser {

    private String mark;
    private String unMark;
    private String added;
    private String deleteMsg;
    private String outOfBound;
    private String emptyTodo;
    private String emptyDeadline;
    private String emptyEvent;
    private String emptyDelete;
    private String emptyFind;

    public Parser() {
        this.outOfBound = "The task that you wish to mark is invalid! please try again!";
        this.emptyTodo = "OOPS!!! The description of a todo cannot be empty.";
        this.emptyEvent = "OOPS!!! The description of a event cannot be empty " +
                "and must contain /from and /to.";
        this.emptyDeadline = "OOPS!!! The description of a deadline cannot be empty and must contain /by ";
        this.emptyDelete = "OOPS!!! The description of a delete cannot be empty.";
        this.mark = "Nice! I've marked this task as done:";
        this.unMark = "OK, I've marked this task as not done yet:";
        this.added = "Got it. I've added this task:";
        this.deleteMsg = "Noted. I've removed this task:";
        this.emptyFind = "OOPS!!! The keyword to find cannot be empty.";
    }

    public void parseMark(TaskList tasklist, String msg) throws DukeException {
        String[] msgFrag = msg.split(" ", 2);
        int index = Integer.parseInt((msgFrag[1])) - 1;

        if (index >= tasklist.size() || index < 0) {
            throw new DukeException(outOfBound);
        }

        Task task = tasklist.getTask(index);
        task.markTask();
        System.out.println(mark);
        tasklist.printTask(task);
    }

    public void parseUnmark(TaskList tasklist, String msg) throws DukeException {
        String[] msgFrag = msg.split(" ", 2);
        int index = Integer.parseInt((msgFrag[1])) - 1;
        if (index >= tasklist.size() || index < 0) {
            throw new DukeException(outOfBound);
        }

        Task task = tasklist.getTask(index);
        task.unMarkTask();
        System.out.println(unMark);
        tasklist.printTask(task);
    }

    public void parseTask(TaskList tasklist, String msg) throws DukeException {
        String[] msgFrag = msg.split(" ", 2);
        Task task;
        if (msgFrag[0].equals("deadline")) {
            if (msgFrag.length <= 1 || !msg.contains("/by ")) {
                throw new DukeException(emptyDeadline);
            }

            String[] deadline = msg.split("/by ", 2);
            //description format: [deadline, description]
            String[] description = deadline[0].split(" ", 2);
            task = new Deadline(description[1], deadline[1]);
        } else if (msgFrag[0].equals("event")) {
            if (msgFrag.length <= 1 || !msg.contains("/from ") || !msg.contains("/to ")) {
                throw new DukeException(emptyEvent);
            }

            String[] event = msg.split("/from ", 2);
            String[] formattedDeadline = event[1].split("/to ", 2);
            task = new Event(event[0], formattedDeadline[0], formattedDeadline[1]);
        } else {
            if (msgFrag.length <= 1) {
                throw new DukeException(emptyTodo);
            }

            task = new Todo(msgFrag[1]);
        }
        tasklist.addTask(task);
        System.out.println(added);
        tasklist.printTask(task);
    }

    public void parseDelete(TaskList tasklist, String msg) throws DukeException {
        String[] msgFrag = msg.split(" ", 2);
        if (msgFrag.length <= 1) {
            throw new DukeException(emptyDelete);
        }
        int index = Integer.parseInt((msgFrag[1])) - 1;

        if (index >= tasklist.size() || index < 0) {
            throw new DukeException(outOfBound);
        }
        Task task = tasklist.getTask(index);
        System.out.println(deleteMsg);
        tasklist.removeTask(index);
        tasklist.printTask(task);
    }

    public void parseFind(TaskList tasklist, String msg) throws DukeException {
        String[] msgFrag = msg.split(" ", 2);

        if (msgFrag.length <= 1) {
            throw new DukeException(this.emptyFind);
        }
        String keyword = msgFrag[1];
        tasklist.printFindTask(keyword);
    }
}
