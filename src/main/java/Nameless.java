import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Nameless {

    public static void main(String[] args) {
        String line = "_____________________________________________________________";
        String name = "Nameless";
        String greetings = "Hello, I'm " + name + "\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again!";

        System.out.println(line + "\n" + greetings + "\n" + line);

        while(true){
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if(input.equals("bye")){
                break;
            }

            System.out.println(line + "\n" + input  + "\n" + line);
        }

        System.out.println(line + "\n" + goodbye + "\n" + line);
    }
}

