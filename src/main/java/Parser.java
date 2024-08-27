public class Parser {
    public static Command parse(String input) throws WaterfallException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ShowTasksCommand();
        } else if (input.startsWith("mark ") && (input.substring(5).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(index);
        } else if (input.startsWith("unmark ") && (input.substring(7).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new UnmarkCommand(index);
        } else if (input.startsWith("delete ") && (input.substring(7).matches("\\d+"))) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(index);
        } else if (input.startsWith("todo ")) {
            String title = input.substring(5);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            return new AddCommand(title);
        } else if (input.startsWith("deadline ")) {
            if (!input.contains("/")) {
                throw new WaterfallException("oh man where's your deadline!");
            }
            int index = input.indexOf("/");
            String title = input.substring(9, index - 1);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String description = input.substring(index + 1);
            if (!description.startsWith("by ")) {
                throw new WaterfallException("unrecognised comment: " + description);
            }
            return new AddCommand(title, description.substring(3));
        } else if (input.startsWith("event ")) {
            String[] inputs = input.split(" /");
            if (inputs.length != 3) {
                throw new WaterfallException("invalid event format: An event must contain only from and to comments");
            }
            String title = inputs[0].substring(6);
            if (title.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty title are you kidding me!");
            }
            String from;
            String to;
            if (inputs[1].startsWith("from ")) {
                from = inputs[1].substring(5);
                if (inputs[2].startsWith("to ")) {
                    to = inputs[2].substring(3);
                } else {
                    throw new WaterfallException("invalid format: missing to comment");
                }
            } else if (inputs[1].startsWith("to")) {
                to = inputs[1].substring(3);
                if (inputs[2].startsWith("from ")) {
                    from = inputs[2].substring(5);
                } else {
                    throw new WaterfallException("invalid format: missing from comment");
                }
            } else {
                throw new WaterfallException("invalid format: missing to and from comment");
            }
            if (to.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty to command are you kidding me!");
            }
            if (from.isEmpty()) {
                throw new WaterfallException("Bruh what is this empty from command are you kidding me!");
            }
            return new AddCommand(title, from, to);
        } else {
            return new UnrecognisedCommand();
        }
    }
}
