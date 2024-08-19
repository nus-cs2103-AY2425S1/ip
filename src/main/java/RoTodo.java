/*
   __________       __________            __   _____
   \______   \  ____\__   ___/____    ___|  | /  _  \   ____    
    |       _/ /  _ \ |   |  /  _ \  /  _   ||  / \  | /  _ \   ___
    |    |   \(  <_> ||   | (  <_> |(  <_>  ||  \_/  |(  <_> | / o \  _
    |____|_  / \____/ |___|  \____/  \_____/  \_____/  \____/  \___/ (_) O o . 
           \/
 */

import java.util.Scanner;

public class RoTodo {
    public static void line(boolean newLine) {
        System.out.println("  " + new String(new char[100]).replace("\0", "-"));
        if (newLine) {
            System.out.println();
        }
    }

    public static void banner() {
        // Declaring ANSI_COLOR 
        String ansiReset = "\u001B[0m"; 
        String ansiRed = "\u001B[31m"; 
        
        RoTodo.line(false);
        System.out.println("    Hello! I'm \n" 
            + ("    R__________E       __________            __   _____\n"
            + "    R\\______   \\E  ____\\__   ___/____    ___|  | /  _  \\   ____\n"
            + "     R|       _/E /  _ \\ |   |  /  _ \\  /  _   ||  / \\  | /  _ \\   ___\n"
            + "     R|    |   \\E(  <_> ||   | (  <_> |(  <_>  ||  \\_/  |(  <_> | / o \\  _\n"
            + "     R|____|_  /E \\____/ |___|  \\____/  \\_____/  \\_____/  \\____/  \\___/ (_) O o .\n"
            + "            R\\/E\n").replace("R", ansiRed).replace("E", ansiReset)
            + "    Your very own Robot Todo List!\n"
            + "    How can I help you help yourself?");
        RoTodo.line(true);
    }

    public static void exit() {
        RoTodo.line(false);
        System.out.println("    Bye, remember to finish all your tasks!");
        RoTodo.line(false);
        System.exit(0);
    }

    public static void main(String[] args) {
        RoTodo.banner();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String input;
        while (true) {
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    RoTodo.exit();
                    break;
            
                default:
                    RoTodo.line(false);
                    System.out.println("    " + input);
                    RoTodo.line(true);
                    break;
            }
        }
    }
}
