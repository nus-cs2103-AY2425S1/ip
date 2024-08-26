import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob\nWhat can I do for you?");
        System.out.println();

        Storage.loadData();

        Scanner input = new Scanner(System.in);
        String userInput;

        boolean cont = true;

        while (cont) {
            try {
                userInput = input.nextLine();
                System.out.println("--------------------------------------------------");
                cont = Parser.parse(userInput);
            } catch (BotException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("--------------------------------------------------\n");
            }
        }
    }

}
