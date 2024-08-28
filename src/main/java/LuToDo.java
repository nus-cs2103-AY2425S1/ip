import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class LuToDo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File taskListFile = new File("docs/taskListFile.txt");
        if (!taskListFile.exists()) {
            try {
                taskListFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        Utility.greeting();
        while (true) {
            String message = sc.nextLine();
            System.out.print(Utility.LINE);
            if (message.equals("bye")) {
                Utility.bye();
                System.out.print(Utility.LINE);
                break;
            }
            Utility.handleMessage(message);
            System.out.print(Utility.LINE);
        }
    }
}
