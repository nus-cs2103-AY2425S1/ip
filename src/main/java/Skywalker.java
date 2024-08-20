import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
public class Skywalker {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String printable = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (Objects.equals(printable, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");

                break;
            }
            System.out.println(printable);
            System.out.println("____________________________________________________________");

        }
    }
}
