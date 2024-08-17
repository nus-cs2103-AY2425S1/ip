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
                ui.showResponse(parser.echo(taskList.listTasks()));
                ui.showLine();
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
