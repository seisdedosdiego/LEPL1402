package quizz;

public class Flipper {

    public static int run(char[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        int sum = 0;
        int x = 0;
        int y = 0;
        int move_x = 0;
        int move_y = 1;

        boolean[][] visited = new boolean[rows][cols];

        while (x >= 0 && x < rows && y >= 0 && y < cols) {

            if (visited[x][y] == false) {
                visited[x][y] = true;
                sum++;
            }

            char currentChar = map[x][y];
            if (currentChar == '/') {
                int a = move_x;
                move_x = -move_y;
                move_y = -a;
            } else if (currentChar == '\\') {
                int b = move_x;
                move_x = move_y;
                move_y = b;
            }

            x += move_x;
            y += move_y;
        }

        return sum;
    }

}