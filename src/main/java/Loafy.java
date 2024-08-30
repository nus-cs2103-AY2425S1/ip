import java.util.Scanner;

public class Loafy {
    private TaskList tasks;
    private final Ui ui;

    public Loafy(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            this.tasks = TaskList.ofLoadFromTxt(storage);
        } catch (LoafyException e) {
            ui.errorMsg(e);
            this.tasks = new TaskList(storage);
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
                command.execute(this.tasks, this.ui);
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