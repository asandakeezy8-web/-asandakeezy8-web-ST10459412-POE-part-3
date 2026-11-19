package com.mycompany.main;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class MessagePart3Test {

    @Before
    public void setUp() {
        Message.resetState();
        // Clean JSON so tests don't read old data
        try { java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(Message.STORE_FILE)); }
        catch (Exception ignored) {}
    }

    // Helper: populate EXACT test data from brief
    private Message[] populateTestData() {
        Message m1 = new Message("0012345678", "+27834557896", "Did you get the cake?");
        m1.SentMessage(1); // Sent

        Message m2 = new Message("0012345679", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        m2.SentMessage(3); // Stored

        Message m3 = new Message("0012345680", "+27834484567", "Yohoooo, I am at your gate.");
        m3.SentMessage(2); // Disregard

        Message m4 = new Message("0012345681", "0838884567", "It is dinner time !");
        m4.SentMessage(1); // Sent

        Message m5 = new Message("0012345682", "+27838884567", "Ok, I am leaving without you.");
        m5.SentMessage(3); // Stored

        return new Message[]{m1, m2, m3, m4, m5};
    }

    // 1. Sent Messages array correctly populated
    @Test
    public void testSentMessagesArrayPopulated() {
        populateTestData();
        List<String> sent = Arrays.asList(Message.getSentMessagesArray());
        assertTrue(sent.contains("Did you get the cake?"));
        assertTrue(sent.contains("It is dinner time !"));
        assertEquals(2, sent.size());

        // Also check other arrays exist
        assertEquals(2, Message.getStoredMessagesArray().length);
        assertEquals(1, Message.getDisregardedMessagesArray().length);
        assertEquals(5, Message.getMessageIDsArray().length);
        assertEquals(5, Message.getMessageHashesArray().length);
    }

    // 2. Display longest message - Test Data 1-4
    @Test
    public void testLongestMessage() {
        Message[] data = populateTestData();
        // Longest among first 4 (brief says 1-4) OR among stored - both give same answer
        List<Message> firstFour = Arrays.asList(data[0], data[1], data[2], data[3]);
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                Message.getLongestMessage(firstFour));
        // Also longest stored
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                Message.getLongestStoredMessage());
    }

    // 3. Search for messageID - Message 4
    @Test
    public void testSearchByMessageID() {
        Message[] data = populateTestData();
        assertEquals("It is dinner time !", Message.searchByMessageID(data[3].getMessageID()));
    }

    // 4. Search all messages for recipient +27838884567
    @Test
    public void testSearchByRecipient() {
        populateTestData();
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // 5. Delete by hash - Message 2
    @Test
    public void testDeleteByHash() {
        Message[] data = populateTestData();
        String result = Message.deleteByHash(data[1].getMessageHash());
        assertEquals("Message 'Where are you? You are late! I have asked you to be on time.' successfully deleted.", result);
        // Verify it's gone
        assertFalse(Message.searchByRecipient("+27838884567").contains("Where are you? You are late!"));
    }

    // 6. Display Report
    @Test
    public void testDisplayReport() {
        populateTestData();
        String report = Message.displayReport();
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
        assertTrue(report.contains("Message:"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time !"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }
}