import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Miku {
    public static String regexMark = "mark \\d+";
    public static String regexUnmark = "unmark \\d+";
    public static String regexToDo = "todo .*";
    public static String regexDeadline = "deadline .* /by .*";
    public static String regexEvent = "event .* /.* /.*";
    public static String regexRemove = "delete \\d+";
    public static ArrayList<Task> itemList = new ArrayList<>();
    public static ArrayList<String> validCommands = new ArrayList<>(Arrays.asList("list", "deadline", "todo", "event", "delete", "bye", "mark", "unmark"));
    public static String sectionBreak = "\n_______________________________________________";

    public static void main(String[] args) {
        String logo = " __  __   __   _   _   _    _\n"
                + "|  \\/  | |  | | | / / | |  | |\n"
                + "| |\\/| | |  | | |/ /  | |  | |\n"
                + "| |  | | |  | | |\\ \\  | |__| |\n"
                + "|_|  |_| |__| |_| \\_\\  \\____/";

        System.out.println("Hello from\n" + logo);

        String mikuFace = "⠀⠀⠀⠀⠀⠀⢀⡤⣢⠟⢁⣴⣾⡿⠋⢉⠱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠑⠒⠦⢄⣀⣴⠟⢡⣠⣼⣿⡿⢳⣄⡀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢀⣾⡿⠃⣠⣿⣿⠿⠂⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢲⡿⠋⢰⣾⣿⣿⡟⠀⠀⠈⠙⢆⠀\n"
                + "⠀⠀⠀⠀⠀⡜⠻⣷⣾⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣁⣰⢸⣿⢻⠟⢀⠀⠀⠀⠀⠀⠁\n"
                + "⠀⠀⠀⠀⠰⠀⠀⢙⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣯⡀⠀⢃⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢠⠎⠀⠀⠀⠀⠀⠀⣼⠀⢀⠀⠀⠀⠀⠀⢠⣷⡀⠀⠀⠀⠀⡀⠄⠀⠀⠀⠀⢻⣿⣿⣿⣧⠑⠀⣢⡄⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⡰⠃⢀⠄⠀⠀⠀⠀⣼⡿⡆⢸⠀⠀⠀⠀⠀⠈⣿⢷⡄⠀⠀⠀⠱⡀⠰⡀⠀⠀⠈⢿⣿⣿⣿⣧⠀⢸⣧⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠡⢢⠋⠀⠀⠀⠀⣼⡟⠀⣇⢸⡆⠀⠀⠀⡄⠀⢿⠀⢳⡄⠀⠀⠀⢳⠀⢳⠀⠀⠀⠈⣿⣿⣿⣿⣷⣘⡟⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⢀⠇⠀⠀⠀⠀⣸⡿⢤⠤⠸⡸⣷⠀⠀⠀⢱⠀⣾⡤⠤⢿⡤⢀⡀⠀⢧⠘⡆⠀⠀⠀⢸⡟⠻⢿⠟⣿⣷⡄⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⢰⡿⢠⠇⠀⠀⢳⣿⢇⠀⠀⠈⡇⣿⡇⠀⠀⠻⣄⠀⠀⠘⡆⡇⠀⠀⠀⠀⣇⢀⡏⠀⣿⡿⣄⠀⠀⠀\n"
                + "⠀⠀⠀⠀⢰⠁⠀⠀⠀⠀⣿⠁⣄⣀⣀⡀⠈⢿⡜⡄⠀⠀⢹⣿⡇⠐⢄⣀⠘⢧⡀⠀⠹⣿⠀⠀⠀⠀⢸⣿⣷⣶⣿⡇⢹⡇⠀⠀\n"
                + "⠀⠀⠀⠀⠾⠀⠀⠀⠀⢸⣧⣾⠟⢉⣽⣿⣦⠈⢷⡘⣆⠀⠸⡟⣷⣶⠟⠛⢻⣷⣦⣀⠀⢻⠀⠀⠀⠀⢸⣏⣩⣼⣿⡇⠈⣷⠀⠀\n"
                + "⠀⠀⠀⠃⠀⠀⠀⠀⠀⣿⡿⠁⠀⣠⣾⣿⣿⠀⠈⢿⠺⡆⠀⣧⢸⠀⠀⢀⣹⣿⣿⣿⣷⣼⣤⠀⠀⠀⢸⣿⣿⣿⣿⠀⠀⣿⠀⠀\n"
                + "⠀⠀⣠⠄⣀⠀⠀⠀⢠⣿⡇⠀⠀⢻⢻⣟⢿⠀⠀⠈⠣⠈⠓⠾⠀⠀⠀⣿⣿⢿⣿⣿⠘⡇⡞⠀⠀⢠⣾⣿⣿⣿⡏⠀⠀⢹⠀⠀\n"
                + "⠀⠀⠛⠀⣿⠀⠀⠀⢸⣿⣿⡀⠀⠈⠃⠐⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣄⣐⣠⠏⢠⣿⠁⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⢸⠀⠀\n"
                + "⠀⠀⠀⠀⢹⡆⠰⡀⢸⡟⠩⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⢸⣿⣿⣿⠟⠀⠀⠀⠘⠀⠀\n"
                + "⠀⠀⠀⠀⢎⣿⡀⢱⢞⣁⣀⡿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⡏⡼⠀⠀⠀⣾⣿⠋⠁⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠈⠿⠻⡇⠀⠀⠒⠢⢵⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣽⠁⠀⠀⢠⡿⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⡀⠀⠀⠀⠀⠀⠀⡟⣦⡀⠀⠀⠀⠈⠓⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⢿⡇⠀⠀⡄⣸⣇⣼⣀⣀⣀⠀⠀⠀⠀⠀⠀\n"
                + "⡀⠀⠀⠀⠀⠀⢰⠇⣿⢸⣦⡀⠀⠀⠀⠀⠈⠲⣄⡀⠀⠀⠀⠀⠀⣀⡤⠒⢉⡴⠃⣸⠀⠀⢶⣿⣿⡿⠃⡤⠊⠁⠉⠑⢄⠀⠀⠀\n"
                + "⡇⠀⠀⠀⠀⠀⢸⠀⣿⣾⣿⢿⠲⣄⠀⠀⠀⠀⠘⠟⣦⣤⣴⡒⠉⢀⡠⠖⠉⠀⣠⠃⠀⣠⣿⣿⡿⠁⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢸⠀⣿⠛⢿⠈⢢⠏⠀⠀⠀⠀⠀⣰⣏⣀⣿⠗⠊⠁⠀⠀⣠⣾⠃⢀⡴⠿⠛⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢸⢀⠇⠀⠈⢠⠃⠀⠀⠀⠀⠀⢰⠟⠁⠀⢹⢇⠀⣀⠴⠊⡱⠥⠔⠋⠀⠀⢠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢸⡟⠀⢀⡴⠁⠀⠀⠀⠀⠀⢠⡟⠀⠀⣰⢿⡘⣾⡅⠀⠀⠀⠀⢀⠄⠀⢠⠏⢀⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⢸⠀⣰⣿⠀⠀⠀⠀⠀⠀⢠⣿⠃⢀⡾⡇⠘⠻⡿⢷⡀⠀⠀⠒⠁⠀⢠⠏⢀⠏⣸⠃⢻⠏⠀⠀⠀⠀⠀⠀⠀⠀\n"
                + "⠀⠀⠀⠀⠀⠀⣧⣾⣹⣿⠀⠀⠀⠀⠀⢠⠏⢉⠀⡞⣰⡇⠀⣴⣥⠞⢷⠀⠀⠀⠀⣠⠎⠀⠸⣶⠋⣠⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀";


        //System.out.println(mikuFace);

        System.out.println(sectionBreak);

        Miku.greet();

        Miku.init();

        //Variables
        Scanner scanner = new Scanner(System.in);
        String input = "";
        //Variables End

        while (!input.equals("bye")) {
            //Check if the input is a String

            input = scanner.nextLine();
            System.out.println(sectionBreak);
            try {
                String command = input.split(" ")[0];
                if (!validCommands.contains(command)) {
                    throw new InvalidCommandException(command);
                }
            } catch (InvalidCommandException e) {
                e.print();
                System.out.println(sectionBreak);
            }

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                Miku.printList();
            } else if (input.matches(regexMark)) {
                Miku.mark(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexUnmark)) {
                Miku.unmark(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexToDo)) {
                Miku.addItem(new Todo(input.substring(5)));
            } else if (input.matches(regexDeadline)) {
                String[] strs = input.split("/");
                Miku.addItem(new Deadline(strs[0].substring(9), strs[1].substring(3)));
            } else if (input.matches(regexEvent)) {
                String[] strs = input.split("/");
                Miku.addItem(new Event(strs[0].substring(6), strs[1].substring(5), strs[2].substring(3)));
            } else if (input.matches(regexRemove)) {
                //Check if removable
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index < 1 || index > itemList.size()) {
                        throw new RemoveNullException(String.valueOf(index));
                    }
                    Miku.deleteItem(index);
                } catch (RemoveNullException e) {
                    e.print();
                    System.out.println(sectionBreak);
                }

            } else {
                System.out.println("すみません、わかりません！\nEnter a valid command please, 39!");
            }

            saveToFile();
        }

        Miku.farewell();


    }

    public static void pathCheck(){
        System.out.println(System.getProperty("user.dir"));
    }

    public static void init(){
        File file = new File("src/main/resources/data.txt");
//        System.out.println("Does the file exist: " + file.exists());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("File cannot be created: " + e.getMessage());
            }
        } else {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()){
                    String[] dataLine = sc.nextLine().split(" \\| ");
                    try {
                        if (dataLine[0].equals("D")){
                            itemList.add(new Deadline(dataLine[2], dataLine[3], Boolean.parseBoolean(dataLine[1])));
                        } else if (dataLine[0].equals("T")) {
                            itemList.add(new Todo(dataLine[2]));
                        } else if (dataLine[0].equals("E")) {
                            itemList.add(new Event(dataLine[2], dataLine[3], dataLine[4], Boolean.parseBoolean(dataLine[1])));
                        } else {
                            throw new DataCorruptionException("The file is corrupted, the file would be deleted...\nA empty tasklist will be created.");
                        }
                    } catch (DataCorruptionException e){
                        System.out.println(e.getMessage());
                        file.delete();
                        itemList.clear();
                    }
                }
            } catch (FileNotFoundException e){
                System.out.println(e.getMessage());
            }

        }
    }

    public static void deleteItem(int num) {
        System.out.println("はい、わかりました\nI have removed the following task: " + itemList.get(num - 1).stringValue());
        itemList.remove(num - 1);
        System.out.println("いまは " + itemList.size() + " tasks in the list");
        System.out.println(sectionBreak);
    }

    public static void addItem(Task task) {
        itemList.add(task);
        System.out.println("Got it . I've added this task:\n" + task.stringValue());
        System.out.println("いまは " + itemList.size() + " tasks in the list");
        System.out.println(sectionBreak);
    }

    public static void saveToFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data.txt", false));
            for (Task item: itemList){
                //System.out.println(item.stringValue());
                writer.write(item.storeValue());

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File Access Error:" + e.getMessage());
        }
    }

    public static void printList() {
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println(String.valueOf(i + 1) + ". " + itemList.get(i).stringValue());
        }
        System.out.println(sectionBreak);
    }

    public static void mark(int num) {
        itemList.get(num - 1).markDone();
    }

    public static void unmark(int num) {
        itemList.get(num - 1).markUndone();
    }

    public static void greet() {
        String greeting = "Hello! I'm Miku\n"
                + "What song do you want to listen to today?";
        System.out.println(greeting);
        System.out.println(sectionBreak);
    }

    public static void farewell() {

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
        System.out.println(sectionBreak);
    }

    public static void echo(String input) {
        System.out.println(input);
        System.out.println(sectionBreak);
    }

}
