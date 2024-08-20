import java.io.IOException;
import java.util.Scanner;


public class Hoodini {
    private Storage store;
    private Parser parser;
    private Ui ui;

    public Hoodini() {
        this.ui = new Ui();
        this.store = new Storage(ui);

        start();


    }

    public void start() {
        ui.showWelcome();
        this.parser = new Parser(store, ui);
        parser.load();
        parser.handleInput();

    }




}
