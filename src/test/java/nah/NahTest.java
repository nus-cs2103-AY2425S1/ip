package nah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class NahTest {

    /**
     * Test method reponse of chatBot.
     * The test pass if the chatBot response match the expected response and
     * no exception is thrown.
     */
    @Test
    public void reponseTest1() {
        Nah nah = new Nah();
        assertEquals(nah.getResponse("clean now"), " Nahh!!! Do not type nonsense after 'clean' command\n");

    }

    /**
     * Test method reponse of chatBot.
     * The test pass if the chatBot response match the expected response and
     * no exception is thrown.
     */
    @Test
    public void reponseTest2() {

        Nah nah = new Nah();
        assertEquals(nah.getResponse("mark one"),
                " Nah.Nah!!! Please give me a valid ordinal number for the task\n");

    }

}
