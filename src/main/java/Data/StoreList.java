package Data;

import Exceptions.*;
import Tasks.*;

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
                items.add(t);

            } else if (type.equals("deadline")) {
                // create a Tasks.Deadlines object
                t = new Deadlines(item); // Constructor might throw Exceptions.EmptyDeadlineException
                items.add(t);

            } else {
                // create an Tasks.Events object
                t = new Events(item); // Constructor might throw Exceptions.EmptyEventException
                items.add(t);
            }

            return "    Got it. I've added this task:\n" + "      " + t.print() +
                    "\n    Now you have " + this.getSize() + " tasks in the list.";

        } catch (EmptyDescException | EmptyDeadlineException
                 | EmptyEventException | EmptyDeadlineDateException
                 | EmptyEventTimingException e) {

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
            throw new InvalidIndexException("Task number does not exist");
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

        if (num >= items.size()) {
            throw new InvalidIndexException("Task number does not exist");
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
                .filter(task -> task instanceof Deadlines && ((Deadlines) task).getLocalDate().equals(inputDate)
                        || task instanceof Events && ((Events) task).getLocalDate().equals(inputDate))
                .map(task -> "    " + (items.indexOf(task) + 1) + "." + task.print() + "\n")
                .collect(Collectors.joining());
        tasksDueOnDate.append("    Here are the tasks due on ").append(date).append(":\n");

        if (tasksDue.isEmpty()) {
            return "No tasks due on " + date;
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

    public String updateTask(int itemNum, String newValue) {
        Task task = items.get(itemNum - 1);
        task.setDesc(newValue);
        return "Task " + itemNum + "desc change to " + newValue;
    }
}

