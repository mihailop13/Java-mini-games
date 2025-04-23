package Loptica;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura {

	private int width, height;

	public Igrac(Scene scene, int x, int y, int width, int height) {
		super(scene, x, y, Color.BLUE);
		this.width = width;
		this.height = height;
	}

	@Override
	public char getOznaka() {
		return 'I';
	}

	@Override
	public /*synchronized*/ void iscrtaj(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x - width / 2, y, width, height);
	}

	@Override
	public /*synchronized*/ void pomeri(double pomeraj_x, double pomeraj_y) {
		if (x + pomeraj_x - width / 2 < 0 || x + pomeraj_x + width / 2 > scene.getWidth())
			return;
		super.pomeri(pomeraj_x, pomeraj_y);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
