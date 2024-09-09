package talker.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import talker.TalkerException;
import talker.Ui;

/**
 * Represents a list of tasks
 */
public class TaskList {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ArrayList<Task> list;

    /**
     * Constructor for a new TaskList object
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Sets the list of tasks as provided
     *
     * @param list representing the list of tasks
     */
    public void setTasks(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Writes the list of tasks using fileWriter object
     *
     * @param fileWriter fileWriter to write tasks into file
     * @throws TalkerException if unable to write to file
     */
    public void writeToFile(FileWriter fileWriter) throws TalkerException {
        try {
            for (Task task: list) {
                fileWriter.write(task.getSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new TalkerException("Unable to write to file. Error occurred: " + e.getMessage());
        }
    }

    /**
     * Prints a list of tasks that are occuring on a certain date
     * ToDo tasks are not printed
     * Deadline tasks where the deadline has not yet passed are printed
     * Event tasks where the start date has already passed but the end date
     * has not yet passed are printed
     *
     * @param date string representing target date
     * @param ui ui object to print output
     * @return String representing tasks on certain date
     * @throws TalkerException if incorrect date format
     */
    public String printTasksOn(String date, Ui ui) throws TalkerException {
        LocalDate targetDate;
        StringBuilder output = new StringBuilder();

        try {
            String[] parsed = date.split(" ");
            targetDate = LocalDate.parse(parsed[1], INPUT_FORMAT);
        } catch (DateTimeException | IndexOutOfBoundsException e) {
            throw new TalkerException("Incorrect date format. Try again with: yyyy/MM/dd");
        }

        output.append(ui.printTasksOn(targetDate.format(OUTPUT_FORMAT)));

        for (Task task: list) {
            String toAppend;

            if (task instanceof Deadline) {
                toAppend = getDeadlineOnTargetDate((Deadline) task, targetDate, ui);
            } else if (task instanceof Event) {
                toAppend = getEventOnTargetDate((Event) task, targetDate, ui);
            } else {
                toAppend = "";
            }

            output.append(toAppend);
        }
        return output.toString();
    }

    /**
     * Gets String representation of deadline task within targetDate
     *
     * @param deadlineTask Deadline task
     * @param targetDate LocalDate target date to see if deadline has passed
     * @param ui ui object to print output
     * @return String representing deadline task if within targetDate else blank String
     */
    private String getDeadlineOnTargetDate(Deadline deadlineTask, LocalDate targetDate, Ui ui) {
        LocalDate deadline = deadlineTask.getDeadline().toLocalDate();

        boolean hasDeadlinePassed = targetDate.isBefore(deadline) || targetDate.isEqual(deadline);

        if (!hasDeadlinePassed || deadlineTask.isComplete()) {
            return "";
        }
        return ui.printTask(deadlineTask) + "\n";
    }

    /**
     * Gets String representation of event task within targetDate
     *
     * @param eventTask Event task
     * @param targetDate LocalDate target date to see if event is ongoing
     * @param ui ui object to print output
     * @return String representing event task if event is still ongoing else blank String
     */
    private String getEventOnTargetDate(Event eventTask, LocalDate targetDate, Ui ui) {
        LocalDate start = eventTask.getFrom().toLocalDate();
        LocalDate end = eventTask.getTo().toLocalDate();

        boolean hasEventStarted = targetDate.isAfter(start) || targetDate.isEqual(start);
        boolean hasEventEnded = targetDate.isBefore(end) || targetDate.isEqual(end);
        boolean isEventOngoing = hasEventStarted && hasEventEnded;

        if (!isEventOngoing || eventTask.isComplete()) {
            return "";
        }
        return ui.printTask(eventTask) + "\n";
    }

    /**
     * Prints the list of current tasks
     *
     * @param ui ui object to print output
     * @return String list of current tasks
     * @throws TalkerException if list is empty
     */
    public String listTasks(Ui ui) throws TalkerException {
        if (list.isEmpty()) {
            throw new TalkerException("List is empty!");
        }
        return ui.printTaskList(list.toArray(new Task[0]));
    }

    /**
     * Marks target task as complete
     *
     * @param parsed string representing task number
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if no corresponding task found
     */
    public String markTaskComplete(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;

            return ui.printTaskMarked(list.get(index).mark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Mark format wrong. Try again with: mark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    /**
     * Marks target task as incomplete
     *
     * @param parsed string representing task number
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if no corresponding task found
     */
    public String unmarkTaskComplete(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;

            return ui.printTaskUnmarked(list.get(index).unmark());
        } catch (NumberFormatException e) {
            throw new TalkerException("Unmark format wrong. Try again with: unmark <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    /**
     * Deletes target task from list
     *
     * @param parsed string representing task number
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if no corresponding task found
     */
    public String deleteTask(String[] parsed, Ui ui) throws TalkerException {
        try {
            int index = Integer.parseInt(parsed[1]) - 1;
            Task removed = list.remove(index);

            return ui.printTaskDelete(removed, list.size());
        } catch (NumberFormatException e) {
            throw new TalkerException("Delete format wrong. Try again with: delete <task number>");
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new TalkerException("Task not found!");
        }
    }

    /**
     * Creates a new ToDo object and adds it into list
     *
     * @param input string representing ToDo task
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if incorrect input format
     */
    public String createToDo(String input, Ui ui) throws TalkerException {
        try {
            String desc = input.substring(5);
            Task newTask = new ToDo(desc);
            list.add(newTask);
            return ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException("ToDo format wrong. Try again with: todo <description>");
        }
    }

    /**
     * Creates new Deadline object and adds it into list
     *
     * @param input string representing Deadline task
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if incorrect input format
     */
    public String createDeadline(String input, Ui ui) throws TalkerException {
        try {
            String contents = input.substring(9);

            String[] parsed = contents.split(" /by ", 2);
            String desc = parsed[0];
            String by = parsed[1];

            Task newTask = new Deadline(desc, by);
            list.add(newTask);
            return ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Deadline format wrong. Try again with: deadline <description> /by <dd-MM-yyyy HH:mm>");
        }
    }

    /**
     * Creates new Event object and adds it into list
     *
     * @param input string representing Event task
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if incorrect input format
     */
    public String createEvent(String input, Ui ui) throws TalkerException {
        try {
            String contents = input.substring(6);

            String[] parsed1 = contents.split(" /from ", 2);
            String[] parsed2 = parsed1[1].split(" /to ", 2);
            String desc = parsed1[0];
            String from = parsed2[0];
            String to = parsed2[1];

            Task newTask = new Event(desc, from, to);

            list.add(newTask);
            return ui.printTaskAdd(newTask, list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new TalkerException(
                    "Event format wrong. Try again with: event <description> "
                            + "/from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>");
        }
    }

    /**
     * Finds certain keyword amongst descriptions of tasks
     *
     * @param keyword keyword to be searched through descriptions
     * @param ui ui object to print output
     * @return String representing outcome of this event
     * @throws TalkerException if nothing found
     */
    public String findTask(String keyword, Ui ui) throws TalkerException {
        ArrayList<Task> outputList = new ArrayList<>();
        for (Task task: list) {
            if (task.getDescription().contains(keyword)) {
                outputList.add(task);
            }
        }
        return ui.printMatchingTasks(outputList.toArray(new Task[0]));
    }
}
