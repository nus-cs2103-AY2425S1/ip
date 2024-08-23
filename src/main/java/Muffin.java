import java.util.Scanner;

public class Muffin {
    enum Command {
        BYE, LIST, ADD
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _  \n" +
                "| |\\/| | || |  _|  _| | ' \\ \n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I'm Muffin \n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);
        command(sc, 0, list);
    }

    public static void command(Scanner sc, int count, String[] list) {
        String userInput = sc.nextLine();

        Command command;
        try {
            command = Command.valueOf(userInput.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.ADD;
        }

        switch(command) {
            case BYE:
                System.out.println("Goodbye~ Hope to see you again soon!");
                break;

            case LIST:
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
                command(sc, count, list);
                break;

            case ADD:
                list[count] = userInput;
                System.out.println("added: " + userInput);
                command(sc, ++count, list);
                break;
        }
    }
}
