import java.util.ArrayList;

public class StoreList {

    //initialize array to store tasks
    protected ArrayList<Task> items;

    //initialize Task
    protected Task t;

    public StoreList() {
        items = new ArrayList<>();
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
                t = new ToDos(item);
                items.add(t);

            } else if (type.equals("deadline")) {
                t = new Deadlines(item); // Constructor might throw EmptyDeadlineException
                items.add(t);

            } else {
                t = new Events(item); // Constructor might throw EmptyEventException
                items.add(t);
            }

            System.out.println("    Got it. I've added this task:\n" + "      " + t.print()
                    + "\n    Now you have " + this.getSize() + " tasks in the list.");

        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        } catch (EmptyDeadlineException e) {
            System.out.println(e.getMessage());
        } catch (EmptyEventException e) {
            System.out.println(e.getMessage());
        } catch (EmptyDeadlineDateException e) {
            System.out.println(e.getMessage());
        } catch (EmptyEventTimingException e) {
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
        items.get(num-1).mark();
        System.out.println("    " + "Wohoo! I've marked this task as done! WELL DONE!:\n" +
                "      " + items.get(num-1).print());
        }

    /**
     * Unmarks item as incomplete
     *
     * @param num Index of task to be unmarked.
     */
    public void UnmarkItem(int num) {
        items.get(num-1).unMark();
        System.out.println("    " + "Aww:( I've marked this task as not done yet:\n" +
                "      " + items.get(num-1).print());
    }


    /**
     * Returns items in list
     *
     */
    public void displayItems() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + "." + items.get(i).print());
        }
    }

}
