public class directedQuestions{

    //207 leetcode

    public boolean canFinish(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
            for(int[] a : arr){
                graph[a[0]].add(a[1]);
            }
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[N];

        // O(E)
        for (int i = 0; i < N; i++) {
            for (Integer v : graph[i]) {
                indegree[v]++;
            }
        }

        // O(V)
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        // O(E + V)
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);

            for (Integer v : graph[rvtx]) {
                if (--indegree[v] == 0)
                    que.addLast(v);
            }
        }

        return ans.size() == N;

    }

    //210 leetcode

    public int[] findOrder(int N, int[][] arr) {
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
            for(int[] a : arr){
                graph[a[1]].add(a[0]);
            }
        LinkedList<Integer> que = new LinkedList<>();
        int[] ans = new int[N];
        int[] indegree = new int[N];
        int idx = 0;

        // O(E)
        for (int i = 0; i < N; i++) {
            for (Integer v : graph[i]) {
                indegree[v]++;
            }
        }

        // O(V)
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        // O(E + V)
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans[idx++] = rvtx;

            for (Integer v : graph[rvtx]) {
                if (--indegree[v] == 0)
                    que.addLast(v);
            }
        }

        if (idx != N)
            ans = new int[0];

        return ans;
    }

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        int[][] indegree = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] > matrix[r][c]) //merepe kitni degree aarhi h
                        indegree[i][j]++;
                }

                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
            }
        }
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                int rvtx = que.removeFirst();
                int i = rvtx / m, j = rvtx % m;

                for (int d = 0; d < 4; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && matrix[i][j] < matrix[r][c] && --indegree[r][c] == 0)
                        que.addLast(r * m + c);

                }
            }
            level++;
        }
        return level;
    }
}