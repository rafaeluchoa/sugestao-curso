package br.flf.curso.sugestaovoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de acesso aos dados para as entidades 
 * que envolvem um Curso.
 */
public class CursoDAO {
	
	/**
	 * Retorna uma conexão do banco de dados.
	 */
	private Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			// url, username, password
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/sugestao_curso", "root", "123");
			
			return conn;
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	
	}
	
	/**
	 * Retorna a lista dos cursos com o somatório 
	 * dos votos para cada curso.
	 * 
	 * @return
	 */
	public List<Curso> listarTodosCursosComQtdeVotos() {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			
			st = conn.prepareStatement(
				"select c.nm_curso, c.ds_descricao, c.ds_email, (select count(ds_email) from tb_voto v where v.nm_curso = c.nm_curso) qtde " +
				"from tb_curso c " +
				"group by c.nm_curso, c.ds_descricao, c.ds_email " +
				"order by qtde desc, c.nm_curso ");
			
			ResultSet rs = st.executeQuery();
			
			List<Curso> lista = new ArrayList<Curso>();
			while(rs.next()) {
				String nmCurso = rs.getString("nm_curso");
				String dsDescricao = rs.getString("ds_descricao");
				String dsEmail = rs.getString("ds_email");
				Long qtde = rs.getLong("qtde");
				
				Curso curso = new Curso();
				curso.setNome(nmCurso);
				curso.setDescricao(dsDescricao);
				curso.setEmail(dsEmail);
				curso.setQtdeVotos(qtde);
				
				lista.add(curso);
			}
			
			return lista;
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}		
	}
	
	/**
	 * Inclui um curso na base de dados.
	 * 
	 * @param nmCurso
	 * @param dsDescricao
	 * @param dsEmail
	 */
	public void incluirCurso(String nmCurso, String dsDescricao, String dsEmail) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			
			st = conn.prepareStatement(
				"insert into tb_curso (nm_curso, ds_descricao, ds_email) " +
				"values (?,?,?) ");
			
			st.setString(1, nmCurso.toUpperCase());
			st.setString(2, dsDescricao);
			st.setString(3, dsEmail);
			
			st.execute();
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Inclui um voto para um curso. 
	 * 
	 * @param nmCurso
	 * @param dsEmail
	 */
	public void incluirVoto(String nmCurso, String dsEmail) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			
			st = conn.prepareStatement(
				"insert into tb_voto (nm_curso, ds_email) " +
				"values (?,?) ");
			
			st.setString(1, nmCurso.toUpperCase());
			st.setString(2, dsEmail);
			
			st.execute();
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	/**
	 * Retorna o curso com o nome passado como parametro. 
	 * Retorna nulo, caso nao encontre o curso.
	 * 
	 * @param nome
	 * @return
	 */
	public Curso carregarCurso(String nome) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			
			st = conn.prepareStatement(
				"select nm_curso, ds_descricao, ds_email " +
				"from tb_curso " +
				"where nm_curso = ? ");
			
			st.setString(1, nome);
			
			ResultSet rs = st.executeQuery();
			
			Curso curso = null;
			if(rs.next()) {
				String nmCurso = rs.getString("nm_curso");
				String dsDescricao = rs.getString("ds_descricao");
				String dsEmail = rs.getString("ds_email");
				
				curso = new Curso();
				curso.setNome(nmCurso);
				curso.setDescricao(dsDescricao);
				curso.setEmail(dsEmail);
				
			}
			
			return curso;
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public boolean existeVoto(String nome, String email) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = getConnection();
			
			st = conn.prepareStatement(
				"select nm_curso " +
				"from tb_voto " +
				"where nm_curso = ? " +
				" and ds_email = ? ");
			
			st.setString(1, nome);
			st.setString(2, email);
			
			ResultSet rs = st.executeQuery();
			
			return (rs.next());
			
		} catch(Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			try {
				if(st != null) st.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
