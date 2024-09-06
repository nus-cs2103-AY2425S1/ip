package pacman;

public class PacMan {
    private static final Storage storage = new Storage("data/PacMan.txt");
    private static final TaskList list = new TaskList(storage.load());
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        ui.showGreet();
        boolean isExit = false;
        while (!isExit) {
            isExit = Parser.execute(ui.readCommand(), list, ui);
        }
        ui.showBye();
        storage.write(list.toFile());
    }
}
