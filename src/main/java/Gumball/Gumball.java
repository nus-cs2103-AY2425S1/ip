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
    public static void main(String[] args) {
        try {
            Gumball chat = new Gumball();
            chat.start();
        } catch (IOException e) {
            UI.print(e.getMessage());
        } catch (InputErrorException e) {
            UI.print("Error in file please start over.");
        }

    }

    /**
     *
     * @throws IOException
     * @throws InputErrorException
     */
    public Gumball() throws IOException, InputErrorException {
        ui = new UI();
        inputScanner = new Scanner(System.in);
        fileManager = new FileManager("./gumball.txt");
        list = fileManager.loadFile();
    }

    /**
     * Will cause the chatbot to start running.
     * @throws FileNotFoundException
     */
    public void start() throws FileNotFoundException {
        ui.intro();
        while (true){
            input = ui.readCommand();
            if(input.equals("bye")) break;
            try {
                Command c = Parser.parse(input);
                c.execute(list, ui, fileManager);
            } catch (InputErrorException e) {
                ui.print(e.getMessage());
            } catch (IOException e) {
                ui.print(e.getMessage());
            }
        }
        ui.outro();
    }

}
