import java.util.ArrayList;
import java.util.Scanner;

public class Tako {
    public static void main(String[] args) {
        String name = "Tako";
        ArrayList<String> storageCommand = new ArrayList<String>();

        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + "\n" +
                           "What can I do for you?\n");

        String command = input.nextLine();

        while(!command.equals("bye")) {
            if (command.equals("list")) {
                listStorage(storageCommand);
            } else {
                System.out.println("added: " + command);
                addStorage(storageCommand, command);
            }
            command = input.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listStorage(ArrayList<String> storageCommand) {
        for (int i = 0; i < storageCommand.size(); i++) {
            System.out.println(i+1 + ". " + storageCommand.get(i));
        }
    }

    public static void addStorage(ArrayList<String> storageCommand, String command) {
        storageCommand.add(command);
    }
}
