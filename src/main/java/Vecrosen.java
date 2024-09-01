import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Vecrosen {
    private static Ui ui;

    public static void main(String[] args) {
        File data = new File("data/vecrosen.txt");
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
        Scanner scanner = new Scanner(System.in);
        int itemNo;
        String desc;
        while (true) {
            String input;
            input = scanner.nextLine();
            ArrayList<Object> parseArgs = new ArrayList<Object>();
            Parser.ActionType actionType = Parser.parse(input, parseArgs);
            switch (actionType) {
            case bye:
                ui.speak("Bye. Hope to see you again soon!");
                return;
            case mark:
                itemNo = (Integer) parseArgs.get(0);
                try {
                    String target = taskList.setDone(itemNo-1, true);
                    ui.speak("Task marked as complete: " + target);
                } catch (IndexOutOfBoundsException e) {
                    ui.speak("Invalid task number!");
                }
                break;
            case unmark:
                itemNo = (Integer) parseArgs.get(0);
                try {
                    String target = taskList.setDone(itemNo-1, false);
                    ui.speak("Task marked as incomplete: " + target);
                } catch (IndexOutOfBoundsException e) {
                    ui.speak("Invalid task number!");
                }
                break;
            case list:
                taskList.printList(ui);
                break;
            case todo:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Task(desc));
                ui.speak("Todo added: " + desc);
                break;
            case deadline:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Deadline(desc, (String) parseArgs.get(1)));
                ui.speak("Deadline added: " + desc);
                break;
            case event:
                desc = (String) parseArgs.get(0);
                taskList.addTask(new Event(desc, (String) parseArgs.get(1), (String) parseArgs.get(2)));
                ui.speak("Event added: " + desc);
                break;
            case delete:
                itemNo = (Integer) parseArgs.get(0);
                String target = taskList.deleteTask(itemNo - 1);
                ui.speak("Removing task: " + target);
                ui.speak("You now have " + taskList.getListSize() + " tasks left in record.");
                break;
            case formatting:
                ui.invalidFormat((String) parseArgs.get(0));
                break;
            case undefined:
                ui.speak("Sorry, I don't understand.");
                ui.speak("Commands: todo deadline event mark unmark delete list bye");
            }
            taskList.save(data);
        }
    }
}
