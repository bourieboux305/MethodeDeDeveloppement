package bean;

import java.util.ArrayList;

public class Packs {

	private String nom;
	private ArrayList<Produit> liste;
	private double prix;
	
	public Packs(){
		liste=new ArrayList<Produit>();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<Produit> getListe() {
		return liste;
	}
	
	public void setListe(ArrayList<Produit> liste) {
		this.liste = liste;
	}
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}	
	
	public void addProduit(Produit produit){
		this.liste.add(produit);
	}
	
	public void removeProduit(Produit produit){
		int i;
		for(i=0;i<liste.size();i++){
			if(liste.get(i).getNom()==produit.getNom() && 
					liste.get(i).getPrix()==produit.getPrix())
				liste.remove(i);
		}
	}
}
