import java.util.Scanner;

import mendel.mendelexception.ServerError;
import mendel.preetyprint.FormatText;
import mendel.mendelexception.MendelException;

public class Mendel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String currAction = "hello";
        TaskManager taskManager = new TaskManager();
        UI ui = new UI();
        ui.showWelcome();
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
