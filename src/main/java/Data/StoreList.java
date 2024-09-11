package Data;

import Exceptions.*;
import Tasks.*;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

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
        assert num > 0 && num <= items.size() : "Invalid index";
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
        assert num > 0 && num <= items.size() : "Invalid index";
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
        assert num > 0 && num < items.size() : "Invalid index";
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
     * Displays items in list due on a specific date.
     *
     * @param date the date in the format yyyy-MM-dd or dd/MM/yyyy to check deadlines against.
     */
    public String dueOnDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate;
        StringBuilder result = new StringBuilder();
        // variable to store if tasks found
        boolean found = false;

        try {
            // check if input date matches format of stored date
            inputDate = LocalDate.parse(date, dateTimeFormatter);
        } catch (Exception e) {
            try {
                // check if input date matches format of stored date
                inputDate = LocalDate.parse(date, dateTimeFormatter2);
            } catch (Exception e2) {
                return "invalid date format! pls use yyyy-MM-dd or dd/MM/yyyy";
            }
        }

        result.append("    Here are the tasks due on ").append(date).append(":\n");
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            if (task instanceof Deadlines) {
                // type cast once sure of type of task
                LocalDate taskDate = ((Deadlines) task).getLocalDate();
                if (taskDate != null && taskDate.equals(inputDate)) {
                    result.append("    ").append(i + 1).append(".").append(items.get(i).print()).append("\n");
                    found = true;
                }
            }

            if (task instanceof Events) {
                // type cast once sure of type of task
                LocalDate taskDate = ((Events) task).getLocalDate();
                if (taskDate != null && taskDate.equals(inputDate)) {
                    result.append("    ").append(i + 1).append(".").append(items.get(i).print()).append("\n");
                    found = true;
                }
            }
        }

        // if no tasks found, return no tasks due
        if (!found) {
            result.append("No tasks due on ").append(date);
        }
        return result.toString();
    }

    /**
     * Displays items in list based on keyword
     *
     * @param substrings the keywords to search against.
     */
    public String displayItemsWithWord(String... substrings) {
        StringBuilder result = new StringBuilder("    Here are the tasks in your list that match your search:\n");
        for (int i = 0; i < items.size(); i++) {
            String taskDesc = items.get(i).getTaskDesc();
            boolean contains = false;

            for (String substring : substrings) {
                if (taskDesc.contains(substring)) {
                    contains = true;
                    break;
                }
            }
            if (contains) {
                result.append("    ").append(i + 1).append(".").append(items.get(i).print()).append("\n");
            }
        }
        return result.toString();
    }
}
