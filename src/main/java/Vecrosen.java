import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Vecrosen {
    private static ArrayList<Task> list;
    private static Ui ui;

    public static void main(String[] args) {
        ui = new Ui(System.in, System.out);
        list = new ArrayList<Task>();
        ui.speak("Hello, I'm Vecrosen.");
        ui.speak("What can I do for you?");
        File data = new File("data/vecrosen.txt");
        try {
            Storage.load(data,list);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            ui.speak("Savefile is corrupted!");
        }
        Scanner scanner = new Scanner(System.in);
        int itemNo;
        String desc;
        while (true) {
            String input;
            input = scanner.nextLine();
            ArrayList<Object> parseArgs = new ArrayList<Object>();
            Parser.ActionType actionType = Parser.parse(input, parseArgs, list.size());
            switch (actionType) {
            case bye:
                ui.speak("Bye. Hope to see you again soon!");
                return;
            case taskNum:
                ui.speak("Invalid task number!");
                break;
            case mark:
                itemNo = (Integer) parseArgs.get(0);
                list.get(itemNo-1).setDone(true);
                ui.speak("Task marked as complete: " + list.get(itemNo-1).getDescription());
                break;
            case unmark:
                itemNo = (Integer) parseArgs.get(0);
                list.get(itemNo-1).setDone(false);
                ui.speak("Task marked as incomplete: " + list.get(itemNo-1).getDescription());
                break;
            case list:
                for (int i = 0; i < list.size(); ++i) {
                    ui.speak((i+1) + "." + list.get(i).toString());
                }
                break;
            case todo:
                desc = (String) parseArgs.get(0);
                list.add(new Task(desc));
                ui.speak("Todo added: " + desc);
                break;
            case deadline:
                desc = (String) parseArgs.get(0);
                list.add(new Deadline(desc, (String) parseArgs.get(1)));
                ui.speak("Deadline added: " + desc);
                break;
            case event:
                desc = (String) parseArgs.get(0);
                list.add(new Event(desc, (String) parseArgs.get(1), (String) parseArgs.get(2)));
                ui.speak("Event added: " + desc);
                break;
            case delete:
                itemNo = (Integer) parseArgs.get(0);
                ui.speak("Removing task: " + list.get(itemNo - 1).getDescription());
                list.remove(itemNo - 1);
                ui.speak("You now have " + list.size() + " tasks left in record.");
                break;
            case formatting:
                ui.invalidFormat((String) parseArgs.get(0));
                break;
            case undefined:
                ui.speak("Sorry, I don't understand.");
                ui.speak("Commands: todo deadline event mark unmark delete list bye");
            }
            Storage.save(data, list);
        }
    }
}
