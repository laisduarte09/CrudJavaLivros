package com.template.controller;

import com.template.model.dao.LivroDAO;
import com.template.model.dto.LivroDTO;
import com.template.service.LivrosService;
import com.template.util.DialogUtil;
import com.template.validator.LivrosValidator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtAno;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnDeletar;

    @FXML
    private TableView<LivroDTO> tblLivros;

    @FXML
    private TableColumn<LivroDTO, Integer> colId;

    @FXML
    private TableColumn<LivroDTO, String> colTitulo;

    @FXML
    private TableColumn<LivroDTO, String> colAutor;

    @FXML
    private TableColumn<LivroDTO, Integer> colAno;

    private final LivrosService livrosService =
            new LivrosService(new LivroDAO());

    @FXML
    public void initialize() {

        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colTitulo.setCellValueFactory(
                new PropertyValueFactory<>("titulo")
        );

        colAutor.setCellValueFactory(
                new PropertyValueFactory<>("autor")
        );

        colAno.setCellValueFactory(
                new PropertyValueFactory<>("anoPublicacao")
        );
    }

    @FXML
    public void btnCadastrarAction() {

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String ano = txtAno.getText();

        if (!LivrosValidator.validarLivro(
                titulo,
                autor,
                ano,
                "genero")) {

            DialogUtil.showError(
                    "Preencha os campos corretamente!"
            );

            return;
        }

        LivroDTO livro = criarLivro(
                titulo,
                autor,
                ano
        );

        livrosService.cadastrar(livro);

        DialogUtil.showInfo(
                "Livro cadastrado com sucesso!"
        );

        limparCampos();
        atualizarTabela();
    }

    @FXML
    public void btnDeletarAction() {

        LivroDTO livroSelecionado =
                tblLivros.getSelectionModel().getSelectedItem();

        if (livroSelecionado == null) {

            DialogUtil.showError(
                    "Selecione um livro na tabela para deletar."
            );

            return;
        }

        boolean confirmado =
                DialogUtil.showConfirmation(
                        "Deseja realmente excluir o livro selecionado?"
                );

        if (!confirmado) {
            return;
        }

        livrosService.deletar(
                livroSelecionado.getId()
        );

        DialogUtil.showInfo(
                "Livro deletado com sucesso!"
        );

        atualizarTabela();
    }

    private LivroDTO criarLivro(
            String titulo,
            String autor,
            String ano) {

        LivroDTO livro = new LivroDTO();

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(
                Integer.parseInt(ano)
        );

        return livro;
    }

    private void atualizarTabela() {

        tblLivros.getItems().setAll(
                livrosService.listarTodos()
        );
    }

    private void limparCampos() {

        txtTitulo.clear();
        txtAutor.clear();
        txtAno.clear();
    }
}
