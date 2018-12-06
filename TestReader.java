package tema;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * clasa pentru citire din fisier
 */

public class TestReader {
    
    public String[] expresii = new String[200];
    
    public String text;
    
    public Scanner in;
    
    /**
     * metoda deschidere fisier
     * @param filename numele fisierului de input
     */

    public TestReader(String fileName) {
        try {
            in = new Scanner(new File(fileName));
            text = in.nextLine();		//citeste prima linie din fisier
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        int i = 0;
    
        while( text != null){
        	
    	try {
        	expresii[i] = new String(text);
        	text = in.nextLine();
    	} catch(NoSuchElementException el) {
    		break;
    	}
    	
    	i = i + 1;
    	
        }
    
        in.close();
        
    }
    
}
