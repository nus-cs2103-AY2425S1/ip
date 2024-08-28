import java.io.IOException;

/** This class encapsulates chatbot parsing and logic.
 * @author Lee Ze Hao (A0276123J)
 */

public class Parser {
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";
    static final String MARK_COMMAND = "mark";
    static final String UNMARK_COMMAND = "unmark";
    static final String DELETE_COMMAND = "delete";

    static final String TODO_COMMAND = "todo";
    static final String DEADLINE_COMMAND = "deadline";
    static final String EVENT_COMMAND = "event";

    enum TASK_TYPE {
        TODO,
        DEADLINE,
        EVENT
    }

    private final Ui ui;
    private final String filePath;
    private final TaskList taskList;

    /** Constructor for a ChatLogic class. Also fetches data from the specified file path upon construction.
     * @param ui The Ui used by the chatbot.
     * @param filePath The file path used for data storage.
     */
    public Parser(Ui ui, String filePath) {
        this.ui = ui;
        this.filePath = filePath;
        this.taskList = new TaskList(this.filePath);
    }

    /** Processes text strings inputted by the user, and calls other functions
     * when appropriate.
     * @param input The text string entered by the user.
     * @throws StelleException Throws certain exceptions related to the chatbot.
     */
    public void processInput(String input) throws StelleException, IOException {
        if (input.equals(BYE_COMMAND)) {
            taskList.writeToFile();
            ui.printBye();
            System.exit(0);
        } else if (input.contains(MARK_COMMAND) || input.contains(UNMARK_COMMAND)) {
            processMarkUnmarkInput(input);
        } else if (input.equals(LIST_COMMAND)) {
            listTasks();
        } else if (input.contains(TODO_COMMAND) || input.contains(DEADLINE_COMMAND) || input.contains(EVENT_COMMAND)) {
            processAddTaskInput(input);
        } else if (input.contains(DELETE_COMMAND)) {
            processDeleteTaskInput(input);
        } else {
            throw new WrongCommandException();
        }
    }

    private void processDeleteTaskInput(String input) throws TaskException, IOException {
        String possibleTaskNumString = input.replaceAll("[^\\d.]", "");
        if (possibleTaskNumString.isEmpty()) {
            throw new DeletionNotSpecifiedException();
        }

        int possibleTaskNum = Integer.valueOf(possibleTaskNumString);
        if (possibleTaskNum <= 0 || possibleTaskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        System.out.println("Alright. Removed the task:");
        System.out.println(this.taskList.get(possibleTaskNum - 1));

        this.taskList.remove(possibleTaskNum - 1);
        this.taskList.writeToFile();
    }

    private void processMarkUnmarkInput(String input) throws TaskException, IOException {
        String possibleTaskNumString = input.replaceAll("[^\\d.]", "");
        if (possibleTaskNumString.isEmpty()) {
            throw new MarkNotSpecifiedException();
        }

        int possibleTaskNum = Integer.valueOf(possibleTaskNumString);
        if (input.contains(UNMARK_COMMAND)) {
            unmarkTask(possibleTaskNum);
        } else if (input.contains(MARK_COMMAND)) {
            markTask(possibleTaskNum);
        }
    }

    private void processAddTaskInput(String input) throws IOException {
        if (input.isEmpty()) {
            System.out.println("Please specify a task name!");
            return;
        }

        TASK_TYPE taskType;
        if (input.contains(TODO_COMMAND)) {
            taskType = TASK_TYPE.TODO;
        } else if (input.contains(DEADLINE_COMMAND)) {
            taskType = TASK_TYPE.DEADLINE;
        } else if (input.contains(EVENT_COMMAND)) {
            taskType = TASK_TYPE.EVENT;
        } else {
            return;
        }

        switch (taskType) {
            case TODO:
                addToDo(input);
                break;
            case DEADLINE:
                addDeadline(input);
                break;
            case EVENT:
                addEvent(input);
                break;
            default:
                break;
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(this.taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    private void addToDo(String input) throws TaskException, IOException {
        String taskName = input.replace(TODO_COMMAND, "").strip();

        if (taskName.isEmpty()) {
            throw new ToDoNoDescriptionException();
        }

        this.taskList.add(new ToDo(taskName));
        this.taskList.writeToFile();
    }

    private void addDeadline(String input) throws TaskException, IOException {
        String noCommandInput = input.replace(DEADLINE_COMMAND, "").strip();
        String taskName = noCommandInput.split("/by")[0].strip();
        if (taskName.isEmpty()) {
            throw new DeadlineNoDescriptionException();
        }
        String date = noCommandInput.split("/by")[1].strip();

        this.taskList.add(new Deadline(taskName, date));
        this.taskList.writeToFile();
    }

    private void addEvent(String input) throws TaskException, IOException {
        String noCommandInput = input.replace(EVENT_COMMAND, "").strip();
        String taskName = noCommandInput.split("/from")[0].strip();
        if (taskName.isEmpty()) {
            throw new EventNoDescriptionException();
        }
        String fromAndTo = noCommandInput.split("/from")[1].strip();
        String fromDate = fromAndTo.split("/to")[0].strip();
        String toDate = fromAndTo.split("/to")[1].strip();

        this.taskList.add(new Event(taskName, fromDate, toDate));
        this.taskList.writeToFile();
    }

    /** Marks a certain task (makes it done).
     * @param taskNum The number of the task (ArrayList index + 1) to be marked as done.
     * @throws TaskException Throws exceptions related to tasks.
     */
    private void markTask(int taskNum) throws TaskException, IOException {
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        Task task = this.taskList.get(taskNum - 1);
        task.mark();

        this.taskList.writeToFile();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /** Unmarks a certain task (makes it not done).
     * @param taskNum The number of the task (ArrayList index + 1) to be marked as not done.
     * @throws TaskException Throws exceptions related to tasks.
     */
    private void unmarkTask(int taskNum) throws TaskException, IOException {
        if (taskNum <= 0 || taskNum > this.taskList.size()) {
            throw new NoSuchTaskException();
        }

        Task task = this.taskList.get(taskNum - 1);
        task.unmark();

        this.taskList.writeToFile();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Reads tasks from local file and lists all Tasks currently stored, with added list numbers.
     */
    private void listTasks() throws IOException {
        this.taskList.readFromFile();

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            String output = " " + (i + 1) + ". " + this.taskList.get(i).toString();
            System.out.println(output);
        }
    }
}
