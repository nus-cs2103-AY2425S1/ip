import java.time.LocalDate;
import java.util.StringTokenizer;

public class Parser {

    private Task taskResult;
    private int intResult;

    public Task parseTxt(String command) {
        StringTokenizer st = new StringTokenizer(command);
        String firstWord = st.nextToken();
        String status = st.nextToken();
        boolean isDone = status.equals("(DONE)");
        if (firstWord.equals("TODO")) {
            String desc = st.nextToken();
            while (st.hasMoreTokens()) {
                desc = desc + " " + st.nextToken();
            }
            taskResult = new Todo(desc);
        } else if (firstWord.equals("DEADLINE")) {
            String desc = st.nextToken();
            String by = "";
            boolean descDone = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/by")) {
                    descDone = true;
                    by = st.nextToken();
                    continue;
                }
                if (descDone) {
                    by = by + " " + nextWord;
                } else {
                    desc = desc + " " + nextWord;
                }
            }
            taskResult = new Deadline(desc, LocalDate.parse(by));
        } else if (firstWord.equals("EVENT")) {
            String desc = st.nextToken();
            String from = "";
            String to = "";
            boolean descDone = false;
            boolean fromDone = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/from")) {
                    descDone = true;
                    from = st.nextToken();
                    continue;
                }
                if (nextWord.equals("/to")) {
                    fromDone = true;
                    to = st.nextToken();
                    continue;
                }
                if (fromDone) {
                    to = to + " " + nextWord;
                } else if (descDone) {
                    from = from + " " + nextWord;
                } else {
                    desc = desc + " " + nextWord;
                }
            }
            taskResult = new Event(desc, from, to);
        }
        if (isDone) {
            taskResult.complete();
        }
        return taskResult;
    }

    public String parseInput(String command) {
        StringTokenizer st = new StringTokenizer(command);
        String firstWord = st.nextToken();
        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return "list";
        } else if (firstWord.equals("todo")) {
            String desc = st.nextToken();
            while (st.hasMoreTokens()) {
                desc = desc + " " + st.nextToken();
            }
            taskResult = new Todo(desc);
            return "task";
        } else if (firstWord.equals("deadline")) {
            String desc = st.nextToken();
            String by = "";
            boolean descDone = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/by")) {
                    descDone = true;
                    by = st.nextToken();
                    continue;
                }
                if (descDone) {
                    by = by + " " + nextWord;
                } else {
                    desc = desc + " " + nextWord;
                }
            }
            taskResult = new Deadline(desc, LocalDate.parse(by));
            return "task";
        } else if (firstWord.equals("event")) {
            String desc = st.nextToken();
            String from = "";
            String to = "";
            boolean descDone = false;
            boolean fromDone = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/from")) {
                    descDone = true;
                    from = st.nextToken();
                    continue;
                }
                if (nextWord.equals("/to")) {
                    fromDone = true;
                    to = st.nextToken();
                    continue;
                }
                if (fromDone) {
                    to = to + " " + nextWord;
                } else if (descDone) {
                    from = from + " " + nextWord;
                } else {
                    desc = desc + " " + nextWord;
                }
            }
            taskResult = new Event(desc, from, to);
            return "task";
        } else if (firstWord.equals("mark")) {
            intResult = Integer.parseInt(st.nextToken());
            return "mark";
        } else if (firstWord.equals("unmark")) {
            intResult = Integer.parseInt(st.nextToken());
            return "unmark";
        } else if (firstWord.equals("delete")) {
            intResult = Integer.parseInt(st.nextToken());
            return "delete";
        } else {
            return "unknown input";
        }
    }

    public Task getTask() {
        return this.taskResult;
    }

    public int getIndex() {
        return this.intResult;
    }

}
