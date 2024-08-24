import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used to store tasks.
 */
public class TaskList {
    /* ArrayList to store tasks */
    private ArrayList<Task> tasks;

    /* Path to save list data */
    private final String PATH = "src/main/java/data.txt";

    /* Regex to read tasks from text file */
    private final Pattern TASK_FORMAT = Pattern.compile("\\[([TED])]\\[([X ])] " +
            "([\\w ]+\\w)(?: \\((?:from|by): ([\\w\\d, :]+)\\)*)?(?: \\| to: ([\\w\\d, :]+)\\))?");

    /**
     * Constructor for task list.
     * @param EXPECTED_SIZE expected number of tasks to store
     */
    public TaskList(int EXPECTED_SIZE) {
        this.tasks = new ArrayList<>(EXPECTED_SIZE);
    }

    /**
     * Adds task to the list and prints list adding message.
     * @param task Task object to be added
     */
    public void addTask(Task task) {
        System.out.println("Ya la, adding this task to your list!");
        this.tasks.add(task);
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
        save();
    }

    /**
     * Deletes Task from the list at specified index.
     * @param index Integer index to delete at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void deleteTask(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task task = this.tasks.remove(index);
        System.out.println("Deleting now hor!");
        System.out.printf("\t%s\n", task);
        System.out.printf("You now got %s tasks in your list leh\n", this.tasks.size());
        save();
    }

    /**
     * Marks task as completed at specified index.
     * @param index Integer index to mark task at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void mark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Solid lah, marked already");
        curr.mark();
        System.out.printf("\t%s\n", curr);
        save();
    }

    /**
     * Unmarks task as completed at specified index.
     * @param index Integer index to unmark task at
     * @throws OutOfListException Thrown if invalid index is given, contains current task size
     */
    public void unmark(int index) throws OutOfListException {
        if (index < 0 || index >= this.tasks.size()) throw new OutOfListException(String.valueOf(this.tasks.size()));
        Task curr = this.tasks.get(index);
        System.out.println("Walao, ok la I unmark already...");
        curr.unmark();
        System.out.printf("\t%s\n", curr);
        save();
    }

    /**
     * Saves task list to PATH static variable.
     * if IOException raised, will print error statement.
     */
    public void save() {
        File f = new File(PATH);
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < this.tasks.size(); i++) {
                fw.write(this.tasks.get(i).toString());
                if (i < this.tasks.size() - 1) {
                    fw.write("\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error! Please check your file permissions!");
        }
    }

    /**
     * Loads task list from save file.
     * Will skip tasks if task format is wrong, will not load the full list if save file cannot be opened.
     */
    public void load() {
        File f = new File(PATH);
        Matcher m;
        Task t;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                m = TASK_FORMAT.matcher(sc.nextLine());
                if (m.matches()) {
                    switch (m.group(1)) {
                    case "T":
                        t = new Todo(m.group(3));
                        if (m.group(2).equals("X")) {
                            t.mark();
                        }
                        this.tasks.add(t);
                        break;
                    case "D":
                        t = new Deadline(m.group(3), m.group(4));
                        if (m.group(2).equals("X")) {
                            t.mark();
                        }
                        this.tasks.add(t);
                        break;
                    case "E":
                        t = new Event(m.group(3), m.group(4), m.group(5));
                        if (m.group(2).equals("X")) {
                            t.mark();
                        }
                        this.tasks.add(t);
                        break;
                    }
                } else {
                    System.out.println("Error with loading task, skipping to next one...");
                }
            }
            sc.close();
            System.out.println("Tasks loaded successfully!");
        } catch (IOException e) {
            System.out.printf("IOException! %s! Aborting loading...\n", e.getMessage());
        } catch (Exception e) {
            System.out.printf("Error! %s! Aborting loading...\n", e.toString());
        }
    }

    /**
     * Returns string representation of the list.
     * Includes task index, task type and whether it is marked or unmarked.
     */
    public void showList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Nothing in list lah!");
        } else {
            StringBuilder output = new StringBuilder("Your list here leh!\n");
            Task curr;
            for (int i = 0; i < tasks.size(); i++) {
                curr = tasks.get(i);
                output.append(String.format("%d. %s\n", i + 1, curr));
            }
            System.out.println(output.toString().strip());
        }
    }
}
