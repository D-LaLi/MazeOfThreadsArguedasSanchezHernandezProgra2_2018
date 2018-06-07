package domain;

/**
 *
 * @author Eduardo
 */
public class Maze {

    private static Maze myMaze = new Maze();
    private short[][] maze;
    
    public static Maze getMyMaze() {
        return myMaze;
    }

    public static void setMyMaze(Maze aMyMaze) {
        myMaze = aMyMaze;
    }

    //Constructor
    private Maze() {
        //Matriz que representa el laberinto en modo facil
        short[][] m = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {2, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 3},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        maze = m;

    }

    public static Maze getInstance() {
        return myMaze;
    }

    public void setMazeType(int n) {
        if (n == 1) {
            short[][] m1 = {//laberinto normal
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {2, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                {1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 3},
                {1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

            maze = m1;
        }
        if (n == 2) {
            //Matriz que representa el laberinto en modo dificil
            short[][] m2//dificil
                = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                {2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 3},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};

            maze = m2;
        }

    }

    //setter and getter
    public short[][] getMaze() {
        return maze;
    }

    public void setMaze(short[][] maze) {
        this.maze = maze;
    }


    //modifica la matriz apartir del click del mouse
    public void addRemoveWall(int x, int y) {
            if (maze[x][y] == 0) {
                maze[x][y] = 1;
            } else {
                maze[x][y] = 0;
            }
    }
    //modifica en laberinto para indicar que hay una moneda en esa posicion

    public boolean moveCoin(int ejeX, int ejeY, boolean direction, int count) {
        if (maze[ejeX][ejeY] == 0 && count != 0)//si entra fue porque encontraron la moneda
        {
            return true;
        }
        if (count != -1) {
            maze[ejeX][ejeY] = 0;//indica que ya no esta la moneda en esa posicion

            if (direction) {//verdadero para ir a la derecha          
                maze[ejeX][++ejeY] = 4;//4 indica que hay una moneda
            } else {//false hacia izquierda 
                maze[ejeX][--ejeY] = 4;//4 indica que hay una moneda
            }
        }
        return false;
    }

    public void removeCoin(int x, int y) {
        maze[x][y] = 0;
    }
}