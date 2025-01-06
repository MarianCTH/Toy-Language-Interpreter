import Controller.Controller;
import Model.State.*;
import Repository.IRepository;
import Repository.Repository;

import Model.Statement.IStatement;
import Model.Value.IValue;
import View.TextMenu.Statements;
import Exception.ToyLangException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private Controller controller;
    private ObservableList<PrgState> prgStates = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws ToyLangException {
        showProgramSelectionWindow(primaryStage);
    }

    private void showProgramSelectionWindow(Stage stage) throws ToyLangException {
        stage.setTitle("Program Selector");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 10;");

        ListView<IStatement> programListView = new ListView<>();
        programListView.setItems(FXCollections.observableArrayList(getPrograms()));

        Button selectButton = new Button("Select Program");
        selectButton.setOnAction(event -> {
            IStatement selectedProgram = programListView.getSelectionModel().getSelectedItem();
            if (selectedProgram == null) {
                showAlert(Alert.AlertType.WARNING, "No Program Selected", "Please select a program to execute.");
                return;
            }

            // Reset prgStates list and set the new program
            IRepository repository = new Repository("log.txt");
            controller = new Controller(repository, true);
            IExecutionStack executionStack = new ExecutionStack();
            ISymTable symTable = new SymTable();
            IOutput output = new Output();
            IFileTable fileTable = new FileTable();
            IHeapTable heapTable = new HeapTable();

            PrgState initialState = new PrgState(executionStack, symTable, output, selectedProgram, fileTable, heapTable);
            repository.add(initialState);

            // Reset prgStates based on the newly selected program
            prgStates.setAll(repository.getPrgList());

            // Update the number of prgStates in the UI
            showExecutionWindow(stage);
        });

        layout.getChildren().addAll(new Label("Select a program:"), programListView, selectButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void showExecutionWindow(Stage stage) {
        stage.setTitle("Program Execution");

        BorderPane root = new BorderPane();

        // Top: Number of PrgStates
        TextField prgStateCountField = new TextField();
        prgStateCountField.setEditable(false);
        prgStateCountField.setText(String.valueOf(prgStates.size()));

        // Left: List of PrgState IDs
        ListView<Integer> prgStateIdListView = new ListView<>();
        prgStateIdListView.setItems(FXCollections.observableArrayList(getPrgStateIds()));

        // Center: Heap Table
        TableView<Map.Entry<Integer, IValue>> heapTable = new TableView<>();
        TableColumn<Map.Entry<Integer, IValue>, String> heapAddressColumn = new TableColumn<>("Address");
        heapAddressColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey().toString()));
        TableColumn<Map.Entry<Integer, IValue>, String> heapValueColumn = new TableColumn<>("Value");
        heapValueColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue().toString()));
        heapTable.getColumns().addAll(heapAddressColumn, heapValueColumn);

        // Right: Out, FileTable, and ExeStack
        ListView<String> outListView = new ListView<>();
        ListView<String> fileTableListView = new ListView<>();
        ListView<String> exeStackListView = new ListView<>();

        // Bottom: SymTable
        TableView<Map.Entry<String, IValue>> symTable = new TableView<>();
        TableColumn<Map.Entry<String, IValue>, String> symVarColumn = new TableColumn<>("Variable");
        symVarColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKey()));
        TableColumn<Map.Entry<String, IValue>, String> symValueColumn = new TableColumn<>("Value");
        symValueColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getValue().toString()));
        symTable.getColumns().addAll(symVarColumn, symValueColumn);

        Button runOneStepButton = new Button("Run One Step");
        runOneStepButton.setOnAction(event -> {
            try {
                controller.oneStepForAllPrg(controller.getPrgList());
                refreshUI(prgStateCountField, heapTable, outListView, fileTableListView, prgStateIdListView, symTable, exeStackListView);
            } catch (InterruptedException e) {
                showAlert(Alert.AlertType.ERROR, "Execution Error", e.getMessage());
            }
        });

        // Back Button - To go back to program selection window
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            try {
                // Hide the current execution window and show the program selection window
                stage.hide();
                showProgramSelectionWindow(stage);
            } catch (ToyLangException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Unable to return to program selection window.");
            }
        });

        // Layout
        VBox leftPanel = new VBox(10, new Label("PrgState IDs"), prgStateIdListView);
        VBox rightPanel = new VBox(10, new Label("Out"), outListView, new Label("File Table"), fileTableListView, new Label("ExeStack"), exeStackListView);

        // Add the Back button to the layout, ideally at the bottom of the screen
        VBox bottomPanel = new VBox(10, new Label("Symbol Table"), symTable, runOneStepButton, backButton);

        root.setTop(new Label("Number of PrgStates:"));
        root.setLeft(leftPanel);
        root.setRight(rightPanel);
        root.setBottom(bottomPanel);
        root.setCenter(new VBox(10, new Label("Heap Table"), heapTable));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private List<IStatement> getPrograms() {
        return List.of(
                Statements.example1(),
                Statements.example2(),
                Statements.example3(),
                Statements.example4(),
                Statements.example5(),
                Statements.example6(),
                Statements.example7(),
                Statements.example8(),
                Statements.example9(),
                Statements.example10(),
                Statements.example11(),
                Statements.example12(),
                Statements.example13(),
                Statements.example14(),
                Statements.example15()
        );
    }

    private List<Integer> getPrgStateIds() {
        List<Integer> ids = new ArrayList<>();
        for (PrgState prg : prgStates) {
            ids.add(prg.getId());
        }
        return ids;
    }

    private void refreshUI(TextField prgStateCountField, TableView<Map.Entry<Integer, IValue>> heapTable, ListView<String> outListView,
                           ListView<String> fileTableListView, ListView<Integer> prgStateIdListView,
                           TableView<Map.Entry<String, IValue>> symTable, ListView<String> exeStackListView) {
        prgStateCountField.setText(String.valueOf(prgStates.size()));
        prgStateIdListView.setItems(FXCollections.observableArrayList(getPrgStateIds()));

        PrgState currentState = prgStates.get(0);
        heapTable.setItems(FXCollections.observableArrayList(currentState.getHeapTable().getContent().entrySet()));

        exeStackListView.setItems(FXCollections.observableArrayList(
                currentState.getExeStack().getStackAsStrings()
        ));

        fileTableListView.setItems(FXCollections.observableArrayList(
                currentState.getFileTable().getKeys()
        ));

        symTable.setItems(FXCollections.observableArrayList(currentState.getSymTable().getContent().entrySet()));

        outListView.setItems(FXCollections.observableArrayList(currentState.getOutput().getOutput()));
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
