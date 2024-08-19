import java.util.Scanner;
import tasklist.TaskList;

/**
 * __________       __________            __   _____
 * \______   \  ____\__   ___/____    ___|  | /  _  \   ____    
 *  |       _/ /  _ \ |   |  /  _ \  /  _   ||  / \  | /  _ \   ___
 *  |    |   \(  <_> ||   | (  <_> |(  <_>  ||  \_/  |(  <_> | / o \  _
 *  |____|_  / \____/ |___|  \____/  \_____/  \_____/  \____/  \___/ (_) O o . 
 *         \/
 * This class implements the CLI of RoTodo.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class RoTodo {
    /**
     * Wraps input string x with line text above and below.
     * 
     * @param x
     */
    public static void print(String x) {
        System.out.println("  " + new String(new char[100]).replace("\0", "-"));
        System.out.println(x);
        System.out.println("  " + new String(new char[100]).replace("\0", "-") + "\n");
    }

    /**
     * Prints chatbot banner.
     */
    public static void banner() {
        // Declaring ANSI_COLOR 
        String ansiReset = "\u001B[0m"; 
        String ansiRed = "\u001B[31m"; 
        
        RoTodo.print("    Hello! I'm \n" 
            + ("    R__________E       __________            __   _____\n"
            + "    R\\______   \\E  ____\\__   ___/____    ___|  | /  _  \\   ____\n"
            + "     R|       _/E /  _ \\ |   |  /  _ \\  /  _   ||  / \\  | /  _ \\   ___\n"
            + "     R|    |   \\E(  <_> ||   | (  <_> |(  <_>  ||  \\_/  |(  <_> | / o \\  _\n"
            + "     R|____|_  /E \\____/ |___|  \\____/  \\_____/  \\_____/  \\____/  \\___/ (_) O o .\n"
            + "            R\\/E\n").replace("R", ansiRed).replace("E", ansiReset)
            + "    Your very own Robot Todo List!\n"
            + "    How can I help you help yourself?");
    }

    /**
     * Exits program after printing goodbye text.
     */
    public static void exit() {
        RoTodo.print("    Bye, remember to finish all your tasks!");
        System.exit(0);
    }

    public static void main(String[] args) {
        RoTodo.banner();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        TaskList taskList = new TaskList();
        String input;
        while (true) {
            input = sc.nextLine();
            switch (input) {
                case "list":
                    RoTodo.print(taskList.toString());
                    break;

                case "bye":
                    sc.close();
                    RoTodo.exit();
                    break;
            
                default:
                    RoTodo.print(taskList.process(input));
                    break;
            }
        }
    }
}
