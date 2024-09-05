package elon;

import java.time.LocalDate;

/**
 * Provides user interface functions for interacting with the task list and its tasks.
 */
public class Ui {
    /**
     * Draws a horizontal line in the console for separation.
     */
    public void drawLine() {
        System.out.println("\t -------------------------------------------------------");
    }


    /**
     * Prints a greeting message to the console.
     */
    public void greet() {
        drawLine();
        System.out.println("\t Hello! I'm Elon");
        System.out.println("\t What can I do for you?");
        drawLine();
    }

    /**
     * Prints a goodbye message to the console.
     */
    public void exit() {
        drawLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        drawLine();
    }

    /**
     * Prints the number of tasks currently in the task list.
     *
     * @param size the number of tasks in the list
     */
    public void numOfTasks(int size) {
        drawLine();
        System.out.println(String.format("\t Now you have %d tasks in the list.", size));
        drawLine();
    }

    /**
     * Prints the tasks in the list to the console.
     * If the list is empty, prints a message indicating that there are no tasks.
     * Otherwise, prints each task with its index, incremented by 1 from the index in the list.
     *
     * @param list the TaskList containing tasks to be displayed
     */
    public void listTasks(TaskList list) {
        drawLine();
        if (list.isEmpty()) {
            System.out.println("\t There are no tasks in your list.");
        } else {
            System.out.println("\t Here are the tasks in your list:");
            for (int i = 0; i < list.listSize(); i++) {
                System.out.println(String.format("\t %d.", i + 1) + list.getTask(i).toString());
            }
        }
        drawLine();
    }

    /**
     * Marks the task at the specified index as done and prints the task to the console.
     * If the task is already marked as done, prints a message indicating so.
     *
     * @param index the index of the task to mark as done
     * @param list the TaskList containing the task
     */
    public void markTask(int index, TaskList list) {
        drawLine();
        if (list.getIsDone(index)) {
            System.out.println("\t Task is already marked as done:");
        } else {
            System.out.println("\t Nice! I've marked this task as done:");
            list.markDone(index);
        }
        System.out.println("\t " + list.getTask(index).toString());
        drawLine();
    }

    /**
     * Marks the task at the specified index as not done and prints the task to the console.
     * If the task is already marked as not done, prints a message indicating so.
     *
     * @param index the index of the task to mark as not done
     * @param list  the TaskList containing the task
     */
    public void unmarkTask(int index, TaskList list) {
        drawLine();
        if (!list.getIsDone(index)) {
            System.out.println("\t Task is already not marked as done:");
        } else {
            System.out.println("\t OK, I've marked this task as not done yet:");
            list.markNotDone(index);
        }
        System.out.println("\t " + list.getTask(index).toString());
        drawLine();
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     */
    public void startAddTask() {
        drawLine();
        System.out.println("\t Got it. I've added this task:");
    }


    /**
     * Prints a message indicating the updated number of tasks in the list after adding a new task.
     *
     * @param size the updated number of tasks in the list
     */
    public void endAddTask(int size) {
        System.out.println(String.format("\t Now you have %d tasks in the list.", size));
        drawLine();
    }

    /**
     * Adds a ToDo task to the task list based on the provided input array.
     * Throws an ElonException if the description is missing.
     *
     * @param inputArr the input array containing the task description
     * @param list the TaskList to add the ToDo task to
     * @throws ElonException if the description is not specified
     */
    public void addToDo(String[] inputArr, TaskList list) throws ElonException {
        if (inputArr.length <= 1) {
            throw new ElonException("Error. Description for ToDo task not specified.");
        }
        String task = "";
        for (int i = 1; i < inputArr.length; i++) {
            task += inputArr[i] + " ";
        }
        task = task.strip();
        ToDo todo = new ToDo(task, false);
        list.addTask(todo);
        startAddTask();
        System.out.println("\t " + todo.toString());
    }

    /**
     * Adds a Deadline task to the task list based on the provided input array.
     * Throws an ElonException if the description or deadline date is missing.
     *
     * @param inputArr the input array containing the task description and deadline date
     * @param list the TaskList to add the Deadline task to
     * @throws ElonException if the description or deadline date is not specified
     */
    public void addDeadline(String[] inputArr, TaskList list) throws ElonException {
        if (inputArr.length <= 1) {
            throw new ElonException("Error. Description and By date for Deadline task not specified.");
        }
        int i = 1;
        String task = "";
        while (!inputArr[i].equals("/by")) {
            task += inputArr[i] + " ";
            i++;
        }
        task = task.strip();
        String by = "";
        if (inputArr.length <= i+1) {
            throw new ElonException("Error. By date for Deadline task not specified.");
        }
        for (int j = i+1; j < inputArr.length; j++) {
            by += inputArr[j] + " ";
        }
        by = by.strip();
        LocalDate byDate = LocalDate.parse(by);
        Deadline deadline = new Deadline(task, false, byDate);
        list.addTask(deadline);
        startAddTask();
        System.out.println("\t " + deadline.toString());
    }

    /**
     * Adds an Event task to the task list based on the provided input array.
     * Throws an ElonException if the description, start date, or end date is missing.
     *
     * @param inputArr the input array containing the task description, start date, and end date
     * @param list the TaskList to add the Event task to
     * @throws ElonException if the description, start date, or end date is not specified
     */
    public void addEvent(String[] inputArr, TaskList list) throws ElonException {
        if (inputArr.length <= 1) {
            throw new ElonException("Error. Description, From and To date for Event task not specified.");
        }
        int i = 1;
        String task = "";
        while (!inputArr[i].equals("/from")) {
            task += inputArr[i] + " ";
            i++;
        }
        task = task.strip();
        i++;
        if (inputArr.length <= i) {
            throw new ElonException("Error. From date for Event task not specified.");
        }
        String from = "";
        while (!inputArr[i].equals("/to")) {
            from += inputArr[i] + " ";
            i++;
        }
        from = from.strip();
        LocalDate fromDate = LocalDate.parse(from);
        i++;
        if (inputArr.length <= i) {
            throw new ElonException("Error. To date for Event task not specified.");
        }
        String to = "";
        for (int j = i; j < inputArr.length; j++) {
            to += inputArr[j] + " ";
        }
        to = to.strip();
        LocalDate toDate = LocalDate.parse(to);
        Event event = new Event(task, false, fromDate, toDate);
        list.addTask(event);
        startAddTask();
        System.out.println("\t " + event.toString());
    }

    /**
     * Deletes the task at the specified index from the task list and prints a message to console.
     *
     * @param index the index of the task to delete
     * @param list the TaskList containing the task
     */
    public void deleteTask(int index, TaskList list) {
        drawLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t " + list.getTask(index));
        list.removeTask(index);
        endAddTask(list.listSize());
    }
}
