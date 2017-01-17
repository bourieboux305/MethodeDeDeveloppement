package main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import DAO.DaoPersonne;
import DAO.DaoStock;
import bean.Commande;
import bean.Packs;
import bean.Personne;
import bean.Produit;
import bean.Stock;

import org.springframework.context.ApplicationContext;

@ContextConfiguration(locations = "/baseSource.xml")
public class CoreManager {


	Stock stock;
	Packs pack;
	Personne personne;


	public void init(){
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

		daoStock.close(this.stock);
	}

	public void menu(){
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		while(scanTest){
			System.out.println("** ---menu--- :");
			System.out.println("** appuyer sur 0 pour aller au menu");
			System.out.println("** appuyer sur 1 pour voir la liste des produits");
			System.out.println("**** appuyer sur 2 pour quitter et sauvegarder ****");
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
				this.close();
				scanTest=false;
				break;
			default:
				;
			}
		}
	}

	public void PrintListProduits(){
		Scanner sc=new Scanner(System.in);
		System.out.println("** --la liste des produvoiits--");
		int i;
		for(i=0;i<stock.getListeProduits().size();i++){
			System.out.println("** num ("+(i+1)+") :"+stock.getListeProduits().get(i).getNom());
			System.out.println("** \tprix: "+stock.getListeProduits().get(i).getPrix());
			System.out.println("** \tquantite: "+stock.getListeProduits().get(i).getQuantite());
		}
		System.out.println("** appuyer sur 0 pour aller au menu");
		System.out.println("** appuyer sur 1 pour ajouter un article aux stock");
		System.out.println("** appuyer sur 2 pour modifier un produit");
		System.out.println("** appuyer sur 3 pour supprimer un produit");
		boolean scanTest=true;
		while(scanTest){
			String choix=sc.next();
			switch (choix){
			case "0":
				this.menu();
				scanTest=false;
				break;
			case "1":
				this.addProduitStock();
				scanTest=false;
				break;
			case "2":
				this.editProduit();
				scanTest=false;
				break;
			case "3" :
				this.removeProduit();
				scanTest=false;
				break;
			default:
				;
			}
		}
	}

	public void addProduitStock(){
		Scanner sc=new Scanner(System.in);
		Produit p=new Produit();
		System.out.println("entrer le nom du produit :");
		p.setNom(sc.nextLine());
		System.out.println("entrer la quantitée du produit");
		p.setQuantite(sc.nextInt());
		System.out.println("entrer le prix unitaire du produit");
		p.setPrix(sc.nextDouble());
		stock.addProduit(p);
		this.PrintListProduits();

	}

	public void editProduit(){
		int num=0;
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("******* edition d'un produit ***********");
		while(scanTest){
			System.out.println("** taper le numero de l'article à modifier");
			num=sc.nextInt();
			if(num>0 && num <= stock.getListeProduits().size())
				break;
		}
		System.out.println("** entrer la nouvelle quantitée");
		stock.getListeProduits().get(num-1).setQuantite(sc.nextInt());
		System.out.println("** entrer la nouveau prix");
		stock.getListeProduits().get(num-1).setPrix(sc.nextDouble());
		this.PrintListProduits();
	}

	public void removeProduit(){
		int num=0;
		boolean scanTest=true;
		Scanner sc=new Scanner(System.in);
		System.out.println("******* suppression d'un produit ***********");
		while(scanTest){
			System.out.println("** taper le numero de l'article à supprimer");
			num=sc.nextInt();
			if(num>0 && num <= stock.getListeProduits().size())
				break;
		}
		stock.removeProduit(stock.getListeProduits().get(num-1));
		this.PrintListProduits();
	}

	
}
