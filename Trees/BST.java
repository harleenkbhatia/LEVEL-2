import java.util.ArrayList;

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
        TreeNode curr = root;
        while(curr.right!=null) //rightMost element is the maximum element in a bst
            curr = curr.right;

        return curr.val;
    }

    public static int Minimum(TreeNode root){ //in terms of edges
        TreeNode curr = root;
        while(curr.left!=null) //leftMost element is the minimum element in a bst
            curr = curr.left;

        return curr.val;
    }

    public static boolean find(TreeNode root, int data){
        TreeNode curr = root;
        while(curr != null){
            if(curr.val == data) 
                return true;
            else if(curr.val > data) 
                curr = curr.left;
            else
                curr = curr.right;
        }
        return false;
    }

    public static ArrayList<TreeNode> rootToNodePath(TreeNode root, int data){
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        boolean flag = false;
        while(curr != null){
            ans.add(curr);
            if(curr.val == data) {
                flag = true;
                break;
            }
            else if(curr.val > data) 
                curr = curr.left;
            else
                curr = curr.right;
        }

        if(!flag) 
            ans.clear(); //clear->logn

        return ans;
    }
    
    public static TreeNode lowestCommonAncester(TreeNode root, int p, int q){
        TreeNode curr = root;
        while(curr != null){
            if(curr.val < p && curr.val < q)  //p,q are greater
                curr = curr.right;
            else if(curr.val > p && curr.val > q)   //p,q are smaller
                curr = curr.left;
            else
                return curr;
        }
        return null;
    }

    class BSTIterator {

        private ArrayDeque<TreeNode> st = new ArrayDeque<>(); //addFirst, removeFirst
        // ArrayDeque behaves same as LL but is slightly faster 
        // private LinkedList<TreeNode> st = new LinkedList<>(); //addFirst, removeFirst
        
        public BSTIterator(TreeNode root) {
            addAllLeft(root);        
        }
        private void addAllLeft(TreeNode node){
            while(node != null){
                this.st.addFirst(node);
                node = node.left;
            }
        }
        public int next() {
            TreeNode rNode = this.st.removeFirst();
            addAllLeft(rNode.right);
            return rNode.val;
        }
    
        public boolean hasNext() {
            return this.st.size() != 0;
        }
    }

    public static void successorAndPredcessor(TreeNode node, int data) {

        TreeNode curr = node, pred = null, succ = null;

        while (curr != null) {
            if (curr.val == data) {

                TreeNode leftMost = getLeftMost(node.right);
                succ = leftMost != null ? leftMost : succ;

                TreeNode rightMost = getRightMost(node.left);
                pred = rightMost != null ? rightMost : pred;

                break;

            } else if (data < curr.val) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
    }

    // S: logn, T : o(n)
    public static int kthLargestEle(TreeNode node, int k) {

    }


}