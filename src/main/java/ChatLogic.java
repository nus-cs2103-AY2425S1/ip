public class ChatLogic {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";
    static final String MARK_COMMAND = "mark";
    static final String UNMARK_COMMAND = "unmark";

    static final String TODO_COMMAND = "todo";
    static final String DEADLINE_COMMAND = "deadline";
    static final String EVENT_COMMAND = "event";

    enum TASK_TYPE {
        TODO,
        DEADLINE,
        EVENT
    }

    private final String name;
    private Task[] taskArray = new Task[100];
    private int taskCount = 0;

    public ChatLogic(String name) {
        this.name = name;
    }

    public void processInput(String input) {
        if (input.equals(BYE_COMMAND)) {
            printBye();
            System.exit(0);
        } else if (input.contains(MARK_COMMAND) || input.contains(UNMARK_COMMAND)) {
            processMarkUnmarkInput(input);
        } else if (input.equals(LIST_COMMAND)) {
            listTasks();
        } else {
            processAddTaskInput(input);
        }
    }

    private void processMarkUnmarkInput(String input) {
        String possibleTaskNumString = input.replaceAll("[^\\d.]", "");
        if (possibleTaskNumString.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Please specify a task number!");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        int possibleTaskNum = Integer.valueOf(possibleTaskNumString);
        if (input.contains(UNMARK_COMMAND)) {
            unmarkTask(possibleTaskNum);
        } else if (input.contains(MARK_COMMAND)) {
            markTask(possibleTaskNum);
        }
    }

    private void processAddTaskInput(String input) {
        if (input.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Please specify a task name!");
            System.out.println(HORIZONTAL_LINE);
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

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println(this.taskArray[this.taskCount - 1].toString());
        System.out.println("Now you have " + this.taskCount + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private void addToDo(String input) {
        String taskName = input.replace(TODO_COMMAND, "").strip();
        taskArray[taskCount] = new ToDo(taskName);
        this.taskCount++;
    }

    private void addDeadline(String input) {
        String noCommandInput = input.replace(DEADLINE_COMMAND, "").strip();
        String taskName = noCommandInput.split("/by")[0].strip();
        String date = noCommandInput.split("/by")[1].strip();
        taskArray[taskCount] = new Deadline(taskName, date);
        this.taskCount++;
    }

    private void addEvent(String input) {
        String noCommandInput = input.replace(EVENT_COMMAND, "").strip();
        String taskName = noCommandInput.split("/from")[0].strip();
        String fromAndTo = noCommandInput.split("/from")[1].strip();
        String fromDate = fromAndTo.split("/to")[0].strip();
        String toDate = fromAndTo.split("/to")[1].strip();
        taskArray[taskCount] = new Event(taskName, fromDate, toDate);
        this.taskCount++;
    }

    private void markTask(int taskNum) {
        if (taskNum < 0 || taskNum >= this.taskCount) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("No such task in task list!");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        Task task = this.taskArray[taskNum - 1];
        task.mark();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    private void unmarkTask(int taskNum) {
        if (taskNum < 0 || taskNum >= this.taskCount) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("No such task in task list!");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        Task task = this.taskArray[taskNum - 1];
        task.unmark();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(HORIZONTAL_LINE);
    }

    private void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.taskCount; i++) {
            String output = " " + (i + 1) + ". " + this.taskArray[i].toString();
            System.out.println(output);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGreeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm " + name + ".");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
