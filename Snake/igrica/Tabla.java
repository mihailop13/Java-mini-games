package igrica;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.util.Random;

import igrica.Pozicija.Smer;

public class Tabla extends Canvas implements Runnable {
	
	private int rows,columns, ms=500;
	private Zmija snake;
	private Muva bug;
	private Thread thread= new Thread(this);
	private Igra game;
	private Label lenght;
	private boolean works=false;
	private Smer direction, direction_last;
	
	public void setLenght(Label lenght) {
		this.lenght = lenght;
	}

	
	public void setDificulty(int ms)
	{	this.ms=ms;	}
	
	public int getRows() {
		return rows;
	}

	public boolean check_poistion(Pozicija position) throws ExceptionWrongPosition
	{ 
		if(position.getId_kolona()>this.columns || position.getId_vrsta()>this.columns || position.getId_vrsta()<0 || position.getId_kolona()<0 )
			throw new ExceptionWrongPosition();
		return this.snake.check(position);
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Tabla(int rows, int columns)
	{
		this.rows=rows;
		this.columns=columns;
		thread.start();
	}
	
	private void make_bug()
	{
		Random rand = new Random();
		Pozicija p;
		do
		{
			int x= 1+rand.nextInt(columns);
			int y= 1+rand.nextInt(rows);
			p= new Pozicija(x,y);
		}while(snake.check(p));
		bug= new Muva(p,Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		int width= getWidth()/rows;
		int height= getHeight()/columns;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(1, 1, width*rows, height*rows);
		g.setColor(Color.BLACK);
		g.drawRect(1, 1, getWidth()-3, height*rows);
		g.setColor(new Color(220, 220, 220));
		for(int i=0; i < getWidth()/width; i++)
			g.drawLine(2, i*height, width*rows-1, i*height);
		for(int i=0; i < getHeight()/height; i++)
			g.drawLine(i*width, 2, i*width, height*rows);
		if (snake!=null) snake.draw(g, this);
		if (bug!=null) bug.draw(g,this);
	}
	
	@Override
	public void run() {
		
		try {
			while(!Thread.interrupted())  //dokle god se ne prekine nit
			{
				synchronized (this) {
					while(!works) wait(); // kada zmijica jos nije kreirana ceka se
				}
				Thread.sleep(ms);
				update(); updateLabels();
			}
		}
		catch(InterruptedException e) {}
		
	}
	
	private void updateLabels() {
		if(this.snake.get_size()==0 || this.snake==null) return;
		this.lenght.setText("Duzina: " + this.snake.get_size());
		
	}


	private synchronized void update() {
		if(snake==null)
		{
			snake=new Zmija(new Pozicija(rows/2, columns/2), Color.GREEN );
			make_bug();
			repaint();
			return;
		}
		try {
			snake.move_figure(direction, this);
			direction_last=direction;
			if(snake.getPosition().equals(bug.getPosition()))
			{
				make_bug();
				snake.increase();
			}
			repaint();
		} catch (ExceptionWrongPosition e) {
			this.snake.setColor(Color.RED);
			stop();
			game.update(false);
			repaint();
		}
	}

	public void setGame(Igra igra) { this.game = igra; }
	
	public void setLabel(Label delova) { this.lenght = delova;}
	
	public synchronized void start_game(int width , int columns) { 
		
			this.rows=width;
			this.columns=columns;
			snake=null;
			bug=null;
			direction=direction_last=Smer.DESNO;
			works=true;
			notify();
	}
	
	public synchronized void move(Smer smer)
	{
		if(snake==null) return;
		if(Math.abs(direction.ordinal()-smer.ordinal())==2) return;
		this.direction=smer;
	}
	
	public void end()
	{
		thread.interrupt();
	}
	
	public void stop()
	{
		works=false;
	}
}
