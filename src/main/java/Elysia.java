import java.util.Objects;
import java.util.Scanner;

public class Elysia {

    public static void main(String[] args) {
        String lines = "________________________________________________________________________________________";

        System.out.println(lines);
        System.out.println("Hi~! I'm Elysia! As you can see, I'm a girl as beautiful as as flower!");
        System.out.println("How can I help you today? I'm all ears! \n" + lines + "\n");

        Scanner command = new Scanner(System.in);
        String input = "";
        String output = "";

        String[] list = new String[100];
        int listPointer = 0;

        while (!Objects.equals(input, "bye")) {
            input = command.nextLine();
            switch(input) {
                case "bye":
                    break;
                case "list":
                    output = "Here's your list! \n";
                    for (int i = 0; i < listPointer; i++) {
                        output += "\n" + (i+1) + ". " + list[i];
                    }
                    System.out.println(lines + "\n" + output + "\n" + lines + "\n");
                    break;
                default:
                    list[listPointer] = input;
                    listPointer++;
                    output = "Added " + input + " to your list~";
                    System.out.println(lines + "\n" + output + "\n" + lines + "\n");
                    break;
            }
        }

        System.out.println(lines + "\n Aww, going already? Don't miss me too much, ok? \n" + lines);
    }
}
