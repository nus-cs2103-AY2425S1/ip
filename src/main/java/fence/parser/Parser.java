package fence.parser;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import fence.task.Deadline;
import fence.task.Event;
import fence.task.Task;
import fence.task.Todo;

public class Parser {

    private Task taskResult;
    private int intResult;

    public Task parseTxt(String command) {
        StringTokenizer st = new StringTokenizer(command);
        String firstWord = st.nextToken();
        String status = st.nextToken();
        boolean isDone = status.equals("(DONE)");
        if (firstWord.equals("TODO")) {
            String description = st.nextToken();
            while (st.hasMoreTokens()) {
                description = description + " " + st.nextToken();
            }
            taskResult = new Todo(description);
        } else if (firstWord.equals("DEADLINE")) {
            String description = st.nextToken();
            String by = "";
            boolean hasCompleteDescription = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/by")) {
                    hasCompleteDescription = true;
                    by = st.nextToken();
                    continue;
                }
                if (hasCompleteDescription) {
                    by = by + " " + nextWord;
                } else {
                    description = description + " " + nextWord;
                }
            }
            taskResult = new Deadline(description, LocalDate.parse(by));
        } else if (firstWord.equals("EVENT")) {
            String description = st.nextToken();
            String from = "";
            String to = "";
            boolean hasCompleteDescription = false;
            boolean hasCompleteFrom = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/from")) {
                    hasCompleteDescription = true;
                    from = st.nextToken();
                    continue;
                }
                if (nextWord.equals("/to")) {
                    hasCompleteFrom = true;
                    to = st.nextToken();
                    continue;
                }
                if (hasCompleteFrom) {
                    to = to + " " + nextWord;
                } else if (hasCompleteDescription) {
                    from = from + " " + nextWord;
                } else {
                    description = description + " " + nextWord;
                }
            }
            taskResult = new Event(description, from, to);
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
            String description = st.nextToken();
            while (st.hasMoreTokens()) {
                description = description + " " + st.nextToken();
            }
            taskResult = new Todo(description);
            return "task";
        } else if (firstWord.equals("deadline")) {
            String description = st.nextToken();
            if (description.equals("/by")) {
                throw new NoSuchElementException();
            }
            String by = "";
            boolean hasCompleteDescription = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/by")) {
                    hasCompleteDescription = true;
                    by = st.nextToken();
                    continue;
                }
                if (hasCompleteDescription) {
                    by = by + " " + nextWord;
                } else {
                    description = description + " " + nextWord;
                }
            }
            if (!hasCompleteDescription) {
                throw new NoSuchElementException();
            }
            taskResult = new Deadline(description, LocalDate.parse(by));
            return "task";
        } else if (firstWord.equals("event")) {
            String description = st.nextToken();
            if (description.equals("/to") || description.equals("/from")) {
                throw new NoSuchElementException();
            }
            String from = "";
            String to = "";
            boolean hasCompleteDescription = false;
            boolean hasCompleteFrom = false;
            while (st.hasMoreTokens()) {
                String nextWord = st.nextToken();
                if (nextWord.equals("/from")) {
                    hasCompleteDescription = true;
                    from = st.nextToken();
                    continue;
                }
                if (nextWord.equals("/to")) {
                    if (!hasCompleteDescription) {
                        throw new NoSuchElementException();
                    }
                    hasCompleteFrom = true;
                    to = st.nextToken();
                    continue;
                }
                if (hasCompleteFrom) {
                    to = to + " " + nextWord;
                } else if (hasCompleteDescription) {
                    from = from + " " + nextWord;
                } else {
                    description = description + " " + nextWord;
                }
            }
            if (!(hasCompleteDescription && hasCompleteFrom)) {
                throw new NoSuchElementException();
            }
            taskResult = new Event(description, from, to);
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
            return "unknown command";
        }
    }

    public Task getTask() {
        return this.taskResult;
    }

    public int getIndex() {
        return this.intResult;
    }

}
