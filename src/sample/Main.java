package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.max;

public class Main extends Application {

    private TableView<Node> table = new TableView<Node>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        int Cmax = 10;
        int n_ex = 0,n_m = 0;
        String color = "";

        Scene scene = new Scene(new Group());
        stage.setTitle("Wizualizacja");
        stage.setWidth(450);
        stage.setHeight(500);
        File plik = new File("C:\\Users\\Weronika\\source\\repos\\johnson_ja\\johnson_ja\\wiz.txt");
        Scanner odczyt = new Scanner(plik);
        String text = odczyt.nextLine();
        n_ex = Integer.parseInt(text);
        text = odczyt.nextLine();
        n_m = Integer.parseInt(text);
        sample.Task[][] macierz = new sample.Task[n_ex][n_m];
        for (int j = 0; j < n_ex; j++) {
            for (int i = 0; i < n_m; i++) {
                macierz[j][i] = new Task();
                text = odczyt.nextLine();
                macierz[j][i].duration = Integer.parseInt(text);
                String st = Integer.toString(j+1);
                macierz[j][i].color = st;
            }
            System.out.println();
        }
        int n_ex2 = n_ex + 1;
        int[][] Cm = new int[n_ex + 1][n_m + 1];
        for (int i = 0; i <= n_m; i++) Cm[0][i] = 0;
        for (int i = 0; i < n_ex2; i++) Cm[i][0] = 0;
        for (int i = 1; i <= n_m; i++) {
            for (int j = 1; j <= n_ex; j++) {
                Cm[j][i] = max(Cm[j][i - 1], Cm[j - 1][i]) + macierz[j - 1][i - 1].duration;
                macierz[j - 1][i - 1].time_of_start = Cm[j][i] - macierz[j - 1][i - 1].duration;
            }
        }
        text = odczyt.nextLine();
        Cmax = Integer.parseInt(text);
        Node[][] nodes = new Text[n_m][Cmax];

        for(int j = 0;j<n_m;j++){
            int k=0;
            for(int i=0;i<n_ex;i++){

                    String tekst = macierz[i][j].color;
                System.out.println(tekst);
                //if(i==n_ex-1){
                    for(int dur=0;dur<macierz[i][j].duration;dur++)nodes[j][k] = new Text(tekst);
//                }
//                    while(k<macierz[i+1][j].time_of_start){
//                        nodes[j][k] = new Text(tekst);
//                        k++;
//                    }
            }
        }

        final Label label = new Label("Wizualizacja");
        label.setFont(new Font("Arial", 20));
        TableColumn[] tab = new TableColumn[Cmax];
        table.setEditable(true);
        for(int i=1;i<=Cmax;i++){
            String j = Integer.toString(i);
            tab[i-1] = new TableColumn(j);
            tab[i-1].setPrefWidth(20);
            tab[i-1].setCellValueFactory(new PropertyValueFactory(j));
        }

        table.getColumns().addAll(tab);

        table.getItems().addAll(nodes[0]);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        System.out.println("dsvcsdcsdcsdc");
        stage.setScene(scene);
        stage.show();
    }
}
