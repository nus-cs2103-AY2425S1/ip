package Kira;



import java.io.IOException;
import java.util.Scanner;

public class Kira {

    private Storage storage;
    private List list;
    private Ui ui;
    public Kira(String filePath) {
        this.storage = new Storage(filePath);
        this.list = storage.retrieve();
        this.ui = new Ui();
    }

    public List getList() {
        return this.list;
    }


    public void run() {

        //Kira.Kira kira = new Kira.Kira("data/kira.txt");
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this);
        String line = "____________________________________________________________\n";

        System.out.println(line +
                " Hello! I'm Kira.Kira\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }

            try {
                parser.parse(userInput);
                this.storage.save(this.list);
            } catch (UnreadableException | EmptyException | InvalidTaskException e) {
                this.ui.showLoadingError();
                System.out.println(e.getMessage());
            } catch (IOException e) {
                this.ui.showLoadingError();
                System.out.println("File cannot be saved" + e.getMessage());
            } catch (Exception e) {
                this.ui.showLoadingError();
                System.out.println("something went wrong");
            }
        }
    }
    public static void main(String[] args) {
        new Kira("data/kira.txt").run();
    }
}
