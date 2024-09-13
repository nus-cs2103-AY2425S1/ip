package pixel;

import java.io.FileNotFoundException;

public class Pixel {
    private static Storage storage;
    private static TaskList tasks;
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

    public static String getResponse(String command) {
        return Parser.parse(command, tasks, storage);
    }

    public static void main(String[] args) {
    }
}
