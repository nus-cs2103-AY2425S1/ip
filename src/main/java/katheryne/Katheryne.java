package katheryne;

import katheryne.exceptions.InvalidInputException;
import katheryne.exceptions.MissingInformationException;


/**
 * Katheryne Class is the main class that will run based on different commands
 * given by users. It has attribute of storage for loading and saving of tasks;
 * taskList to manage the tasks on record; and ui to handle interation with users.
 */
public class Katheryne {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Katheryne(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) throws MissingInformationException {
        Katheryne k = new Katheryne("./data/Katheryne.txt");
        k.run();
    }


    public void run() throws MissingInformationException {
        System.out.println(ui.getDivide());
        System.out.println(ui.getGreeting());

        boolean isFinish = false;

        while (!isFinish) {
            System.out.println(ui.getDivide());
            try {
                Command c = new Command(ui, taskList);
                String str = ui.getLine();
                String commandWord = ui.getCommand(str);
                if (commandWord.equals("list")) {
                    System.out.println(c.executeList());
                } else if (commandWord.equals("mark")) {
                    System.out.println(c.executeMark(str));

                } else if (commandWord.equals("unmark")) {
                    System.out.println(c.executeUnmark(str));
                } else if (commandWord.equals("todo")) {
                    System.out.println(c.executeAddToDo(str));
                } else if (commandWord.equals("event")) {
                    System.out.println(c.executeAddEvent(str));
                } else if (commandWord.equals("deadline")) {
                    System.out.println(c.executeAddDeadline(str));
                } else if (commandWord.equals("bye")) {
                    isFinish = true;
                    System.out.println(ui.getDivide());
                    System.out.println(ui.getBye());
                    System.out.println(ui.getDivide());
                } else if (commandWord.equals("delete")) {
                    System.out.println(c.executeDelete(str));
                } else if (commandWord.equals("find")) {
                    System.out.println(c.executeFind(str));
                } else {
                    String msg = "Katheryne: I'm sorry, Katheryne is unable to comprehend your request.";
                    throw new InvalidInputException(msg);
                }
                storage.save(taskList);
            } catch (InvalidInputException e) {
                System.out.println("Katheryne: " + e.getMessage());
            }
        }
    }

    public String getResponse(String userInput) throws MissingInformationException {
        try {
            Command c = new Command(ui, taskList);
            String str = userInput;
            String commandWord = ui.getCommand(str);
            switch (commandWord) {
                case "list" -> {
                    return c.executeList();
                }
                case "mark" -> {
                    storage.save(taskList);
                    return c.executeMark(str);
                }
                case "unmark" -> {
                    storage.save(taskList);
                    return c.executeUnmark(str);
                }
                case "todo" -> {
                    storage.save(taskList);
                    return c.executeAddToDo(str);
                }
                case "event" -> {
                    storage.save(taskList);
                    return c.executeAddEvent(str);
                }
                case "deadline" -> {
                    storage.save(taskList);
                    return c.executeAddDeadline(str);
                }
                case "bye" -> {
                    return ui.getBye();
                }
                case "delete" -> {
                    storage.save(taskList);
                    return c.executeDelete(str);
                }
                case "find" -> {
                    return c.executeFind(str);
                }
                default -> {
                    String msg = "I'm sorry, Katheryne is unable to comprehend your request.";
                    throw new InvalidInputException(msg);
                }
            }
        } catch (InvalidInputException e) {
            return String.format("Katheryne: %s", e.getMessage());
        }
    }
}




