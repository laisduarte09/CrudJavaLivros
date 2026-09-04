package com.template.service;

import com.template.model.dao.LivroDAO;
import com.template.model.dto.LivroDTO;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.util.List;

public class LivrosService {

    private final LivroDAO livrosDAO;

    public LivrosService(LivroDAO livrosDAO) {
        this.livrosDAO = livrosDAO;
    }

    public List<LivroDTO> listarTodos() {
        return livrosDAO.listarLivros();
    }

    public void cadastrar(LivroDTO livro) {
        livrosDAO.cadastrarLivro(livro);
    }

    public void alterar(LivroDTO livro) {
        livrosDAO.alterarLivro(livro);
    }

    public void deletar(int id) {
        livrosDAO.deletarLivro(id);
    }

    public static void limparCampos(TextField txtId, TextField txtTitulo, TextField txtAutor, TextField txtAno, TextField txtGenero, TableView tblLivros) {

        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtAno.clear();
        txtGenero.clear();

        tblLivros.getSelectionModel().clearSelection();
    }
}