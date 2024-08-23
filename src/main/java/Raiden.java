import java.util.Objects;
import java.util.Scanner;

public class Raiden {

    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Greetings, I am Raiden. It is a pleasure to meet you.");
        System.out.println("Is there anything I can assist you with? \n" + lines + "\n");

        Scanner command = new Scanner(System.in);
        String output = "";

        while (true) {
            output = command.nextLine();
            if (Objects.equals(output, "bye")) break;
            System.out.println(lines + "\n" + output + "\n" + lines + "\n");
        }

        System.out.println(lines + "\n I hope my assistance was of help. Do visit again. \n" + lines);
    }
}
