package com.template.validator;

public class LivrosValidator {

    public static boolean validarLivro(
            String titulo,
            String autor,
            String anoPublicacao,
            String genero) {

        if (isVazio(titulo)
                || isVazio(autor)
                || isVazio(anoPublicacao)
                || isVazio(genero)) {
            return false;
        }

        try {
            Integer.parseInt(anoPublicacao);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private static boolean isVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}