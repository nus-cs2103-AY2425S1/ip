package slave;

import java.time.LocalDate;
import java.util.LinkedList;

import slave.task.RecurringTypeTask;
import slave.task.Task;

/**
 * It is the main class for the program
 */
public class Slave {
    public static final String UNKNOWN_USER_INPUT = "You're spouting gibberish...";
    private final LinkedList<Task> list = new LinkedList<>();
    private final LinkedList<Task> tasksPastEndDate = new LinkedList<>();
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a new Slave object which will save user interactions to
     * the specified file path
     *
     * @param filePath is the address of the save file
     */
    public Slave(String filePath) {
        storage = new Storage(list, filePath);
        ui = new Ui();
        parser = new Parser(list);
        getAndUpdateTasks();
    }

    public void getAndUpdateTasks() {
        load();
        LocalDate dateToday = LocalDate.now();
        LinkedList<Task> newRecurringEvents = new LinkedList<>();
        list.forEach(task -> {
            if (!(task instanceof RecurringTypeTask)) {
                return;
            }
            if (!((RecurringTypeTask) task).hasEnded(dateToday)) {
                return;
            }
            // here the task is recurring and has ended
            if (((RecurringTypeTask) task).isRecurring()) {
                newRecurringEvents.add(((RecurringTypeTask) task).createRecurringEvent());
            }
            tasksPastEndDate.add(task);
            list.remove(task);
        });
        list.addAll(newRecurringEvents);
        save();
    }

    /**
     * Returns Slave's response to the user's input.
     *
     * @param input is the user's input.
     * @return Slave's response to the user's input.
     */
    public String[] getResponse(String input) {
        return parser.handleUserInput(input);
    }

    /**
     * Saves any changes made to the list into the save file.
     */
    public void save() {
        storage.save();
    }

    public String[] getWelcomeMsg() {
        return ui.appWelcomeMsg();
    }

    /**
     * Loads all tasks from the save file into the list.
     */
    public void load() {
        storage.load();
    }

    public String[] getPastTasks() {
        if (tasksPastEndDate.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] result = new String[3];
        result[0] = "Here are some tasks that are past their end date:";
        tasksPastEndDate.forEach(task -> {
            sb.append(task);
            sb.append("\n");
        });
        result[1] = sb.toString();
        result[2] = "I sure hope you didn't forget about them, because I won't bother remembering them anymore";
        return result;
    }
}
