package Gumball;

import java.io.IOException;

public class Gumball {
    private TaskList list;
    private FileManager fileManager;

    private static String FILEPATH = "./gumball.txt";


    public Gumball() {
        try {
            fileManager = new FileManager(FILEPATH);
            list = fileManager.loadFile();
        } catch (IOException | InputErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(list, fileManager);
        } catch (InputErrorException | IOException e) {
            return e.getMessage();
        }
    }

}
