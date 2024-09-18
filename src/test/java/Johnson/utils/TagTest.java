package Johnson.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagTest {

    @Test
    void testTagCreation() {
        Tag tag = new Tag("urgent");
        assertEquals("urgent", tag.getTag());
    }

    @Test
    void testTagToString() {
        Tag tag = new Tag("work");
        assertEquals("work", tag.toString());
    }
}