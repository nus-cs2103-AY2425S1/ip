package carly.tasks;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.IntStream;

import carly.Carly;
import carly.exception.CarlyException;
import carly.exception.CarlyIncorrectIndexFormat;
import carly.exception.CarlyIndexOutOfBoundsException;
import carly.exception.CarlyMissingDateTimeException;
/**
 * Represents a list of tasks.
 * Handle a collection of {@link Task} objects.
 */

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void mark(String taskNumString) throws CarlyException{
        Integer taskNum = null;
        try {
            taskNum = Integer.parseInt(taskNumString);
            Task t = this.taskList.get(taskNum - 1);
            Task updatedT = t.markAsDone();
            this.taskList.set(taskNum - 1, updatedT);
            String msg = "Okiee! I've marked this task as done:\n    " + t.toString();
            System.out.println(msg);
        } catch (NumberFormatException e) {
            throw new CarlyIncorrectIndexFormat();
        } catch (IndexOutOfBoundsException e) {
            throw new CarlyIndexOutOfBoundsException(taskNum, this.getSize());
        }
    }

    public void unmark(String taskNumString) throws CarlyException{
        Integer taskNum = null;
        try {
            taskNum = Integer.parseInt(taskNumString);
            Task t = this.taskList.get(taskNum - 1);
            Task updatedT = t.unmarkAsDone();
            this.taskList.set(taskNum - 1, updatedT);
            String msg = "Okiee! I've marked this task as not done yet:\n    " + t.toString();
            System.out.println(msg);
        } catch (IndexOutOfBoundsException e) {
            throw new CarlyIndexOutOfBoundsException(taskNum, this.getSize());
        }
    }

    public void addToDo(String taskDescription) throws CarlyException{
        Todo t = new Todo(taskDescription);
        this.taskList.add(t);
        System.out.println("Got it. I've added this task:\n     " + t.toString());
        taskListSize();
    }

    public void addDeadLine(String taskDescription) throws CarlyException{
        try {
            String[] taskDueDate = taskDescription.split(" /by ");
            String task = taskDueDate[0];
            String duedate = taskDueDate[1];

            Deadline t = new Deadline(task, duedate);
            this.taskList.add(t);

            System.out.println("Got it. I've added this task:\n     " + t.toString());
            taskListSize();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CarlyMissingDateTimeException("task description or \"/by\" command");
        }
    }

    public void addEvent(String taskDescription) throws CarlyMissingDateTimeException{
        try {
            String[] taskTimeParts = taskDescription.split(" /from ");
            String task = taskTimeParts[0];
            String timeParts = taskTimeParts[1];
            String[] startEndTimeParts = timeParts.split(" /to ");
            String startTime = startEndTimeParts[0];
            String endTime = startEndTimeParts[1];

            Event t = new Event(task, startTime, endTime);
            this.taskList.add(t);
            System.out.println("Got it. I've added this task:\n     " + t.toString());
            taskListSize();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CarlyMissingDateTimeException("\"/from\" or \"/to\" command");
        }
    }

    public Integer getSize() {
        return this.taskList.size();
    }

    public void taskListSize() {
        System.out.println("Now you have " + this.getSize() + " tasks in the list.");
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public Task get(Integer index) {
        return this.taskList.get(index);
    }

    public void printTaskList() {
        if (this.taskList.isEmpty()) {
            System.out.println("There's nothing in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            IntStream.range(0, this.getSize())
                    .forEach(i -> System.out.println(
                            MessageFormat.format("{0}.{1}", i + 1, taskList.get(i).toString())));
        }

    }
}
