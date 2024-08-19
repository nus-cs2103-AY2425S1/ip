import java.util.Scanner;

public class GreetBot {
    public static void main(String[] args) {
        new GreetBot().run();
    }

    /* solution below inspired by main function in:
    https://github.com/Wincenttjoi/CS2103T-duke-chatbot/blob/master/src/main/java/duke/Duke.java
    */
    private void run() {
        System.out.println("Hello! I'm GreetBot");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        
        String[] list = new String[100];
        int counter = 0;

        while (true) {
            System.out.println("-----------------------");
            String currentCommand = scanner.nextLine();

            if (currentCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentCommand.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(String.format("%d. %s", i + 1, list[i]));
                }
            } else {
                                 
                System.out.println("-----------------------");
                list[counter] = currentCommand;
                counter += 1;
                System.out.println(String.format("added: %s", currentCommand));
            }

                           
        }

        scanner.close();
        
    }
}
