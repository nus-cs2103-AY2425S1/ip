package Gumball;

import java.io.IOException;

public class Gumball {
    private TaskList list;
    private FileManager fileManager;

    private UI ui;

    public Gumball() {
        try {
            ui = new UI();
            fileManager = new FileManager("./gumball.txt");
            list = fileManager.loadFile();
        } catch (IOException | InputErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, ui, fileManager);
        } catch (InputErrorException | IOException e) {
            return e.getMessage();
        }
    }

}
