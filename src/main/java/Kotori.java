import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Kotori {
    public static void main(String[] args) {
        printGreeting();
        File file = makeFile("data","Kotori.txt");
        ArrayList<Task> list = readFile(file);
        Scanner s = new Scanner(System.in);
        while (s.hasNext()){
            String input = s.nextLine();
            if (input.equals("bye")) {
                printExit();
                break;
            } else if (input.equals("list")) {
                printList(list);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0 ) {
                   printMessage("Sorry~ can not mark that task because it does not exist");
                } else {
                    Task task = list.get(index - 1);
                    try {
                        task.mark();
                        printMessage(String.format("Nice Job, Job %s has been marked as done!\n    %s",index,task));
                        updateFile(file, list);
                    } catch (IncorrectStateException e) {
                        printMessage(e.getMessage());
                    }
                }

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0 ) {
                    printMessage("Sorry~ can not unmark that task because it does not exist");
                } else {
                    Task task = list.get(index - 1);
                    try {
                        task.unmark();
                        printMessage(String.format("Alright, Job %s has been marked as not done!\n    %s",index,task));
                        updateFile(file, list);
                    } catch (IncorrectStateException e) {
                        printMessage(e.getMessage());
                    }
                }
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                if (list.size() < index || index <= 0) {
                    printMessage("Sorry~ Can not delete this task as such task does not exist.");
                } else {
                    Task task = list.remove(index - 1);
                    printMessages(new String[]{"OK~. I've deleted this task:",task.toString(),
                            String.format("Now you have %s tasks in the list",list.size())});
                    updateFile(file, list);
                }

            } else {
                try {
                    Task task = Task.of(input);
                    list.add(task);
                    printMessages(new String[]{"Got it. I've added this task:",task.toString(),
                    String.format("Now you have %s tasks in the list",list.size())});
                    updateFile(file, list);
                } catch (MissingInformationException e) {
                    printMessage(e.getMessage());
                } catch (InvalidInputException e) {
                    printMessage(e.getMessage());
                }
            }
        }

        s.close();

    }

    private static void updateFile(File file, List<Task> list) {
        try {
            FileWriter writer = new FileWriter(file);
            String content = "";
            for (Task task : list) {
                content += task.getStorageMessage() + "\n";
            }
            writer.write(content);
            writer.close();
        } catch (FileNotFoundException e) {
            printMessages("Sorry~ I can not find the storage file", "Please ensure there is a file with path" +
                    "data/Kotori.txt");
        } catch (IOException e) {
            printMessage(String.format("There is something wrong about: %s", e.getMessage()));
        }


    }

    private static File makeFile(String directoryName, String fileName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(directory,fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Error("A fatal error has occurs in creating the file");
            }
        }
        return file;
    }

    private static ArrayList<Task> readFile(File file) {
        try {
            ArrayList<Task> result = new ArrayList<>();
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String input = s.nextLine();
                String[] elements = input.split(" \\| ");
                if (elements.length < 1) {
                    throw new CorruptedFileException("");
                } else {
                    result.add(Task.read(elements));
                }
            }
            return result;
        } catch (FileNotFoundException e) {
            printMessage("There is no existing memory so I create a new one~ ^_^");
            return new ArrayList<>();
        } catch (CorruptedFileException e) {
            printMessage("The memory file is corrupted so I create a new one~ ^_^");
            return new ArrayList<>();
        }

    }

    private static void printLine() {
        System.out.println("    ___________________________________________");
    }

    private static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    private static void printMessages(String... inputs) {
        printLine();
        for (String s : inputs) {
            System.out.println("    " + s);
        }
        printLine();
    }



    private static void printList(List<? extends Object> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i).toString()));
        }
        printLine();
    }

    private static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    private static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}


