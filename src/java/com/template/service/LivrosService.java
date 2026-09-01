package com.template.service;

import com.template.model.dao.LivroDAO;
import com.template.model.dto.LivroDTO;

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

    public void deletar(int id) {
        livrosDAO.deletarLivro(id);
    }
}