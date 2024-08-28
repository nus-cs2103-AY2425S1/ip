package TrackBot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class TrackBot {
    private TrackBotStorage storage;
    private TrackList trackList;
    private Ui ui;

    public TrackBot(String filePath) {
        ui = new Ui();
        try {
            storage = new TrackBotStorage(filePath);
            trackList = new TrackList(new File(filePath), storage);
        } catch (IOException e) {
            System.out.println("File not found");
//            ui.showLoadingError();
        }
        if (trackList == null) {
            System.out.println("Failed to initialize TrackBot.TrackBot.TrackList.");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();

        // Solution below inspired by https://www.javatpoint.com/chatbot-application-in-java
        while (true) {
            String userInput = scanner.nextLine();
            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
                InputParser.checkUserInput(userInput, trackList);
            } catch (TrackBotException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        // close scanner and exit with message
        scanner.close();
        ui.showBye();
    }
    
    public static void main(String[] args) {
        String filePath = "src/main/java/TrackBot/data/TrackBot.txt";
        new TrackBot(filePath).run();
    }
}
