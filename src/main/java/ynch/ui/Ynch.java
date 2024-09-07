package ynch.ui;

import java.nio.file.Paths;

public class Ynch {
    // initialize
    String filePath = Paths.get("").toAbsolutePath().toString();
    String filename = "Ynch.txt";
    Storage storage = new Storage(filePath, filename);
    TaskList taskList = storage.load();
    Parser parser = new Parser();
    YnchUi ui = new YnchUi();

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

    public String greet() {
        return ui.greet();
    }

    public String getResponse(String userInput) {

        if (userInput.equals("bye")) {
            storage.save(taskList);
            return ui.exit();
        }

        try {
            checkForEmpty(userInput);
            checkForInvalid(userInput);
        } catch (EmptyTaskException e) {
            return e.getMessage();
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }

        switch (parser.processInput(userInput)) {
            case list: {
                return taskList.list();
            }
            case mark: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                return ui.printMark(taskList.mark(i));
            }
            case unmark: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                return ui.printUnmark(taskList.unmark(i));
            }
            case todo: {
                String task = userInput.split(" ", 2)[1];
                return ui.printAdd(taskList.add(task), taskList.getSize());
            }
            case deadline: {
                System.out.println("deadline");
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/by")[0];
                String deadline = userInput.split("/by")[1];
                return ui.printAdd(taskList.add(task, deadline), taskList.getSize());
            }
            case event: {
                userInput = userInput.split(" ", 2)[1];
                String task = userInput.split("/from")[0];
                String fromAndTo = userInput.split("/from")[1];
                String from = fromAndTo.split("/to")[0];
                String to = fromAndTo.split("/to")[1];
                return ui.printAdd(taskList.add(task, from, to), taskList.getSize());
            }
            case delete: {
                int i = Integer.valueOf(userInput.split(" ")[1]);
                return ui.printDelete(taskList.delete(i), taskList.getSize());
            }
            case find: {
                String keyword = userInput.split(" ", 2)[1];
                return ui.printFind(taskList.find(keyword));
            }
        }

        return userInput;
    }

}