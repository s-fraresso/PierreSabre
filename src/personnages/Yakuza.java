package personnages;

public class Yakuza extends Humain {
	private String clan;
	private int reputation = 0;
	
	public Yakuza(String nom, String boissonFavorite, int argent, String clan) {
		super(nom, boissonFavorite, argent);
		this.clan = clan;
	}
	
	public int getReputation() {
		return reputation;
	}
	
	public void extorquer(Commercant victime) {
		parler("Tiens, tiens, ne serait-ce pas un faible marchand qui passe par l� ?");
		parler(victime.getNom() + ", si tu tiens � la vie donne moi ta bourse !");
		int argentExtorque = victime.seFaireExtorquer();
		gagnerArgent(argentExtorque);
		parler("J�ai piqu� les " + argentExtorque + " sous de " + victime.getNom() + ", ce qui me fait " + getArgent() + " sous dans ma poche. Hi ! Hi !");
	}
	
	public int perdre() {
		int perte = getArgent();
		perdreArgent(perte);
		reputation -= 1;
		parler("�ai perdu mon duel et mes " + perte + " sous, snif... J'ai d�shonor� le clan de " + clan + ".");
		return perte;
	}
	
	public void gagner(int gain) {
		gagnerArgent(gain);
		reputation += 1;
		parler("Ce ronin pensait vraiment battre " + getNom() + " du clan de " + clan + " ? Je l'ai d�pouill� de ses " + gain + " sous.");
	}
}
