import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private static List<Task> memory = new ArrayList<Task>();
    public Bob(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (IOException e) {
            ui.show("I'm having trouble initialising my memory :(");
        }

        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.show("Seems like I'm missing my memory (still)");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showBar();
        ui.show("Hello! I'm your chatbot, Bob.\n" +
                "How may I assist you?\n");
        ui.advise();

        String response;

        while (!(response = ui.readInput()).equals("bye")) {
            try {
                Command c = parser.parse(response, ui);
                c.execute(tasks, ui, storage);
            } catch (InvalidCommandException e) {
                ui.show("I don't recognise that command :( Try again.");
                ui.advise();
            }
        }
        ui.show("Bye.");
        ui.showBar();


    }
    public static void main(String[] args) {
        new Bob("./data/bob.txt").run();
    }
}
