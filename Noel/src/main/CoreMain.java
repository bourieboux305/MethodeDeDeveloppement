package main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import DAO.DaoPersonne;
import bean.Personne;
@ContextConfiguration(locations = "/baseSource.xml")
public class CoreMain {

	Personne personne;
	Core core;
	CoreManager coreM;
	
	public void init(){
		core=new Core();
		core.init();
		coreM=new CoreManager();
		coreM.init();
	}
	
	public void bienvenue(){
		Scanner sc=new Scanner(System.in);
		boolean scanTest=true;
		while(scanTest){
			System.out.println("***** bonjour *******");
			System.out.println("** appuyer sur 0 pour aller au menu");
			System.out.println("** appuyer sur 1 pour se connecter");
			System.out.println("** appuyer sur 2 pour s'inscrire");
			String choix=sc.next();
			switch (choix){
			case "0":
				core.menu();
				scanTest=false;
				break;
			case "1":
				this.connexion();
				scanTest=false;
				break;
			case "2":
				this.insciption();
				scanTest=false;
				break;
			default:
				;
			}
		}
	}
	
	public void insciption(){
		ApplicationContext appContext;
		appContext=new GenericXmlApplicationContext("baseSource.xml");
		DaoPersonne daoPers=(DaoPersonne)appContext.getBean(DaoPersonne.class);
		
		Scanner sc=new Scanner(System.in);
		String nom,prenom,email,pwd;
		System.out.println("***** inscription *****");
		System.out.println("** entrer votre nom");
		nom=sc.next();
		System.out.println("** entrer votre prenom");
		prenom=sc.next();
		System.out.println("** entrer votre adresse e-mail");
		email=sc.next();
		System.out.println("** entrer votre mot de passe");
		pwd=sc.next();
		if(daoPers.insription(nom, prenom, email, pwd)){
			personne=new Personne();
			personne.setNom(nom);
			personne.setPrenom(prenom);
			personne.seteMail(email);
			core.commande.setPersonne(personne);
			core.menu();
		}
		else{
			System.out.println("**** inscription échoué. e-mail déja utiliser.");
			boolean scanTest=true;
			while(scanTest){
				System.out.println("** appuyer sur 0 pour revenir");
				System.out.println("** appuyer sur 1 pour se réessayer");
				String choix=sc.next();
				switch (choix){
				case "0":
					this.bienvenue();;
					scanTest=false;
					break;
				case "1":
					this.insciption();
					scanTest=false;
					break;
				default:
					;
				}
			}
		}
		
	}

	public void connexion(){
		ApplicationContext appContext;
		appContext=new GenericXmlApplicationContext("baseSource.xml");
		DaoPersonne daoPers=(DaoPersonne)appContext.getBean(DaoPersonne.class);
		
		Scanner sc=new Scanner(System.in);
		System.out.println("***** connexion *******");
		System.out.println("** entrer votre e-mail:");
		String mail=sc.next();
		System.out.println("** entrer votre mot de passe:");
		String mdp=sc.next();
		personne=daoPers.connexion(mail, mdp);
		if(personne!=null){
			if(personne.geteMail().compareTo("admin")==0)
				coreM.menu();
			else{
				core.commande.setPersonne(personne);
				core.menu();
			}
				
		}
		else{
			System.out.println("!!!! connexion échoué !!!!");
			boolean scanTest=true;
			while(scanTest){
				System.out.println("** appuyer sur 0 pour revenir");
				System.out.println("** appuyer sur 1 pour se réessayer");
				String choix=sc.next();
				switch (choix){
				case "0":
					this.bienvenue();;
					scanTest=false;
					break;
				case "1":
					this.connexion();
					scanTest=false;
					break;
				default:
					;
				}
			}
		}
	}

	public static void main(String[] args){
		CoreMain coreM=new CoreMain();
		coreM.init();
		coreM.bienvenue();
	}
	
}
