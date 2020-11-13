import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class XmlFileTest {

    @Test
    void readXMlInvalid() {
        XmlFile x = new XmlFile();
        try{
            x.readXMl("file.xml");
            fail("Wrong file name in readCSV() should trigger an exception");
        }catch (FileNotFoundException e){
            System.out.println("Invalid file name in readXMl(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in readXMl(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for readXMl()");
        }
        try{
            x.readXMl("CreditData.json");
            //fail("Wrong file name in readCSV() should trigger an exception");
        }catch (IllegalAccessException | IOException e){
            System.out.println("Invalid file name in readXMl(): " + e.getMessage() );
        }
    }

    @Test
    void writeXMLInvalid() throws Exception{
        XmlFile x = new XmlFile();
        try{
            x.writeXML("doesn't exist");
            fail("Invalid value in writeXML() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in writeXML(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in writeXML(): " + e.getMessage() );
        }catch (Exception e){
            System.out.println("Exception in writeXML(): " + e.getMessage() );
        }
    }

    @Test
    void testWriteJSON() throws ParseException, IllegalAccessException, IOException {
        XmlFile x = new XmlFile();
        try{
            x.readXMl("creditOutputSample.xml");
            x.writeXML("creditCheckOutput.xml");
            final File expected = new File( "creditCheckOutput.xml");
            final File output = new File("creditOutputSample.xml");
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
