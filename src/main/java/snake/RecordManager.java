package snake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RecordManager {
    public void saveRecord(Record record) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/Archive.csv", true));
        writer.write(record.getName() +"," + record.getPoints() + "\n");
        writer.close();
        // TODO wyjątki
    }
}
