package tema;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * clasa pentru scriere din fisier
 */

public class TestWriter {
    
    private PrintWriter out = null;
    
    /**
     * metoda deschidere fisier
     * @param filename numele fisierului de output
     */

    public TestWriter(String filename) {
        try {
            out = new PrintWriter(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * metoda de scriere in fisier
     * @param reader obiectul ce contine expresiile din input
     */
    
    public void print ( TestReader reader) {
    	
    	for ( int i = 0 ; i < reader.expresii.length ; i++ ) {
    		
    		if ( reader.expresii[i] == null ) {   			
    			break;
    		}
   
			out.println(reader.expresii[i]);
    		
    	}
    	
    }
    
    /**
     * 
     * metoda inchidere fisier
     */

    public void close() {
        out.close();
    }   
}