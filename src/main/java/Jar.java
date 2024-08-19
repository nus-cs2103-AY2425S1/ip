
public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;
    private TaskList taskList;

    public Jar() {
        parser = new Parser();
        ui = new Ui();
        taskList = new TaskList();
    }

    public void runBot() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            ui.showLine();
            try {
                isRunning = parser.handleCommand(userInput, taskList, ui);
            } catch (JarException e) {
                ui.showResponse(e.getMessage());
            }
            ui.showLine();
        }
    }


    public static void main(String[] args) {
        Jar jar = new Jar();
        jar.runBot();
    }
}
