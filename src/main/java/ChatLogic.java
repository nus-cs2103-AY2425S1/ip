public class ChatLogic {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";
    static final String MARK_COMMAND = "mark";
    static final String UNMARK_COMMAND = "unmark";

    private final String name;
    private Task[] taskArray = new Task[100];
    private int taskCount = 0;

    public ChatLogic(String name) {
        this.name = name;
    }

    public void processInput(String input) {
        if (input.contains(MARK_COMMAND) || input.contains(UNMARK_COMMAND)) {
            processMarkUnmarkInput(input);
        } else {
            switch (input) {
                case BYE_COMMAND:
                    printBye();
                    System.exit(0);
                    break;
                case LIST_COMMAND:
                    listTasks();
                    break;
                default:
                    addTask(input);
            }
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

    private void addTask(String input) {
        if (input.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Please specify a task name!");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        Task newTask = new Task(input);
        taskArray[taskCount] = newTask;
        this.taskCount++;

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" added: " + input);
        System.out.println(HORIZONTAL_LINE);
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
