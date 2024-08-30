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
    public static void main(String[] args) {
        Ui.greet();
        TaskList taskList = new TaskList();
        Storage.fetchStorage(taskList);


        Scanner userInput = new Scanner(System.in);
        // GitHub CoPilot helped a lot with auto-complete (except personalisation)
        // it is actually really smart haha
        while (true) {
            String input = userInput.nextLine();

            // handle bye
            if (input.equalsIgnoreCase("bye")) { // I added equalsIgnoreCase() by myself
                Ui.sayGoodBye();
                userInput.close();
                break;
            }

            // handle other input
            Ui.showLine();
            try {
                String reply = Parser.parseUserInput(input, taskList);
                System.out.println(reply);
            } catch (Exception e) {
                System.out.println(e);
            }
            Ui.showLine();
        }

    }
}
