import java.util.Scanner;

public class Parser {
    public static void parse() {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String command;
        String text ="";
        String input;
        do {
            input = scanner.nextLine().toLowerCase();
            String[] parts = input.split("\\s+", 2);
            command = parts[0];
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                text = parts[1];
            }
            switch (command) {
                case "list":
                    if (storage.size()>0) System.out.println(storage);
                    else System.out.println("it's empty... what do u want me to list? :(");
                    break;
                case "bye":
                    System.out.println("It's finally over... *yawn*\nI'm heading to bed\nExiting the program...");
                    break;
                case "mark":
                    int i;
                    try {
                        i = Integer.valueOf(text);
                    } catch (NumberFormatException e) {
                        System.out.println("sorry bud that ain't a number\ni don't know which task u're referring to...");
                        break;
                    }
                    if(0 < i && i <= storage.size()) {
                        storage.mark(i);
                        System.out.println(String.format("marked %s as completed",i));
                    }else {
                        System.out.println("that number isn't a valid task dude...");
                        System.out.println("it has to be from 1 to " + storage.size());
                    }
                    break;
                case "unmark":
                    try {
                        i = Integer.valueOf(text);
                    } catch (NumberFormatException e) {
                        System.out.println("sorry bud that ain't a number\ni don't know which task u're referring to...");
                        break;
                    }
                    if(0 < i && i <= storage.size()) {
                        storage.unmark(i);
                        System.out.println(String.format("marked %s as completed",i));
                    }else {
                        System.out.println("that number isn't a valid task dude...");
                        System.out.println("it has to be from 1 to " + storage.size());
                    }
                    break;
                case "todo":
                    if(!text.isEmpty()){
                        storage.add(text);
                        System.out.println("Added " + '\"'+ text + "\"" + " as a new task I guess");
                        System.out.println(String.format("you have %s task now",storage.size()));
                    } else System.out.println("bruh? type a task to add I'm not adding a blank...");
                break;
//                case "event":
//                    storage.event(text);
//                case "deadline":
//                    storage.deadline(text);
                default:
                    System.out.println("what? type an actual command pls...");
            }
            text ="";
            Duke.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}