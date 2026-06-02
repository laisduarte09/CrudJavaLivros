package com.template;

import com.template.LivrosDAO;
import com.template.LivrosDTO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

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
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        int ano = Integer.parseInt(txtAno.getText());

        LivrosDTO objlivrodto = new LivrosDTO();
        objlivrodto.setTitulo(titulo);
        objlivrodto.setAutor(autor);
        objlivrodto.setGenero(genero);
        objlivrodto.setAnoPublicacao(ano);

        LivrosDAO objlivrosdao = new LivrosDAO();
        objlivrosdao.cadastrarLivros(objlivrodto);

        carregarLivros();
    }

    @FXML
    private void btnAlterarAction(ActionEvent event) {
        LivrosDTO livroSelecionado = tblLivros.getSelectionModel().getSelectedItem();

        if (livroSelecionado != null) {
            livroSelecionado.setTitulo(txtTitulo.getText());
            livroSelecionado.setAutor(txtAutor.getText());
            livroSelecionado.setGenero(txtGenero.getText());
            livroSelecionado.setAnoPublicacao(Integer.parseInt(txtAno.getText()));

            LivrosDAO objlivrosdao = new LivrosDAO();
            objlivrosdao.atualizarLivros(livroSelecionado);

            carregarLivros();
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        LivrosDTO livroSelecionado = tblLivros.getSelectionModel().getSelectedItem();

        if (livroSelecionado != null) {
            LivrosDAO objlivrosdao = new LivrosDAO();
            objlivrosdao.deletarLivros(livroSelecionado.getId());

            carregarLivros();
        }
    }

    private void carregarLivros() {
        LivrosDAO objlivrosdao = new LivrosDAO();
        ArrayList<LivrosDTO> listaLivros = objlivrosdao.selecionarLivros();
        tblLivros.setItems(FXCollections.observableArrayList(listaLivros));
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        txtId.clear();
        txtTitulo.clear();
        txtAutor.clear();
        txtGenero.clear();
        txtAno.clear();
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));
        tblLivros.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> carregarCampos()
        );

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