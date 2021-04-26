package tema1;


import junit.framework.TestCase;

public class testCalculator extends TestCase {
	
	public void testAdunare() {
		Polinom P = new Polinom("x^2+1");
		Polinom Q = new Polinom("x^2");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+2.0x^2+1");
		Polinom rezultat=op.adunare(P,Q);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Adunarea s-a efectuat corect");
	}

	public void testScadere() {
		Polinom P = new Polinom("x^2+1");
		Polinom Q = new Polinom("x^2");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+1");
		Polinom rezultat = op.scadere(P,Q);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Scaderea s-a efectuat corect");
	}

	public void testInmultire() {
		Polinom P = new Polinom("x^2+1");
		Polinom Q = new Polinom("x^2");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+x^4+x^2");
		Polinom rezultat = op.inmultire(P,Q);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Inmultirea s-a efectuat corect");
	}

	
	/*public void testImpartire() {
		Polinom P = new Polinom("2x^2+4x+3");
		Polinom Q = new Polinom("x+1");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+2.0x+2.0");
		RezultatImpartire rezultat = op.impartire2(P, Q);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Impartirea s-a efectuat corect");
	}*/
	 

	public void testDerivare() {
		Polinom P = new Polinom("x^2+1");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+2x");
		Polinom rezultat = op.derivare(P);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Derivarea s-a efectuat corect");
	}

	public void testIntegrare() {
		Polinom P = new Polinom("x^2+1");
		Operatii op=new Operatii();
		Polinom rezultatAsteptat = new Polinom("+0.33x^3+x");
		Polinom rezultat = op.integrare(P);
		assertEquals(rezultat.toString(), rezultatAsteptat.toString());
		System.out.println("Integrarea s-a efectuat corect");

	}
	
}
