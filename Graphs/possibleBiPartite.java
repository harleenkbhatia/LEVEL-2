public class possibleBiPartite {
    
    public static boolean isBipartite(ArrayList<Integer>[] graph, int[] vis, int src){
        LinkedList<Integer> que = new LinkedList<>();
        int color = 0; // 0 : red, 1 : green
        que.add(src);
        boolean isCycle = false, isBipartite = true;
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                int rvtx = que.removeFirst();
                if(vis[rvtx] != -1){
                    isCycle = true;
                    if(vis[rvtx] != color){
                        isBipartite = false;
                        break;
                    }
                    continue;
                }

                vis[rvtx] = color;
                for(int v : graph[rvtx]){
                    if(vis[v] == -1){
                        que.addLast(v);
                    }
                }
            }
            color = (color + 1) % 2; 
            if(!isBipartite) break;
        }
        
        return isBipartite;
    }
    public static boolean possibleBipartition(int N, int[][] dislikes) {
        
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        
        ArrayList<Integer>[] graph = new ArrayList[N];
        for(int i = 0; i < N; ++i)
            graph[i] = new ArrayList<>();
        
        for(int[] p: dislikes){
            graph[p[0] - 1].add(p[1] - 1);
            graph[p[1] - 1].add(p[0] - 1);
        }
        
        
        boolean res = true;
        for(int i = 0; i < N; ++i){
            if(vis[i] == -1){
                res = res && isBipartite(graph, vis, i);
            }
        }
        return res;
    }
}