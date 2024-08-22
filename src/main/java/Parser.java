public class Parser {
    public static boolean checkCommand(String input, TaskList taskList) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("""
                    -----------------------------------------
                    Meep: Bye! Have a great day!
                    -----------------------------------------
                    """);
            return true;
        } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsDone(index);
            System.out.println("""
                    -----------------------------------------
                    Meep: Nice! I've marked this task as done:
                    """ + taskList.getTask(index) + """
                    -----------------------------------------
                    """);
        } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            taskList.markAsUndone(index);
            System.out.println("""
                    -----------------------------------------
                    Meep: OK, I've marked this task as not done yet:
                    """ + taskList.getTask(index) + """
                    -----------------------------------------
                    """);
        } else if (input.equalsIgnoreCase("list")) {
            System.out.println("""
                    -----------------------------------------
                    Meep: Here are the tasks in your list:
                    """ + taskList.getList() + """
                    -----------------------------------------
                    """);
        } else {
            System.out.println("-----------------------------------------\n" +
                    "Meep: " +
                    "added: " + input + "\n" +
                    "-----------------------------------------");
            taskList.addItem(input);
        }
        return false;
    }
}
