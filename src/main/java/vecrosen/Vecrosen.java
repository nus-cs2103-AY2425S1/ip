package vecrosen;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The main application. Essentially a memo app.
 */
public class Vecrosen {
    private static Ui ui;

    public static void main(String[] args) {
        File data = new File("data/vecrosen.txt");
        File datafolder = new File("data");
        if (!datafolder.exists()) {
            datafolder.mkdir();
        }
        TaskList taskList;
        try {
            taskList = new TaskList(data);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            ui.speak("Savefile is corrupted!");
            taskList = new TaskList();
        }
        ui = new Ui(System.in, System.out);
        ui.speak("Hello, I'm Vecrosen.");
        ui.speak("What can I do for you?");
        int itemNo;
        String desc;
        while (true) {
            String input;
            input = ui.readLine();
            ArrayList<Object> parseArgs = new ArrayList<Object>();
            Parser.ActionType actionType = Parser.parse(input, parseArgs);
            switch (actionType) {
            case BYE:
                ui.speak("Bye. Hope to see you again soon!");
                return;
            case MARK:
                itemNo = (Integer) parseArgs.get(0);
                try {
                    String target = taskList.setDone(itemNo, true);
                    ui.speak("Task marked as complete: " + target);
                } catch (IndexOutOfBoundsException e) {
                    ui.speak("Invalid task number!");
                }
                break;
            case UNMARK:
                itemNo = (Integer) parseArgs.get(0);
                try {
                    String target = taskList.setDone(itemNo, false);
                    ui.speak("Task marked as incomplete: " + target);
                } catch (IndexOutOfBoundsException e) {
                    ui.speak("Invalid task number!");
                }
                break;
            case list:
                taskList.printList(ui);
                break;
            case TODO:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Task(desc));
                ui.speak("Todo added: " + desc);
                break;
            case DEADLINE:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Deadline(desc, (String) parseArgs.get(1)));
                ui.speak("Deadline added: " + desc);
                break;
            case EVENT:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Event(desc, (String) parseArgs.get(1), (String) parseArgs.get(2)));
                ui.speak("Event added: " + desc);
                break;
            case DELETE:
                itemNo = (Integer) parseArgs.get(0);
                String target = taskList.deleteTask(itemNo);
                ui.speak("Removing task: " + target);
                ui.speak("You now have " + taskList.getListSize() + " tasks left in record.");
                break;
            case FIND:
                ArrayList<Integer> indices = new ArrayList<Integer>();
                ArrayList<Task> res = taskList.find((String) parseArgs.get(0), indices);
                ui.printList(res, indices, "Here are the matching tasks:", "No matches!");
                break;
            case FORMATTING:
                ui.alertInvalidFormat((String) parseArgs.get(0));
                break;
            case UNDEFINED:
                ui.speak("Sorry, I don't understand.");
                ui.speak("Commands: todo deadline event mark unmark delete list bye");
                break;
            default:
                System.err.println("Action type not recognized!");
            }
            taskList.save(data);
        }
    }
}
