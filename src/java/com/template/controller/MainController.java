package com.template.controller;

import com.template.model.LivrosDAO;
import com.template.model.LivrosDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import static com.template.util.DialogUtil.*;

public class MainController {

    @FXML private Button btnSalvar;
    @FXML private Button btnAlterar;
    @FXML private Button btnExcluir;

    @FXML private TextField txtId;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtGenero;
    @FXML private TextField txtAno;

    @FXML private TableView<LivrosDTO> tblLivros;
    @FXML private TableColumn<LivrosDTO, Integer> colId;
    @FXML private TableColumn<LivrosDTO, String> colTitulo;
    @FXML private TableColumn<LivrosDTO, String> colAutor;
    @FXML private TableColumn<LivrosDTO, String> colGenero;
    @FXML private TableColumn<LivrosDTO, Integer> colAno;

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }

        LivrosDTO livro = new LivrosDTO();
        livro.setTitulo(txtTitulo.getText().trim());
        livro.setAutor(txtAutor.getText().trim());
        livro.setGenero(txtGenero.getText().trim());
        livro.setAnoPublicacao(Integer.parseInt(txtAno.getText().trim()));

        LivrosDAO livrosDAO = new LivrosDAO();
        livrosDAO.cadastrarLivros(livro);

        carregarLivros();
        limparCampos();

        showInfo("Salvo com sucesso");
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        LivrosDTO livroSelecionado = tblLivros.getSelectionModel().getSelectedItem();

        if (livroSelecionado == null || !validarCampos()) {
            return;
        }

        livroSelecionado.setTitulo(txtTitulo.getText().trim());
        livroSelecionado.setAutor(txtAutor.getText().trim());
        livroSelecionado.setGenero(txtGenero.getText().trim());
        livroSelecionado.setAnoPublicacao(Integer.parseInt(txtAno.getText().trim()));

        LivrosDAO livrosDAO = new LivrosDAO();
        livrosDAO.atualizarLivros(livroSelecionado);

        carregarLivros();
        limparCampos();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        LivrosDTO livroSelecionado = tblLivros.getSelectionModel().getSelectedItem();

        if (livroSelecionado == null) {
            return;
        }

        LivrosDAO livrosDAO = new LivrosDAO();
        livrosDAO.deletarLivros(livroSelecionado.getId());

        carregarLivros();
        limparCampos();
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    private void carregarLivros() {
        LivrosDAO livrosDAO = new LivrosDAO();
        ArrayList<LivrosDTO> listaLivros = livrosDAO.selecionarLivros();
        tblLivros.setItems(FXCollections.observableArrayList(listaLivros));
    }

    private void limparCampos() {
        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
        txtAno.clear();
        tblLivros.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        if (txtTitulo.getText().trim().isEmpty()) {
            return false;
        }

        if (txtAutor.getText().trim().isEmpty()) {
            return false;
        }

        if (txtGenero.getText().trim().isEmpty()) {
            return false;
        }

        if (txtAno.getText().trim().isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(txtAno.getText().trim());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));

        txtId.setEditable(false);

        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);

        tblLivros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            carregarCampos();

            boolean selecionado = newValue != null;
            btnAlterar.setDisable(!selecionado);
            btnExcluir.setDisable(!selecionado);
        });

        carregarLivros();
    }

    @FXML
    private void carregarCampos() {
        LivrosDTO livroDto = tblLivros.getSelectionModel().getSelectedItem();

        if (livroDto != null) {
            txtId.setText(String.valueOf(livroDto.getId()));
            txtTitulo.setText(livroDto.getTitulo());
            txtAutor.setText(livroDto.getAutor());
            txtGenero.setText(livroDto.getGenero());
            txtAno.setText(String.valueOf(livroDto.getAnoPublicacao()));
        }
    }
}