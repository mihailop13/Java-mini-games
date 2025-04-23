package igrica;

import java.awt.Color;
import java.awt.Graphics;

import igrica.Pozicija.Smer;

public class Muva extends Figura {

	public Muva(Pozicija position, Color color) {
		super(position, color);
	}

	
	@Override
	public void draw(Graphics g, Tabla t) {
		int width=t.getWidth()/t.getRows();
		int height=t.getHeight()/t.getColumns();
		g.setColor(this.color);
		g.drawLine((this.position.getId_kolona()-1)*width+width/2,(this.position.getId_vrsta()-1)*height, (this.position.getId_kolona()-1)*width+width/2,
				(this.position.getId_vrsta()-1)*height+height);
		g.drawLine((this.position.getId_kolona()-1)*width,(this.position.getId_vrsta()-1)*height+height/2, (this.position.getId_kolona()-1)*width+ width,
				(this.position.getId_vrsta()-1)*height+height/2);
		g.drawLine((this.position.getId_kolona()-1)*width,(this.position.getId_vrsta()-1)*height, (this.position.getId_kolona()-1)*width+ width,
				(this.position.getId_vrsta()-1)*height+height);
		g.drawLine((this.position.getId_kolona()-1)*width+ width,(this.position.getId_vrsta()-1)*height, (this.position.getId_kolona()-1)*width,
				(this.position.getId_vrsta()-1)*height+ height);
	}

	@Override
	public boolean check(Pozicija position) {
		return this.getPosition().equals(position);
	}

	@Override
	public void move_figure(Smer smer, Tabla t) throws ExceptionWrongPosition {
		//nema efekta
	}

}
