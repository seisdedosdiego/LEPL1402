package basics;

public class Flipper {

    public static int run(char [][] map) {
        int n = map.length;
        int m = map[0].length;
        boolean[][] visited = new boolean[n][m];
        int count = 0;

        int[] right = {0,1};
        int[] left = {0,-1};
        int[] up = {-1,0};
        int[] down = {1,0};

        int[] position = {0,0};
        String direction = "right";

        while (-1 < position[0] && position[0] < n
                && -1 < position[1] && position[1] < m) {

                    System.out.println("Position actuelle : [" + position[0] + ", " + position[1] + "]");

                    if (visited[position[0]][position[1]] == false) {
                        count++;
                        visited[position[0]][position[1]] = true;
                    }

                    switch (map[position[0]][position[1]]) {
                        case '\\':
                            if (direction.equals("right")) {
                                direction = "down";
                            } else if (direction.equals("left")) {
                                direction = "up";
                            } else if (direction.equals("up")) {
                                direction = "left";
                            } else if (direction.equals("down")) {
                                direction = "right";
                            }
                            break;
                        case '/':
                            if (direction.equals("right")) {
                                direction = "up";
                            } else if (direction.equals("left")) {
                                direction = "down";
                            } else if (direction.equals("up")) {
                                direction = "right";
                            } else if (direction.equals("down")) {
                                direction = "left";
                            }
                            break;
                        default:
                            break;
                    }

                    switch (direction) {
                        case "right":
                            position[0] += right[0];
                            position[1] += right[1];
                            break;
                        case "left":
                            position[0] += left[0];
                            position[1] += left[1];
                            break;
                        case "up":
                            position[0] += up[0];
                            position[1] += up[1];
                            break;
                        case "down":
                            position[0] += down[0];
                            position[1] += down[1];
                            break;
                        default:
                            break;
                    }
                }
         
        return count;
    }

}
