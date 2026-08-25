package com.template.validator;

public class LivrosValidator {
    public static boolean validarLivro(
            String titulo,
            String autor,
            String ano_publicacao,
            String genero) {

        if (isVazio(titulo) || isVazio(autor) || isVazio(ano_publicacao) ||
                isVazio(genero)) {
            return false;
        }

        return true;
    }

    private static boolean isVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
