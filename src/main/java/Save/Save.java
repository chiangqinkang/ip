package Save;
import java.io.*;

public class Save {
    private File saveFile;
    private final String savePath = "C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt";

    public Save(){
        this.saveFile = new File("C:\\Users\\chian\\Desktop\\ip\\saves\\save.txt");
    }

    public String getPath() {
        return this.savePath;
    }

    public void writeToSave(String s) {
        try {
            FileWriter writer = new FileWriter(this.savePath);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the save file");
        }
    }

    public void loadTasksFromSave(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.saveFile))){
            String line;
            while ((line = reader.readLine()) != null) {
                ;
            }

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading from save file");
        }
    }
}