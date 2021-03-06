public class dfsQuestions{

    // O(E+V) ~ O(n*m)
    public static void dfs_NumIslands(char[][] grid, int i, int j, int[][] dir){
        grid[i][j] = '2'; //a value that isn't present in the grid already
        //if interviewer says donot change the graph you  can change the value while going down 
        //and then make it correct while coming back as done in 2nd method
        //tree ll m compulsory hota h jese aaya tha vese rkhna original data graph m not necessary
        int n = grid.length, m = grid[0].length;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1'){
                dfs_NumIslands(grid, r, c, dir);
            }
        }
    }

    public static int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, componentCount = 0;

        int[][] dir = {{1,0}, {-1,0}, {0,-1}, {0,1}};
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    dfs_NumIslands(grid, i, j, dir);
                    componentCount++;
                }
            }
        }
        return componentCount;
    }

    //2nd method
    public void dfs_NumIsland(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '2';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1')
                dfs_NumIsland(grid, r, c, dir);
        }

    }

    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length, componentCount = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs_NumIsland(grid, i, j, dir);
                    componentCount++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '2') {
                    grid[i][j] = '1';
                }
            }
        }

        return componentCount;
    }

    // O(E+V) ~ O(n*m)
    public static int dfs_MaxAreaOfIsland(int[][] grid, int i, int j, int[][] dir){
        grid[i][j] = 0; //a value that isn't present in the grid already
        int n = grid.length, m = grid[0].length;

        int size = 0;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1){
                size += dfs_MaxAreaOfIsland(grid, r, c, dir);
            }
        }
        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    int s = dfs_MaxAreaOfIsland(grid, i, j, dir);
                    maxSize = Math.max(maxSize, s);
                }
            }
        }
        return maxSize;
    }

    //463
    public int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length, maxSize = 0;

        int[][] dir = { { 1, 0 }, { 0, 1 } };

        int onesCount = 0, nbrCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    onesCount++;
                    for(int d = 0; d < 2; d++){
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if(r < n && c < m && grid[r][c] == 1){
                            nbrCount++;
                        }
                    }
                }
            }
        }
        return 4 * onesCount - 2 * nbrCount;
    }

    //130
    //O(n*m) precise 2n*m
    public void surrounded_DFS(char[][] grid, int i, int j, int[][] dir) {
        grid[i][j] = '$';
        int n = grid.length, m = grid[0].length;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O')
                surrounded_DFS(grid, r, c, dir);
        }
    }
    public void surrounded(char[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if((i == 0 || j == 0 || i == n-1 || j == m-1) && grid[i][j] == 'O'){
                    surrounded_DFS(grid, i, j, dir);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == '$') 
                    grid[i][j] = 'O';
                else 
                    grid[i][j] = 'X';
            }
        }
    }

    // https://www.hackerrank.com/challenges/journey-to-the-moon/problem

    //694 no. of distinct island
    //T=O(n*m), S=O(n*m)


    int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    String[] dirS = { "D", "U", "R", "L" };

    StringBuilder sb;
    int n, m;

    public void numDistinctIslands(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        for (int d = 0; d < 4; d++) {
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                sb.append(dirS[d]);
                numDistinctIslands(grid, r, c);
                sb.append("b");
            }
        }
    }

    public int numDistinctIslands(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sb = new StringBuilder();
                    numDistinctIslands(grid, i, j);
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    //1905

    public boolean countSubIslands(int[][] grid1, int[][] grid2, int i, int j) {
        grid2[i][j] = 0;
        boolean res = true;
        for(int d = 0; d < 4; d++){
            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if(r>=0 && c>=0 && r<n && c<m && grid2[r][c] == 1){
                res = countSubIslands(grid1, grid2, r, c) && res;
            }
        }
        return res && grid1[i][j] == 1;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        n = grid2.length;
        m = grid2[0].length;
        int count =0;
        for(int i = 0; i<n; i++){
            for(int j = 0; j< m; j++){
                if(grid2[i][j] == 1){
                    count += countSubIslands(grid1, grid2, i, j) ? 1 : 0;
                }
            }
        }
        return count;
    }

}