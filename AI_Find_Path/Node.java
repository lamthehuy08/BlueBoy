package AI_Find_Path;

import Main.GamePanel;
import entity.Entity;

public class Node {

    public int row;
    public int col;
    boolean checked = false;
    public boolean locked = false;
    Node parent;
    public Node(int row, int col){
        this.row = row;
        this.col = col;
    }
}
