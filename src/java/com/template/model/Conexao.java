package com.template.model;

import com.template.util.DialogUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final Logger logger = Logger.getLogger(Conexao.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/seu_banco";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao conectar ao banco de dados", e);
            DialogUtil.showError("Erro ao conectar ao banco de dados.");
            return null;
        }
    }
}