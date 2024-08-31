import Exceptions.EmptyDescriptionException;
import Exceptions.InputErrorException;
import Exceptions.WrongNumberOfItemException;

import java.util.Scanner;
public class Blue {
    private TaskList tasklist;
    private Parser parser;

    public Blue() {
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    public void run() {
        UI.greet();
        parser.parse(tasklist);
        UI.farewell();
    }
    public static void main(String[] args) {
        new Blue().run();
    }
}