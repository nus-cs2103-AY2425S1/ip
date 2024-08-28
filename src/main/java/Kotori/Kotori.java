package Kotori;

import Kotori.Command.Command;
import Kotori.Parser.Parser;
import Kotori.Storage.Storage;
import Kotori.TaskList.TaskList;
import Kotori.Command.ExitCommand;
import Kotori.Command.GreetCommand;

import java.util.Scanner;

public class Kotori {
    private Storage storage;
    private TaskList list;
    private Parser parser;

    public Kotori () {
        this.storage = new Storage();
        this.list = storage.load();
        this.parser = new Parser(storage, list);
    }

    public void run() {
        Scanner s = new Scanner(System.in);
        new GreetCommand().execute();
        while (s.hasNextLine()) {
            Command command = parser.parse(s.nextLine());
            command.execute();
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Kotori().run();
    }
}


