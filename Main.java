package tema;

public class Main {

	public static void main(String[] args) {

		TestReader reader = new TestReader("in.txt");		//fisier input
		TestWriter writer = new TestWriter("out.txt");		//fisieru output
		
		writer.close();
		Arbore arb = new Arbore();
		
		for ( int i = 0 ; i < reader.expresii.length ; i++ ) {			
			if ( reader.expresii[i] == null ){				
				break;			
			}
			
			arb.creare(reader.expresii[i]);		//apelare metoda ce creeaza arborele			
		}	

	}

}
