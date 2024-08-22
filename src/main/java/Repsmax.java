import java.util.Scanner;

public class Repsmax {
    public static void main(String[] args) {
        //initialise scanner
        Scanner scanner = new Scanner(System.in);
        //greet the user
        String greeting = "  ____________________________________________________________\n" +
                "   Hello! I'm Repsmax\n" +
                "   What can I do for you?\n" +
                "  ____________________________________________________________\n";
        String goodbye = "  ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "  ____________________________________________________________\n";
        System.out.println(greeting);
        while(true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(goodbye);
                break;
            }

            System.out.println("  ____________________________________________________________\n" +
                    "   " +
                    userInput +
                    "\n" +
                    "  ____________________________________________________________\n");
        }
    }
}