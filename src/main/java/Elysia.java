import java.util.Objects;
import java.util.Scanner;

public class Elysia {


    public static void main(String[] args) {
        Message.print("Hi~! I'm Elysia! As you can see, I'm a girl as beautiful as as flower!\n" +
                "How can I help you today? I'm all ears!");

        Scanner command = new Scanner(System.in);
        String input = "";
        String output = "";
        boolean repeat = true;
        InputOutputHandler handler = new InputOutputHandler(input);

        while (repeat) {
            input = command.nextLine();
            try {
                repeat = handler.parseInput(input);
            } catch (ElysiaException | StringIndexOutOfBoundsException e) {
                Message.print("What are you trying to say? You need to talk to pretty girls nicely...");
            }
        }

        Message.print("Aww, going already? Don't miss me too much, ok?");
    }
}
