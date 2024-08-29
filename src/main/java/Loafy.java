import java.nio.file.Paths;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Loafy {
    private TaskList taskList;
    private final Ui ui;

    public Loafy(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.taskList = TaskList.ofLoadFromTxt(storage);
        } catch (LoafyException e) {
            ui.errorMsg(e);
            this.taskList = new TaskList(storage);
        }
    }

    public void run() {
        this.ui.greeting();

        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (! exit) {
            System.out.print("You: ");
            String line = input.nextLine();
            try {
                Command command = Parser.parse(line);
                command.execute(this.taskList, this.ui);
                exit = command.isExit();
            } catch (LoafyException e) {
                this.ui.errorMsg(e);
            }
        }

        this.ui.exit();
    }

    public static void main(String[] args) {
        new Loafy("./data/loafy.txt").run();
    }
}