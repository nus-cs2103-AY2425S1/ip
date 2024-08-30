package TrackBot;

import TrackBot.commands.Command;
import TrackBot.task.TrackList;
import TrackBot.ui.Parser;
import TrackBot.ui.Ui;

import java.io.IOException;
import java.util.Scanner;
public class TrackBot {
    private TrackBotStorage storage;
    private TrackList trackList;
    private final Ui ui;
    private final Parser parser;

    public TrackBot(String filePath) {
        ui = new Ui();
        parser = new Parser();
        try {
            storage = new TrackBotStorage(filePath);
            trackList = new TrackList(storage);
        } catch (IOException e) {
            System.out.println("File not found");
//            ui.showLoadingError();
        }
        if (trackList == null) {
            System.out.println("Failed to initialize TrackBot.TrackBot.task.TrackList.");
        }
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String userInput = scanner.nextLine();
            try {
                Command command = parser.parse(userInput);
                command.execute(trackList, ui, storage);
                isExit = command.isExit();
            } catch (TrackBotException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
        ui.showBye();
    }
    
    public static void main(String[] args) {
        String filePath = "src/main/java/TrackBot/data/TrackBot.txt";
        new TrackBot(filePath).run();
    }
}
