package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexao {
	
    private Connection connection;
	
    public Conexao() {
        
        
        try {
        	Class.forName("org.mariadb.jdbc.Driver");
        	String bancoDados = "olameudb";
            String url = "jdbc:mariadb://localhost:3306/"+bancoDados;
            String usuario= "root";
            String senha = "120558";
        	

        	connection = DriverManager.getConnection(url, usuario, senha);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao realizar conexão.");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void desconectar() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}