package personnages;
import java.util.Random;

public class GrandMere extends Humain {
	private static final int NB_CONNAISSANCES_MAX = 5;
	
	private enum TypeHumain {
		HUMAIN("habitant"), COMMERCANT("commerçant"), RONIN("ronin"), SAMOURAI("samourai"), YAKUZA("yakuza"), GRAND_MERE("grand-mère");
		
		private String nom;
		
		private TypeHumain(String nom) {
			this.nom = nom;
		}
		
		@Override
		public String toString() {
			return nom;
		}
	}
	
	private Random rand = new Random();

	public GrandMere(String nom, int argent) {
		super(nom, "tisane", argent);
	}

	@Override
	protected void memoriser(Humain autreHumain) {
		if (nbConnaissances < NB_CONNAISSANCES_MAX) {
			super.memoriser(autreHumain);
		}
		else {
			parler("Oh ma tête ! Je ne peux plus retenir le nom d'une personne supplémentaire !");
		}
	}
	
	private String humainHasard() {
		TypeHumain typeAleatoire = TypeHumain.values()[rand.nextInt(TypeHumain.values().length)];
		return typeAleatoire.toString();
	}
	
	public void ragoter() {
		Humain connaissance;
		for (int i = 0; i < nbConnaissances; ++i) {
			connaissance = memoire[i];
			if (connaissance instanceof Traitre) {
				parler("Je sais que " + connaissance.getNom() + " est un traitre. Petit chenapan !");
			}
			else {
				parler("Je crois que " + connaissance.getNom() + " est un " + humainHasard());
			}
		}
	}
}
