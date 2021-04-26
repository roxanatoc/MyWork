package tema1;

import java.util.ArrayList;
import java.util.List;

public class Operatii {
	
	public Polinom adunare(Polinom p, Polinom q) {
		Polinom r = new Polinom();
		int i = 0, j = 0;
		int sizeP = p.getMonoame().size();
		int sizeQ = q.getMonoame().size();
		Monom m;
		while (i < sizeP && j < sizeQ) {
			if (p.getMonoame().get(i).getGrad() == q.getMonoame().get(j).getGrad()) {
				m = new Monom(p.getMonoame().get(i).getCoef() + q.getMonoame().get(j).getCoef(),
						p.getMonoame().get(i).getGrad());
				i++;
				j++;
			} else if (p.getMonoame().get(i).getGrad() > q.getMonoame().get(j).getGrad()) {
				m = new Monom(p.getMonoame().get(i).getCoef(), p.getMonoame().get(i).getGrad());
				i++;
			} else {
				m = new Monom(q.getMonoame().get(j).getCoef(), q.getMonoame().get(j).getGrad());
				j++;
			}
			r.addMonom(m);
		}
		while (j < sizeQ) {
			m = new Monom(q.getMonoame().get(j).getCoef(), q.getMonoame().get(j).getGrad());
			j++;
			r.addMonom(m);
		}
		while (i < sizeP) {
			m = new Monom(p.getMonoame().get(i).getCoef(), p.getMonoame().get(i).getGrad());
			i++;
			r.addMonom(m);
		}

		return r;
	}

	public Polinom scadere(Polinom p, Polinom q) {
		Polinom r = new Polinom();
		int i = 0, j = 0;
		int sizeP = p.getMonoame().size();
		int sizeQ = q.getMonoame().size();
		Monom m;
		while (i < sizeP && j < sizeQ) {
			if (p.getMonoame().get(i).getGrad() == q.getMonoame().get(j).getGrad()) {

				m = new Monom(p.getMonoame().get(i).getCoef() - q.getMonoame().get(j).getCoef(),
						p.getMonoame().get(i).getGrad());
				i++;
				j++;

			} else if (p.getMonoame().get(i).getGrad() > q.getMonoame().get(j).getGrad()) {
				m = new Monom(p.getMonoame().get(i).getCoef(), p.getMonoame().get(i).getGrad());
				i++;
			} else {
				m = new Monom(-q.getMonoame().get(j).getCoef(), q.getMonoame().get(j).getGrad());
				j++;
			}
			r.addMonom(m);
		}
		
		while (j < sizeQ) {
			m = new Monom(-q.getMonoame().get(j).getCoef(), q.getMonoame().get(j).getGrad());
			j++;
			r.addMonom(m);
		}
		while (i < sizeP) {
			m = new Monom(p.getMonoame().get(i).getCoef(), p.getMonoame().get(i).getGrad());
			i++;
			r.addMonom(m);
		}
		
		return r;
	}

	public Polinom inmultire(Polinom p, Polinom q) {
		List<Monom> m = new ArrayList<Monom>();
		for (Monom i : p.getMonoame()) {
			for (Monom j : q.getMonoame()) {
				m.add(i.inmultireMonom(i,j));
			}
		}
		Polinom r = new Polinom(m);
		r.reducereGrad(r);
		return r;
	}
	
	/*public RezultatImpartire impartire(Polinom p, Polinom q) {
		//Exceptie grade
		Polinom rezultat = new Polinom();
		Polinom rest = new Polinom();
		Monom aux1=rest.cautaMaxim(rest);
		Monom aux2=p.cautaMaxim(p);
		List<Monom> rezultatList =new ArrayList<Monom>();
		List<Monom> restList = p.getMonoame();
		List<Monom> qList = q.getMonoame();
		rezultat.setMonoame(rezultatList);
		rest.setMonoame(restList);
		int  i = 0;
		while( aux1.getGrad() >= aux2.getGrad()){

			rezultatList.add(restList.get(0).impartireMonom(restList.get(0),qList.get(0)));
			List<Monom> monom =new ArrayList<Monom>();
			monom.add(rezultatList.get(i++));
			Polinom m1 = new Polinom(monom);
			Polinom aux3 = inmultire(m1, q);
			Polinom aux4 = scadere(rest,aux3);
			restList = aux4.getMonoame();
			rest.setMonoame(restList);
			if( restList.size() == 0 )
				break;
		}
		rezultat.setMonoame(rezultatList);
		rest.setMonoame(restList);
		
	   return new RezultatImpartire(rezultat,rest);

	}*/
	public RezultatImpartire impartire2(Polinom I, Polinom D) {
		Polinom C=new Polinom();
		Monom aux=D.cautaMaxim(D);
		Monom aux1=I.cautaMaxim(I);
		if (aux.getGrad() > aux1.getGrad())
			return new RezultatImpartire(C,D);
		while (aux.getGrad() >= aux1.getGrad())
		{
			Monom md=D.cautaMaxim(D);
			Monom mi=I.cautaMaxim(I);
			Monom mc=md.impartireMonom(md, mi);
			C.monoame.add(mc);
			Polinom aux2=I.inmulMonom(mc);
			D=scadere(D,aux2);
		}
		return new RezultatImpartire(C,D);
	}
	
	public Polinom derivare(Polinom p) {
		Polinom r = new Polinom();
		for (Monom m : p.getMonoame()) {
			r.getMonoame().add(m.derivareMonom(m));
		}
		return r;
	}

	public Polinom integrare(Polinom p) {
		Polinom r = new Polinom();
		for (Monom m : p.getMonoame()) {
			r.getMonoame().add(m.integrareMonom(m));
		}
		return r;
	}
}
