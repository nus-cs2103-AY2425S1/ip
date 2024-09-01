import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors

/** Bword is a chatbot that helps with planning tasks
 *
 */

public class Bword {
    public static final String FILELOCATION = "./data/bword.txt";
    public static final String HLINE = "____________________________________________________________\n";

    public static void main(String[] args) {
        TaskHandler th = new TaskHandler();
        File file;
        try {
            file = new File(FILELOCATION);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                // System.out.println(s);
                th.addPastTask(s);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
            return;
        }
        Scanner sc = new Scanner(System.in);

        Ui.showWelcome();

        enum States {to_loop, to_exit, to_list}
        States currentState = States.to_loop;

        while (currentState != States.to_exit) {
            String command = sc.next();
            String s = sc.nextLine();
            System.out.print(HLINE);
            String tmp = s.strip();
            if (command.equals("bye")) {
                break;
            }
            th.handleCommand(command, s);
            System.out.print(HLINE);
        }

        file.delete();
        th.writeToFile(FILELOCATION);

        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                HLINE);
    }
}
