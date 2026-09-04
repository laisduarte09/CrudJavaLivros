package com.template.model;

import com.template.util.DialogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (SQLException e) {
            e.printStackTrace();
            DialogUtil.showError("Erro: " + e.getMessage());
            return null;
        }
    }
}