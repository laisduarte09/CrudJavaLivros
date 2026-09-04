package com.template.controller;

import com.template.model.dao.LivroDAO;
import com.template.model.dto.LivroDTO;
import com.template.service.LivrosService;
import com.template.util.DialogUtil;
import com.template.validator.LivrosValidator;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.template.service.LivrosService.limparCampos;

public class MainController {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtGenero;

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

    @FXML
    private TableColumn<LivroDTO, String> colGenero;

    private final LivrosService livrosService =
            new LivrosService(new LivroDAO());

    @FXML
    public void initialize() {
        configurarTabela();
        atualizarTabela();

        tblLivros.setOnMouseClicked(event -> preencherCampos());
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

        colGenero.setCellValueFactory(
                new PropertyValueFactory<>("genero")
        );
    }

    @FXML
    public void btnSalvarAction() {

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String ano = txtAno.getText();
        String genero = txtGenero.getText();

        if (!LivrosValidator.validarLivro(
                titulo,
                autor,
                ano,
                genero)) {

            DialogUtil.showError(
                    "Preencha os campos corretamente!"
            );

            return;
        }

        LivroDTO livro = new LivroDTO();

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAnoPublicacao(
                Integer.parseInt(ano)
        );
        livro.setGenero(genero);

        livrosService.cadastrar(livro);

        DialogUtil.showInfo(
                "Livro cadastrado com sucesso!"
        );

        limparCampos(txtId, txtTitulo, txtAutor, txtAno, txtGenero, tblLivros);
        atualizarTabela();
    }

    @FXML
    public void btnAlterarAction() {

        LivroDTO livroSelecionado =
                tblLivros.getSelectionModel()
                        .getSelectedItem();

        if (livroSelecionado == null) {

            DialogUtil.showError(
                    "Selecione um livro na tabela para alterar."
            );

            return;
        }

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String ano = txtAno.getText();
        String genero = txtGenero.getText();

        if (!LivrosValidator.validarLivro(
                titulo,
                autor,
                ano,
                genero)) {

            DialogUtil.showError(
                    "Preencha os campos corretamente!"
            );

            return;
        }

        livroSelecionado.setTitulo(titulo);
        livroSelecionado.setAutor(autor);
        livroSelecionado.setAnoPublicacao(
                Integer.parseInt(ano)
        );
        livroSelecionado.setGenero(genero);

        livrosService.alterar(livroSelecionado);

        DialogUtil.showInfo(
                "Livro alterado com sucesso!"
        );

        limparCampos(txtId, txtTitulo, txtAutor, txtAno, txtGenero, tblLivros);
        atualizarTabela();
    }

    @FXML
    public void btnExcluirAction() {

        LivroDTO livroSelecionado =
                tblLivros.getSelectionModel()
                        .getSelectedItem();

        if (livroSelecionado == null) {

            DialogUtil.showError(
                    "Selecione um livro na tabela para excluir."
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
                "Livro excluído com sucesso!"
        );

        limparCampos(txtId, txtTitulo, txtAutor, txtAno, txtGenero, tblLivros);
        atualizarTabela();
    }

    @FXML
    public void btnLimparAction() {
        limparCampos(txtId, txtTitulo, txtAutor, txtAno, txtGenero, tblLivros);
    }

    private void preencherCampos() {

        LivroDTO livroSelecionado =
                tblLivros.getSelectionModel()
                        .getSelectedItem();

        if (livroSelecionado == null) {
            return;
        }

        txtId.setText(
                String.valueOf(livroSelecionado.getId())
        );

        txtTitulo.setText(
                livroSelecionado.getTitulo()
        );

        txtAutor.setText(
                livroSelecionado.getAutor()
        );

        txtAno.setText(
                String.valueOf(
                        livroSelecionado.getAnoPublicacao()
                )
        );

        txtGenero.setText(
                livroSelecionado.getGenero()
        );
    }

    private void atualizarTabela() {

        tblLivros.getItems().setAll(
                livrosService.listarTodos()
        );
    }

}