import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Tudee {
    public static void main(String[] args) {
        String logo = "____________________________________________________________ \n"
                + "Hello! I'm Tudee, your chatbot bestie! \n"
                + "How can I help you today? :) \n"
                + "____________________________________________________________ \n";
        System.out.println(logo);
        String input;
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true) {
            String output = "";
            input = sc.nextLine();
            if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________ \n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i) + "\n");
                }
                System.out.println("____________________________________________________________ \n");
            }
            else if (input.equalsIgnoreCase("bye")){
                output = "____________________________________________________________ \n"
                        + "Bye. Hope to see you again soon! Don't forget about me :( \n"
                        + "____________________________________________________________";
                System.out.println(output);
                break;
            }
            else {
                list.add(input);
                System.out.println("____________________________________________________________ \n");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________ \n");
            }
        }
        sc.close();
    }
}
