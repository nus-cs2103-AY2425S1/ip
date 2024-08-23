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
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                        taskManager.getTasks()[taskNumber - 1].markAsDone();
                        ui.showMarkedTask(taskManager.getTasks()[taskNumber - 1]);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (taskNumber > 0 && taskNumber <= taskManager.getTaskCount()) {
                        taskManager.getTasks()[taskNumber - 1].unmarkAsDone();
                        ui.showUnmarkedTask(taskManager.getTasks()[taskNumber - 1]);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid task number.");
                }
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