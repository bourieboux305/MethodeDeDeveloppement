package bean;

import java.util.ArrayList;

public class Commande {

	private Personne personne=null;
	private ArrayList<Produit> liste;
	private ArrayList<Packs> pack;
	private double prixTotal;
	
	public Commande(){
		liste=new ArrayList<Produit>();
		pack=new ArrayList<Packs>();
	}
	
	
	public ArrayList<Packs> getPack() {
		return pack;
	}


	public void setPack(ArrayList<Packs> pack) {
		this.pack = pack;
	}


	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public ArrayList<Produit> getListe() {
		return liste;
	}
	public void setListe(ArrayList<Produit> liste) {
		this.liste = liste;
	}
	public double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}
	
	public void addProduit(Produit produit){
		liste.add(produit);
	}
	
	public void removeProduit(Produit produit){
		int i;
		for(i=0;i<liste.size();i++){
			if(liste.get(i).getNom()==produit.getNom() && 
					liste.get(i).getPrix()==produit.getPrix())
				liste.remove(i);
		}
	}
	
	public void addPack(Packs pack){
		this.pack.add(pack);
	}
	
	public void removePack(Packs pack){
		int i;
		for(i=0;i<this.pack.size();i++){
			if(this.pack.get(i).getNom()==pack.getNom() && 
					this.pack.get(i).getPrix()==pack.getPrix())
				this.pack.remove(i);
		}
	}
	
}
