package com.template.service;

import com.template.model.LivrosDAO;
import com.template.model.LivrosDTO;

import java.util.List;

public class LivrosService {

    private final LivrosDAO livrosDAO;

    public LivrosService(LivrosDAO livrosDAO) {
        this.livrosDAO = livrosDAO;
    }

    public List<LivrosDTO> listarTodos(){
        return livrosDAO.selecionarLivros();
    }

    public void cadastrar(LivrosDTO livro){
        livrosDAO.cadastrarLivros(livro);
    }

    public void atualizar(LivrosDTO livro){
        livrosDAO.atualizarLivros(livro);
    }

    public void deletar(int id){
        livrosDAO.deletarLivros(id);
    }
}
