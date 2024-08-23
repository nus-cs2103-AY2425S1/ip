public class Agave {

    private UserInterface ui;
    private boolean isRunning;
    private Task taskManager;

    public Agave() {
        this.ui = new UserInterface();
        this.isRunning = true;
        this.taskManager = new Task();
    }

    public void run() {
        ui.showWelcome();

        while (isRunning) {
            String userInput = ui.getUserInput("Enter a command: ");
            System.out.println(userInput);
            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showBye();
            } else if (userInput.equalsIgnoreCase("list")) {
                ui.showTasks(taskManager.getTasks(), taskManager.getTaskCount());
            } else if (userInput.startsWith("mark")) {

                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                    taskManager.getTasks()[taskNumber - 1].markAsDone();
                    ui.showMarkedTask(taskManager.getTasks()[taskNumber - 1]);
                }

            } else if (userInput.startsWith("unmark")) {

                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                    taskManager.getTasks()[taskNumber - 1].unmarkAsDone();
                    ui.showUnmarkedTask(taskManager.getTasks()[taskNumber - 1]);
                }
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                taskManager.addTask(new ToDo(userInput));
                System.out.println("Added todo: ");
                System.out.println(" [T][ ] " + description);
                taskManager.showNumberOfTasks();
            } else if (userInput.startsWith("deadline ")) {
                String[] split = userInput.split(" /by ");
                String description = split[0].substring(9);
                String by = split[1];
                taskManager.addTask(new Deadline(description, by));
                System.out.println("Added deadline: ");
                System.out.println("  [D][ ] " + description + " (by: " + by + ")");
                taskManager.showNumberOfTasks();
            } else if (userInput.startsWith("event ")) {
                String[] split = userInput.split(" /from | /to ");
                String description = split[0].substring(6);
                String from = split[1];
                String to = split[2];
                taskManager.addTask(new Event(description, from, to));
                System.out.println("Added event:");
                System.out.println("  [E][ ] " + description + " (from: " + from + " to: " + to + ")");
            } else {
                taskManager.addTask(new Task(userInput));
                ui.showEcho(userInput);
            }
        }
    }

    public static void main(String[] args) {
        Agave agave = new Agave();
        agave.run();
    }
}