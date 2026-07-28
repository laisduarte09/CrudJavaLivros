package com.template.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class LivrosDAO {

    private static final Logger logger = Logger.getLogger(LivrosDAO.class.getName());

    public ArrayList<LivrosDTO> selecionarLivros() {
        String sql = "SELECT * FROM livros";
        ArrayList<LivrosDTO> lista = new ArrayList<>();

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LivrosDTO livro = new LivrosDTO();

                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setGenero(rs.getString("genero"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));

                lista.add(livro);
            }

        } catch (SQLException e) {
            logger.severe("Erro ao selecionar livros: " + e.getMessage());
        }

        return lista;
    }

    public void cadastrarLivros(LivrosDTO livro) {
        String sql = "INSERT INTO livros (titulo, autor, genero, ano_publicacao) VALUES (?, ?, ?, ?)";

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getGenero());
            ps.setInt(4, livro.getAnoPublicacao());

            ps.execute();

        } catch (SQLException e) {
            logger.severe("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void atualizarLivros(LivrosDTO livro) {
        String sql = "UPDATE livros SET titulo=?, autor=?, genero=?, ano_publicacao=? WHERE id=?";

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getGenero());
            ps.setInt(4, livro.getAnoPublicacao());
            ps.setInt(5, livro.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.severe("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void deletarLivros(int id) {
        String sql = "DELETE FROM livros WHERE id=?";

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.severe("Erro ao deletar livro: " + e.getMessage());
        }
    }
}