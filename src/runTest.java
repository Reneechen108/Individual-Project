public class runTest {

    public static void main(String[] args) throws Exception {
        String csvFile = "creditData.csv";
        String csvFileOutput = "creditOutput.csv";
        String jsonFile = "creditData.json";
        String jsonFileOutput = "creditOutput.json";
        String xmlFile = "creditData.xml";
        String xmlFileOutput = "creditOutput.xml";

        System.out.println("Read CSV file: ");
        CsvFile c = new CsvFile();
        c.readCSV(csvFile);
        c.writeCSV(csvFileOutput);

        System.out.println("Read JSON file: ");
        JsonFile j = new JsonFile();
        j.readJSON(jsonFile);
        j.writeJSON(jsonFileOutput);

        System.out.println("Read XML file: ");
        XmlFile x = new XmlFile();
        x.readXMl(xmlFile);
        x.writeXML(xmlFileOutput);
    }

}