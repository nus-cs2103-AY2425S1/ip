package Gumball;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Gumball {

    private String input;
    private Scanner inputScanner;
    private TaskList list;
    private FileManager fileManager;

    private UI ui;

    /*public static void main(String[] args) {
        try {
            Gumball chat = new Gumball();
            chat.start();
        } catch (IOException e) {
            UI.print(e.getMessage());
        }

    }*/

    /**
     *
     * @throws IOException
     * @throws InputErrorException
     */
    public Gumball() {
        try {
            ui = new UI();
            inputScanner = new Scanner(System.in);
            fileManager = new FileManager("./gumball.txt");
            list = fileManager.loadFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InputErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Will cause the chatbot to start running.
     * @throws FileNotFoundException
     */
    public void start() throws FileNotFoundException {
        ui.intro();
        while (true) {
            input = ui.readCommand();
            if (input.equals("bye")) break;
            try {
                Command c = Parser.parse(input);
                c.execute(list, ui, fileManager);
            } catch (InputErrorException e) {
                UI.print(e.getMessage());
            } catch (IOException e) {
                UI.print(e.getMessage());
            }
        }
        ui.outro();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, fileManager);
        } catch (InputErrorException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

}
