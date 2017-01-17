package main;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import DAO.DaoCommande;
import DAO.DaoPersonne;
import DAO.DaoStock;
import bean.*;

public class Core {
	Stock stock;
	Packs pack;
	Commande commande;
	public void init(){
		commande = new Commande();
		ApplicationContext appContext;
		appContext=new GenericXmlApplicationContext("baseSource.xml");
		DaoStock daoStock=(DaoStock)appContext.getBean(DaoStock.class);
		DaoPersonne daoPers=(DaoPersonne)appContext.getBean(DaoPersonne.class);


		Produit p1=new Produit();
		p1.setNom("coca");
		p1.setQuantite(4);
		p1.setPrix(1);
		Produit p2=new Produit();
		p2.setNom("sprite");
		p2.setQuantite(4);
		p2.setPrix(1);

		stock=new Stock();
		stock.setListeProduits(daoStock.listeProduit());

	}
	
	public void close(){
		ApplicationContext appContext;
		appContext=new GenericXmlApplicationContext("baseSource.xml");
		DaoStock daoStock=(DaoStock)appContext.getBean(DaoStock.class);
		DaoCommande daoCmd=(DaoCommande)appContext.getBean(DaoCommande.class);
		daoCmd.save(commande);
		daoStock.close(this.stock);
	}

	public void menu(){
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		while(scanTest){
			System.out.println("** ---menu--- :");
			System.out.println("** appuyer sur 0 pour aller au menu");
			System.out.println("** appuyer sur 1 pour voir la liste des produits");
			System.out.println("** appuyer sur 2 pour voir le contenu de votre commande");
			String choix=sc.next();
			switch (choix){
				case "0":
					this.menu();
					scanTest=false;
					break;
				case "1":
					this.PrintListProduits();
					scanTest=false;
					break;
				case "2":
					this.listPanier();
					scanTest=false;
					break;
				default:
					;
			}
		}
	}
	
	public void PrintListProduits(){
		Scanner sc=new Scanner(System.in);
		System.out.println("** --la liste des produits--");
		int i;
		for(i=0;i<stock.getListeProduits().size();i++){
			System.out.println("** num ("+(i+1)+") :"+stock.getListeProduits().get(i).getNom());
			System.out.println("** \tprix: "+stock.getListeProduits().get(i).getPrix());
			System.out.println("** \tquantite: "+stock.getListeProduits().get(i).getQuantite());
		}
		System.out.println("** appuyer sur 0 pour aller au menu");
		System.out.println("** appuyer sur 1 pour ajouter un article dans le panier");
		boolean scanTest=true;
		while(scanTest){
			String choix=sc.next();
			switch (choix){
				case "0":
					this.menu();
					scanTest=false;
					break;
				case "1":
					this.addProduitPanier();
					scanTest=false;
					break;
				default:
					;
			}
		}
	}
	
	public void addProduitPanier(){
		Scanner sc=new Scanner(System.in);
		int num=0,quantite=0;
		boolean scanTest=true;
		while(scanTest){
			System.out.println("**** taper le numero du produit que vous voulez");
			num=sc.nextInt();
			if(num>0 && num <= stock.getListeProduits().size())
				break;
		}
		while(scanTest){
			System.out.println("**** taper la quantite du produit que vous avez choisi");
			quantite=sc.nextInt();
			if(quantite>0 && quantite <= stock.getListeProduits().get(num-1).getQuantite())
				break;
		}
		Produit p=new Produit();
		p.setNom(stock.getListeProduits().get(num-1).getNom());
		p.setPrix(stock.getListeProduits().get(num-1).getPrix());
		p.setQuantite(quantite);
		commande.addProduit(p);
		commande.setPrixTotal(commande.getPrixTotal()+(p.getPrix()*p.getQuantite()));
		if(stock.getListeProduits().get(num-1).getQuantite()==quantite){
			stock.removeProduit(stock.getListeProduits().get(num-1));
			
		}
		else{
			stock.getListeProduits().get(num-1).setQuantite(stock.getListeProduits().get(num-1).getQuantite() - quantite);
		}
		this.menu();
		
	}
	
	public void listPanier(){
		System.out.println("** contenue de votre panier :");
		int i;
		for(i=0;i<commande.getListe().size();i++){
			System.out.println("** \t-(num"+(i+1)+")"+commande.getListe().get(i).getNom());
			System.out.println("** \t\t-quantite:"+commande.getListe().get(i).getQuantite());
			System.out.println("** \t\t-prix unitaire:"+commande.getListe().get(i).getPrix());
		}
		System.out.println("********* prix total = "+commande.getPrixTotal()+" ***************");
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		while(scanTest){
			System.out.println("** appuyer sur 0 pour aller au menu");
			System.out.println("** appuyer sur 1 pour voir modifier de votre commande");
			System.out.println("** appuyer sur 2 pour valider la commande");
			String choix=sc.next();
			switch (choix){
				case "0":
					this.menu();
					scanTest=false;
					break;
				case "1":
					this.editPanier();;
					scanTest=false;
					break;
				case "2":
					this.validationCommande();
					scanTest=false;
					break;
				default:
					;
			}
		}
	}
	
	public void validationCommande(){
		if(commande.getPersonne()==null){
			ApplicationContext appContext;
			appContext=new GenericXmlApplicationContext("baseSource.xml");
			DaoPersonne daoPers=(DaoPersonne)appContext.getBean(DaoPersonne.class);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("***** connexion *******");
			System.out.println("** entrer votre e-mail:");
			String mail=sc.next();
			System.out.println("** entrer votre mot de passe:");
			String mdp=sc.next();
			Personne personne=daoPers.connexion(mail, mdp);
			if(personne!=null){
					commande.setPersonne(personne);
					this.listPanier();
			}
			else{
				System.out.println("!!!! connexion échoué !!!!");
				boolean scanTest=true;
				while(scanTest){
					System.out.println("** appuyer sur 0 pour revenir a la liste du panier");
					System.out.println("** appuyer sur 1 pour se réessayer");
					String choix=sc.next();
					switch (choix){
					case "0":
						this.listPanier();;;
						scanTest=false;
						break;
					case "1":
						this.validationCommande();
						scanTest=false;
						break;
					default:
						;
					}
				}
			}
		}
		else
			this.close();
	}
	
	public void editPanier(){
		int num=0,quantite=0;
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("******* edition du panier ***********");
		while(scanTest){
			System.out.println("** taper le numero du commande à modifier");
			num=sc.nextInt();
			if(num>0 && num <= commande.getListe().size())
				break;
		}
		while(scanTest){
			System.out.println("** taper la quantitée à soustraire");
			quantite=sc.nextInt();
			if(quantite>=0 && quantite <= commande.getListe().get(num-1).getQuantite())
				break;
		}
		int i;
		boolean find=false;
		Produit p=commande.getListe().get(num-1);
		if(quantite== p.getQuantite()){
			//remetre dans stock avant de supprimer
			for(i=0;i<stock.getListeProduits().size();i++){
				if(stock.getListeProduits().get(i).getNom().compareTo(p.getNom())==0){
					stock.getListeProduits().get(i).setQuantite(quantite +stock.getListeProduits().get(i).getQuantite() );
					find=true;
				}
			}
			if(!find)
				stock.addProduit(p);
			commande.removeProduit(p);
		}
		else{
			for(i=0;i<stock.getListeProduits().size();i++){
				if(stock.getListeProduits().get(i).getNom().compareTo(p.getNom())==0){
					stock.getListeProduits().get(i).setQuantite(quantite +stock.getListeProduits().get(i).getQuantite() );
					find=true;
				}
			}
			if(!find)
				stock.addProduit(p);
			commande.getListe().get(num-1).setQuantite(p.getQuantite() - quantite);
		}
		commande.setPrixTotal(commande.getPrixTotal() - (p.getPrix()*quantite));
		this.menu();
	}
	


}
