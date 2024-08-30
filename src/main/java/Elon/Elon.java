package Elon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Elon {
    Ui ui = new Ui();

    private Storage storage;

    public Elon(Storage storage) {
        this.storage = storage;
    }
    private void Run() throws ElonException {
        ui.greet();
        TaskList list = new TaskList(storage.loadFile());
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        Parser parser = new Parser(this.ui, list, scanner, inputArr, this.storage);
        parser.Parse();
    }
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("./Data.txt");
            Elon elon = new Elon(storage);
            elon.Run();
        } catch (ElonException | IOException e) {
            System.out.println(e);
        }
    }
}
