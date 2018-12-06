package tema;

/**
 * 
 * clasa ce defineste un nod
 */

public class Nod {
	
	Nod stanga,dreapta,parinte;
	
	String variabila;		//in cazul in care nodul va contine o variabila
	
	String valoare;		//in cazul in care nodul va contine o variabila atunci valoare va fi valoarea ei
	
	String operator;		//in cazul in care nodul va contine un operator
	
	int distanta;
	
	/**
	 * constructorul nodului
	 * 
	 * @param variabila daca se doreste ca in nodul creat sa fie memorata o variabila , null in caz contrat
	 * @param valoare daca se doreste ca in nodul creat sa fie memorata o valoare a variabilei , null in caz contrat
	 * @param operator daca se doreste ca in nodul creat sa fie memorat un operator , null in caz contrat
	 * 
	 */
	
	public Nod(String variabila , String valoare , String operator ) {
		
		this.variabila = variabila;
		this.valoare = valoare;
		this.operator = operator;
		this.stanga = null;
		this.dreapta = null;
		this.parinte = null;
		
	}

}
