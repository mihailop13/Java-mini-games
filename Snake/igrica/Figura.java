package igrica;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {

	protected Pozicija position;
	public Pozicija getPosition() {
		return position;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected Color color;
	
	public Figura(Pozicija position, Color color)
	{
		this.position=position; this.color=color;
	}
	
	public abstract void draw(Graphics g, Tabla t);
	
	public abstract boolean check(Pozicija position);
	
	public abstract void move_figure(Pozicija.Smer smer, Tabla t) throws ExceptionWrongPosition;
}
