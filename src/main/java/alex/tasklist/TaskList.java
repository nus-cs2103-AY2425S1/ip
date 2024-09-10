package alex.tasklist;

import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be done. A TaskList object corresponds to
 * a list of actions represented by an arraylist.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
    * Displays the list of tasks when commanded by the user.
     * @return string to be displayed to user
     */
    public String handleList() {
        String reply = "";
        reply = "Here are the tasks in your list: ";
        for (int i = 0; i < list.size(); i++) {
            reply = reply + "\n" + (i + 1) + ". " + list.get(i);
        }
        return reply;
    }
    /**
     * Displays the desired task as marked when commanded by the user.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleMark(String input) {
        String reply = "";
        int index = Integer.parseInt(input.substring(5)) - 1;
        boolean isLessThanSizeOfList = index < list.size();
        boolean isGreaterThanZero = index >= 0;
        boolean isValidIndex = isGreaterThanZero && isLessThanSizeOfList;
        if (isValidIndex) {
            Task task = list.get(index);
            task.markAsDone();
            reply = "Nice! I've marked this task as done: \n" +
                    task.toString();
        } else {
            reply = "It seems that task does not exist. Please try again.";
        }
        return reply;
    }
    /**
     * Displays the desired task as unmarked when commanded by the user.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleUnmark(String input) {
        String reply = "";
        int index = Integer.parseInt(input.substring(7)) - 1;
        boolean isLessThanSizeOfList = index < list.size();
        boolean isGreaterThanZero = index >= 0;
        boolean isValidIndex = isGreaterThanZero && isLessThanSizeOfList;
        if (isValidIndex) {
            Task task = list.get(index);
            task.unmark();
            reply = "OK, I've marked this task as not done yet: \n" +
                    task.toString();
        } else {
            reply = "It seems that task does not exist. Please try again.";
        }
        return reply;
    }
    /**
     * Adds the to-do to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleTodo(String input) {
        String reply = "";
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            reply = "You missed out some details. Try again";
        } else {
            Todo todo = new Todo(description);
            list.add(todo);
            reply = "Got it. I've added this task: \n" + todo.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.";
            assert !list.isEmpty() : "list should not be empty";
        }
        return reply;
    }
    /**
     * Adds the deadline to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     * If the date entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleDeadline(String input) {
        String reply = "";
        String description = input.substring(8).trim();
        if (description.isEmpty()) {
            reply = "You missed out the details of the deadline task. Try again";
        } else {
            try {
                String[] details = description.split("/", 2);
                String task = details[0].trim();
                String dueInter = details[1].substring(2).trim();
                LocalDate dueDate = LocalDate.parse(dueInter);
                if (task.isEmpty() || dueInter.isEmpty()) {
                    reply = "You missed out some details. Try again";
                } else {
                    Deadline deadline = new Deadline(task, dueDate);
                    list.add(deadline);
                    reply = "Got it. I've added this task: \n" + deadline.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.";
                    assert !list.isEmpty() : "list should not be empty";
                }
            } catch (DateTimeParseException e) {
                reply = "Invalid date entered. Use this format: YYYY-MM-DD";
            }
        }
        return reply;
    }
    /**
     * Adds the event to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     * If the date(s) entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleEvent(String input) {
        String reply = "";
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            reply = "You missed out the details of the event task. Try again";
        } else {
            try {
                String[] details = description.split("/", 3);
                String task = details[0].trim();
                String startInter = details[1].substring(4).trim();
                LocalDate startDate = LocalDate.parse(startInter);
                String endInter = details[2].substring(2).trim();
                LocalDate endDate = LocalDate.parse(endInter);
                if (task.isEmpty() || startInter.isEmpty() || endInter.isEmpty()) {
                    reply = "You missed out some details. Try again";
                } else {
                    Event event = new Event(task, startDate, endDate);
                    list.add(event);
                    reply = "Got it. I've added this task: \n" + event.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.";
                    assert !list.isEmpty() : "list should not be empty";
                }
            } catch (DateTimeParseException e) {
                reply = "Invalid date(s) entered. Use this format: YYYY-MM-DD";
            }
        }
        return reply;
    }
    /**
     * Deletes the desired task from the list when commanded by the user.
     * Displays the task as removed into the list.
     * If the index entered by the user is < -1 or > size of list, it will ask user to try again.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleDelete(String input) {
        String reply = "";
        int index = Integer.parseInt(input.substring(7)) - 1;
        boolean isLessThanSizeOfList = index < list.size();
        boolean isGreaterThanZero = index >= 0;
        boolean isValidIndex = isGreaterThanZero && isLessThanSizeOfList;
        if (isValidIndex) {
            Task task = list.get(index);
            list.remove(index);
            reply = "OK, I've deleted this task: \n" + task.toString() +
                    "\n Now you have " + list.size() + " tasks left in the list.";
        } else {
            reply = "It seems that task does not exist. Please try again.";
        }
        return reply;
    }
    /**
     * Displays the deadlines that are due on or events that end on the specified date.
     * If the date entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     * @return string to be displayed to user
     */
    public String handleDate(String input) {
        String reply = "";
        try {
            LocalDate dateToFind = LocalDate.parse(input.substring(9).trim());
            @SuppressWarnings("unchecked")
            ArrayList<Task> newList = (ArrayList<Task>) list.clone();
            newList.removeIf(task -> task.dueDate == null);
            for (Task task : newList) {
                if (task.dueDate.isEqual(dateToFind)) {
                    reply = reply + "\n" + task;
                }
            }
        } catch (DateTimeParseException e) {
            reply = "Invalid date(s) entered. Use this format: YYYY-MM-DD";
        }
        return reply;
    }

    /**
     * Finds the tasks in the list of tasks that contain the keyword entered by the user.
     *
     * @param input input entered by user.
     * @return string to be displayed to user
     */
    public String handleFind(String input) {
        String reply = "";
        String keyword = input.substring(5).trim();
        @SuppressWarnings("unchecked")
        ArrayList<Task> newList = (ArrayList<Task>) list.clone();
        newList.removeIf(task -> !task.description.toLowerCase().contains(keyword.toLowerCase()));
        int startingIndex = 1;
        int index = startingIndex;
        if (newList.isEmpty()) {
            reply = "No tasks found matching the keyword: " + keyword;
        } else {
            reply = "Tasks found matching the keyword: " + keyword;
            for (Task task : newList) {
                reply = reply + "\n" + index + ". " + task;
                index++;
            }
        }
        return reply;
    }
}
