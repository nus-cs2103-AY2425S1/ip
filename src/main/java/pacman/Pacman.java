package pacman;

public class Pacman {
    private final Storage storage = new Storage("data/PacMan.txt");
    private final TaskList list = new TaskList(storage.load());
    private final Ui ui = new Ui();

    public String getResponse(String userInput) {

        boolean isExit = Parser.execute(userInput, list, ui);
        if (isExit) {
            ui.showBye();
            storage.write(list.toFile());
        }
        return ui.flushOutput();
    }
}
