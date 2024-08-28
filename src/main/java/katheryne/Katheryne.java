package katheryne;

import katheryne.exceptions.InvalidInputException;
import katheryne.exceptions.MissingInformationException;


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
                    System.out.println(c.executeList(taskList));
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
                } else {
                    throw new InvalidInputException("Katheryne: " + "I'm sorry, Katheryne is unable to comprehend your request.");
                }
                storage.save(taskList);
            } catch (InvalidInputException e) {
                System.out.println("Katheryne: " + e.getMessage());
            }
        }
    }
}



