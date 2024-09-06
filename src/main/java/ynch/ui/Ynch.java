package ynch.ui;

import java.util.Scanner;

import java.nio.file.Paths;

public class Ynch {

    public static void run(String[] args) {
        // Storage will load and save tasks
        // TaskList will store tasks
        // Parser will tell Main what operations to call for TaskList
        // YnchUi will print statements

        // initialize
        String filePath = Paths.get("").toAbsolutePath().toString();
        String filename = "Ynch.txt";
        Storage storage = new Storage(filePath, filename);
        TaskList taskList = storage.load();
        Parser parser = new Parser();
        YnchUi ui = new YnchUi();

        Scanner scanner = new Scanner(System.in);

        ui.greet();

        while (true) {
            String userInput = scanner.nextLine();
            
            if (userInput.equals("bye")) {
                storage.save(taskList);
                ui.exit();
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
            
            switch (parser.processInput(userInput)) {
            case list: {
                System.out.println(taskList.list());
                break;
            }
            case mark: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                ui.printMark(taskList.mark(i));
                break;
            }
            case unmark: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                ui.printUnmark(taskList.unmark(i));
                break;
            }
            case todo: {
                String task = userInput.split(" ", 2)[1];
                ui.printAdd(taskList.add(task), taskList.getSize());
                break;
            }
            case deadline: {
                System.out.println("deadline");
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/by")[0];
                String deadline = userInput.split("/by")[1];
                ui.printAdd(taskList.add(task, deadline), taskList.getSize());
                break;
            }
            case event: {
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/from")[0];
                String fromAndTo = userInput.split("/from")[1];
                String from = fromAndTo.split("/to")[0];
                String to = fromAndTo.split("/to")[1];
                ui.printAdd(taskList.add(task, from, to), taskList.getSize());
                break;
            }
            case delete: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                ui.printDelete(taskList.delete(i), taskList.getSize());
                break;
            }
            case find: {
                String keyword = userInput.split(" ", 2)[1];
                ui.printFind(taskList.find(keyword));
            }
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