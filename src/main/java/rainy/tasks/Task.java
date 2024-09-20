package rainy.tasks;

import java.time.LocalDate;

import rainy.database.UI;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;

/**
 * Represents each individual task. Its parameters tells users of details such as its completion status,
 * name, date, and a <code>UI</code> object to display chatbot messages.
 */
public class Task implements Comparable<Task> {
    private static final char END_OF_OUTPUT = '^';
    private static final char ERROR_OUTPUT = '`';
    protected String name;
    protected LocalDate compareDate;
    private boolean isDone;
    private UI ui;

    /**
     * Constructs a <code>Task</code> object. The date of the object is initially set to MAX.
     * For <code>Deadline</code> objects
     * and <code>Event</code> objects which extend the <code>Task</code> class,
     * the date of the object is determined by user input.
     * For the <code>ToDo</code> class which also extends <code>Task</code>,
     * the date will be set to MAX because the user input does not
     * require a date. This is important when this class sorts the tasks in a list.
     * @param name
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
        this.compareDate = LocalDate.MAX;
        this.ui = new UI();
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Marks the task itself as specified by the user.
     * @throws InvalidMarkAndUnmarkException  Thrown by <code>Task</code> object when user marks a marked tasked.
     */
    public void mark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == true) {
            System.out.println(this.ui.taskDone() + ERROR_OUTPUT);
            throw new InvalidMarkAndUnmarkException(this.ui.taskDone());
        } else {
            this.isDone = true;
        }
    }

    /**
     * Unmarks the task itself as specified by the user.
     * @throws InvalidMarkAndUnmarkException  Thrown by <code>Task</code> object when user unmarks an unmarked task.
     */
    public void unmark() throws InvalidMarkAndUnmarkException {
        if (this.isDone == false) {
            System.out.println(this.ui.taskNotDone() + ERROR_OUTPUT);
            throw new InvalidMarkAndUnmarkException(this.ui.taskNotDone());
        } else {
            this.isDone = false;
        }
    }

    /**
     * Compares tasks by their dates in ascending order. For <code>ToDo</code> tasks with no
     * date requirement, the date parameter is set to MAX. This method sorts them by this MAX date.
     * @param t The <code>Task</code> object to be compared.
     * @return  Returns a -1 if this the object to be compared has a later date, 0 if it has the same date,
     *          and 1 if it has an earlier date.
     */

    @Override
    public int compareTo(Task t) {
        return this.compareDate.compareTo(t.compareDate);
    }
}
