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
        Boolean isRunning = true;
        while (isRunning) {
            userInput = ui.readCommand();
            ui.showLine();
            if (parser.isExit(userInput)) {
                isRunning = false;
            } else if (parser.isList(userInput)){
                ui.showResponse("Here are the tasks in your list:\n" + parser.echo(taskList.listTasks()));
                ui.showLine();
            } else if (parser.isMark(userInput)){
                int number = parser.getTaskNumber(userInput);
                Task task = taskList.getTask(number);
                if (task != null) {
                    taskList.markTaskAsDone(number);
                    ui.showResponse("Nice! I've marked this task as done:\n" + task);
                } else {
                    ui.showResponse("Invalid task number.");
                }
            } else if (parser.isUnmarked(userInput)) {
                int number = parser.getTaskNumber(userInput);
                Task task = taskList.getTask(number);
                if (task != null) {
                    taskList.markTaskAsUndone(number);
                    ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
                } else {
                    ui.showResponse("Invalid task number.");
                }
            } else {
                ui.showResponse("Added: " + userInput);
                ui.showLine();
                taskList.addTask(new Task(userInput));
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        Jar jar = new Jar();
        jar.runBot();
    }
}
