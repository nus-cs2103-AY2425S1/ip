package oyster.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTest() {
        assertEquals("""
                1. [T][ ] Test task
                2. [D][ ] Test task (by: 28 NOV 2002)
                3. [E][ ] Test task (from: 30 AUG 2024 to: 31 AUG 2024)
                4. [T][X] Test task 2
                5. [D][X] Test task 2 (by: 28 NOV 2002)
                6. [E][X] Test task 2 (from: 30 AUG 2024 to: 31 AUG 2024)""".trim(),
                Parser.parseTaskList("""
                T~ ~0~ ~Test task
                D~ ~0~ ~Test task~ ~2002-11-28T00:00
                E~ ~0~ ~Test task~ ~2024-08-30T00:00~ ~2024-08-31T00:00
                T~ ~1~ ~Test task 2
                D~ ~1~ ~Test task 2~ ~2002-11-28T00:00
                E~ ~1~ ~Test task 2~ ~2024-08-30T00:00~ ~2024-08-31T00:00
                """).toString().trim());

        assertEquals("1. [T][ ] Test task",
                Parser.parseTaskList("""
                T~ ~0~ ~Test task
                """).toString());

        assertEquals("1. [T][ ] Test task",
                Parser.parseTaskList("""
                T~ ~0~ ~Test task
                """).toString());
    }

    @Test
    public void corrupted() {
        try {
            Parser.parseTaskList("""
                T~ ~0Test task
                """);
            fail();
        } catch (Exception e) {
            assertEquals("[OYSTER CHATBOT \uD83E\uDDAA] [Parsing] Your data is corrupted!",
                    e.getMessage());
        }

        try {
            Parser.parseTaskList("""
                D~ ~0Test task
                """);
            fail();
        } catch (Exception e) {
            assertEquals("[OYSTER CHATBOT \uD83E\uDDAA] [Parsing] Your data is corrupted!",
                    e.getMessage());
        }

        try {
            Parser.parseTaskList("""
                E~ ~0Test task
                """);
            fail();
        } catch (Exception e) {
            assertEquals("[OYSTER CHATBOT \uD83E\uDDAA] [Parsing] Your data is corrupted!",
                    e.getMessage());
        }

        try {
            Parser.parseTaskList("""
                T~ ~3~ ~Test task
                """);
            fail();
        } catch (Exception e) {
            assertEquals("[OYSTER CHATBOT \uD83E\uDDAA] [Parsing] Your data is corrupted!",
                    e.getMessage());
        }
    }
}
