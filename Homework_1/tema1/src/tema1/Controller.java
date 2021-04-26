package tema1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controller implements ActionListener {
	private View view;
	
	public Controller(View v) {
		this.view=v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Operatii op=new Operatii();
		String polinom1 = view.getTextPolinom1().getText();
		String polinom2 = view.getTextPolinom2().getText();

		if (polinom1.length() != 0 && polinom2.length() != 0) {

			Polinom poliP = new Polinom(polinom1);
			Polinom poliQ = new Polinom(polinom2);

			if (poliP.citirePolinom == true && poliQ.citirePolinom == true) {
				if (e.getSource() == view.getAdunare()) {
					view.getTextPolinom1().setEnabled(true);
					view.getTextPolinom2().setEnabled(true);
					view.getRezultat().setEnabled(true);
					view.getLabel1().setEnabled(true);
					view.getLabel2().setEnabled(true);
				    view.getRezultat().setText(op.adunare(poliQ,poliP).toString());
				}
				if (e.getSource() == view.getScadere()) {
					view.getTextPolinom1().setEnabled(true);
					view.getTextPolinom2().setEnabled(true);
					view.getRezultat().setEnabled(true);
					view.getLabel1().setEnabled(true);
					view.getLabel2().setEnabled(true);
				    view.getRezultat().setText(op.scadere(poliP,poliQ).toString());
				}
				if (e.getSource() == view.getInmultire()) {
					view.getTextPolinom1().setEnabled(true);
					view.getTextPolinom2().setEnabled(true);
					view.getRezultat().setEnabled(true);
					view.getLabel1().setEnabled(true);
					view.getLabel2().setEnabled(true);
				    view.getRezultat().setText(op.inmultire(poliP,poliQ).toString());
				}
				if (e.getSource() == view.getImpartire()) {
					RezultatImpartire r=op.impartire2(poliP, poliQ);
					view.getTextPolinom1().setEnabled(true);
					view.getTextPolinom2().setEnabled(true);
					view.getRezultat().setEnabled(true);
					view.getLabel1().setEnabled(true);
					view.getLabel2().setEnabled(true);
				    view.getRezultat().setText(r.toString());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Introduceti polinoamele conform instructiunii",
						"Introducere invalida", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			view.getTextPolinom1().setEnabled(true);
			view.getLabel2().setEnabled(true);
			if (e.getSource() != view.getIntegrare() && e.getSource() != view.getDerivare() && e.getSource() != view.getResetare()) {
				JOptionPane.showMessageDialog(null, "Introduceti polinoamele", "Introducere invalida",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource() == view.getIntegrare()) {
			view.getTextPolinom1().setEnabled(true);
			view.getTextPolinom2().setEnabled(false);
			view.getRezultat().setEnabled(true);
			view.getLabel1().setEnabled(true);
			view.getLabel2().setEnabled(false);
			if (polinom1.length() != 0) {
				Polinom poli = new Polinom(polinom1);
				view.getRezultat().setText(op.integrare(poli).toString());
			} else {
				JOptionPane.showMessageDialog(null, "Introduceti polinomul P", "Introducere invalida",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource() == view.getDerivare()) {
			view.getTextPolinom1().setEnabled(true);
			view.getTextPolinom2().setEnabled(false);
			view.getRezultat().setEnabled(true);
			view.getLabel1().setEnabled(true);
			view.getLabel2().setEnabled(false);
			if (polinom1.length() != 0) {
				Polinom poli = new Polinom(polinom1);
				view.getRezultat().setText(op.derivare(poli).toString());
			} else {
				JOptionPane.showMessageDialog(null, "Introduceti polinomul P", "Introducere invalida",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (e.getSource() == view.getResetare()) {
			view.getTextPolinom1().setEnabled(true);
			view.getTextPolinom2().setEnabled(true);
			view.getRezultat().setEnabled(true);
			view.getLabel1().setEnabled(true);
			view.getLabel2().setEnabled(true);
		    view.getRezultat().setText("");
		}
	}	

}

