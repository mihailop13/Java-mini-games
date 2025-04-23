package igrica;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import igrica.Pozicija.Smer;

public class Zmija extends Figura {
	
	ArrayList<Pozicija> positions= new ArrayList<Pozicija>(); 

	public Zmija(Pozicija position, Color color) {
		super(position, color);
		positions.add(position);
	}

	@Override
	public void draw(Graphics g, Tabla t) {
		g.setColor(this.color);
		int width=t.getWidth()/t.getRows();
		int height=t.getHeight()/t.getColumns();
		for(Pozicija p : positions)
		{
			g.fillOval((p.getId_kolona()-1)*width, (p.getId_vrsta()-1)*height,t.getWidth()/t.getRows(),t.getHeight()/t.getColumns());
			if(p.equals(positions.get(0)))
			{
				g.setColor(Color.WHITE);
				g.fillOval((p.getId_kolona()-1)*width+width/4, (p.getId_vrsta()-1)*height+ height/4,width/2,height/2);
				g.setColor(this.color);
			}
		}
	}

	@Override
	public boolean check(Pozicija position) {
		return positions.contains(position);
	}

	//kad se napravi tabla dodadati u argument tablu
	@Override
	public void move_figure(Smer smer, Tabla t) throws ExceptionWrongPosition{
		Pozicija temp= this.position.make_position(smer);
		if(temp.equals(this.positions.get(this.positions.size()-1)) || t.check_poistion(temp)) throw new ExceptionWrongPosition();
		positions.remove(get_size()-1);
		positions.add(0, position=temp);
	}

	public int get_size()
	{
		return positions.size();
	}
	
	public void increase()
	{
		positions.add(positions.get(get_size()-1));
	}
}
