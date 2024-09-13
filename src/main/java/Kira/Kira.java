package Kira;

import Exceptions.EmptyException;
import Exceptions.UnreadableException;
import Exceptions.InvalidTaskException;
import Tasks.List;
import Tasks.Task;

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

    public String getResponse(String input) {

        Parser parser = new Parser(this.list);
        /*
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }

         */

        try {
            Parser.CommandType command = parser.intepreteCommand(input);
            Task task = parser.execute(command, input);
            this.storage.save(this.list);
            return parser.getResponse(command, input, task);
            //System.out.println(ui.addLines(response));

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
        return "";
    }



    public void run() {

        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(this.list);
        String line = "____________________________________________________________\n";

        System.out.println(line +
                " Hello! I'm Kira\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                Parser.CommandType command = parser.intepreteCommand(userInput);
                Task task = parser.execute(command, userInput);
                String response = parser.getResponse(command, userInput, task);
                System.out.println(ui.addLines(response));
                this.storage.save(this.list);
                if (command == Parser.CommandType.BYE) {
                    break;
                }
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
