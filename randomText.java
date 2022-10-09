import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class randomText{
    private File file; 
    private BufferedReader br; 
    private Random random;

    public randomText(){
        this.file = new File("preset_text.txt");
        this.br = null;
        this.random = new Random();
    }

    private int totallines(){
        int total = 0;
        try{
            br = new BufferedReader(new FileReader(file));
            while((br.readLine()) != null){
                total++;
            }
            br.close();    
        }catch(IOException e){
            System.out.println("File not found");
        }
        return total;
        
    }
    
    
    public String randomTString(){
        try{
            int randInt = random.nextInt(totallines());
            return Files.readAllLines(Paths.get(file.getName())).get(randInt);
        }catch(IOException e){
            System.out.println("File not found");
        }
        return "";
    }

}