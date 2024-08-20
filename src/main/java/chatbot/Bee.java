package chatbot;

import todo.*;

import java.util.Scanner;

public class Bee {
    //Print out "Bee" logo in ASCII art
    private static final String logo = " ____\n"
            + "|  _ \\  ___   ___ \n"
            + "| |_/  / _ \\ / _ \\\n"
            + "| |_\\ |  __/|  __/ \n"
            + "|____/ \\___| \\___|\n";

    public static void main(String[] args) {
        TodoList todoList = new TodoList();

        //Welcome user
        System.out.println(logo);
        System.out.println(Format.printBtnLines("Hello, I'm Bee! What can I do for you?"));

        //Scan for user input
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            //Exit program if user inputs bye
            if (next.equalsIgnoreCase("bye")) {
                System.out.println(Format.printBtnLines("Bye~ Hope to see you again soon!"));
                break;

            //Prompt user for input if no input
            } else if (next.isEmpty()) {
                System.out.println(Format.printBtnLines("Hey! Say something."));

            } else if (next.equalsIgnoreCase("list")) {
                System.out.println(Format.printBtnLines(todoList.toString()));

            //Echo user input
            } else {
                todoList.addTask(new Task(next));
                System.out.println(Format.printBtnLines(String.format("added: \"%s\"", next)));
            }
        }
    }
}
