package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.max;

public class Main extends Application {
    public static int Cmax = 10;
    public static int n_m,n_ex;
    private static int W,H;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        String[] list_colors = {"X","A","C","S","W","O","E","T","U"};
        String color = "";
        Image[] images = new Image[8];
        images[0] = new Image("sample/bluei.jpg",20,20,false,false);
        images[1] = new Image("sample/cyan.jpg",20,20,false,false);
        images[2] = new Image("sample/green.png",20,20,false,false);
        images[3] = new Image("sample/orange.png",20,20,false,false);
        images[4] = new Image("sample/red.jpg",20,20,false,false);
        images[5] = new Image("sample/yel.jpg",20,20,false,false);
        images[6] = new Image("sample/purple.png",20,20,false,false);
        images[7] = new Image("sample/szary.png",20,20,false,false);
        File plik = new File("C:\\Users\\Weronika\\source\\repos\\johnson_ja\\johnson_ja\\wiz.txt");
        Scanner odczyt = new Scanner(plik);
        String text = odczyt.nextLine();
        n_ex = Integer.parseInt(text);
        text = odczyt.nextLine();
        n_m = Integer.parseInt(text);
        Task[][] macierz = new Task[n_ex][n_m];
        for (int j = 0; j < n_ex; j++) {
            for (int i = 0; i < n_m; i++) {
                macierz[j][i] = new Task();
                text = odczyt.nextLine();
                macierz[j][i].duration = Integer.parseInt(text);
                macierz[j][i].color = list_colors[j];
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
        W = 20*Cmax;
        H = 20*(n_m+1);
        Tab tab = new Tab();
        Node[][] nodes;
        nodes = tab.give_nodes0(Cmax,n_m);

        stage.setTitle("Wizualizacja");

        for(int j = 0;j<n_m;j++){
            int k=0;
            for(int i=0;i<n_ex;i++){
                String tekst = macierz[i][j].color;
                    for(int dur=0;dur<macierz[i][j].duration;dur++){
                        for(int z=k;z<=macierz[i][j].time_of_start;z++){
                            k=z;
                        }
                        nodes[j+1][k] = new ImageView(images[i%8]);
                        k++;
                    }
            }
        }

        final Label label = new Label("Wizualizacja");
        label.setFont(new Font("Arial", 20));

        tab.give_nodes1(nodes,Cmax,n_m);
        Scene scene = new Scene(tab.getRoot(),W,H);
        stage.setScene(scene);
        stage.show();
    }
}
