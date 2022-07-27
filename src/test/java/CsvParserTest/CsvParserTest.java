package CsvParserTest;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class CsvParserTest {
    @Test
    public void testCsvReader() throws Exception {
        StringBuilder sb = new StringBuilder(CSVParser.INITIAL_READ_SIZE);
        sb.append("a,b,c").append("\n");   // test standard case
        sb.append("a,\"b,b,b\",c").append("\n");  // test with quotes
        sb.append(",,").append("\n"); // test empty
        sb.append("a,\",\n\n\",d.\n");
        sb.append("\"Car \"\"Vehicle\"\" Drive\",,\n"); // test strings with quotes
        sb.append("\"\"\"\"\"\",\"test\"\n"); // test non comma quote elements with single comma and string

        CSVReader csvr = new CSVReader(new StringReader(sb.toString()));

        // test normal case
        String[] nextLine = csvr.readNext();
        assertEquals("a", nextLine[0]);
        assertEquals("b", nextLine[1]);
        assertEquals("c", nextLine[2]);

        // test quoted commas
        nextLine = csvr.readNext();
        assertEquals("a", nextLine[0]);
        assertEquals("b,b,b", nextLine[1]);
        assertEquals("c", nextLine[2]);

        // test empty elements
        nextLine = csvr.readNext();
        assertEquals(3, nextLine.length);

        // test multiline quoted
        nextLine = csvr.readNext();
        assertEquals(3, nextLine.length);

        // test quoted quote chars
        nextLine = csvr.readNext();
        assertEquals("Car \"Vehicle\" Drive", nextLine[0]);

        nextLine = csvr.readNext();
        assertEquals("\"\"", nextLine[0]); // checking non comma elements
        assertEquals("test", nextLine[1]); // test that the word is still printed properly

        //test end of stream
        assertNull(csvr.readNext());

    }
}
