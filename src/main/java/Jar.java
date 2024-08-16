public class Jar {
    //class fields for parser and UI
    private Parser parser;
    private Ui ui;

    public Jar() {
        parser = new Parser();
        ui = new Ui();
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
            } else {
                ui.showResponse(parser.echo(userInput));
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        Jar jar = new Jar();
        jar.runBot();
    }
}
