public class Parse {
    private static void parseTask(String input) throws TinaException {
        try {
            if (input.startsWith("todo")) {
                String des = input.substring(5);
                TaskList.addTask(new Todo(des));
            }
            else if (input.startsWith("deadline")) {
                int byIdx = input.indexOf("/by");
                String des = input.substring(9, byIdx - 1);
                String end = input.substring(byIdx + 4);
                TaskList.addTask(new Deadline(des, end));
            }
            else if (input.startsWith("event")) {
                int fromIdx = input.indexOf("/from");
                int toIdx = input.indexOf("/to");
                String des = input.substring(6, fromIdx - 1);
                String start = input.substring(fromIdx + 6, toIdx - 1);
                String end = input.substring(toIdx + 4);
                TaskList.addTask(new Event(des, start, end));
            } else {
                throw new TinaException("I have no idea what that means");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new TinaException("Where is your description?");
        }
    }
    public static void parseInput(String input) throws TinaException {
        try {
            if (input.equals("list")) {
                TaskList.listTask();
            } else if (input.startsWith("mark")) {
                int idx = Integer.parseInt(input.substring(5));
                TaskList.markTask(idx);
            } else if (input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7));
                TaskList.unmarkTask(idx);
            } else if (input.startsWith("delete")) {
                int idx = Integer.parseInt(input.substring(7));
                TaskList.deleteTask(idx);
            } else {
                parseTask(input);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new TinaException("What is the index?");
        } catch (NumberFormatException e) {
            throw new TinaException("Enter the index after the space.");
        }
    }
}
