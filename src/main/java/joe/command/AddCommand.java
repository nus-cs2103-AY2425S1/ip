package joe.command;

import joe.Commands;
import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * This class represents commands that will add a new task to the task list, such as, 'todo', 'deadline' and 'event'.
 */
public class AddCommand extends Command {
    String[] inputArr;
    Commands c;
    public AddCommand(Commands c, String[] inputArr) {
        this.c = c;
        this.inputArr = inputArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        Task newTask = null;
        switch (c) {
            case TODO:
                if (inputArr.length == 1) {
                    throw new JoeException("OOPS!!! The description of a todo cannot be empty.");
                }
                newTask = new Todo(String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length)));
                break;
            case DEADLINE:
                int byIdx = Arrays.asList(inputArr).indexOf("/by");
                if (byIdx == -1) {
                    throw new JoeException("Oops! Try adding it like this: deadline {task description} /by {duedate}");
                }
                String taskDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, byIdx));
                String taskBy = String.join("", Arrays.copyOfRange(inputArr, byIdx + 1, inputArr.length));
                try {
                    LocalDate taskByDate = LocalDate.parse(taskBy);
                    newTask = new Deadline(taskDesc, taskByDate);
                } catch (DateTimeParseException e) {
                    throw new JoeException("Please enter a date with the format yyyy-mm-dd");
                }
                break;
            case EVENT:
                int fromIdx = Arrays.asList(inputArr).indexOf("/from");
                int toIdx = Arrays.asList(inputArr).indexOf("/to");
                if (fromIdx == -1) {
                    throw new JoeException("Oops! Let's try again with this format: event {task description} /from {start date} /to {end date}");
                }
                String eventDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, fromIdx));
                if (toIdx != -1) {
                    // to date exists
                    String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, toIdx));
                    String eventTo = String.join(" ", Arrays.copyOfRange(inputArr, toIdx + 1, inputArr.length));
                    try {
                        LocalDate fromDate = LocalDate.parse(eventFrom);
                        LocalDate toDate = LocalDate.parse(eventTo);
                        newTask = new Event(eventDesc, fromDate, toDate);
                    } catch (DateTimeParseException e) {
                        throw new JoeException("Please enter a date with the format yyyy-mm-dd");
                    }
                } else {
                    String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, inputArr.length));
                    LocalDate fromDate = LocalDate.parse(eventFrom);
                    LocalDate maxDate = LocalDate.MAX;
                    newTask = new Event(eventDesc, fromDate, maxDate);
                }
                break;
        }
        taskList.addTask(newTask);
        System.out.println("\tGot it. I've added this task:\n\t  " + newTask);
        System.out.printf("\tNow you have %d tasks in the list.%n", taskList.getSize());
    }
}
