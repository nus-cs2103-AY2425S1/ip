package slothingwaffler;

import javafx.scene.control.skin.TextAreaSkin;
import java.util.Scanner;

public class SlothingWaffler {

    private static final Ui ui = new Ui();
    private final TaskList tasks;
    private final Storage storage;

    public SlothingWaffler(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new TaskList(storage);
    }

    public static void main(String[] args) {
        new SlothingWaffler("data.txt").run();
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isTerminate = false;

        while (!isTerminate) {
            try {
                String input = scanner.nextLine();
                isTerminate = Parser.parse(input, tasks, ui, storage);
            } catch (SlothingWafflerException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("YUM. The Waffler is ready for your next command!");
            }
        }
        scanner.close();
    }

}
