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
        String[] tasks = new String[100];
        int count = 0;



        while (true) {
            Scanner scanner = new Scanner(System.in);
            String printable = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (Objects.equals(printable, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");

                break;
            }
            else if(Objects.equals(printable, "list")){
                for(int i =0; i<count; i++){
                    System.out.println(i+1+". "+tasks[i]);
                }
                System.out.println("____________________________________________________________");

            }
            tasks[count++]= printable;
            System.out.println("added: "+ printable);
            System.out.println("____________________________________________________________");

        }
    }
}
