import java.util.Scanner;

public class Dash {
    public static void main(String[] args) {
        //Initialise scanner for user input
        Scanner scanner = new Scanner(System.in);

        //Greeting
        String logo = " .----------------.  .----------------.  .----------------.  .----------------.\n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  ________    | || |      __      | || |    _______   | || |  ____  ____  | |\n" +
                "| | |_   ___ `.  | || |     /  \\     | || |   /  ___  |  | || | |_   ||   _| | |\n" +
                "| |   | |   `. \\ | || |    / /\\ \\    | || |  |  (__ \\_|  | || |   | |__| |   | |\n" +
                "| |   | |    | | | || |   / ____ \\   | || |   '.___`-.   | || |   |  __  |   | |\n" +
                "| |  _| |___.' / | || | _/ /    \\ \\_ | || |  |`\\____) |  | || |  _| |  | |_  | |\n" +
                "| | |________.'  | || ||____|  |____|| || |  |_______.'  | || | |____||____| | |\n" +
                "| |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'";
        String horizontalLine = "________________________________________";
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm\n" + logo + "\nWhat can I do for you?");

        //Echo loop
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
        }

        //Exit
        System.out.println(horizontalLine);
        String goodbye = "   ___                _ _                  _ \n" +
                "  / _ \\___   ___   __| | |__  _   _  ___  / \\\n" +
                " / /_\\/ _ \\ / _ \\ / _` | '_ \\| | | |/ _ \\/  /\n" +
                "/ /_\\\\ (_) | (_) | (_| | |_) | |_| |  __/\\_/ \n" +
                "\\____/\\___/ \\___/ \\__,_|_.__/ \\__, |\\___\\/   \n" +
                "                              |___/          ";
        System.out.println(goodbye);
        System.out.println(horizontalLine);
    }
}
