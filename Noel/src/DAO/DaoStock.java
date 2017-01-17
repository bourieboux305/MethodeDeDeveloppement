package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import bean.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import bean.Personne;

public class DaoStock {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public ArrayList<Produit> listeProduit(){
		ArrayList<Produit> liste=new ArrayList<Produit>();
		String sql="select * from produit";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map row : rows) {
			Produit p=new Produit();
			p.setNom((String)row.get("nom"));
			p.setPrix((double)row.get("prix"));
			p.setQuantite((int)row.get("quantite"));
			liste.add(p);
		}
		return liste;
	}

	public void close(Stock stock){
		int i;
		String sql="delete from produit";
		jdbcTemplate.update(sql);
		for(i=0;i<stock.getListeProduits().size();i++){
			Produit p=stock.getListeProduits().get(i);
			sql="insert into produit values (?,?,?)";
			try{
				jdbcTemplate.update(sql,p.getNom(),p.getPrix(),p.getQuantite());
				
			}
			catch(Exception e){
			}
		}
	}

}
