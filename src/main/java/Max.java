import java.util.Scanner;


public class Max {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "./data/max.txt";

    public static void main(String[] args) {
        Max max = new Max();
        max.runMax();
    }

    public Max() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadList());

    }
    public void runMax() {
        Scanner scanner = new Scanner(System.in);
        ui.printHello();

        Parser parser = new Parser(tasks, ui, storage);
        try {
            parser.parseText(scanner);
        } catch (MaxException e) {
            ui.printMessage(e.getMessage());
        }

        ui.printBye();
    }


}
