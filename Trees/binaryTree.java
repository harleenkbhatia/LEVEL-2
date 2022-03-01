import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class binaryTree{
    public static class TreeNode{
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val){
            this.val = val;
        }
    }
    public static int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root){ //in terms of edges
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int Maximum(TreeNode root){ //in terms of edges
        return root == null ? -(int)1e9 : Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val) ;
    }

    public static int Minimum(TreeNode root){ //in terms of edges
        return root == null ? (int)1e9 : Math.min(Math.min(Minimum(root.left), Minimum(root.right)), root.val) ;
    }

    public static boolean find(TreeNode root, int data){
        if(root == null) 
            return false;
        if(root.val == data) 
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    public static boolean nodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans){ //like find
        if(root == null) return false;
        if(root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        if (res){
            ans.add(root);
        }
        return res;
    }

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data){
        
        if(root == null) 
            return new ArrayList<>();

        if(root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        
        ArrayList<TreeNode> left = nodeToRootPath(root.left, data);
        if(left.size() != 0){
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = nodeToRootPath(root.right, data);
        if(right.size() != 0){
            right.add(root);
            return right;
        }
        return new ArrayList<>();//doesnt matter as jo ret hona hoga vo abi tk hogya hoga
    }

    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> smallAns){
        if(root == null) 
            return;
        if(root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallAns); //creating a copy of smallAns
            base.add(root.val);
            ans.add(base);
            return;
        }
        smallAns.add(root.val);

        rootToAllLeafPath(root.left, ans, smallAns);
        rootToAllLeafPath(root.right, ans, smallAns);

        smallAns.remove(smallAns.size() - 1);

    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallAns = new ArrayList<>(); 
        // smallAns acts like a bucket that gets filled while traversing down and gets empty while backtracking up
        
        rootToAllLeafPath(root, ans, smallAns);
        return ans;        
    }

    public static void singleChildNodes(TreeNode node, ArrayList<Integer> ans){
        if(node == null || (node.left == null && node.right == null)) return; //if empty or leaf node return

        if(node.left == null || node.right == null){
            ans.add(node.val);
        }
        singleChildNodes(node.left, ans);
        singleChildNodes(node.right, ans);
    }

    public static int countSingleChildNodes(TreeNode node){
        if(node == null || (node.left == null && node.right == null)) return 0; //if empty or leaf node return

        int left = countSingleChildNodes(node.left);
        int right = countSingleChildNodes(node.right);

        int ans = left + right;
        if(node.left == null || node.right == null)
            ans++;
        return ans;
    }

    public static void kDown(TreeNode root, TreeNode blockNode, int K, List<Integer> ans){
        if(root== null || root == blockNode || K<0){
            return;
        }
        if(K==0){
            ans.add(root.val);
            return;
        }
        kDown(root.left, blockNode, K-1, ans);
        kDown(root.right, blockNode, K-1, ans);
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K){
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.val, path);

        TreeNode blockNode = null;
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i<path.size(); i++){
            if(K-i < 0) break;
            kDown(path.get(i), blockNode, K - i, ans);
            blockNode = path.get(i);
        }
        return ans;
    }
    
    //optimised do this one after you have done the 1st one and thaye asked you to do it optimised
    // this will make them think u can think on that point itself

    public int distanceK_01(TreeNode root, TreeNode target, int k, ArrayList<Integer> ans) {
        if (root == null)
            return -1;
        if (root == target) {
            kDown(root, null, k, ans);
            return 1;
        }

        int ld = distanceK_01(root.left, target, k, ans);
        if (ld != -1) {
            kDown(root, root.left, k - ld, ans);
            return ld + 1;
        }

        int rd = distanceK_01(root.right, target, k, ans);
        if (rd != -1) {
            kDown(root, root.right, k - rd, ans);
            return rd + 1;
        }

        return -1;
    }

    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kdown(root.left, time + 1, blockNode, ans);
        kdown(root.right, time + 1, blockNode, ans);
    }

    public static int burningTree(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == target) {
            kdown(root, 0, null, ans);
            return 1;
        }

        int ld = burningTree(root.left, target, ans);
        if (ld != -1) {
            kdown(root, ld, root.left, ans);
            return ld + 1;
        }

        int rd = burningTree(root.right, target, ans);
        if (rd != -1) {
            kdown(root, rd, root.right, ans);
            return rd + 1;
        }

        return -1;
    }

    
    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null || root == blockNode || water.contains(root.val))
            return;

        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        kdown(root.left, time + 1, blockNode, ans);
        kdown(root.right, time + 1, blockNode, ans);
    }

    // -1 : did we gett the target node, -2 : fire will not reach that node, t > 0 :
    // fire will reach with time t.
    public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null)
            return -1;
        if (root.val == target) {
            if (!water.contains(root.val)) {
                kdown(root, 0, null, ans);
                return 1;
            } else
                return -2;
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if (ld > 0) {
            if (!water.contains(root.val)) {
                kdown(root, ld, root.left, ans);
                return ld + 1;
            }
            return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if (rd > 0) {
            if (!water.contains(root.val)) {
                kdown(root, rd, root.right, ans);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;
    }

    public static void burningTree(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
    }

    // ==================================================================================


    TreeNode LCA = null;

    public boolean lowestCommonAncestor_(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return false;

        boolean self = false;
        if (node == p || node == q)
            self = true;

        boolean left = lowestCommonAncestor_(node.left, p, q);
        if(LCA != null) return true;
        //left saare ghumliye aur lca milgya toh ab hume need ni h right m jane ki
        boolean right = lowestCommonAncestor_(node.right, p, q);

        if ((left && right) || (left && self) || (right && self))
            LCA = node;

        return left || right || self;
    }
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(node, p, q);
        return LCA;
    }
    public static class allSoluPair {
        TreeNode pred = null;
        TreeNode succ = null;

        int ceil = (int) 1e9;
        int floor = -(int) 1e9;

        TreeNode prev = null;
    }

    public static void allSolution(TreeNode node, int data, allSoluPair pair) {
        if (node == null)
            return;

        if (node.val < data)
            pair.floor = Math.max(pair.floor, node.val);

        if (node.val > data)
            pair.ceil = Math.min(pair.ceil, node.val);

        allSolution(node.left, data, pair);

        if (node.val == data)
            pair.pred = pair.prev;

        if (pair.prev != null && pair.prev.val == data)
            pair.succ = node;

        pair.prev = node;

        allSolution(node.right, data, pair);
    }
}