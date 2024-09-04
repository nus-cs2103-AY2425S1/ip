import task.TaskList;
import task.TaskManager;

public class Maga {

    private void printGreeting() {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
    }

    private TaskList initialiseBot(TaskManager taskManager) {
        return taskManager.loadTasks();
    }

    public void closeBot(TaskManager taskManager, TaskList taskList) {
        System.out.println("Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!");
        taskManager.saveTasks(taskList);
    }

    private void run() {
        printGreeting();
        TaskManager taskManager = new TaskManager();
        TaskList taskList = initialiseBot(taskManager);
        Ui ui = new Ui(taskList);
        ui.start();
        closeBot(taskManager, taskList);
    }

    public static void main(String[] args) {
        Maga maga = new Maga();
        maga.run();
    }

}
