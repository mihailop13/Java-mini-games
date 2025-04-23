package Loptica;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Igra_Loptica extends Frame {

	private Scene scena;
	private Igrac igrac;

	public Igra_Loptica() {
		setTitle("Loptica");
		setBounds(700, 300, 300, 300);
		scena = new Scene();
		setLayout(new BorderLayout());
		dodaj_figure();
		this.add(scena, BorderLayout.CENTER);
		scena.requestFocus();
		setResizable(false);
		addListeners();
		pack();
		setVisible(true);
	}

	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scena.end();
				dispose();
			}
		});

		scena.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					igrac.pomeri(-5, 0);
					break;
				case KeyEvent.VK_RIGHT:
					igrac.pomeri(5, 0);
					break;
				}
			}
		});

		scena.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new Loptica(scena, igrac.x, igrac.y - igrac.getHeight() / 2 , igrac.getHeight() / 2 , 10);
			}
		});
	}

	private void dodaj_figure() {
		int pomeraj_x = scena.getWidth() / 10;
		int yGap = 2;
		for (int k = 1; k <= 5; k += 2) {
			int mul = 1;
			for (int i = 1; i <= 5; i++) {
				new Cigla(scena, mul * pomeraj_x, k * 5 + yGap, 2 * (pomeraj_x) - 2, 10, 20);
				mul = mul + 2;
			}
			yGap += 2;
		}
		igrac = new Igrac(scena, scena.getWidth() / 2, scena.getHeight() - 20, 40, 9);
	}
}
