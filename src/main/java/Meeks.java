import java.util.Scanner;
public class Meeks {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("____________________________________________________________\n"
                         + "Hello! I'm Meeks! Your friendly chatbot!\n"
                         + "What can I do for you?\n");

        Echo echo = new Echo();


        while (true) {
            String command = scanner.nextLine();
            echo.setWord(command);


            if (command.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue; // Skip to the next iteration of the loop
            }
            echo.echoOut();

            if (command.equalsIgnoreCase("bye")) {
                break; // Exit the loop
            }
        }


        scanner.close();
    }
}


