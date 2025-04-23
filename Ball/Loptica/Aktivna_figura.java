package Loptica;

import java.awt.Color;

public abstract class Aktivna_figura extends Figura implements Runnable {

	//protected boolean aktivna;
	protected Thread nit;
	protected int ms;

	public Aktivna_figura(Scene scene, int x, int y, Color color, int ms) {
		super(scene, x, y, color);
		this.nit = new Thread(this);
		nit.start();
		this.ms=ms;
	}
	public abstract void pokreni();

	public void zaustavi() {
		this.nit.interrupt();
	}
}
