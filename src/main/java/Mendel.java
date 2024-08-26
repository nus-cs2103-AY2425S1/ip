import java.util.Scanner;

import mendel.mendelexception.ServerError;
import mendel.preetyprint.FormatText;
import mendel.metacognition.Welcome;
import mendel.mendelexception.MendelException;

public class Mendel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "Mendel";
        System.out.println("Hello from\n" + logo);
        String currAction = "hello";
        TaskManager taskManager = new TaskManager();
        new Welcome().speak();
        while (!currAction.equals("bye")) {
            currAction = sc.nextLine().trim();
            try {
                taskManager.manage(currAction);
            } catch (MendelException e) {
                System.out.println(new FormatText(e.toString()).wrapLines());
            } catch (ServerError e) {
                System.out.println(new FormatText(e.toString()).wrapLines());
                taskManager.manage("bye");
                currAction = "bye";
            }
        }
    }
}
