package DEL;

public class Pracownik {



	private static long liczbaPracownikow=0;

	public long getId() {
		return id;
	}

	private long id;
	private boolean stanPracy = false;

	public Pracownik() {
		liczbaPracownikow++;
		id = liczbaPracownikow;
	}


	public boolean getStanPracy() {
		return this.stanPracy;
	}

	public void setStanPracy(boolean stanPracy) {
		this.stanPracy = stanPracy;
	}

}