package rainy.tasks;

import java.util.ArrayList;
import java.util.Collections;

import rainy.database.UI;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;

/**
 * Keeps track of the list of classes and variables pertaining to how many tasks there are.
 *
 */
public class TaskTracker {
    private ArrayList<Task> taskList;
    private int counter;
    private boolean receivedInputs;
    private UI ui;

    /**
     * Constructs a new <code>TaskTracker</code> object.
     */
    public TaskTracker() {
        this.taskList = new ArrayList<Task>();
        this.counter = 0;
        this.receivedInputs = false;
        this.ui = new UI();
    }

    public int getCounter() {
        return this.counter;
    }

    public boolean getReceivedInputs() {
        return this.receivedInputs;
    }

    public void toggleReceivedInputs() {
        this.receivedInputs = !this.receivedInputs;
    }

    /**
     * Displays the list of tasks when user inputs list.
     * This method also checks if a particular task is marked or unmarked.
     * @return  This method returns the list of tasks in <code>String</code>.
     */
    public String getList() {
        if (this.counter == 0) {
            return "No tasks currently!";
        } else {
            String result = "";
            result += "Here are your tasks!!! Remember to complete them!!!" + "\n";
            for (int i = 0; i < this.counter; i++) {
                if (this.taskList.get(i).getIsDone() == false) {
                    result += "" + (i + 1) + ". [ ] " + this.taskList.get(i);
                } else {
                    result += "" + (i + 1) + ". [X] " + this.taskList.get(i);
                }
                if (i != this.counter - 1) {
                    result += "\n";
                }
            }
            return result;
        }
    }

    /**
     * Marks a particular task as specified by the user.
     * @param z                               Represents the index of the task in the task list.
     * @throws InvalidIndexException          Thrown by <code>TaskManager</code> object when user
     *                                        provides a non-existent task number.
     * @throws InvalidMarkAndUnmarkException  Thrown by <code>Task</code> object when user
     *                                        marks a marked tasked or unmarks an unmarked task.
     */
    public void markDone(int z) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (z < 0 || z >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            Task newTask = this.taskList.get(z);
            newTask.mark();
            this.taskList.set(z, newTask);
            if (this.receivedInputs) {
                this.ui.markedTask();
            }
        }
    }

    /**
     * Unmarks a particular task as specified by the user.
     * @param q                               Represents the index of the task in the task list.
     * @throws InvalidIndexException          Thrown by <code>TaskManager</code> object when user
     *                                        provides a non-existent task number.
     * @throws InvalidMarkAndUnmarkException  Thrown by <code>Task</code> object when user
     *                                        marks a marked tasked or unmarks an unmarked task.
     */
    public void unmarkDone(int q) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (q < 0 || q >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            Task newTask = this.taskList.get(q);
            newTask.unmark();
            this.taskList.set(q, newTask);
            if (this.receivedInputs) {
                this.ui.unmarkedTask();
            }
        }
    }

    /**
     * Sorts the list according to the date of the task.
     */

    public void sortList() {
        String tempStorage;
        for (Task t: this.taskList) {
            tempStorage = t.toString();
        }
        Collections.sort(this.taskList);
        if (this.taskList.size() > 0) {
            ui.sortDone();
        }
    }

    /**
     * Updates the list with a todo task as specified by the user.
     * @param s  Represents the name of the todo task.
     */

    public void updateListToDo(String s) {
        this.taskList.add(new ToDo(s));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter
                    + " task/s on your list. You got this Champ!!!");
        }
    }

    /**
     * Updates the list with a deadline task as specified by the user.
     * @param s  Represents the name of the deadline.
     * @param t  Represents the date and time of the deadline.
     */
    public void updateListDeadline(String s, String t) {
        this.taskList.add(new Deadline(s, t));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter
                    + " task/s on your list. You got this Champ!!!");
        }
    }

    /**
     * Updates the list with an event task as specified by the user.
     * @param s  Represents the name of the event.
     * @param t  Represents the date of the event.
     * @param u  Represents the timeframe of the event.
     */
    public void updateListEvent(String s, String t, String u) {
        this.taskList.add(new Event(s, t, u));
        this.counter++;
        if (this.receivedInputs) {
            this.ui.addedTask();
            System.out.println(this.taskList.get(this.counter - 1));
            System.out.println("Take note you currently have " + this.counter
                    + " task/s on your list. You got this Champ!!!");
        }
    }

    /**
     * Deletes a task as specified by the user.
     * @param i                       Represents the index of the task.
     * @throws InvalidIndexException  Thrown when user enters an invalid index.
     */
    public void delete(int i) throws InvalidIndexException {
        if (this.counter == 0) {
            this.ui.noTasksBeforeDelete();
        } else if (i < 0 || i >= this.counter) {
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            this.ui.removedTask();
            System.out.println(this.taskList.get(i));
            this.taskList.remove(i);
            counter--;
            System.out.println("You currently have " + counter
                    + " task/s on hand. Please remember to complete them!");
        }

    }

    /**
     * Compares the keyword provided by user with the task description of each task in the list.
     * @param compareTask  Represents the keyword provided by the user.
     */
    public void findTask(String compareTask) {
        int numMatch = 0;
        String output = "Here are some tasks matching your description:";
        for (Task t: this.taskList) {
            String taskDescription = t.toString();
            String[] extractDesc = taskDescription.substring(4).split("\\(");
            String secondCompare = extractDesc[0];
            if (secondCompare.toUpperCase().contains(compareTask.toUpperCase())) {
                numMatch++;
                output += (t.getIsDone()) ? "\n" + numMatch + ". [X] " + t
                        : "\n" + numMatch + ". [ ] " + t;
            }
        }
        if (numMatch > 0) {
            System.out.println(output);
        } else {
            System.out.println("There are no tasks matching your description!");
        }
    }

    public void receivedFirstInput() {
        this.receivedInputs = true;
    }
}
