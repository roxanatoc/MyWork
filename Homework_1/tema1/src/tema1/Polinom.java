package tema1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Polinom {
	public boolean citirePolinom = true;

	public List<Monom> monoame = new ArrayList<Monom>();


	public Polinom(List<Monom> monoame) {
		this.monoame = monoame;
	}
	public Polinom(String polinom) {
		citirePolinom(polinom);
	}

	public List<Monom> getMonoame() {
		return monoame;
	}

	public Polinom() {
		this.monoame.clear();
	}

	public void setMonoame(List<Monom> monoame) {
		this.monoame = monoame;
	}

	public void citirePolinom(String polinom) {
		citirePolinom = true;
		polinom = polinom.replace("-", "+-");
		if (polinom.charAt(0) != '+') {
			polinom = "+" + polinom;
		}

		String[] m = polinom.split("\\+");

		for (int i = 1; i < m.length; i++) {
			Monom monom = Monom.citireMonom(m[i]);
			if (monom != null && citirePolinom == true) {
				monoame.add(monom);
			} else {
				citirePolinom = false;
				monoame.clear();
			}
		}
	}

	public void reducereGrad(Polinom p) {
		List<Monom> monom = new ArrayList<Monom>();
		int i=0;
		while( i < p.getMonoame().size()) {
			Monom x = p.getMonoame().get(i);
			int j = i + 1;
			while (j < p.getMonoame().size()) {
				Monom y = p.getMonoame().get(j);
				Monom xy = x.adunareMonom(x,y);
				if (xy != null) {
					x = xy;
					p.getMonoame().remove(y);
				} else j++;
			}
			if (x.getCoef() != 0) {
				monom.add(x);
				i++;
			}
			if (monom.size() == 0) {
				monom.add(new Monom(0, 0));
			}
		}
		this.monoame = monom;
	}

	public void addMonom(double coef, int grad) {
		monoame.add(new Monom(coef, grad));
	}

	public void addMonom(Monom monom) {
		monoame.add(monom);
	}

	public void ordonare(List<Monom> monoame) {
		Collections.sort(monoame, new Comparator<Monom>() {
			public int compare(Monom i, Monom j) {
				return i.getGrad() - j.getGrad();
			}
		});
	}
	

	// inmultire polinom cu monom
	public Polinom inmulMonom(Monom m) {
		List<Monom> monom = new ArrayList<Monom>();
		for (Monom i : this.monoame) {
			monom.add(i.inmultireMonom(i,m));
		}
		Polinom P = new Polinom(monom);
		P.reducereGrad(P);
		return P;
	}

	//gradul polinomului
	public int getGrad() {
		int grad = -1;
		for (int i = 0; i < monoame.size(); i++) {
			if (grad < monoame.get(i).getGrad()) {
			grad = monoame.get(i).getGrad();
			}
		}
		return grad;
	}

	public Monom cautaMaxim(Polinom p) {
		int grad = p.getGrad();
		int i = p.getIndexGrad(grad);
		return p.monoame.get(i);
	}

	// indexul polinomului maxim
	public int getIndexGrad(int grad) {
		for (int i = 0; i < monoame.size(); i++) {
			if (grad == monoame.get(i).getGrad()) {
				return i;
			}
		}
		return -1;
	}
	public String toString() {
		String polinom = "";
		for (Monom i : this.monoame) {
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
