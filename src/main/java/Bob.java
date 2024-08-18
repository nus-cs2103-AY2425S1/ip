import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String greeting = """
                ____________________________________________________________
                 Hello! I'm Bob
                 What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(greeting);

        ArrayList<String> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String input = in.nextLine();
            switch (input) {
                case "bye":
                    String exit = """
                        ____________________________________________________________
                         Bye. Hope to see you again soon!
                        ____________________________________________________________
                        """;
                    System.out.print(exit);
                    System.exit(0);
                case "list":
                    StringBuilder listMsg = new StringBuilder("____________________________________________________________");
                    for (int i = 0; i < list.size(); i++) {
                        listMsg
                                .append("\n ")
                                .append(i + 1)
                                .append(". ")
                                .append(list.get(i));
                    }
                    listMsg.append("\n____________________________________________________________\n");
                    System.out.print(listMsg);
                    break;
                default:
                    list.add(input);
                    String addedMsg = "____________________________________________________________\n"
                            + " added: " + input + "\n"
                            + "____________________________________________________________\n";
                    System.out.print(addedMsg);
                    break;
            }
        }
    }
}
