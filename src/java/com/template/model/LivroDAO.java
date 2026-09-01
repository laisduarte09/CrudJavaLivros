package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.LivroDTO;
import com.template.util.DialogUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LivroDAO {

    private static final Logger logger =
            Logger.getLogger(LivroDAO.class.getName());

    public void cadastrarLivro(LivroDTO livro) {

        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar livro", e);
            DialogUtil.showError("Erro ao cadastrar livro no banco de dados.");
        }
    }

    public List<LivroDTO> listarLivros() {

        List<LivroDTO> listaLivros = new ArrayList<>();

        String sql = "SELECT * FROM livros";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                LivroDTO livro = new LivroDTO();

                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));

                listaLivros.add(livro);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar livros", e);
            DialogUtil.showError("Erro ao listar livros.");
        }

        return listaLivros;
    }

    public void deletarLivro(int id) {

        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar livro", e);
            DialogUtil.showError("Erro ao deletar livro.");
        }
    }
}
