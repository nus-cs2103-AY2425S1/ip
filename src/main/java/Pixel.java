import java.io.FileNotFoundException;

public class Pixel {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pixel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.handleFileNotFoundException();
        } catch (IndexOutOfBoundsException e) {
            Ui.handleIndexOutOfBoundsException();
        }
    }

    public void run() {
        Ui.printGreeting();
        while (true) {
            String command = Ui.getCommand();
            Parser.parse(command, tasks, storage);
        }
    }

    public static void main(String[] args) {
        new Pixel("../data/pixel.txt").run();
    }
}
