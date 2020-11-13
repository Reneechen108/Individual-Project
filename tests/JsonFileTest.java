import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonFileTest {
    private JsonFile j = new JsonFile();

    @Test
    void readJSONInvalid() {
        try{
            j.readJSON("file.json");
            fail("Wrong file name in readCSV() should trigger an exception");
        }catch (FileNotFoundException e){
            System.out.println("Invalid file name in readJSON(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in readJSON(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for readJSON()");
        }
        try{
            j.readJSON("CreditData.xml");
            //fail("Wrong file name in readCSV() should trigger an exception");
        } catch (IllegalAccessException e){
            System.out.println("Invalid file name in readJSON(): " + e.getMessage() );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void writeJSONInvalid() {
        JsonFile j = new JsonFile();
        try{
            j.writeJSON("doesn't exist");
            fail("Invalid value in writeJSON() should trigger an exception");
        }catch (IllegalAccessException e){
            System.out.println("Invalid value in writeJSON(): " + e.getMessage() );
        }catch (IOException e){
            System.out.println("IO exception in writeJSON(): " + e.getMessage() );
        }catch (Exception e){
            fail("Wrong exception thrown for writeJSON()");
        }
    }

    @Test
    void testWriteJSON() throws ParseException, IllegalAccessException, IOException {
        try{
            j.readJSON("creditDataSample.json");
            j.writeJSON("creditCheckOutput.json");
            final File expected = new File( "creditDataSample.json");
            final File output = new File("creditData.json");
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