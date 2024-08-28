import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class StoreList {

    //declare array to store tasks
    protected ArrayList<Task> items;

    //declare Task
    protected Task t;

    //initialize items array
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
                // create a ToDos object
                t = new ToDos(item);
                items.add(t);

            } else if (type.equals("deadline")) {
                // create a Deadlines object
                t = new Deadlines(item); // Constructor might throw EmptyDeadlineException
                items.add(t);

            } else {
                // create an Events object
                t = new Events(item); // Constructor might throw EmptyEventException
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

    /**
     * Saves appropriate task to file
     * Catch all the relevant exceptions when trying to save invalid tasks
     *
     */
    public void saveTasksToFile() {
        FileUI.createFileIfNotPresent();

        try {
            FileWriter fw = new FileWriter(FileUI.FILE_PATH);

            for (Task task: items) {
                String taskType = task instanceof ToDos ? "T"
                        : task instanceof Deadlines ? "D" : "E";
                String taskStatus = task.getStatusIcon();
                String taskDesc = task.getTaskDesc();
                String taskInfo = taskType + "|" + taskStatus + "|" + taskDesc + "\n";

                fw.write(taskInfo);
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        }
    }

    /**
     * loads tasks from file and adds to list
     * Catch all the relevant exceptions when trying to add invalid tasks
     *
     */
    public void loadTasks() {
        FileUI.createFileIfNotPresent();

        try {
            FileReader fr = new FileReader(FileUI.FILE_PATH);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            // loop runs till file is empty
            while (line != null) {
                String[] parts = line.split("\\|");
                //extract the diff parts of a task
                String taskType = parts[0];
                String taskStatus = parts[1];
                String taskDesc = parts[2];

                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new ToDos(taskDesc);
                        break;

                    case "D":
                        task = new Deadlines(taskDesc);
                        break;

                    case "E":
                        task = new Events(taskDesc);
                        break;

                    default:
                        System.out.println("Unknown taskType");
                        continue;
                }
                if (task != null) {
                    if (taskStatus.equals("X")) {
                        task.mark();
                    }
                    items.add(task);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error occurred" + e.getMessage());
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
                "      " + items.get(num-1).print()) ;
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
     * Deletes item
     *
     * @param num Index of task to be deleted.
     */
    public void deleteItem(int num) {
        Task temp = items.get(num-1);
        items.remove(num-1);
        System.out.println("    " + "Noted! I've removed this task:\n" +
                "      " + temp.print() + "\n    Now you have " + this.getSize() + " tasks in the list.");

    }

    /**
     * Displays items in list
     *
     */
    public void displayItems() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + "." + items.get(i).print());
        }
    }

}
