// TC: O(C(H*W, N) * H*W) Combinatorial placements of N buildings times BFS for each grid
// SC: O(H*W)
public class OptimalPlacementOfBuildings {
    public static void main(String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.println(buildingPlacement.findMinDistance(7, 7, 3));
    }

    public static class BuildingPlacement {

        int H;
        int W;
        int min;

        public int findMinDistance(int h, int w, int n) {
            this.H = h;
            this.W = w;
            this.min = Integer.MAX_VALUE;

            int[][] grid = new int[H][W];
            for(int i=0; i<H; i++){
                for(int j=0; j<W; j++){
                    grid[i][j] = -1;
                }
            }
            dfs(grid, 0, n);
            return min;
        }

        private void bfs(int[][] grid) {

            Queue<int[]> bfs = new LinkedList<int[]>();
            boolean[][] visited = new boolean[H][W];
            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            // get buildings
            for(int i=0; i< H; i++){
                for(int j=0; j<W; j++){
                    if(grid[i][j] == 0){
                        bfs.add(new int[] {i, j});
                        visited[i][j] = true;
                    }
                }
            }

            int level = 0;
            while(!bfs.isEmpty()){
                int size = bfs.size();

                for (int i=0; i< size; i++){
                    int[] curr = bfs.poll();
                    int r = curr[0];
                    int c = curr[1];

                    for(int[] dir: dirs){
                        int x = r + dir[0];
                        int y = c + dir[1];

                        if( x >= 0 && y>=0 && x < H && y < W && grid[x][y] == -1 &&  !visited[x][y]){
                            bfs.add(new int[] {x, y});
                            visited[x][y] = true;
                        }
                    }
                }
                level++;
            }
            min = Math.min(min, level-1);

        }

        private void dfs(int[][] grid, int idx, int n) {
            // base
            if( n == 0){
                bfs(grid);
                return;
            }
            if(idx >= H*W) {
                return;
            }

            // logic
            for(int i =idx; i< H*W; i++){
                int r = i / W;
                int c = i % W;
                // action
                grid[r][c] = 0;

                // recurse
                dfs(grid, i +1, n -1);

                // backtrack
                grid[r][c] = -1;
            }
        }
    }
}
