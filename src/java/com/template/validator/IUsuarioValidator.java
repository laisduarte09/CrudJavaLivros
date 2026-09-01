package com.template.validator;

public interface IUsuarioValidator {
    boolean validarUsuario(String nome, String genero, String ano, String autor);
    boolean validarTitulo(String titulo);
    boolean validarGenero(String genero);
    boolean validarAno(String ano);
    boolean validarAutor(String autor);
}