package rainy.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import rainy.database.UI;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;

/**
 * Keeps track of the list of classes and variables pertaining to how many tasks there are.
 *
 */
public class TaskTracker {
    private static final int START_DESC = 4;
    private static final char END_OF_OUTPUT = '^';
    private static final char ERROR_OUTPUT = '`';
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

    public ArrayList<Task> getList() {
        return this.taskList;
    }


    /**
     * Displays the list of tasks when user inputs list.
     * This method also checks if a particular task is marked or unmarked.
     * @return  This method returns the list of tasks in <code>String</code>.
     */
    public String printList() {
        assert(this.counter >= 0);
        AtomicInteger numMatch = new AtomicInteger(1);
        return (this.counter == 0) ? "No tasks currently!" : (this.taskList.stream()
                    .map(t -> t.getIsDone() == false ? "\n" + numMatch.getAndIncrement() + ". [ ] " + t
                        : "\n" + numMatch.getAndIncrement() + ". [X] " + t)
                    .reduce("Here are your tasks!!! Remember to complete them!!!", (a, b) -> a + b));
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
        assert(this.counter >= 0);
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (z < 0 || z >= this.counter) {
            System.out.println(this.ui.invalidTask() + ERROR_OUTPUT);
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            Task newTask = this.taskList.get(z);
            newTask.mark();
            assert(newTask.getIsDone() == true);
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
        assert(this.counter >= 0);
        if (this.counter == 0) {
            this.ui.noTasksAdded();
        } else if (q < 0 || q >= this.counter) {
            System.out.println(this.ui.invalidTask() + ERROR_OUTPUT);
            throw new InvalidIndexException(this.ui.invalidTask());
        } else {
            Task newTask = this.taskList.get(q);
            newTask.unmark();
            assert(newTask.getIsDone() == false);
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
    public void addListToDo(String s) {
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
    public void addListDeadline(String s, String t) {
        if (this.receivedInputs) {
            assert(Integer.valueOf(t.substring(0, 4)) instanceof Integer);
            assert(Integer.valueOf(t.substring(5, 7)) instanceof Integer);
            assert(Integer.valueOf(t.substring(8, 10)) instanceof Integer);
            assert(Integer.valueOf(t.substring(11, 15)) instanceof Integer);
            assert(t.charAt(4) == '-');
            assert(t.charAt(7) == '-');
        }
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
    public void addListEvent(String s, String t, String u) {
        if (this.receivedInputs) {
            assert(Integer.valueOf(t.substring(0, 4)) instanceof Integer);
            assert(Integer.valueOf(t.substring(5, 7)) instanceof Integer);
            assert(Integer.valueOf(t.substring(8, 10)) instanceof Integer);
            assert(t.charAt(4) == '-');
            assert(t.charAt(7) == '-');
            assert(Integer.valueOf(u.substring(0, 4)) instanceof Integer);
            assert(Integer.valueOf(u.substring(8, 12)) instanceof Integer);
            assert(u.substring(5, 7).equals("to"));
        }
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
            System.out.println(this.ui.invalidTask() + ERROR_OUTPUT);
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
        AtomicInteger numMatch = new AtomicInteger(1);
        List<Task> newList = this.taskList;
        String output = newList.stream()
                .filter(t -> t.toString().substring(START_DESC)
                        .split("\\(")[0].toUpperCase().contains(compareTask.toUpperCase()))
                .map(x -> ((x.getIsDone()) ? "\n" + (numMatch.getAndIncrement()) + ". [X] " + x
                        : "\n" + (numMatch.getAndIncrement()) + ". [ ] " + x))
                .reduce("Here are some tasks matching your description: ", (a, b) -> a + b);
        if (numMatch.get() > 1) {
            System.out.println(output);
        } else {
            this.ui.noTaskMatchFound();
        }
    }

    /**
     * Updates the description of the deadline
     * @param taskNumber  The index number of the <code>Task</code> in the task list.
     * @param name        The new description of the <code>Deadline</code> to be updated.
     */
    public void updateDeadlineName(int taskNumber, String name) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof Deadline currentDeadline) {
            currentDeadline.setName(name);
        }
    }

    /**
     * Updates the date of the deadline.
     * @param taskNumber   The index number of the <code>Task</code> in the task list.
     * @param date         The new date of the <code>Deadline</code> to be updated.
     */
    public void updateDeadlineDate(int taskNumber, String date) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof Deadline currentDeadline) {
            currentDeadline.setDate(date);
        }
    }

    /**
     * Updates the description of the event.
     * @param taskNumber   The index number of the <code>Task</code> in the task list.
     * @param name         The new description of the <code>Event</code> to be updated.
     */
    public void updateEventName(int taskNumber, String name) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof Event currentEvent) {
            currentEvent.setName(name);
        }
    }

    /**
     * Updates the date of the event.
     * @param taskNumber   The index number of the <code>Task</code> in the task list.
     * @param date         The new date of the <code>Event</code> to be updated.
     */
    public void updateEventDate(int taskNumber, String date) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof Event currentEvent) {
            currentEvent.setDate(date);
        }
    }

    /**
     * Updates the timeframe of the event.
     * @param taskNumber   The index number of the <code>Task</code> in the task list.
     * @param time         The new timeframe of the <code>Event</code> to be updated.
     */
    public void updateEventTime(int taskNumber, String time) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof Event currentEvent) {
            currentEvent.setTime(time);
        }
    }

    /**
     * Updates the description of the ToDo.
     * @param taskNumber   The index number of the <code>Task</code> in the task list.
     * @param description  The new description of the <code>ToDo</code> to be updated.
     */
    public void updateToDo(int taskNumber, String description) {
        Task currentEdit = this.taskList.get(taskNumber);
        if (currentEdit instanceof ToDo currentToDo) {
            currentToDo.setDescription(description);
        }
    }

    public void receivedFirstInput() {
        this.receivedInputs = true;
    }
}
