public class Parser {
    public static boolean checkCommand(String input, TaskList taskList) {
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("""
                    -----------------------------------------
                    Meep: Bye! Have a great day!
                    -----------------------------------------
                    """);
            return true;
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
