package tema1;

import javax.swing.*;


public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel fereastra = new JPanel();
	Controller controller = new Controller(this);
	private JPanel panel1 = new JPanel();
	private JLabel label1 = new JLabel("Polinom 1:");
	private JTextField textPolinom1 = new JTextField("", 15);

	private JPanel panel2 = new JPanel(); 
	private JLabel label2 = new JLabel("Polinom 2:");
	private JTextField textPolinom2 = new JTextField("", 15);

	private JPanel panel3 = new JPanel();
	private JButton adunare = new JButton("+");
	private JButton scadere = new JButton("-");
	private JButton inmultire = new JButton("*");
	private JButton impartire = new JButton("/");
	private JButton derivare = new JButton("'");
	private JButton integrare = new JButton("\u222Bdx");
	
	private JPanel panel4 = new JPanel();
	private JLabel label3 = new JLabel("Rezultat");
	private JTextField rezultat = new JTextField("", 20);
	private JButton resetare = new JButton("Resetare");


	public JButton getAdunare() {
		return adunare;
	}

	public JButton getScadere() {
		return scadere;
	}

	public JButton getInmultire() {
		return inmultire;
	}

	public JButton getImpartire() {
		return impartire;
	}

	public JButton getDerivare() {
		return derivare;
	}

	public JButton getIntegrare() {
		return integrare;
	}
	
	public View() {

		fereastra.setLayout(new BoxLayout(fereastra, BoxLayout.PAGE_AXIS));

		panel1.add(label1);
		panel1.add(textPolinom1);
		panel2.add(label2);
		panel2.add(textPolinom2);
		panel3.add(adunare);
		panel3.add(scadere);
		panel3.add(inmultire);
		panel3.add(impartire);
		panel3.add(derivare);
		panel3.add(integrare);
		panel4.add(label3);
		panel4.add(rezultat);
		panel4.add(resetare);
	

		adunare.addActionListener(controller);
		scadere.addActionListener(controller);
		inmultire.addActionListener(controller);
		impartire.addActionListener(controller);
		derivare.addActionListener(controller);
		integrare.addActionListener(controller);
		resetare.addActionListener(controller);
		
		fereastra.add(panel1);
		fereastra.add(panel2);
		fereastra.add(panel3);
		fereastra.add(panel4);

		add(fereastra);
	}
	
	public JLabel getLabel1() {
		return label1;
	}
	
	public JTextField getTextPolinom1() {
		return textPolinom1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public JTextField getTextPolinom2() {
		return textPolinom2;
	}
	
	public JLabel getLabel3() {
		return label3;
	}

	public JButton getResetare() {
		return resetare;
	}
	
	public JTextField getRezultat() {
		return rezultat;
	}

	public static void main(String[] args) {
		View fereastra = new View();
		fereastra.setTitle("Calculator de polinoame");
		fereastra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fereastra.setSize(400, 400);
		fereastra.setVisible(true);
	}

}
