import java.util.Scanner;

public class Tudee {
    public static void main(String[] args) {
        String logo = "____________________________________________________________ \n"
                + "Hello! I'm Tudee, your chatbot bestie! \n"
                + "How can I help you today? :) \n"
                + "____________________________________________________________ \n";
        System.out.println(logo);
        String input;
        Scanner sc = new Scanner(System.in);
        while (true) {
            String output = "";
            input = sc.nextLine();
            output += "____________________________________________________________ \n";
            output += input + "\n";
            output += "____________________________________________________________ \n";
            System.out.println(output);
            if (input.equalsIgnoreCase("bye")) {
                output = "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! Don't forget about me :( \n"
                        + "____________________________________________________________";
                System.out.println(output);
                break;
            }
        }
        sc.close();
    }
}
