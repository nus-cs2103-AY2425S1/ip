package cow.commands;

import static cow.message.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;

import cow.exceptions.CowExceptions;
import cow.filesaver.FileSaver;
import cow.message.Ui;
import cow.tasks.Deadlines;
import cow.tasks.Task;
import cow.todolist.TodoList;

// solution below inspired by https://github.com/se-edu/addressbook-level2/tree/master

/** Creates a recurring command object. **/
public class RecurringCommand extends Command {
    public static final String COMMAND_WORD = "recurring";
    public static final String COMMAND_EXAMPLE = "recurring return book /start 7/9/2019 1800 /freq day /times 5";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates recurring deadlines.\n"
            + "Example: " + COMMAND_EXAMPLE;

    private final LocalDateTime start;
    private final String freq;
    private final int times;
    private final String description;

    /**
     * Creates a RecurringCommand instance.
     *
     * @param description of the deadline task.
     * @param start LocalDateTime of the deadline.
     * @param freq either day/week.
     * @param times number of times to repeat.
     */
    public RecurringCommand(String description, LocalDateTime start, String freq, int times) {
        this.description = description;
        this.start = start;
        this.freq = freq;
        this.times = times;
    }

    /**
     * Creates a reoccurring task and adds them to the todo list.
     *
     * @param todoList  the list of the tasks.
     * @param fileSaver files aver object used to write data to txt.
     * @throws CowExceptions any exceptions that might arise from the implementation.
     */
    @Override
    public void execute(TodoList todoList, Ui ui, FileSaver fileSaver) throws CowExceptions {
        System.out.println("ok");
        ui.printRecurringTask(this.times, this.freq);

        for (int i = 0; i < this.times; i++) {
            System.out.println(i);

            LocalDateTime date = getFutureDate(i);
            Task t = new Deadlines(this.description, date);
            todoList.add(t);
            fileSaver.saveData(todoList);
        }

    }

    /**
     * Calculates and returns the future date added by days or weeks.
     *
     * @param i number of days or weeks to add.
     * @return the new local date time.
     * @throws CowExceptions if the frequency is not day or week.
     */
    private LocalDateTime getFutureDate(int i) throws CowExceptions {
        LocalDateTime date;
        System.out.println("'" + this.freq + ",");
        if (this.freq.equals("day")) {
            date = this.start.plusDays(i);
        } else if (this.freq.equals("week")) {
            date = this.start.plusWeeks(i);
        } else {
            throw new CowExceptions(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }
        return date;
    }
}
