package AI_Find_Path;

import EntitySetup.EntitySetup;
import Main.GamePanel;
import java.util.ArrayList;

public class AI_Search {
    GamePanel gp;
    Node node[][][];
    Node startNode = null;
    Node goalNode = null;
    public Node currentNode = null;
    public EntitySetup entity;
    ArrayList<Node>q = new ArrayList<>();
    public ArrayList<Node>Path =new ArrayList<>();
    public AI_Search(GamePanel gp) {
        this.gp = gp;
        initializeNode();
    }
    private void initializeNode() {
        node = new Node[4][51][51];
        for(int k = 0 ; k < gp.maxMap ;k++) {
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    node[k][i][j] = new Node(i, j);
                    node[k][i][j].locked = gp.scenery.Tile[gp.scenery.indexMap[k][i][j]].collision;
                }
            }
        }
    }
    public void resetNode(int startRow, int startCol, int goalRow, int goalCol) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                node[gp.currentMap][i][j].checked = false;
            }
        }
        startNode = node[gp.currentMap][startRow][startCol];
        currentNode = startNode;
        startNode.checked = true;
        goalNode = node[gp.currentMap][goalRow][goalCol];
        q.clear();
        q.add(startNode);
        Path.clear();
    }
    public void FindPath(int goalRow, int goalCol) {

        while(q.size()!=0){
            Node tm = q.get(0);
            q.remove(0);
            if(FindMin(tm , goalRow , goalCol) == true){
                return;
            }
        }
    }
    public boolean FindMin(Node tm , int goalRow, int goalCol){
        int startRow = tm.row;
        int startCol = tm.col;
        if (startRow - 1 >= 0) {
            if (startRow - 1 == goalRow && startCol == goalCol) {
                goalNode.parent = tm;
                currentNode = goalNode;
                return true;
            } else if (node[gp.currentMap][startRow - 1][startCol].checked == false && node[gp.currentMap][startRow - 1][startCol].locked == false) {
                node[gp.currentMap][startRow - 1][startCol].checked = true;
                node[gp.currentMap][startRow - 1][startCol].parent = tm;
                q.add(node[gp.currentMap][startRow - 1][startCol]);
            }

        }
        if (startRow + 1 < 50) {
            if (startRow + 1 == goalRow && startCol == goalCol) {
                goalNode.parent = tm;
                currentNode = goalNode;
                return true;
            } else if (node[gp.currentMap][startRow + 1][startCol].checked == false && node[gp.currentMap][startRow + 1][startCol].locked == false) {
                node[gp.currentMap][startRow + 1][startCol].checked = true;
                node[gp.currentMap][startRow + 1][startCol].parent = tm;
                q.add(node[gp.currentMap][startRow + 1][startCol]);
            }

        }
        if (startCol - 1 >= 0) {

            if (startRow == goalRow && startCol - 1 == goalCol) {
                goalNode.parent = tm;
                currentNode = goalNode;
                return true;
            } else if (node[gp.currentMap][startRow][startCol - 1].checked == false && node[gp.currentMap][startRow][startCol - 1].locked == false) {
                node[gp.currentMap][startRow][startCol - 1].checked = true;
                node[gp.currentMap][startRow][startCol - 1].parent = tm;
                q.add(node[gp.currentMap][startRow][startCol - 1]);
            }

        }
        if (startCol + 1 < 50) {

            if (startRow == goalRow && startCol + 1 == goalCol) {
                goalNode.parent = tm;
                currentNode = goalNode;
                return true;
            } else if (node[gp.currentMap][startRow][startCol + 1].checked == false && node[gp.currentMap][startRow][startCol + 1].locked == false) {
                node[gp.currentMap][startRow][startCol + 1].checked = true;
                node[gp.currentMap][startRow][startCol + 1].parent = tm;
                q.add(node[gp.currentMap][startRow][startCol + 1]);
            }

        }
        return false;
    }
    public void tracePath() {
        while (currentNode.parent != startNode) {
            Path.add(0,currentNode);
            currentNode = currentNode.parent;
        }
        Path.add(0,currentNode);
    }
}