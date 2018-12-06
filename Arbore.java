package tema;

/**
 * 
 * clasa ce creeaza arborele
 */

public class Arbore {
	
	Nod radacina = new Nod(null , null , "=");
	
	String [][] alfabet = new String[2][200];		//variabilele care apar in expresiile date ( inafara de constante ) pe o linie , si respectiv valorile lor pe a doua linie
	
	int a = 0;		//contorul coloanelor din alfabet
	
	/**
	 * metoda pentru crearea unei cozi ce descompune expresia din input
	 * 
	 * @param s stringul ce reprezinta expresia din input
	 */
	
	public void creare( String s) {

		char [] stack = new char[200];		//stiva utilizata in descompunere
		int vf = 0;			//varful stivei
		
		String [] coada = new String[200];		//coada ce va rezulta in urma descompunerii expresiei
		
		int c = 0;		//contor coada
		int k,y = 0;
		
		//ma intai descompun expresia intr-o coada utilizand o stiva
		char [] exp = s.toCharArray();
		
		String variabila = "";
		
		for ( int i = 0 ; i < s.length(); i++ ){
			
			if (exp[i] != '*' && exp[i] != '/' && exp[i] != ':' && exp[i] != '+' && exp[i] != '-' && exp[i] != '>' && exp[i] != '<' && exp[i] != '(' && exp[i] != ')' && exp[i] != '=' && exp[i] != '?') {
				
				variabila = variabila + exp[i];
				y = 1;
				
			}
			
			else if (exp[i] == '*' || exp[i] == '/' || exp[i] == ':' || exp[i] == '+' || exp[i] == '-' || exp[i] == '>' || exp[i] == '<' || exp[i] == '(' || exp[i] == ')' || exp[i] == '=' || exp[i] == '?'){		//daca caracterul citit este un operator
				
				if ( y == 1 ) {
					alfabet[0][a] = variabila;
					a = a + 1;
					coada[c] = variabila;
					c = c + 1;
					variabila = "";
					y = 0;
				}
				
				//inserez operatorul gasit in stiva in ordinea precedentei
				
				if ( vf == 0 ) {		//stiva goala
					
					stack[vf] = exp[i];
					vf = vf + 1;
					
				}
				else { 		//inserez in ordinea precedentei
					
					if ( exp[i] == '*' || exp[i] == '/' || exp[i] == ':' ){

						k = 1;

						if ( stack[vf - 1] == '*' || stack[vf - 1] == '/' || stack[vf - 1] == ':'){	// transfer din stiva in coada pana ajung in stiva la un operator a carui precedenta este mai mica decat a operatorului ce se doreste a fi inserat

							while ( stack[vf - 1] == '*' || stack[vf - 1] == '/' || stack[vf - 1] == ':' ){							
								if (vf - 1 == 0 ){

									coada[c] = String.valueOf(stack[vf - 1]);
									stack[vf - 1] = exp[i];
									c  = c + 1;
									k = 0;
									break;
								}

								coada[c] = String.valueOf(stack[vf-1]);
								c  = c + 1;
								vf = vf - 1;

							}

							if ( k == 1 ){		//whileul s-a oprit din cauza ca in stiva sa ajuns la un operator mai mic	
								stack[vf] = exp[i];
								vf = vf + 1;

							}

						}
						else {

							stack[vf] = exp[i];
							vf = vf + 1;

						}

					}

					else if ( exp[i] == '-' || exp[i] == '+' || exp[i] == '>' || exp[i] == '<' ){

						k = 1;

						if ( stack[vf - 1] == '*' || stack[vf - 1] == '/' || stack[vf - 1] == ':' || stack[vf - 1] == '-' || stack[vf - 1] == '+' || stack[vf - 1] == '>' || stack[vf - 1] == '<' ){	// transfer din stiva in coada pana ajung in stiva la un operator a carui precedenta este mai mica decat a operatorului ce se doreste a fi inserat
							while ( stack[vf - 1] == '*' || stack[vf - 1] == '/' || stack[vf - 1] == ':' || stack[vf - 1] == '-'  || exp[i] == '+'|| exp[vf - 1] == '>' || exp[vf - 1] == '<' ){

								if (vf - 1 == 0 ){

									coada[c] = String.valueOf(stack[vf - 1]);
									stack[vf - 1] = exp[i];
									c  = c + 1;
									k = 0;
									break;
								}

								coada[c] = String.valueOf(stack[vf-1]);
								c  = c + 1;
								vf = vf - 1;

							}

							if ( k == 1 ){		//whileul s-a oprit din cauza ca in stiva sa ajuns la un operator mai mic

								stack[vf] = exp[i];
								vf = vf + 1;

							}

						}
						else {
							stack[vf] = exp[i];
							vf = vf + 1;

						}
					}
					else if ( exp[i] == ')' ){		//scot tot din stiva pana la '('

						while ( stack[vf - 1] != '(' ){

							//System.out.println(stack[vf - 1]);

							coada[c] = String.valueOf(stack[vf - 1]);
							c = c + 1;										
							vf = vf - 1;

						}

						vf = vf -1;

					}
					else if ( exp[i] == '(' || exp[i] == '?'){		//inserez indiferent de precedenta

						stack[vf] = exp[i];
						vf = vf + 1;

					}
				}

			}
			
		}
		
		//copiez in coada elementele ramase in stiva		
		while ( vf > 0) {
			
			coada[c] = String.valueOf(stack[vf - 1]);
			vf = vf - 1;
			c = c + 1;
			
		}
		
		//coada a fost creata
		
		//vreau inserez "?" intre elementele j-3 si j-2
		
		for ( int j = 0 ; j < c; j++ ) {
			
			if ( coada[j].compareTo("?") == 0 ){

				coada[j] = coada[j-1];
				coada[j-1] = coada[j-2];
				coada[j-2] = "?";
				
			}
			
		}
		
		Nod R = radacina;

		for ( int j = c - 2 ; j >= 0 ; j-- ) {		//iau pe rand fiecare element din coada de la dreapta la stanga si creez arborele nod cu nod

			if ( coada[j].compareTo("*") == 0 || coada[j].compareTo("/") == 0 || coada[j].compareTo(":") == 0 || coada[j].compareTo("+") == 0 || coada[j].compareTo("-") == 0 || coada[j].compareTo(">") == 0 || coada[j].compareTo("<") == 0 || coada[j].compareTo(")") == 0 || coada[j].compareTo("(") == 0 || coada[j].compareTo("?") == 0 ) {
			//citesc un operator din coada, in afara de = deoarece deja am creat radacina arborelui care contine "="

				if ( R.dreapta == null ) {

					R.dreapta = new Nod(null , null , coada[j]);
					R.dreapta.parinte = R;
					R = R.dreapta;						
				}

				else if (R.stanga == null) {

					R.stanga = new Nod(null , null , coada[j]);
					R.stanga.parinte = R;
					R = R.stanga;

				}

				else{
					//daca ambii copii exista trebuie sa ma intorc pana in nodul in care are cel putin unul dintre copii null

					while ( R.stanga != null /*|| R.dreapta != null */) {
						//System.out.println(j);
						R = R.parinte;

					}

					if ( R.dreapta == null ) {

						R.dreapta = new Nod(null , null , coada[j]);
						R.dreapta.parinte = R;
						R = R.dreapta;

					}

					else if (R.stanga == null) {

						R.stanga = new Nod(null , null , coada[j]);
						R.stanga.parinte = R;
						R = R.stanga;

					}

				}
			}

			if ( coada[j].compareTo("*") != 0 || coada[j].compareTo("/") != 0 || coada[j].compareTo(":") != 0 || coada[j].compareTo("+") != 0 || coada[j].compareTo("-") != 0 || coada[j].compareTo(">") != 0 || coada[j].compareTo("<") != 0 || coada[j].compareTo(")") != 0 || coada[j].compareTo("(") != 0 || coada[j].compareTo("?") != 0 ) {
				//din coada se citeste o variabila
				if ( R.dreapta == null ) {

					R.dreapta = new Nod(coada[j] , null , null);
					R.dreapta.parinte = R;

				}

				else if ( R.stanga == null ){

					R.stanga = new Nod(coada[j] , null , null);
					R.dreapta.parinte = R;

				}

			}
		}
		
		//parcurgere in latime pentru afisare
		
		Nod [] queue = new Nod[100];		//coada de noduri
		vf = 0;		//coada cozii
		int cap = 0;
		
		Nod curent;
		
		String rez = "";
		
		char [] str = new char[1000];
		
		R = radacina;
		
		R.distanta = 0;		//distanta fata de radacina e 0
		
		queue[vf] = R;		//adaug radacina in coada
		
		while ( vf > 0 )	{		//cat timp am elemente in coada
			
			curent = queue[vf - 1];

			vf = vf - 1;
			//adaug fii in coada
			if ( curent.dreapta != null ) {
				
				for ( int i = vf ; i < cap ; i-- ){
					
					queue[i] = queue[i - 1];
					
				}
				
				curent.dreapta.distanta = curent.distanta + 1;
				queue[cap] = curent.dreapta;
				
				vf = vf + 1;
				
			}
			
			if ( curent.stanga != null ) {
				
				for ( int i = vf ; i < cap ; i-- ){
					
					queue[i] = queue[i - 1];
					
				}
				
				curent.stanga.distanta = curent.stanga.distanta + 1;
				queue[0] = curent.stanga;
				
				vf = vf + 1;
				
			}
			
			//dupa ce am adugat fii lui curent prelucrez curent
			
			str = rez.toCharArray();
				
			for ( int j = 0 ; j < rez.length() ; j++ ) {
				
				if ( str[j] == 'E' ) {
					
					rez = rez + "(";
					
					if ( curent.stanga == null && curent.dreapta == null  ) {		//frunza = T sau N
						
						int asd = 0;
						while ( queue[asd].distanta == curent.distanta ) {

							if ( queue[asd].dreapta == null && queue[asd].stanga == null) {

								if ( queue[asd].parinte.operator.compareTo("+") == 0 || queue[asd].parinte.operator.compareTo("-") == 0 || queue[asd].parinte.operator.compareTo("=") == 0) {

								rez = rez + "T" + queue[asd].parinte.operator;

								}

								else if ( queue[asd].parinte.operator.compareTo("*") == 0){

									rez = rez + "F" + queue[asd].parinte.operator;

								}

							else {

							rez = rez + "N" + queue[asd].parinte.operator;

							}

						}
								
						else {
							rez = rez + "E" + queue[asd].parinte.operator;
						}
							
						if ( queue[asd + 1].dreapta == null && queue[asd].stanga == null ){

							if ( queue[asd].parinte.operator.compareTo("+") == 0 || queue[asd].parinte.operator.compareTo("-") == 0 || queue[asd].parinte.operator.compareTo("=") == 0) {

							rez = rez + "T" + queue[asd].parinte.operator;

							}									

							else if ( queue[asd].parinte.operator.compareTo("*") == 0){

								rez = rez + "F" + queue[asd].parinte.operator;

							}

							else {

								rez = rez + "N" + queue[asd].parinte.operator;

							}

						}						
						else {
							rez = rez + "E" + queue[asd].parinte.operator;
						}
								
						asd = asd + 2;
						c = asd;

						if ( asd >= vf - 1 ){

							break;

						}
					}

				}
					
			}			

		}

		if ( rez.length() == 0 ) {

			rez = rez + "E";

		}
	   }
		
	}
	
	public void erori(){
			
	}
	
	public void evaluare(){
			
	}

}
