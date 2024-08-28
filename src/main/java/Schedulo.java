import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Schedulo {
    public static void main(String[] args) {
        File file = new File("./data/data.txt");
        Scanner sc = new Scanner(System.in);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
            String horizontalLine = "---------------";
            TaskList taskList = new TaskList(fileReader);
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found, please create a new file data.txt under data folder outside src");
        } catch (Exception e) {
            System.err.println("Something went wrong, please try again");
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            sc.close();
        }
    }
}

// Todo
// testing
// add enums