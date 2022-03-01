import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;

public class bfsQuestions{

    class Solution {
    
    //T: O(n*m)
    public int orangesRotting(int[][] grid) {
        
        LinkedList<Integer> que = new LinkedList<>();
        
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        
        int freshOranges = 0, time = 0, n = grid.length, m = grid[0].length;
        for(int i = 0; i<n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1) freshOranges++;
                else if(grid[i][j] == 2){
                    que.addLast(i * m + j);
                    grid[i][j] = 2; // markinng them visited
                }
            }
        }

        if(freshOranges == 0) return 0;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rottenOrangeIDX = que.removeFirst();
                int sr = rottenOrangeIDX / m;
                int sc = rottenOrangeIDX % m;

                for(int d = 0; d < 4; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r>=0 && c>=0 && r < n && c < m && grid[r][c] == 1){
                        if(--freshOranges == 0) 
                            return time + 1;
                        grid[r][c] = 2;
                        que.addLast(r * m + c);
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length, shortestPath = 1;
        if(n == 0 || m == 0) 
            return 0;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1)
            return -1;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        que.addLast(0);
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                if(sr == n - 1 && sc == m - 1) 
                    return shortestPath;

                for(int d = 0; d < 8; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r>=0 && c>=0 && r < n && c < m && grid[r][c] == 0){
                        grid[r][c] = 1;
                        que.addLast(r * m + c);
                    }
                }
            }
            shortestPath++;
        }
        return -1;
    }

    //leetcode 542
    public int[][] updateMatrix(int[][] grid) {
        int n = grid.length, m = grid[0].length, shortestPath = 1;
        if(n == 0 || m == 0) 
            return grid;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 }};
        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(grid[i][j]==0){
                    vis[i][j] = true;
                    que.addLast(i * m + j);
                }
            }
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for(int d = 0; d < 4; d++){
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if(r>=0 && c>=0 && r < n && c < m && !vis[r][c]){
                        vis[r][c] = true;
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
        return grid;        
    }

    //leetcode 286
    public void wallsAndGates(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return;

        int n = grid.length, m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    que.addLast(i * m + j);
                }
            }
        }

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int idx = que.removeFirst();
                int sr = idx / m, sc = idx % m;

                for (int d = 0; d < dir.length; d++) {
                    int r = sr + dir[d][0];
                    int c = sc + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 2147483647) {
                        grid[r][c] = grid[sr][sc] + 1;
                        que.addLast(r * m + c);
                    }
                }
            }
        }
    }

    //815 bus routes leetcode

    public int numBusesToDestination(int[][] routes, int source, int target) {
        int N = routes.length;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>(); // busStand to bus mapping
        for (int bus = 0; bus < routes.length; bus++) {
            for (int busStand : routes[bus]) {
                map.putIfAbsent(busStand, new ArrayList<>());
                map.get(busStand).add(bus);
            }
        }

        HashSet<Integer> busStandVisted = new HashSet<>();
        boolean[] busVisited = new boolean[N];
        int interchange = 0;

        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(source);
        busStandVisted.add(source);

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int busStand = que.removeFirst();
                for (int bus : map.get(busStand)) {

                    if (busVisited[bus])
                        continue;

                    for (int upcomingBusStand : routes[bus]) {
                        if (!busStandVisted.contains(upcomingBusStand)) {
                            busStandVisted.add(upcomingBusStand);
                            que.addLast(upcomingBusStand);
                            if (upcomingBusStand == target) {
                                return interchange + 1;
                            }
                        }
                    }

                    busVisited[bus] = true;
                }
            }
            interchange++;
        }

        return -1;
    }


}