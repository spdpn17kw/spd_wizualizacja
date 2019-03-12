package sample;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Tab {
    public static int Cmax = 50;
    public static int n_m;
    private Group root = new Group();

    public Node[][] give_nodes0(int cmax, int m) {
        Cmax = cmax;
        n_m = m;
        Node[][] nodes = new Node[n_m + 1][Cmax];
        for(int i = 0; i<n_m+1;i++)
            for(int j=0;j<Cmax;j++) {
                if(i == 0) nodes[i][j] = new Text(Integer.toString(j+1));

                else nodes[i][j] = new ImageView(new Image("sample/white.jpg",20,20,false,false));
            }
            return nodes;
    }
    public void give_nodes1(Node[][] nodes, int cmax, int m) {
        Cmax = cmax;
        n_m = m;
        Node[] tab_node = new Node[Cmax*(n_m+1)];
        for(int j=0;j<Cmax;j++) {
            tab_node[j] = nodes[0][j];
            tab_node[j].setLayoutX(j*20);
            tab_node[j].setLayoutY(1*20);
            System.out.println(tab_node[j]);
        }
        for(int i = 1; i<n_m+1;i++)
            for(int j=0;j<Cmax;j++){
                tab_node[j+Cmax*i] = nodes[i][j];
                tab_node[j+Cmax*i].setLayoutX(j*20);
                tab_node[j+Cmax*i].setLayoutY((i+1)*20);
                System.out.println(tab_node[j+Cmax*i]);
                System.out.println(tab_node[j]);
            }
        root = new Group(tab_node);
    }

    public Group getRoot() {
        return root;
    }

}
