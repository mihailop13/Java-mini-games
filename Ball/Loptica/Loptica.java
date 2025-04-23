package Loptica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Loptica extends Aktivna_figura {

	private int precnik;
	private double brzina_x;
	private double brzina_y;
	private int counter = 0;

	public Loptica(Scene scene, int x, int y, int precnik, int ms) {
		super(scene, x, y, Color.GREEN, ms);
		Random rand = new Random();
		brzina_x = Math.round(-1 + 2 * rand.nextDouble());
		brzina_y = -1 + rand.nextDouble();
		//pomeri(brzina_x, brzina_y);
		this.precnik = precnik;
		this.ms = ms;
	}

	private synchronized void azuriraj() {
		brzina_x += 0.1 * brzina_x;
		brzina_y += 0.1 * brzina_y;
	}

	private /*synchronized*/ void detect_colision() {
		int i = 0;
		Figura f1 = scene.get_figura(i);
		try {
			while (true) {
				if (f1 instanceof Cigla) {
					Cigla tmp = (Cigla) f1;
					synchronized (this) {
						if (((this.y - this.precnik / 2 <= tmp.y + tmp.getHeight() / 2)
								&& (this.y - this.precnik / 2 >= tmp.y - tmp.getHeight() / 2))
								&& ((this.x + this.precnik / 2 <= tmp.x + tmp.getWidth() / 2)
										&& (this.x - this.precnik / 2 >= tmp.x - tmp.getWidth() / 2))
								&& !tmp.isPogodjeno()) {
							tmp.pokreni();
							brzina_y = -brzina_y;
						}
					}
				}
				i++;
				f1 = scene.get_figura(i);
			}
		} catch (IndexOutOfBoundsException e) {
		}

	}
	
	private /*synchronized*/ void detect_colision_igrac(Igrac igrac)
	{
		synchronized (this) {

			if (((this.y + this.precnik / 2 >= igrac.y - igrac.getHeight() / 2)
					&& (this.y + this.precnik / 2 <= igrac.y + igrac.getHeight() / 2))
					&& ((this.x + this.precnik / 2 <= igrac.x + igrac.getWidth() / 2)
							&& (this.x - this.precnik / 2 >= igrac.x - igrac.getWidth() / 2))) {
				brzina_y = -brzina_y;
			}
			if (this.y <= 0)
				brzina_y = -brzina_y;
		}
	}

	@Override
	public void run() {
		try {
			while (!nit.isInterrupted()) {
				synchronized (this) {
					if (brzina_x < 0 && this.x - this.precnik <= 0)
						brzina_x = -brzina_x;
					if (brzina_x > 0 && this.x + this.precnik >= scene.getWidth())
						brzina_x = -brzina_x;
					pomeri(brzina_x, brzina_y);
					counter++;
					if (counter == 100) {
						azuriraj();
						counter = 0;
					}
				}
				
				Thread.sleep(this.ms);
				try {
					int i = 0;
					Figura tmp = scene.get_figura(i);
					while (true) {
						if (tmp instanceof Igrac) {
							Igrac igrac = (Igrac) tmp;
							detect_colision();
							detect_colision_igrac(igrac);
							if (this.y > scene.getHeight()) {
								this.zaustavi();
								this.unisti();
							}

						}
						i++;
						tmp = scene.get_figura(i);
					}

				} catch (IndexOutOfBoundsException e) {
				}
			}
		} catch (InterruptedException e) {
		}
	}

	@Override
	public char getOznaka() {
		return 'L';
	}

	public /*synchronized*/ void iscrtaj(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x - this.precnik, this.y - this.precnik, 2 * this.precnik, 2 * this.precnik);
	}

	@Override
	public synchronized void pokreni() {
		
	}

}
