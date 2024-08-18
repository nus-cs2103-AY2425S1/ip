import java.util.ArrayList;
import java.util.Scanner;

public class Terminator {
    public static void main(String[] args) {
        String hline = "\t____________________________________________________________\n";
        String logo = """
                                     <((((((\\\\\\
                                     /      . }\\
                                     ;--..--._|}
                  (\\                 '--/\\--'  )
                   \\\\                | '-'  :'|
                    \\\\               . -==- .-|
                     \\\\               \\.__.'   \\--._
                     [\\\\          __.--|       //  _/'--.
                     \\ \\\\       .'-._ ('-----'/ __/      \\
                      \\ \\\\     /   __>|      | '--.       |
                       \\ \\\\   |   \\   |     /    /       /
                        \\ '\\ /     \\  |     |  _/       /
                         \\  \\       \\ |     | /        /
                          \\  \\      \\        /
                """;
        String greeting = hline +
                logo +
                "\tDevice booted successfully.\n" +
                "\tState your request.\n" +
                hline;
        System.out.println(greeting);

        String exit = hline + "\tAll objectives fulfilled. Deactivating...\n" + hline;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        ArrayList<String> todoList = new ArrayList<>();
        while (!userInput.equals("bye")) {
            System.out.print(hline);
            if (userInput.equals("list")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + todoList.get(i));
                }
            } else {
                todoList.add(userInput);
                System.out.println("\tadded: " + userInput);
            }
            System.out.println(hline);
            userInput = sc.nextLine();
        }
        System.out.println(exit);
    }
}
