package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class ConexaoBd {
	private static Connection conexao = null;
	
	public static Connection getConexao() throws SQLException {
		if(conexao == null) {
			String urlBd = "jdbc:mysql://localhost:3306/projnotas?useTimezone=true&serverTimezone=UTC";
			String password = "nsvw@#220Z";
			String user = "es2lp3n2";
			
			conexao = DriverManager.getConnection(urlBd, user, password);
		}
		
		return conexao;
	}	
	
	public static void fecharConexao() throws SQLException {
		if(conexao != null) {
			conexao.close();
			conexao = null;
		}
	}
}
