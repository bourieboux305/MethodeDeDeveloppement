package bean;

import java.util.ArrayList;

public class Stock {

	private ArrayList<Produit> listeProduits;
	
	public Stock(){
		listeProduits=new ArrayList<Produit>();
	}

	public ArrayList<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(ArrayList<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	
	public void addProduit(Produit produit){
		listeProduits.add(produit);
	}
	
	public void removeProduit(Produit produit){
		int i;
		for(i=0;i<listeProduits.size();i++){
			if(listeProduits.get(i).getNom()==produit.getNom() && 
					listeProduits.get(i).getPrix()==produit.getPrix())
				listeProduits.remove(i);
		}
	}
	
	
}
