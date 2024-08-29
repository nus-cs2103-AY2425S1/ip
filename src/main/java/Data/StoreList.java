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
        return items.get(i);
    }

    /**
     * Adds appropriate task to list
     * Catch all the relevant exceptions when trying to add invalid tasks
     *
     * @param item task to be added.
     * @param type tasktype to be created and stores.
     */
    public void addItem(String item, String type) {
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

            System.out.println("    Got it. I've added this task:\n" + "      " + t.print()
                    + "\n    Now you have " + this.getSize() + " tasks in the list.");

        } catch (EmptyDescException | EmptyDeadlineException
                 | EmptyEventException | EmptyDeadlineDateException
                 | EmptyEventTimingException e) {

            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
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
    public void markItem(int num) {
        items.get(num - 1).mark();
        System.out.println("    " + "Wohoo! I've marked this task as done! WELL DONE!:\n" +
                "      " + items.get(num - 1).print());
    }

    /**
     * Unmarks item as incomplete
     *
     * @param num Index of task to be unmarked.
     */
    public void UnmarkItem(int num) {
        items.get(num - 1).unMark();
        System.out.println("    " + "Aww:( I've marked this task as not done yet:\n" +
                "      " + items.get(num - 1).print());
    }

    /**
     * Deletes item
     *
     * @param num Index of task to be deleted.
     */
    public void deleteItem(int num) {
        if (num > items.size()) {
            System.out.println("Tasks.Task number does not exist");
            return;
        }
        Task temp = items.get(num - 1);
        items.remove(num - 1);
        System.out.println("    " + "Noted! I've removed this task:\n" +
                "      " + temp.print() + "\n    Now you have " + this.getSize() + " tasks in the list.");

    }

    /**
     * Displays items in list
     */
    public void displayItems() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + "." + items.get(i).print());
        }
    }

    /**
     * Displays items in list due on a specific date.
     *
     * @param date the date in the format yyyy-MM-dd or dd/MM/yyyy to check deadlines against.
     */
    public void dueOnDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inputDate;
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
                System.out.println("invalid date format! pls use yyyy-MM-dd or dd/MM/yyyy");
                return;
            }
        }

        System.out.println("    Here are the tasks due on " + date + ":");
        for (int i = 0; i < items.size(); i++) {
            Task task = items.get(i);
            if (task instanceof Deadlines) {
                // type cast once sure of type of task
                LocalDate taskDate = ((Deadlines) task).getLocalDate();
                if (taskDate != null && taskDate.equals(inputDate)) {
                    System.out.println("    " + (i + 1) + "." + items.get(i).print());
                    found = true;
                }
            }
        }

        // if no tasks found, return no tasks due
        if (!found) {
            System.out.println("No tasks due on " + date);
        }
    }

    public void displayItemsWithWord(String substring) {
        System.out.println("    Here are the tasks in your list that match " + substring + ":");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTaskDesc().contains(substring)) {
                System.out.println("    " + (i + 1) + "." + items.get(i).print());
            }
        }
    }
}
