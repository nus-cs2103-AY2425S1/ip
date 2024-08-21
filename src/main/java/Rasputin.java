import java.sql.Array;
import java.util.Scanner;
import  java.util.ArrayList;

public class Rasputin {

    private static void printText(String text) {
        System.out.println(lineBreak);
        System.out.println(text);
        System.out.println(lineBreak + "\n");
    }

    private static String lineBreak = "____________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String name = "Rasputin";
        ArrayList<String> ls = new ArrayList<>();



        Scanner scanner = new Scanner(System.in);
        printText("Hello, I'm " + name + "!\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                int index = 1;
                System.out.println(lineBreak);
                for (String item: ls) {
                    System.out.println(index + ". " + item);
                    index++;
                }

                System.out.println(lineBreak + "\n");
                continue;
            }
            ls.add(input);
            printText("added: " + input);

        }

        printText("Bye. Hope to see you soon!");
    }


}
