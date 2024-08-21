import java.util.Scanner;
import java.util.ArrayList;
public class MichaelScott {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true;
        ArrayList<String> todo = new ArrayList<String>();

        String logo = " __  __ _      _                _   ____            _   _  \n"
                + "|  \\/  (_) ___| |__   __ _  ___| | / ___|  ___ ___ | |_| |_ \n"
                + "| |\\/| | |/ __| '_ \\ / _` |/ _ \\ | \\___ \\ / __/ _ \\| __| __|\n"
                + "| |  | | | (__| | | | (_| |  __/ |  ___) | (_| (_) | |_| |_ \n"
                + "|_|  |_|_|\\___|_| |_|\\__,_|\\___|_| |____/ \\___\\___/ \\__|\\__|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Michael Scott");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while(flag) {
            String command = myScanner.nextLine();

            if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < todo.size(); i++) {
                    System.out.println(i + 1 + ". " + todo.get(i));
                }
                System.out.println("____________________________________________________________");

            } else if (command.equals("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");
            } else if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Catch you on the flippity flip! ");
                System.out.println("____________________________________________________________");
                flag = false;
            } else {
                todo.add(command);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }

        }
    }
}
