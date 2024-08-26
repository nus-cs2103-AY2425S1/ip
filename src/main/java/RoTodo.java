import rotodo.exception.InvalidInputException;
import rotodo.processes.Parser;
import rotodo.processes.Storage;
import rotodo.processes.Ui;
import rotodo.tasklist.TaskList;

import java.util.Scanner;

/**
 * __________       __________            __   _____
 * \______   \  ____\__   ___/____    ___|  | /  _  \   ____    
 *  |       _/ /  _ \ |   |  /  _ \  /  _   ||  / \  | /  _ \   ___
 *  |    |   \(  <_> ||   | (  <_> |(  <_>  ||  \_/  |(  <_> | / o \  _
 *  |____|_  / \____/ |___|  \____/  \_____/  \_____/  \____/  \___/ (_) O o . 
 *         \/
 * This class implements the CLI of RoTodo.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class RoTodo {
    private Storage storage;

    public RoTodo() {
        storage = Storage.of("./data/rotodo.txt");
    }

    public void run() {
        storage.loadList();
        Ui.banner();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        while (true) {
            try {
                Ui.print(Parser.process(sc.nextLine()));
            } catch (InvalidInputException e) {
                Ui.print(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        (new RoTodo()).run();
    }
}
