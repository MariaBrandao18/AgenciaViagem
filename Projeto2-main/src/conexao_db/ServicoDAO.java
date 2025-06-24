package conexao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import classes.ServicoAdicional;

public class ServicoDAO {
	 public static void inserirServiço(ServicoAdicional servico) {
	        String sql = "INSERT INTO servicos (nome_servico, descricao, preco) VALUES (?, ?, ?)";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, servico.getNome());
	            stmt.setString(2, servico.getDescricao());
	            stmt.setDouble(3, servico.getPreco());

	            stmt.executeUpdate();
	            System.out.println("Servico cadastrado com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public static void excluirServiço(String nome) {
	        String sql = "DELETE FROM servicos WHERE nome_servico = ?";
	        try (Connection conn = Conexao.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) 
	        {
	            stmt.setString(1, nome.trim());
	            stmt.executeUpdate();
	            System.out.println("Servico removido com sucesso!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 public static List<ServicoAdicional> listarServicos() {
		 List<ServicoAdicional> servicos = new ArrayList<>();
		 String sql = "SELECT * FROM servicos";
		 Connection conn = null;
    
    try {
    	conn = Conexao.conectar();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String nome = rs.getString("nome_servico");
            String descricao = rs.getString("descricao");
            Double preco = rs.getDouble("preco");
            
            ServicoAdicional s;
                s = new ServicoAdicional(nome, preco, descricao);
                s.setServicoId(rs.getLong("servico_id"));
                servicos.add(s);

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return servicos;
}
	 
	  public static void incluirServico(String pacote, String servico) throws SQLException {
	        String sqlSelectServico = "SELECT servico_id FROM clientes WHERE nome_servico = ?";
	        String sqlSelectPacote = "SELECT pacote_id FROM pacotes WHERE nome = ?";
	        String sqlInsertVinculo = "INSERT INTO clientes_pacotes (cliente_id, pacote_id) VALUES (?, ?)";
	        
	        Connection conn = null;

	        try {
	            conn = Conexao.conectar();
	            conn.setAutoCommit(false);

	            int servicoId = -1;
	            int pacoteId = -1;

	                        try (PreparedStatement stmtCliente = conn.prepareStatement(sqlSelectServico)) {
	                stmtCliente.setString(1, servico);
	                try (ResultSet rs = stmtCliente.executeQuery()) {
	                    if (rs.next()) {
	                        servicoId = rs.getInt("servico_id");
	                    } else {
	                        throw new SQLException("Servico não encontrado com o nome informado.");
	                    }
	                }
	            }

	                        try (PreparedStatement stmtPacote = conn.prepareStatement(sqlSelectPacote)) {
	                stmtPacote.setString(1, pacote);
	                try (ResultSet rs = stmtPacote.executeQuery()) {
	                    if (rs.next()) {
	                        pacoteId = rs.getInt("pacote_id");
	                    } else {
	                        throw new SQLException("Pacote não encontrado com o nome informado.");
	                    }
	                }
	            }

	                        try (PreparedStatement stmtVinculo = conn.prepareStatement(sqlInsertVinculo)) {
	                stmtVinculo.setInt(1, servicoId);
	                stmtVinculo.setInt(2, pacoteId);
	                stmtVinculo.executeUpdate();
	            }

	            conn.commit();

	        } catch (SQLException e) {
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	            e.printStackTrace();
	            throw e;
	        } finally {
	            if (conn != null) {
	                Conexao.desconectar(conn);
	            }
	        }
	    }

}
