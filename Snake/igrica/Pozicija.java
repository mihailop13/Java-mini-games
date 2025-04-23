package igrica;



public class Pozicija {
	public enum Smer {LEVO, GORE, DESNO, DOLE};
	
	public Pozicija(int vrsta, int kolona) {
		
		this.id_vrsta=vrsta;
		this.id_kolona=kolona;
		
	}

	public int getId_vrsta() {
		return id_vrsta;
	}

	public int getId_kolona() {
		return id_kolona;
	}

	private int id_vrsta, id_kolona;
	
	public void move_position(Smer smer)
	{
		switch(smer)
		{
		case LEVO:
			this.id_kolona-=1; break;
		case DESNO: 
			this.id_kolona+=1; break;
		case GORE: 
			this.id_vrsta-=1; break;
		case DOLE:
			this.id_vrsta+=1; break;
		}
	}
	
	public Pozicija make_position(Smer smer)
	{
		Pozicija p=new Pozicija(this.id_vrsta, this.id_kolona);
		p.move_position(smer);
		return p;
		
	}
	
	@Override
	public boolean equals(Object obj) {
		super.equals(obj);
		if(!(obj instanceof Pozicija))
			return false;
		Pozicija tmp= (Pozicija) obj;
		return tmp.getId_kolona()==this.id_kolona && tmp.getId_vrsta()==this.id_vrsta;
	}
}
