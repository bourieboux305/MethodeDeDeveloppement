package DAO;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import bean.Personne;

public class DaoPersonne {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Personne connexion(String eMail,String pass_word){
		Personne p;
		String sql="select * from personne where eMail='"+eMail+"'";
		try{
			p=(Personne) jdbcTemplate.queryForObject(sql,
				new BeanPropertyRowMapper(Personne.class));
		}
		catch(Exception e){
			p=null;
		}
		return p;
	}
	
	public boolean insription(String nom,String prenom,String email,String pwd){
		boolean succes=false;
		String sql="insert into personne values (?,?,?,?)";
		try{
			jdbcTemplate.update(sql,nom,prenom,email,pwd);
			succes=true;
		}
		catch(Exception e){
		}
		return succes;
	}
	
}
