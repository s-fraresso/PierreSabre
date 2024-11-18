package personnages;

public class Humain {	
	static final int NB_CONNAISSANCES_MAX = 30;
	
	private String nom;
	private String boissonFavorite;
	private int argent;
	protected int nbConnaissances = 0;
	protected Humain[] memoire = new Humain[NB_CONNAISSANCES_MAX];
	
	
		
	public Humain(String nom, String boissonFavorite, int argent) {
		this.nom = nom;
		this.boissonFavorite = boissonFavorite;
		this.argent = argent;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getArgent() {
		return argent;
	}
	
	protected void parler(String texte) {
		System.out.println("(" + nom + ") - " + texte);
	}
	
	public void direBonjour() {
		parler("Bonjour ! Je m'appelle " + nom + " et j'aime boire du " + boissonFavorite + ".");
	}
	
	public void boire() {
		parler("Mmmm, un bon verre de " + boissonFavorite + " ! GLOUPS !");
	}
	
	public void acheter(String bien, int prix) {
		if (prix > argent) {
			parler("je n'ai plus que " + argent + " sous en poche. Je ne peux même pas m'offrir un " + 
					bien + " à " + prix + " sous.");
		} else {
			parler("J'ai " + argent + " sous en poche. je vais pouvoir m'offrir un " + bien + " à " + prix + " sous.");
			argent -= prix;
		}
	}
	
	protected void gagnerArgent(int gain) {
		argent += gain;
	}
	
	protected void perdreArgent(int perte) {
		argent -= perte;
	}
	
	private void oublier(int nbOublis) {
		assert nbOublis <= nbConnaissances;
		for (int i = 0; i < nbOublis; ++i) {
			memoire[i] = null;
		}
		for (int j = nbOublis; j < nbConnaissances; ++j) {
			memoire[j - nbOublis] = memoire[j];
			memoire[j] = null;
		}
		nbConnaissances -= nbOublis;
	}
	
	private void memoriser(Humain autreHumain) {
		if (nbConnaissances < NB_CONNAISSANCES_MAX) {
			memoire[nbConnaissances++] = autreHumain;
		}
		else {
			oublier(1);
			memoriser(autreHumain);
		}
	}
	
	public void faireConnaissanceAvec(Humain autreHumain) {
		direBonjour();
		memoriser(autreHumain);
		autreHumain.repondre(this);
	}
	
	private void repondre(Humain autreHumain) {
		direBonjour();
		memoriser(autreHumain);
	}
	
	public void listerConnaissance() {
		if (nbConnaissances == 0) {
			parler("Je ne connais personne...");
		}
		else {
			StringBuilder bld = new StringBuilder();
			for (int i = 0; i < nbConnaissances; ++i) {
				bld.append(memoire[i].getNom());
				if (i < nbConnaissances - 1) {
					bld.append(", ");
				}
			}
			parler("Je connais beaucoup de monde dont : " + bld.toString());
		}
	}
	
}
