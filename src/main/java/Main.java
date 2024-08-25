import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {
        // save data feature
        String filename = "Ynch.txt";
        File file = new File(filename);
        String userInput;
        Ynch chatbot = new Ynch();

        // check if file exists
        try {
            if (file.exists()) {
                //  read file contents
                Scanner scannerHistory = new Scanner(file);
                
                // add each task to chatbot history
                while (scannerHistory.hasNextLine()) {
                    String task = scannerHistory.nextLine();
                    chatbot.processInput(task);
                }
                scannerHistory.close();
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println(chatbot.greet());
        while (true) {
            userInput = scanner.nextLine();
            
            if (userInput.equals("bye")) {
                System.out.println(chatbot.exit());
                break;
            } 

            try {
                checkForEmpty(userInput);
                checkForInvalid(userInput);
            } catch (EmptyTaskException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                continue;
            }

            chatbot.processInput(userInput);

            // save chat history
            try (FileWriter writer = new FileWriter(filename, true)) {
                writer.write(userInput + System.lineSeparator());
                // System.out.println("Appended: " + userInput);
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }

        }
        scanner.close(); 
        
        
    }

    private static void checkForEmpty(String userInput) throws EmptyTaskException {
        if (userInput.equals("todo")) {
            throw new EmptyTaskException();
        }
    }

    private static void checkForInvalid(String userInput) throws InvalidCommandException {
        if (!isValidInput(userInput)) {
            throw new InvalidCommandException();
        }
    }

    private static boolean isValidInput(String userInput) { 
        try {
            ValidCommand.valueOf(userInput.split(" ")[0]); 
            return true; // valid enum
        } catch (IllegalArgumentException e) {
            return false; // invalid enum
        }
    }
}