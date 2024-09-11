/*public class BuddyBot {
    static FileStorage store = new FileStorage("./data/BuddyBot.txt");
    public static void main(String[] args) {
        //Scanner object
        Scanner myObj = new Scanner(System.in);
        //Opening statement
        System.out.println("__________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");

        TaskList myList;
        try {
            myList = new TaskList(store.readFileContents());
        } catch (BuddyBotException e) {
            myList = new TaskList();
        }

        //taking in user input
        String input = myObj.nextLine();

        for (int i = 0; i < 100; i++) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) { // LIST command
                System.out.println("Here are the tasks in your list:" + "\n");
                read(myList);
                i--;
                input = myObj.nextLine();
            } else if (input.startsWith("mark")) { //MARK command
                String last = input.substring(input.replaceAll("[0-9]+$","").length());
                try { // Non-integer exception
                    int num = Integer.parseInt(last);
                    if (num > myList.size() || num <= 0) {
                        throw new BuddyBotException("Invalid input!");
                    }
                    myList.get(num - 1).status = BuddyBot.TaskStatus.DONE;
                    System.out.println("Nice! I've marked this task as done:");
                    read(myList);
                } catch (NumberFormatException | BuddyBotException e) {
                    System.out.println("This is an invalid input!");
                } finally {
                    i--;
                    input = myObj.nextLine();
                }
            } else {
                if (input.startsWith("deadline")) { // DEADLINE case
                    String[] parts = input.substring(8).split("/by");
                    String description = parts[0].trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        String time = parts[1].trim();
                        try {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date = LocalDate.parse(time, format);
                            Deadline additionD = new Deadline(description, date);
                            myList.add(additionD);
                            System.out.println("Got it. I've added this task: \n" + additionD);
                            System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            input = myObj.nextLine();
                        }
                    }
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split("/from|/to");
                    String description = parts[0].trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        String start = parts[1].trim();
                        String end = parts[2].trim();
                        try {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate startTime = LocalDate.parse(start, format);
                            LocalDate endTime = LocalDate.parse(end, format);
                            Event additionE = new Event(description, startTime, endTime);
                            myList.add(additionE);
                            System.out.println("Got it. I've added this task: \n" + additionE);
                            System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        } catch (DateTimeParseException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            input = myObj.nextLine();
                        }
                    }
                } else if (input.startsWith("todo")) { //NORMAL case (TODOs)
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) { // empty field exception
                        System.out.println("This field cannot be empty");
                        i--;
                        input = myObj.nextLine();
                    } else {
                        Task additionT = new BuddyBot.Todo(description);
                        myList.add(additionT);
                        System.out.println("Got it. I've added this task: \n" + additionT);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                        input = myObj.nextLine();
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        String index = input.substring(6).trim();
                        int num = Integer.parseInt(index);
                        if (num  > myList.size() || num <= 0) {
                            throw new BuddyBotException("Invalid input");
                        }
                        Task removed = myList.get(num - 1);
                        delete(myList, num);
                        System.out.println("Got it. I've removed this task: \n" + removed);
                        System.out.println("Now you have " + count(myList) + " tasks in the list.");
                    } catch (NumberFormatException | BuddyBotException e) { // non-integer input
                        System.out.println("This is an invalid input!");
                    } finally {
                        i--;
                        input = myObj.nextLine();
                    }
                } else {
                    System.out.println("Sorry I don't understand...");
                    i--;
                    input = myObj.nextLine();
                }
            }
        }
        store.writeToTxt(writtenList(myList));
        readList(myList);
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public static void read(TaskList arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == null) {
                break;
            } else {
                System.out.println(i+ 1 + "." + arr.get(i).toString() + "\n");
            }
        }
    }

    public static int count(TaskList arr) {
        return arr.size();
    }

    public static void delete(TaskList arr, int num) {
        arr.delete(num - 1);
    }

    public static String writtenList(TaskList arr) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != null) {
                result.append(arr.get(i).toFile()).append("\n");
            }
        }
        return result.toString();
    }

    public static void readList(TaskList arr) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) != null) {
                result.append(arr.get(i).toString()).append("\n");
            }
        }
        System.out.println(result);
    }

}*/
