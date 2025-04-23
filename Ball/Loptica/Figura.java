package Loptica;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	protected int x, y; // koordinate centra
	protected Color color; // boja figure
	protected Scene scene; // zadata scena

	public Figura(Scene scene, int x, int y, Color color) {
		this.scene = scene;
		this.x = x;
		this.y = y;
		this.color = color;
		scene.dodaj(this); // odmah je stavljamo na scenu
	}

	public void unisti() {
		scene.ukloni(this);
	}

	public abstract char getOznaka();

	public synchronized void pomeri(double pomeraj_x, double pomeraj_y) {
		x += pomeraj_x;
		if (pomeraj_y < 0)
			y -= 1;
		else if (pomeraj_y > 0)
			y += 1;
		y += pomeraj_y;
	}

	public abstract void iscrtaj(Graphics g);
}
