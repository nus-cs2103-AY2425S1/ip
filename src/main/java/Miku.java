import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
//    public static ArrayList<Task> itemList = new ArrayList<>();
    public static ArrayList<String> validCommands = new ArrayList<>(Arrays.asList("list", "deadline", "todo", "event", "delete", "bye", "mark", "unmark"));
    public static Storage storage = new Storage();
    public static TaskList taskList = new TaskList();
    public static UI ui = new UI();

    public static void main(String[] args) {

        ui.printLogo();

        ui.printSectionBreak();

        ui.greet();


        storage.init(taskList);

        //Variables
        Scanner scanner = new Scanner(System.in);
        String input = "";
        //Variables End

        while (!input.equals("bye")) {
            //Check if the input is a String

            input = scanner.nextLine();
            ui.printSectionBreak();
            try {
                String command = input.split(" ")[0];
                if (!validCommands.contains(command)) {
                    throw new InvalidCommandException(command);
                }
            } catch (InvalidCommandException e) {
                e.print();
                ui.printSectionBreak();
            }

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                taskList.printList();
            } else if (input.matches(regexMark)) {
                taskList.mark(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexUnmark)) {
                taskList.unmark(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexToDo)) {
                taskList.addItem(new Todo(input.substring(5)));
            } else if (input.matches(regexDeadline)) {
                String[] strs = input.split("/");
                taskList.addItem(new Deadline(strs[0].substring(9), strs[1].substring(3)));
            } else if (input.matches(regexEvent)) {
                String[] strs = input.split("/");
                taskList.addItem(new Event(strs[0].substring(6), strs[1].substring(5), strs[2].substring(3)));
            } else if (input.matches(regexRemove)) {
                //Check if removable
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index < 1 || index > taskList.size()) {
                        throw new RemoveNullException(String.valueOf(index));
                    }
                    taskList.deleteItem(index);
                } catch (RemoveNullException e) {
                    e.print();
                    ui.printSectionBreak();
                }

            } else {
                System.out.println("すみません、わかりません！\nEnter a valid command please, 39!");
            }
//            saveToFile();
            storage.saveFromTaskList(taskList);
//            storage.init(taskList);
        }

        ui.farewell();


    }

    public static void pathCheck(){
        System.out.println(System.getProperty("user.dir"));
    }
//
//    public static void init(){
//        File file = new File("src/main/resources/data.txt");
//        File dir = new File("src/main/resources");
////        System.out.println("Does the file exist: " + file.exists());
//        if (!dir.exists()){
//            boolean dirCreated = dir.mkdirs();
//            if (dirCreated){
//                System.out.println("Directory created\n");
//            } else {
//                System.out.println("Directory not created\n");
//            }
//        }
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e){
//                System.out.println("File cannot be created: " + e.getMessage());
//            }
//        } else {
//            try {
//                Scanner sc = new Scanner(file);
//                while (sc.hasNext()){
//                    String[] dataLine = sc.nextLine().split(" \\| ");
//                    try {
//                        if (dataLine[0].equals("D")){
//                            taskList.addItem(new Deadline(dataLine[2], dataLine[3], Boolean.parseBoolean(dataLine[1])));
//                        } else if (dataLine[0].equals("T")) {
//                            taskList.addItem(new Todo(dataLine[2]));
//                        } else if (dataLine[0].equals("E")) {
//                            taskList.addItem(new Event(dataLine[2], dataLine[3], dataLine[4], Boolean.parseBoolean(dataLine[1])));
//                        } else {
//                            throw new DataCorruptionException("The file is corrupted, the file would be deleted...\nA empty tasklist will be created.");
//                        }
//                    } catch (DataCorruptionException e){
//                        System.out.println(e.getMessage());
//                        file.delete();
//                        taskList.clear();
//                    }
//                }
//            } catch (FileNotFoundException e){
//                System.out.println(e.getMessage());
//            }
//
//        }
//    }

//    public static void deleteItem(int num) {
//        System.out.println("はい、わかりました\nI have removed the following task: " + itemList.get(num - 1).stringValue());
//        itemList.remove(num - 1);
//        System.out.println("いまは " + itemList.size() + " tasks in the list");
//        System.out.println(sectionBreak);
//    }
//
//    public static void addItem(Task task) {
//        itemList.add(task);
//        System.out.println("Got it . I've added this task:\n" + task.stringValue());
//        System.out.println("いまは " + itemList.size() + " tasks in the list");
//        System.out.println(sectionBreak);
//    }

//    public static void saveToFile(){
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data.txt", false));
//            for (Task item: itemList){
//                //System.out.println(item.stringValue());
//                writer.write(item.storeValue());
//
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("File Access Error:" + e.getMessage());
//        }
//    }

//    public static void printList() {
//        for (int i = 0; i < itemList.size(); i++) {
//            System.out.println(String.valueOf(i + 1) + ". " + itemList.get(i).stringValue());
//        }
//        System.out.println(sectionBreak);
//    }

//    public static void mark(int num) {
//        itemList.get(num - 1).markDone();
//    }

//    public static void unmark(int num) {
//        itemList.get(num - 1).markUndone();
//    }


}
