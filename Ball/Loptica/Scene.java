package Loptica;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Scene extends Canvas implements Runnable {

	private ArrayList<Figura> figure;
	private Thread nit;

	public Scene() {
		figure = new ArrayList<Figura>();
		setSize(new Dimension(300, 300));
		nit = new Thread(this);
		nit.start();
	}

	public synchronized void dodaj(Figura f) { 	// mora bude synchronized posto moze da se doda usred koriscenja liste
												// figure kroz nit
		this.figure.add(f);
	}

	public synchronized void ukloni(Figura f) {
		this.figure.remove(this.figure.indexOf(f));
	}

	public void pokreni() {
		for (Figura f : figure) {
			if (f instanceof Aktivna_figura) {
				Aktivna_figura tmp = (Aktivna_figura) f;
				tmp.pokreni();
			}
		}
	}

	public void end() {
		for (Figura f : figure) {
			if (f instanceof Aktivna_figura) {
				Aktivna_figura tmp = (Aktivna_figura) f;
				tmp.zaustavi();
			}
		}
		this.nit.interrupt();
	}

	public Figura get_figura(int id) {
		return this.figure.get(id);
	}

	@Override
	public synchronized void paint(Graphics g) {

		for (Figura f : figure) {
			f.iscrtaj(g);
		}

	}

	@Override
	public void run() {
		while (!nit.isInterrupted()) {
			repaint();
		}
	}
}
