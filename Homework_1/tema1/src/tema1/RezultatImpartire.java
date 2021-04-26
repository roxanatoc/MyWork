package tema1;

import java.util.ArrayList;
import java.util.List;

public class RezultatImpartire {
	
		Polinom cat=new Polinom();
	    Polinom rest=new Polinom();
	    
		public List<Monom> monoame = new ArrayList<Monom>();


		public void Polinom(List<Monom> monoame) {
			this.monoame = monoame;
		}
	    
		public RezultatImpartire(Polinom cat, Polinom rest) {
			this.cat = cat;
			this.rest = rest;
		}
		public Polinom getCat() {
			return cat;
		}
		public void setCat(Polinom cat) {
			this.cat = cat;
		}
		public Polinom getRest() {
			return rest;
		}
		public void setRest(Polinom rest) {
			this.rest = rest;
		}
		
		public String toString() {
			String polinom = "";
			for (Monom i : 	this.monoame) {
				if (i.getGrad() != 0) {
					if (i.toString().charAt(0) == '+' && i.getCoef() == 0) {
						polinom = polinom + "+";
					}
					if (i.getCoef() != 0) {
						if (i.toString().charAt(0) == '-') {
							polinom = polinom + i.toString();
						} else {
							polinom = polinom + "+" + i.toString();
						}
					}
				} else {
					if (i.getCoef() != 0) {
						polinom = polinom + "+" + i.toString();
					}
				}
			}
			if (polinom.equals("")) {
				return "0";
			}
			polinom = polinom.replace("++", "+");
			polinom = polinom.replace("+-", "-");

			return polinom;
		}


}
