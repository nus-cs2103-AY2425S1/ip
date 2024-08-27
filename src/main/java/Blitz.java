import java.util.Scanner;

public class Blitz {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Blitz(String path, String divider, String tab) {
        this.ui = new Ui(divider, tab);
        this.storage = new Storage(path);
        this.tasklist = storage.readFromFile(this.ui);
    }

    public void run() {
        String[] greet = {"Hello! I'm Blitz.", "What can I do for you?"};
        String[] end = {"Bye. Hope to see you again soon!"};

        ui.printInDivider(greet);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String inp = sc.nextLine();

            try {
                Command c = Parser.parse(inp);
                if (c.isExit()) {
                    break;
                }
                c.execute(this.tasklist, this.ui, this.storage);
            } catch (BlitzException e) {
                ui.printError(e);
            }
        }

        ui.printInDivider(end);
    }

    public static void main(String[] args) {
        new Blitz("./Blitz.txt", "    -----------------------------------------------\n", "    ").run();
    }
}
