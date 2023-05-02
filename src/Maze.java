import java.util.*;

public class Maze{
    int sizeX;
    int sizeY;
    Cell[][] cells;

    public Maze(){
        sizeX = 25;
        sizeY = 25;
        cells = new Cell[sizeX][sizeY];
        initializeCells();
        generateMaze();
    }

    public Maze(int x, int y){
        sizeX = x;
        sizeY = y;
        cells  = new Cell[sizeX][sizeY];
        initializeCells();
        generateMaze();
    }

    public void printAllCells(){
        for(int i = 0; i<sizeX;i++){
            for(int j = 0;i<sizeY;j++){
                System.out.println(i+ " "+j);
                cells[i][j].printCell();
                System.out.println("\n");
            }
        }
    }

    //N = 0, E = 1, S = 2, W = 3
    // Setup Borders (Ended up not using the borders)
    // Also initializes all the cells in the array.

    private void initializeCells(){
        for (int i = 0; i < sizeX; i++) {
            for(int j =0;j<sizeY;j++){
                cells[i][j] = new Cell();
                cells[i][j].x = i;
                cells[i][j].y = j;
                if(i == 0){
                    cells[i][j].borders[0] = 1;
                }
                if(j == 0){
                    cells[i][j].borders[3] = 1;
                }
                if (i == sizeX -1){
                    cells[i][j].borders[2] = 1;
                }
                if (j == sizeY -1){
                    cells[i][j].borders[1] = 1;
                }
            }
        }
    }

    //N =0, E=1,S=2,W=3
    // Generate the Actual Maze. Used a Depth-First Search Algorith.
    private void generateMaze(){
        Random rand = new Random();

        int x = rand.nextInt(sizeX);
        int y = rand.nextInt(sizeY);

        Stack<Cell> cellStack = new Stack<Cell>();
        int totalCells = sizeX * sizeY;
        int visitedCells = 1;
        Cell currentCell = cells[x][y];

        ArrayList<Vertex> neighborCellList = new ArrayList<Vertex>();

        Vertex tmpV = new Vertex();

        while (visitedCells < totalCells){
            neighborCellList.clear();

            tmpV = new Vertex();
            if (y-1 >= 0 && cells[x][y-1].checkWalls()==true){
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y -1;
                tmpV.wall1 = 0;
                tmpV.wall2 = 2;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (y+1 < sizeY && cells[x][y+1].checkWalls()==true){
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x;
                tmpV.y2 = y +1;
                tmpV.wall1 = 2;
                tmpV.wall2 = 0;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (x-1 >= 0 && cells[x-1][y].checkWalls()==true){
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x-1;
                tmpV.y2 = y;
                tmpV.wall1 = 3;
                tmpV.wall2 = 1;
                neighborCellList.add(tmpV);
            }

            tmpV = new Vertex();
            if (x+1 < sizeX && cells[x+1][y].checkWalls()==true){
                tmpV.x1 = x;
                tmpV.y1 = y;
                tmpV.x2 = x+1;
                tmpV.y2 = y;
                tmpV.wall1 = 1;
                tmpV.wall2 = 3;
                neighborCellList.add(tmpV);
            }

            if (neighborCellList.size() >=1){
                int r1 = rand.nextInt(neighborCellList.size());
                tmpV = neighborCellList.get(r1);

                cells[tmpV.x1][tmpV.y1].walls[tmpV.wall1] = 0;
                cells[tmpV.x2][tmpV.y2].walls[tmpV.wall2] = 0;

                cellStack.push(currentCell);

                currentCell = cells[tmpV.x2][tmpV.y2];

                x = currentCell.x;
                y = currentCell.y;

                visitedCells++;
            } else{
                currentCell = cellStack.pop();
                x = currentCell.x;
                y = currentCell.y;
            }
        }

        cells[0][0].walls[3] = 0;
        cells[sizeX - 1][sizeY - 1].walls[1] = 0;
    }
}