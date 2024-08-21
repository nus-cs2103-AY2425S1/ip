package main.java;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CasperBot {
    private enum Command {
        EVENT, TODO, DEADLINE;
    }
    private static List<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm CasperBot.\n" +
                "What can I do for you?");
        line();
        echo();
    }

    private static void line() {
        System.out.println("------------------------------------------");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            String[] inputArray = splitInputIntoTwo(input);
            line();
            if (inputArray[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, list.get(i));
                }
            }
            else if (inputArray[0].equalsIgnoreCase("mark")) {
                Task task = list.get(Integer.parseInt(inputArray[1])-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            }
            else if (inputArray[0].equalsIgnoreCase("unmark")) {
                Task task = list.get(Integer.parseInt(inputArray[1])-1);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            }
            else if (isValidCommand(inputArray[0])) {
                System.out.println("Got it. I've added this task:");
                HashMap<String, String> hashMap = new HashMap<>();
                parseBySlash(inputArray[1], hashMap);
                Command command = Command.valueOf(inputArray[0].trim().toUpperCase());
                switch (command) {
                    case TODO:
                        String todoDescription = hashMap.get("description");
                        ToDo newToDo = new ToDo(todoDescription);
                        list.add(newToDo);
                        System.out.println("  " + newToDo);
                        break;
                    case DEADLINE:
                        String deadlineDescription = hashMap.get("description");
                        String deadline = hashMap.get("by");
                        Deadline newDeadline = new Deadline(deadlineDescription, deadline);
                        list.add(newDeadline);
                        System.out.println("  " + newDeadline);
                        break;
                    case EVENT:
                        String eventDescription = hashMap.get("description");
                        String start = hashMap.get("from");
                        String end = hashMap.get("to");
                        Event newEvent = new Event(eventDescription, start, end);
                        list.add(newEvent);
                        System.out.println("  " + newEvent);
                        break;
                }
                System.out.printf("Now you have %d tasks in the list.\n", list.size());
            }
            else if (inputArray[0].equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon!");
            }
            line();
        }
        scanner.close();
    }

    private static boolean isMatch(String input, String regex) {
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(input);
        String[] parts = input.split(" ");
        return matcher.matches() && Integer.parseInt(parts[1]) <= list.size() && Integer.parseInt(parts[1]) > 0;
    }

    private static String[] splitInputIntoTwo(String input) {
        int firstSpaceIndex = input.indexOf(" ");

        // If there is no space, return the original string as the first element
        if (firstSpaceIndex == -1) {
            return new String[] { input, "" };
        }

        // Split the string into two parts
        String part1 = input.substring(0, firstSpaceIndex).trim();  // Before the first space
        String part2 = input.substring(firstSpaceIndex + 1).trim(); // After the first space

        return new String[] { part1, part2 };
    }

    private static void parseBySlash(String input, HashMap<String, String> hashMap) {
        String[] parsedInput = input.split("/");
        hashMap.put("description", parsedInput[0].trim());
        for (int i = 1; i < parsedInput.length; i++) {
            String[] inputPart = splitInputIntoTwo(parsedInput[i]);
            hashMap.put(inputPart[0].trim(), inputPart[1].trim());
        }
    }

    private static boolean isValidCommand(String command) {
        try {
            Command.valueOf(command.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
