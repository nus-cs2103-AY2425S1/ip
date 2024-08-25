import java.util.Scanner;

public class Prince {
    public static String conversation(String command) {
        if(command.equals("bye")) { //string cannot do ==
            return "Bye! Hope to see you again soon!";
        } else {
            return command;
        }
    }
    public static void main(String[] args) {
        // if any words, repeat scanning, but the moment the word is bye,
        // then exit and print bye
        String line = "";
        System.out.println("Hello! I'm Prince!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type something!");

        line = scanner.nextLine(); // what the user replied

        while(!line.equals("bye")) {
            System.out.println(conversation(line));
            System.out.println("Is there anything else you'd like to say?");
            line = scanner.nextLine();
        }

        System.out.println(conversation(line));
    }
}

