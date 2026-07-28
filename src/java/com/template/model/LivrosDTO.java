package com.template.model;

public class LivrosDTO {

    private int id;
    private String titulo;
    private String autor;
    private String genero;
    private int ano_publicacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String pelagem) {
        this.genero = pelagem;
    }

    public int getAnoPublicacao() {
        return ano_publicacao;
    }

    public void setAnoPublicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
}