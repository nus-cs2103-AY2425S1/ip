package tags;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TagListTest {
    @Test
    public void addTagTest() {
        TagList tagList = new TagList();
        Tag tag = new Tag("tag1");
        tagList.addTag(tag);
        assertEquals(true, tagList.containsTag("tag1"));
    }

    @Test
    public void addTagFromStringTest() {
        TagList tagList = new TagList();
        Tag tag = tagList.addTagFromString("tag1");
        assertEquals(true, tagList.containsTag("tag1"));
        assertEquals("tag1", tag.getTagName());
    }

    @Test
    public void removeTagTest() {
        TagList tagList = new TagList();
        Tag tag = new Tag("tag1");
        tagList.addTag(tag);
        tagList.removeTag("tag1");
        assertEquals(false, tagList.containsTag("tag1"));
    }

    @Test
    public void containsTagTest() {
        TagList tagList = new TagList();
        Tag tag = new Tag("tag1");
        tagList.addTag(tag);
        assertEquals(true, tagList.containsTag("tag1"));
        assertEquals(false, tagList.containsTag("tag2"));
    }

    @Test
    public void getTagTest() {
        TagList tagList = new TagList();
        Tag tag = new Tag("tag1");
        tagList.addTag(tag);
        assertEquals(tag, tagList.getTag("tag1"));
    }

}
