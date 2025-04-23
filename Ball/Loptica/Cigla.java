package Loptica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Cigla extends Aktivna_figura {

	private int width, height;
	private boolean pogodjeno;

	public Cigla(Scene scene, int x, int y, int width, int height, int ms) {
		super(scene, x, y, Color.RED, ms);
		this.width = width;
		this.height = height;
	}

	@Override
	public char getOznaka() {
		return 'C';
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void iscrtaj(Graphics g) {
		if(isPogodjeno())
			g.setColor(Color.GRAY);
		else
			g.setColor(this.color);
		g.fillRect(x - this.getWidth() / 2, y - this.getHeight() / 2, width, height);
	}

	public boolean isPogodjeno() {
		return pogodjeno;
	}

	@Override
	public void run() {
		try {
			while (!nit.isInterrupted()) {
				synchronized (this) {
					while (!pogodjeno) {
						wait();
					}
				}
				if (isPogodjeno() && this.y + height/2 > scene.getHeight()) {
					this.zaustavi();
					this.unisti();
				}
				else
					pomeri(0,5);
				Thread.sleep(ms);
			}
		} catch (InterruptedException e) {
		}
	}

	@Override
	public synchronized void pokreni() {
		pogodjeno=true;
		this.notify();
	}

}
