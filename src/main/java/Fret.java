import java.util.Scanner;

public class Fret {
    public static void main(String[] args) {
        String logo = "________                ___ \n"
                + "| _____|             ___| |___ \n"
                + "| |___  __   _   ___ |__   __|\n"
                + "| ___|  | |/ /  / _ \\   | |\n"
                + "| |     |   /  <  __/   | |__\n"
                + "|_|     |__|    \\___|   |___|\n";
        
        System.out.println("Initiating...\n" + logo);
        
        System.out.println("\t-----------------------------------------");
        System.out.println("\tPersonal AI Fret, coming online!\n\tHey, how can I be of assistance?");
        System.out.println("\t-----------------------------------------");

        Scanner input = new Scanner(System.in);
        String userInput;

        do {
            userInput = input.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println("\t-----------------------------------------");
                System.out.println("\t" + userInput);
                System.out.println("\t-----------------------------------------");
            }
        } while (!userInput.equals("bye"));

        input.close();

        System.out.println("\t-----------------------------------------");
        System.out.println("\tOh well, it was fun while it lasted. Goodbye!");
        System.out.println("\t-----------------------------------------");
    }
}
