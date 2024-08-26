import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Schedulo {
    public static void main(String[] args) {
        File file = new File("./data/data.txt");
        Scanner sc = new Scanner(System.in);
        try {
            Scanner fileReader = new Scanner(file);
            String horizontalLine = "---------------";
            TaskList taskList = new TaskList();
            // read from file and add to task list
            System.out.println(horizontalLine);
            System.out.println("Hello I am Schedulo!");
            System.out.println("What can I do for you?");
            System.out.println(horizontalLine);
            while (true) {
                String command = sc.nextLine();
                System.out.println(horizontalLine);
                if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(horizontalLine);
                    break;
                }

                taskList.executeCommand(command);
                
                System.out.println(horizontalLine);
            }
            fileReader.close(); // see if this is the correct place
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please create a new file data.txt under data folder outside src");
        } catch (Exception e) {
            System.err.println("Something went wrong, please try again");
        } finally {
            sc.close();
        }
    }
}

// Todo
// testing
// add enums