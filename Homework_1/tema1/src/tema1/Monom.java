package tema1;

public class Monom {

	private double coeficient;
	private int grad;

	public Monom() {
		this.coeficient = 0;
		this.grad = 0;
	}

	public Monom(double coef, int grad) {
		this.coeficient = coef;
		this.grad = grad;
	}

	public double getCoef() {
		return coeficient;
	}

	public int getGrad() {
		return grad;
	}

	public void setCoef(double coef) {
		this.coeficient = coef;
	}

	public void setGrad(int grad) {
		this.grad = grad;
	}

	public static Monom citireMonom(String monom) {
		double coef = 0;
		int grad = 0;
		int pozitie = monom.indexOf("x");
		if (pozitie != -1) {
			String coef_aux = monom.substring(0, pozitie);
			if (coef_aux.isEmpty() || coef_aux.equals("+")) {
				coef = 1;
			} else if (coef_aux.equals("-")) {
				coef = -1;
			} else {
				try {
					coef = Double.parseDouble(coef_aux);
				} catch (NumberFormatException e) {
					return null;
				}
			}
			String grad_aux = monom.substring(pozitie + 1);
			if (grad_aux.isEmpty()) {
				grad = 1;
			} else {
				if (grad_aux.charAt(0) == '^') {
					try {
						grad = Integer.parseInt(grad_aux.substring(1));
					} catch (NumberFormatException e) {
						return null;
					}
				} else {
					return null;
				}
			}
		} else {
			try {
				coef = Double.parseDouble(monom);
			} catch (NumberFormatException e) {
				return null;
			}
			grad = 0;
		}
		return new Monom(coef, grad);
	}

	public Monom adunareMonom(Monom p, Monom q) {
		if (p.grad == q.grad) {
			return new Monom((p.coeficient + q.coeficient), p.grad);
		} else
			return null;
	}

	public Monom scadereMonom(Monom p, Monom q) {
		if (p.grad == q.grad) {
			return new Monom((p.coeficient - q.coeficient), p.grad);
		} else
			return null;
	}

	public Monom inmultireMonom(Monom p, Monom q) {
		return new Monom((p.coeficient * q.coeficient), (p.grad + q.grad));
	}

	public Monom impartireMonom(Monom p, Monom q) {
		if (q.coeficient != 0) {
			return new Monom((p.coeficient / q.coeficient), (p.grad - q.grad));
		} else
			return null;
	}

	public Monom derivareMonom(Monom p) {
		return new Monom(p.coeficient * p.grad, p.grad - 1);
	}

	public Monom integrareMonom(Monom p) {
		return new Monom(Math.round(p.coeficient / (p.grad + 1) * 100.0) / 100.0, p.grad + 1);
	}
	

	public String toString() {
		String monom = "";
		if (this.coeficient != 1 && this.coeficient != -1) {
			if (this.coeficient == 0) {
				return "0";
			}
			if ((this.coeficient * 10) % 10 == 0) {
				monom = monom + (int) this.coeficient;
			} else {
				monom = monom + this.coeficient;
			}

		}
		if (this.grad != 0) {
			if (this.coeficient == -1) {
				monom = monom + "-x";
			} else {
				monom = monom + "x";
			}
			if (this.grad != 1) {
				monom = monom + "^" + this.grad;
			}
		} else {
			if ((this.coeficient * 10) % 10 == 0) {
				if (this.coeficient == 1) {
					monom = monom + "+" + (int) this.coeficient;
				}
				if (this.coeficient == -1) {
					monom = monom + (int) this.coeficient;
				}
			} else {
				if (this.coeficient == 1) {
					monom = monom + "+" + this.coeficient;
				}
				if (this.coeficient == -1) {
					monom = monom + this.coeficient;
				}
			}
		}
		return monom;
	}
}
