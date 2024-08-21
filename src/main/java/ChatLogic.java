public class ChatLogic {
    static final String HORIZONTAL_LINE = "____________________________________________________________";
    static final String BYE_COMMAND = "bye";
    static final String LIST_COMMAND = "list";

    private final String name;
    private Task[] taskArray = new Task[100];
    private int taskCount = 0;

    public ChatLogic(String name) {
        this.name = name;
    }

    public void processInput(String input) {
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

    private void addTask(String input) {
        Task newTask = new Task(input);
        taskArray[taskCount] = newTask;
        this.taskCount++;

        System.out.println(HORIZONTAL_LINE);
        System.out.println(" added: " + input);
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

    private void printInputEcho(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" " + input);
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
