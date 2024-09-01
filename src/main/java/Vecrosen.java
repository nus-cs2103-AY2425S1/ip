import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Vecrosen {

    private enum TaskType {todo, deadline, event};
    private static ArrayList<Task> list;
    private static Ui ui;

    /**
     * Saves the task list to vecrosen.txt.
     */
    private static void save() {
        try {
            File f = new File("data/vecrosen.txt");
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < list.size(); ++i) {
                Task t = list.get(i);
                if (i != 0) {
                    fw.write('\n');
                }
                fw.write(t.getDescription());
                fw.write("\n" + t.isDone());
                if (t.getClass() == Task.class) {
                    fw.write(" " + TaskType.todo.ordinal());
                } else if (t.getClass() == Deadline.class) {
                    fw.write(" " + TaskType.deadline.ordinal() + '\n');
                    Deadline d = (Deadline) t;
                    fw.write(d.getBy());
                } else if (t.getClass() == Event.class) {
                    fw.write(" " + TaskType.event.ordinal() + '\n');
                    Event e = (Event) t;
                    fw.write(e.getBegin() + '\n');
                    fw.write(e.getEnd());
                } else {
                    System.err.println("Unrecognized task type when saving!");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Savefile cannot be accessed!");
        }
    }

    /**
     * Loads the data from the file into the list.
     * @param file
     */
    private static void load(File file) {
        if (!file.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String description = s.nextLine();
                boolean isDone = s.nextBoolean();
                int tth = s.nextInt();
                TaskType tt = TaskType.values()[tth];
                s.nextLine();
                switch (tt) {
                case event:
                    String begin = s.nextLine();
                    String end = s.nextLine();
                    list.add(new Event(description, begin, end));
                    break;
                case deadline:
                    String by = s.nextLine();
                    list.add(new Deadline(description, by));
                    break;
                case todo:
                    list.add(new Task(description));
                    break;
                }
                list.get(list.size() - 1).setDone(isDone);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File exists but cannot be found...?");
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            ui.speak("Savefile is corrupted!");
            System.err.println(list.size());
            list.clear();
        }
    }

    public static void main(String[] args) {
        ui = new Ui(System.in, System.out);
        list = new ArrayList<Task>();
        ui.speak("Hello, I'm Vecrosen.");
        ui.speak("What can I do for you?");
        load(new File("data/vecrosen.txt"));
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
            save();
        }
    }
}
