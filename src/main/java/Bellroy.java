import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bellroy {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String message = "____________________________________________________________\n" +
                " Hello! I'm Bellroy\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String userInput = "";
        List<Task> toDoList = new ArrayList<>();
        File f = new File("Bellroy.txt");
        try (Scanner fileScanner = new Scanner(f)) {
            BufferedReader reader = new BufferedReader(new FileReader("Bellroy.txt"));
            String line = reader.readLine();
            while (line != null) {
                char taskType = line.charAt(1);
                boolean isDone = line.charAt(4) == 'X';
                String remainder = line.substring(7).trim();

                switch (taskType) {
                    case('T'):
                        Task tempToDo = new Todo(remainder);
                        if (isDone) {
                            tempToDo.markDone();
                        }
                        toDoList.add(tempToDo);
                        break;
                    case('D'):
                        String[] deadlineParts = remainder.split(" \\(by: ");
                        String deadlineDescription = deadlineParts[0].trim();
                        String by = deadlineParts[1].replace(")", "").trim();
                        Task temp = new deadline(deadlineDescription, by);
                        if (isDone) {
                            temp.markDone();
                        }
                        toDoList.add(temp);
                        break;
                    case ('E'):
                        String[] eventParts = remainder.split(" \\(from: | to: ");
                        String eventDescription = eventParts[0].trim();
                        String from = eventParts[1].trim();
                        String to = eventParts[2].replace(")", "").trim();
                        Task tempEvent = new Event(eventDescription, from, to);
                        if (isDone) {
                            tempEvent.markDone();
                        }
                        toDoList.add(tempEvent);
                        break;
                }

                line = reader.readLine();

            }
        } catch (FileNotFoundException e) {
            try {
                File newFile = new File("Bellroy.txt");
                if (newFile.createNewFile()) {
                    System.out.println("Bellroy.txt file created");
                };

            } catch (IOException IOexception2) {
                System.out.println(IOexception2.getMessage());
            }
        } catch (IOException IOexception1) {
            System.out.println(IOexception1.getMessage());
        };

        System.out.println(message);

        while (true) {

            userInput = scanner.nextLine();
            String[] input = userInput.split(" /", 2);
            String type = input[0].split(" ")[0].toLowerCase();
            String description = input[0].substring(type.length());

            switch (type) {
                case "bye":
                    System.out.println("____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                    try (FileWriter writer = new FileWriter("Bellroy.txt")) {
                        for (Task t: toDoList) {
                            writer.write(t.toString());
                            writer.write("\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    };
                    scanner.close();
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    for(int i = 0; i < toDoList.size(); i++) {
                        System.out.println("     " + (i + 1) + ". " + toDoList.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                    break;

                case "mark":
                    try {
                        int position = Integer.parseInt(userInput.split(" ")[1]);
                        toDoList.get(position - 1).markDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done:\n" +
                                "     " + toDoList.get(position - 1).toString() + "\n" +
                                "    ____________________________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please specify which task to mark!!");
                    }
                    break;

                case "unmark":
                    try {
                        int pos = Integer.parseInt(userInput.split(" ")[1]);
                        toDoList.get(pos - 1).undo();
                        System.out.println("    ____________________________________________________________\n" +
                                "     OK, I've marked this task as not done yet:\n" +
                                "     " + toDoList.get(pos - 1).toString() + "\n" +
                                "    ____________________________________________________________");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please specify which task to unmark!!");
                    }
                    break;

                case "todo":
                    Task todo = new Todo(description);
                    toDoList.add(todo);
                    String response1 = String.format("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + todo + "\n" +
                            "     Now you have %d tasks in the list.\n" +
                            "    ____________________________________________________________", toDoList.size());
                    System.out.println(response1);
                    break;

                case "deadline":
                    try {
                        String dueDate = input[1].split(" ", 2)[1].trim();
                        Task deadline = new deadline(description, dueDate);
                        toDoList.add(deadline);
                        String response2 = String.format("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n" +
                                "       " + deadline + "\n" +
                                "     Now you have %d tasks in the list.\n" +
                                "    ____________________________________________________________", toDoList.size());
                        System.out.println(response2);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please specify a deadline!!");
                    }
                    break;

                case "event":
                    try {
                        String startTime = input[1].split(" /", 2)[0].split(" ", 2)[1].trim();
                        String endTime = input[1].split(" /", 2)[1].split(" ", 2)[1].trim();
                        Task event = new Event(description, startTime, endTime);
                        toDoList.add(event);
                        String response3 = String.format("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n" +
                                "       " + event + "\n" +
                                "     Now you have %d tasks in the list.\n" +
                                "    ____________________________________________________________", toDoList.size());
                        System.out.println(response3);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please make sure that you provide a /from and /to time");
                    }
                    break;

                case "delete":
                    int target = Integer.parseInt(userInput.split(" ")[1]);
                    Task temp = toDoList.get(target - 1);
                    toDoList.remove(target - 1);
                    String response3 = String.format("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + temp + "\n" +
                            "     Now you have %d tasks in the list.\n" +
                            "    ____________________________________________________________", toDoList.size());
                    System.out.println(response3);
            }
        }

    }
}
