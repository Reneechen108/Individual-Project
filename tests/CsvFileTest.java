import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CsvFileTest {

    @Test
    void readCSVInvalid() throws Exception {
        CsvFile c = new CsvFile();
        try{
            c.readCSV("file.csv");
            fail("Wrong file name in readCSV() should trigger an exception");
        }catch (FileNotFoundException e){
            System.out.println("Invalid file name in readCSV(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in readCSV(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for readCSV()");
        }
        try{
            c.readCSV("CreditData.xml");
            //fail("Wrong file name in readCSV() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid file name in readCSV(): " + e.getMessage() );
        }
    }

    @Test
    void writeCSVInvalid() {
        CsvFile c = new CsvFile();
        try{
            c.writeCSV("doesn't exist");
            fail("Invalid value in writeCSV() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in writeCSV(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in writeCSV(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for writeCSV()");
        }
    }

    @Test
    void testWriteCSV() throws ParseException, IllegalAccessException, IOException {
        CsvFile c = new CsvFile();
        try{
            c.readCSV("creditOutputSample.csv");
            c.writeCSV("creditCheckOutput.csv");
            final File expected = new File( "creditCheckOutput.csv");
            final File output = new File("creditOutputSample.csv");
            assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
        }catch(IllegalAccessException e){
            System.out.println("IllegalAccessException" + e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("NumberFormatException" + e.getMessage());
        } catch (Exception e){
            System.out.println("Exception" + e.getMessage());
        }
    }
}