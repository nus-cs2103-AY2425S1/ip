public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;
    private TaskList taskList;

    public Jar() {
        parser = new Parser();
        ui = new Ui();
        taskList  = new TaskList();
    }

    public void runBot() {
        ui.showWelcome();
        String userInput;
        boolean isRunning = true;
        while (isRunning) {
            userInput = ui.readCommand();
            ui.showLine();
            if (parser.isExit(userInput)) {
                isRunning = false;
            } else if (parser.isList(userInput)) {
                ui.showTaskList(taskList.listTasks());
            } else if (parser.isMark(userInput)) {
                int number = parser.getTaskNumber(userInput);
                Task task = taskList.getTask(number);
                if (task != null) {
                    taskList.markTaskAsDone(number);
                    ui.showTaskMarked(task);
                } else {
                    ui.showInvalidTaskNumber();
                }
            } else if (parser.isUnmarked(userInput)) {
                int number = parser.getTaskNumber(userInput);
                Task task = taskList.getTask(number);
                if (task != null) {
                    taskList.markTaskAsUndone(number);
                    ui.showTaskUnmarked(task);
                } else {
                    ui.showInvalidTaskNumber();
                }
            } else {
                Task task = parser.parseTask(userInput);
                if (task != null) {
                    taskList.addTask(task);
                    ui.showTaskAdded(task.toString());
                    ui.showTaskCount(taskList.getTaskCount());
                } else {
                    ui.showResponse("Invalid command.");
                }
            }
            ui.showLine();
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        Jar jar = new Jar();
        jar.runBot();
    }
}
