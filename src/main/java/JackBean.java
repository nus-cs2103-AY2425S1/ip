import java.nio.file.Path;
import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JackBean {
    public static String horizontalLine = "____________________________________________________________";
    public static String greeting = "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!\nHow may I help you today my homie?";
    public static String exitMessage = "Bye homie! Come back if you need anything else. JackBean, signing off!";


    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println(greeting);
        System.out.println(horizontalLine);

        TaskList taskList = new TaskList();
        Storage.fetchStorage(taskList);


        Scanner userInput = new Scanner(System.in);
        // GitHub CoPilot helped a lot with auto-complete (except personalisation)
        // it is actually really smart haha
        while (true) {
            String input = userInput.nextLine();

            // handle bye
            if (input.equalsIgnoreCase("bye")) { // I added equalsIgnoreCase() by myself
                System.out.println(horizontalLine);
                System.out.println(exitMessage);
                System.out.println(horizontalLine);
                userInput.close();
                break;
            }

            // handle other input
            System.out.println(horizontalLine);
            try {
                String reply = Parser.parseUserInput(input, taskList);
                System.out.println(reply);
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(horizontalLine);
        }

    }
}
