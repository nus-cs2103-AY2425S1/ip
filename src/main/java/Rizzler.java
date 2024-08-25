import java.util.Scanner;

public class Rizzler {
    public static void main(String[] args) {
        RizzlerSpeech rizzlerSpeech = new RizzlerSpeech();
        InputLog inputLog = new InputLog();
        Scanner scanner = new Scanner(System.in);

        // greet user
        rizzlerSpeech.greet();

        // interact with user
        boolean userIsDone = false;
        while (!userIsDone) {
            try {
                String userInput = scanner.nextLine();
                switch (userInput) {
                    case "bye":
                        userIsDone = true;
                        break;
                    case "list":
                        rizzlerSpeech.list(inputLog.getLog());
                        continue;
                    default:
                        rizzlerSpeech.lineBreak();
                        rizzlerSpeech.say("added: " + userInput);
                        rizzlerSpeech.lineBreak();
                        inputLog.addInput(userInput);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();

        // bid farewell to user
        rizzlerSpeech.bidFarewell();
    }
}
