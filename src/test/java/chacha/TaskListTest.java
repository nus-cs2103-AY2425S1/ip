//package chacha;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//
//import org.junit.jupiter.api.Test;
//
//import chacha.exception.ChaChaException;
//import chacha.exception.WrongCommandFormatException;
//import chacha.exception.WrongDateFormatException;
//import chacha.exception.WrongTimeFormatException;
//import chacha.parser.DateParser;
//import chacha.parser.TimeParser;
//import chacha.task.TaskList;
//
//public class TaskListTest {
//
//    private TaskList taskList;
//    private UiStub uiStub;
//    private StorageStub storageStub;
//    private DateParserStub dateParserStub;
//    private TimeParserStub timeParserStub;
//
//    public void setUp() {
//        taskList = new TaskList();
//        uiStub = new UiStub();
//        storageStub = new StorageStub();
//        dateParserStub = new DateParserStub();
//        timeParserStub = new TimeParserStub();
//
//        // Inject stubs into the DateParser and TimeParser
//        DateParser.setInstance(dateParserStub);
//        TimeParser.setInstance(timeParserStub);
//    }
//
//    @Test
//    public void testAddToDo() throws ChaChaException {
//        String cmd = "todo read book";
//
//        uiStub.setPrintToDoResponse("ToDo task added: read book");
//
//        Task task = taskList.addToDo(cmd, uiStub, storageStub);
//
//        assertEquals(1, taskList.getTotalNumber());
//        assertEquals("read book", task.getDescription());
//        assertFalse(task.isDone());
//        assertEquals("ToDo task added: read book", storageStub.getWrittenContent());
//    }
//
//    @Test
//    public void testAddEvent() throws ChaChaException, WrongCommandFormatException, WrongTimeFormatException, WrongDateFormatException {
//        String cmd = "event meeting /2024-09-20 /from 09:00 to 10:00";
//
//        // Setup stubs to return specific values
//        dateParserStub.setDate(LocalDate.of(2024, 9, 20));
//        timeParserStub.setStartTime(LocalTime.of(9, 0));
//        timeParserStub.setEndTime(LocalTime.of(10, 0));
//
//        uiStub.setPrintEventResponse("Event task added: meeting on 2024-09-20 from 09:00 to 10:00");
//
//        Task task = taskList.addEvent(cmd, uiStub, storageStub);
//
//        assertEquals(1, taskList.getTotalNumber());
//        assertEquals("meeting", task.getDescription());
//        assertFalse(task.isDone());
//        assertEquals(LocalDate.of(2024, 9, 20), ((EventTask) task).getDate());
//        assertEquals(LocalTime.of(9, 0), ((EventTask) task).getStartTime());
//        assertEquals(LocalTime.of(10, 0), ((EventTask) task).getEndTime());
//        assertEquals("Event task added: meeting on 2024-09-20 from 09:00 to 10:00", storageStub.getWrittenContent());
//    }
//
//    // Stub classes
//    static class UiStub extends Ui {
//        private String printToDoResponse;
//        private String printEventResponse;
//
//        public void setPrintToDoResponse(String response) {
//            this.printToDoResponse = response;
//        }
//
//        public void setPrintEventResponse(String response) {
//            this.printEventResponse = response;
//        }
//
//        @Override
//        public String printToDo(String description) {
//            return printToDoResponse;
//        }
//
//        @Override
//        public String printEvent(String description, LocalDate date, LocalTime startTime, LocalTime endTime) {
//            return printEventResponse;
//        }
//
//        @Override
//        public String printList(ArrayList<Task> tasks, String header) {
//            return null;
//        }
//    }
//
//    static class StorageStub extends Storage {
//        private String writtenContent;
//
//        public String getWrittenContent() {
//            return writtenContent;
//        }
//
//        @Override
//        public void writeFile(String content) {
//            this.writtenContent = content;
//        }
//
//        @Override
//        public void overwriteFile(TaskList taskList) {
//            // Not needed for these tests
//        }
//    }
//
//    static class DateParserStub extends DateParser {
//        private LocalDate date;
//
//        public void setDate(LocalDate date) {
//            this.date = date;
//        }
//
//        @Override
//        public LocalDate parseDate(String dateStr) {
//            return date;
//        }
//    }
//
//    static class TimeParserStub extends TimeParser {
//        private LocalTime startTime;
//        private LocalTime endTime;
//
//        public void setStartTime(LocalTime startTime) {
//            this.startTime = startTime;
//        }
//
//        public void setEndTime(LocalTime endTime) {
//            this.endTime = endTime;
//        }
//
//        @Override
//        public LocalTime parseStringToTime(String timeStr) {
//            if (timeStr.startsWith("09:00")) {
//                return startTime;
//            } else if (timeStr.startsWith("10:00")) {
//                return endTime;
//            }
//            return null;
//        }
//    }
//}
