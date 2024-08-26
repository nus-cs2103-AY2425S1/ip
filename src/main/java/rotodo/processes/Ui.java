package rotodo.processes;

import java.io.IOException;

public class Ui {
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
        
        Ui.print("    Hello! I'm \n" 
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
     * Prints chatbot help message
     */
    public static void help() {
        Ui.print(
            "    Options:\n"
            + "      help          list all available options\n"
            + "      list          Prints all tasks on tasklist\n"
            + "      mark INDEX    Mark task as done\n"
            + "      unmark INDEX  Mark task as undone\n"
            + "      bye           Exit program\n\n"
            + "      Supported Task commands:\n"
            + "        todo TASK_DESCRIPTION\n"
            + "                    Add new ToDo task to tasklist\n"
            + "        deadline TASK_DESCRIPTION /by DEADLINE\n"
            + "                    Add new Deadline task to tasklist,\n"
            + "                    with due by date/time\n"
            + "        event TASK_DESCRIPTION /from START /to END\n"
            + "                    Add new Event task to tasklist,\n"
            + "                    with start and end date/time\n\n");
    }
    
    /**
     * Exits program after printing goodbye text.
     */
    public static void exit() {
        Ui.print("    Bye, RoTodo wish you all the best. Remember to finish all your tasks!");
        try {
            Storage.of().saveList();
        } catch (IOException e) {
            Ui.print("    Unable to save list :(\n    RoTodo is sorry)");
        }
        System.exit(0);
    }
}
