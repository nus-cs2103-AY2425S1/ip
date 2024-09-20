package Data;

import Exceptions.*;
import Tasks.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StoreList {

    //declare array to store tasks
    protected ArrayList<Task> items;

    //declare Tasks.Task
    protected Task t;

    //initialize items array
    public StoreList(ArrayList<Task> items) {
        this.items = items;
    }

    public ArrayList<Task> getItems() {
        return items;
    }

    public Task get(int i) {

        assert i >= 0 && i < items.size() : "Index out of bounds";
        return items.get(i);
    }

    /**
     * Adds appropriate task to list
     * Catch all the relevant exceptions when trying to add invalid tasks
     *
     * @param item task to be added.
     * @param type tasktype to be created and stores.
     * @return
     */
    public String addItem(String item, String type) {
        try {
            assert type.equals("todo") || type.equals("deadline") || type.equals("event") : "Invalid task type";
            if (type.equals("todo")) {
                // create a Tasks.ToDos object
                t = new ToDos(item);
                if (items.contains(t)) {
                    throw new DuplicateTaskException("OOPS!!! Task already exists leh");
                }
                items.add(t);

            } else if (type.equals("deadline")) {
                // create a Tasks.Deadlines object
                t = new Deadlines(item); // Constructor might throw Exceptions.EmptyDeadlineException
                if (items.contains(t)) {
                    throw new DuplicateTaskException("OOPS!!! Task already exists leh");
                }
                items.add(t);

            } else {
                // create an Tasks.Events object
                t = new Events(item); // Constructor might throw Exceptions.EmptyEventException
                if (items.contains(t)) {
                    throw new DuplicateTaskException("OOPS!!! Task already exists leh");
                }
                items.add(t);
            }

            return "    Got it. I've added this task:\n" + "      " + t.print() +
                    "\n    Now you have " + this.getSize() + " tasks in the list.";

        } catch (EmptyDescException | EmptyDeadlineException
                 | EmptyEventException | EmptyDeadlineDateException
                 | EmptyEventTimingException | EmptyEventDateException
                 | InvalidTimeException | DuplicateTaskException e) {

            return e.getMessage();

        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    //getter
    public int getSize() {

        return items.size();
    }

    /**
     * Marks item as completed
     *
     * @param num Index of task to be marked.
     */
    public String markItem(int num) throws InvalidIndexException {
        assert num > 0 : "Task number does not exist";

        if (num > items.size()) {
            throw new InvalidIndexException("Task number does not exist");
        }
        items.get(num - 1).mark();
        return "    Wohoo! I've marked this task as done! WELL DONE!:\n" +
                "      " + items.get(num - 1).print();
    }

    /**
     * Unmarks item as incomplete
     *
     * @param num Index of task to be unmarked.
     */
    public String UnmarkItem(int num) throws InvalidIndexException {
        assert num > 0 : "Task number does not exist";

        if (num > items.size()) {
            throw new InvalidIndexException("Task number does not exist leh");
        }
        items.get(num - 1).unMark();
        return "    " + "Aww:( I've marked this task as not done yet:\n" +
                "      " + items.get(num - 1).print();
    }

    /**
     * Deletes item
     *
     * @param num Index of task to be deleted.
     */
    public String deleteItem(int num) throws InvalidIndexException {

        assert num > 0 : "Task number does not exist";

        if (num > items.size()) {
            throw new InvalidIndexException("Task number does not exist leh");
        }
        Task temp = items.get(num - 1);
        items.remove(num - 1);
        return "    " + "Noted! I've removed this task:\n" +
                "      " + temp.print() + "\n    Now you have " + this.getSize() + " tasks in the list.";

    }

    /**
     * Displays items in list
     */
    public String displayItems() {
        StringBuilder result = new StringBuilder("    Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            result.append("    ").append(i + 1).append(".").append(items.get(i).print()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns a formatted string representing the tasks due on the specified date.
     *
     * @param date The date to check deadlines against (in format yyyy-MM-dd or dd/MM/yyyy).
     */
    public String dueOnDate(String date) {
        LocalDate inputDate;
        //StringBuilder to append tasks due on the date
        StringBuilder tasksDueOnDate = new StringBuilder();

        // variable to store if tasks found
        boolean found = false;

        // check if input date matches format of stored date
        inputDate = parseDeadline(date);

        if (inputDate == null) {
            return "invalid date format! pls use yyyy-MM-dd or dd/MM/yyyy";
        }


        return tasksDue(date, tasksDueOnDate, inputDate, found);

    }

    /**
     * Returns true if any tasks were found, false otherwise.
     * Helper method to find and append tasks due on a specific date.
     *
     * @param date           The original date string used in the request.
     * @param tasksDueOnDate StringBuilder to append tasks due on the date.
     * @param inputDate      The parsed LocalDate of the input date.
     * @param found          A boolean indicating if any tasks were found due on the date.
     */
    private String tasksDue(String date, StringBuilder tasksDueOnDate, LocalDate inputDate, boolean found) {
        String tasksDue = items.stream()
                .filter(task -> {
                        // Check if task is a Deadline or Event and ensure getLocalDate() is not null
                        LocalDate taskDate = null;
        if (task instanceof Deadlines) {
            taskDate = ((Deadlines) task).getLocalDate();
        } else if (task instanceof Events) {
            taskDate = ((Events) task).getLocalDate();
        }

        // Only include tasks with a non-null date that matches the inputDate
        return taskDate != null && taskDate.equals(inputDate);
    })
                .map(task -> "    " + (items.indexOf(task) + 1) + "." + task.print() + "\n")
                .collect(Collectors.joining());
        tasksDueOnDate.append("    Here are the tasks due on ").append(date).append(":\n");

        if (tasksDue.isEmpty()) {
            return "Yay! No tasks due on " + date;
        } else {
            return "    Here are the tasks due on " + date + ":\n" + tasksDue;
        }
    }

    /**
     * Parses a date string and returns a LocalDate object.
     *
     * @param date The date string to be parsed (formats supported: yyyy-MM-dd or dd/MM/yyyy).
     * @return A LocalDate object if the date is valid, or null if the format is invalid.
     */
    private static LocalDate parseDeadline(String date) {
        LocalDate inputDate;
        try {
            // check if input date matches format of stored date
            inputDate = ParseTasks.parseDateFormat1(date);
        } catch (Exception e) {
            try {
                // check if input date matches format of stored date
                inputDate = ParseTasks.parseDateFormat2(date);
            } catch (Exception e2) {
                return null;
            }
        }
        return inputDate;
    }

    /**
     * Displays items in list based on keyword
     *
     * @param substrings the keywords to search against.
     */
    public String displayItemsWithWord(String... substrings) {
        String result = items.stream()
                .filter(task -> {
                    String taskDesc = task.getTaskDesc();
                    return Arrays.stream(substrings)
                            .anyMatch(taskDesc::contains);
                })
                .map(task -> "    " + (items.indexOf(task) + 1) + "." + task.print() + "\n")
                .collect(Collectors.joining());

        return "    Here are the tasks in your list that match your search:\n" + result;
    }

    /**
     * Updates the specified attribute (date, time, startTime, endTime) of a task in the list.
     *
     * This method takes the task number, the type of attribute to update, and the new value.
     * It performs validation on the input, particularly for dates and times, to ensure proper formatting.
     *
     * @param itemNum the task number in the list (1-based index).
     * @param type the type of update (e.g., "date", "time", "startTime", "endTime").
     * @param newValue the new value to update the task with (date or time).
     * @return a message indicating the result of the update, or an error message for invalid inputs.
     */
    public String updateTask(int itemNum, String type, String newValue) {
        Task task = items.get(itemNum - 1);
        if (type.equals("date")) {

            return updateTaskDate(itemNum, newValue, task);

        } else if (type.equals("time")) {

            return updateDeadlineTime(itemNum, newValue, task);

        } else if (type.equals("datetime")) {

            return updateDeadlineDateTime(itemNum, newValue, task);

        } else if (type.equals("startTime")) {

            return updateEventStartTime(itemNum, newValue, task);

        } else if (type.equals("endTime")) {

            return updateEventEndTime(itemNum, newValue, task);

        } else {
            return "Only task type desc, date, startTime, endTime, time, datetime are allowed";
        }
    }

    /**
     * Updates the date of a task in the list.
     *
     * Validates the input for proper date format and updates the task with the new date.
     * Accepts dates in formats yyyy-MM-dd or dd/MM/yyyy.
     * Updates task desc so that updated value is stored in storage file
     *
     * @param itemNum the task number (1-based index) to update.
     * @param newValue the new date value to update the task with.
     * @param task the task to update.
     * @return a message indicating success or an error message for invalid date formats.
     */
    private static String updateTaskDate(int itemNum, String newValue, Task task) {
        try {
            LocalDate updatedDate = ParseTasks.parseDateFormat1(newValue);
            task.setDate(updatedDate);
            task.setDesc(newValue);
            return "Task " + itemNum + " date changed to " + newValue;
        } catch (DateTimeParseException e) {
            try {
                LocalDate updatedDate = ParseTasks.parseDateFormat2(newValue);
                task.setDate(updatedDate);
                return "Task " + itemNum + " date changed to " + newValue;
            } catch (DateTimeParseException e1) {
                return "Invalid Date format: Use, yyyy-MM-dd/ dd/MM/yyyy";
            }
        }
    }

    /**
     * Updates the time of a deadline task in the list.
     *
     * Validates the input for proper time format and updates the task with the new time.
     * Accepts time in the format HH:mm.
     * Updates task desc so that updated value is stored in storage file
     *
     * @param itemNum the task number (1-based index) to update.
     * @param newValue the new time value to update the task with.
     * @param task the task to update.
     * @return a message indicating success or an error message for invalid time formats.
     */

    private static String updateDeadlineTime(int itemNum, String newValue, Task task) {
        try {
            LocalTime updatedDeadlineTime = ParseTasks.parseTime(newValue);
            task.setTime(updatedDeadlineTime);
            task.setDesc(newValue);
            return "Task " + itemNum + " time changed to " + newValue;
        } catch (DateTimeParseException e) {
            return "Invalid Time format: Use, 16:00";
        }
    }

    /**
     * Updates the end time of an event task in the list.
     *
     * Validates the input for proper time format and updates the task with the new end time.
     * Accepts time in the format HH:mm.
     * Updates task desc so that updated value is stored in storage file
     *
     * @param itemNum the task number (1-based index) to update.
     * @param newValue the new end time value to update the task with.
     * @param task the task to update.
     * @return a message indicating success or an error message for invalid time formats.
     */

    private static String updateEventEndTime(int itemNum, String newValue, Task task) {
        try {
            LocalTime updatedEventEndTime = ParseTasks.parseTime(newValue);
            task.setEndTime(updatedEventEndTime);
            task.setDesc(newValue);
            return "Task " + itemNum + " end time changed to " + newValue;
        } catch (DateTimeParseException e) {
            return "Invalid Time format: Use, 16:00";
        }
    }

    /**
     * Updates the start time of an event task in the list.
     *
     * Validates the input for proper time format and updates the task with the new start time.
     * Accepts time in the format HH:mm.
     * Updates task desc so that updated value is stored in storage file
     *
     * @param itemNum the task number (1-based index) to update.
     * @param newValue the new start time value to update the task with.
     * @param task the task to update.
     * @return a message indicating success or an error message for invalid time formats.
     */

    private static String updateEventStartTime(int itemNum, String newValue, Task task) {
        try {
            LocalTime updatedEventStartTime = ParseTasks.parseTime(newValue);
            task.setStartTime(updatedEventStartTime);
            task.setDesc(newValue);
            return "Task " + itemNum + " start time changed to " + newValue;
        } catch (DateTimeParseException e) {
            return "Invalid Time format: Use, 16:00";
        }
    }

    /**
     * Updates the date and time of a deadline task in the list.
     *
     * Validates the input for proper date-time format and updates the task with the new date-time.
     * Accepts date-time in formats yyyy-MM-dd HH:mm or dd/MM/yyyy HH:mm.
     * Updates task desc so that updated value is stored in storage file
     *
     * @param itemNum the task number (1-based index) to update.
     * @param newValue the new date-time value to update the task with.
     * @param task the task to update.
     * @return a message indicating success or an error message for invalid date-time formats.
     */

    private static String updateDeadlineDateTime(int itemNum, String newValue, Task task) {
        try {
            LocalDateTime updatedDeadlineDateTime = ParseTasks.parseDateTimeFormat1(newValue);
            task.setDateTime(updatedDeadlineDateTime);
            task.setDesc(newValue);
            return "Task " + itemNum + " time changed to " + newValue;
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime updatedDeadlineDateTime = ParseTasks.parseDateTimeFormat2(newValue);
                task.setDateTime(updatedDeadlineDateTime);
                return "Task " + itemNum + " time changed to " + newValue;
            } catch (DateTimeParseException e2) {
                return "Invalid DateTime format: Use, yyyy-MM-dd 16:00/ dd/MM/yyyy 16:00";
            }
        }
    }
}
