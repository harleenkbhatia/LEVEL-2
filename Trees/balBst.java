/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 //leetcode1382
class Solution {
    
    public int getHeight(TreeNode node){
        return node == null ? -1 : Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
    
    public int getBal(TreeNode node){
        int lh = getHeight(node.left);
        int rh = getHeight(node.right);
        
        return lh - rh;
    }
    

    // O(1)
    public TreeNode rightRotation(TreeNode A) {
        TreeNode B = A.left;
        TreeNode BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        B.right = getRotation(A);
        return getRotation(B);
    }

    // O(1)
    public TreeNode leftRotation(TreeNode A) {
        TreeNode B = A.right;
        TreeNode BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;
        
        B.left = getRotation(A);
        return getRotation(B);
    }

    // O(1)
    public TreeNode getRotation(TreeNode root) {

        if (getBal(root) >= 2) { // ll,lr
            if (getBal(root.left) >= 1) { // ll
                return rightRotation(root);
            } else { // lr
                root.left = leftRotation(root.left);
                return rightRotation(root);
            }

        } else if (getBal(root) <= -2) { // rr,rl
            if (getBal(root.right) <= -1) { // rr
                return leftRotation(root);
            } else { // rl
                root.right = rightRotation(root.right);
                return leftRotation(root);
            }
        }

        return root;
    }
    
    public TreeNode balanceBST(TreeNode root) {
        if(root == null) return null;
        
        root.left = balanceBST(root.left);
        root.right = balanceBST(root.right);
        
        return getRotation(root);
    }
}