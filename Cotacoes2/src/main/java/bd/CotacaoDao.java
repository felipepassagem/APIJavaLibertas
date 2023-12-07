package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;



public class CotacaoDao {
	
	public void inserir(Cotacao c) {
		Conexao con = new Conexao();
		String sql = "INSERT INTO cotacoes(nome, local, preco, data) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement pst = con.getConnection().prepareStatement(sql);
			pst.setString(1, c.getNome());
			pst.setString(2, c.getLocal());
			pst.setFloat(3, c.getPreco());
			java.sql.Date sqlDate = new java.sql.Date(c.getData().getTime());
	        pst.setDate(4, sqlDate);
			pst.execute();			
			
		} catch( Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
	}
	
	public void alterar(Cotacao c) {
		Conexao con = new Conexao();
		String sql = "UPDATE cotacoes SET nome = ?, local = ?, preco = ?, data = ? WHERE id = ?";
		try {
			System.out.println(c.getId());
			System.out.println(c.getNome());
			System.out.println(c.getLocal());
			System.out.println(c.getPreco());
			System.out.println(c.getData());


			PreparedStatement pst = con.getConnection().prepareStatement(sql);
			pst.setString(1, c.getNome());
			pst.setString(2, c.getLocal());
			pst.setFloat(3, c.getPreco());
			java.sql.Date sqlDate = new java.sql.Date(c.getData().getTime());
	        pst.setDate(4, sqlDate);
			pst.setInt(5, c.getId());
			pst.execute();			
			
		} catch( Exception e) {
			e.printStackTrace();
		}
		con.desconectar();	
	}
	
	public void excluir(Cotacao c) {
		Conexao con = new Conexao();
		String sql = "DELETE FROM cotacoes WHERE id = ?";
		try {
			PreparedStatement pst = con.getConnection().prepareStatement(sql);
			System.out.println(c.getId());
			pst.setInt(1, c.getId());
			pst.execute();			
		} catch( Exception e) {
			e.printStackTrace();
		}
		con.desconectar();	
		
	}
	
	public Cotacao consultar(int id) {
		Cotacao c = new Cotacao();
		Conexao con = new Conexao();
		String sql = "SELECT * FROM cotacoes WHERE id = ?";
		try {
			PreparedStatement pst = con.getConnection().prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			if (res.next()) {
				c.setId(res.getInt("idpessoa"));
				c.setNome(res.getString("nome"));
				c.setLocal(res.getString("local"));
				c.setPreco(res.getFloat("preco"));
				c.setData(res.getDate("data"));
				
			}
		} catch( Exception e) {
			e.printStackTrace();
		}
		con.desconectar();	
		return c;
		
	}
	public List<Cotacao> listar(){
		Conexao con =  new Conexao();
		List<Cotacao> lista = new LinkedList<Cotacao>();
		try {
			String sql = "SELECT * FROM cotacoes ORDER BY id";
			Statement sta = con.getConnection().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				Cotacao c = new Cotacao();
				c.setId(res.getInt("id"));
				c.setNome(res.getString("nome"));
				c.setLocal(res.getString("local"));
				c.setPreco(res.getFloat("preco"));
				c.setData(res.getDate("data"));
				lista.add(c);			
				}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		con.desconectar();
		
		return lista;
	}

}
