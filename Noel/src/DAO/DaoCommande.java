package DAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bean.Commande;
import bean.Produit;

public class DaoCommande {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void save(Commande commande){
		String sql="insert into commande (email,nom,quantite,prix) values (?,?,?,?)";
		try{
			int i;
			for(i=0;i<commande.getListe().size();i++){
				Produit p=commande.getListe().get(i);
				jdbcTemplate.update(sql,commande.getPersonne().geteMail(),p.getNom(),p.getQuantite(),p.getPrix());
			}
		}
		catch(Exception e){
		}
	}
	
}
