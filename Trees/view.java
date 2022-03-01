import java.util.ArrayList;

public class view{

    public static void levelOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        int level = 0;
        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(level);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
            ans.add(smallAns);
            level++;
        }

        int count = 0;
        for (var list : ans) {
            System.out.println(count++ + " -> " + list);
        }
    }

    public static ArrayList<Integer> leftView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        ArrayList<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {

            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.left != null)
                    que.addLast(rnode.left);
                if (rnode.right != null)
                    que.addLast(rnode.right);
            }
        }

        return ans;
    }

    public static List<Integer> rightView(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {

            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.right != null)
                    que.addLast(rnode.right);
                if (rnode.left != null)
                    que.addLast(rnode.left);
            }
        }

        return ans;
    }

    public static void widthOfShadow(TreeNode node, int vl, int[] minMax){
        
        if(node == null) return;
        
        minMax[0]=Math.min(minMax[0], vl);
        minMax[1]=Math.max(minMax[1], vl);
        
        widthOfShadow(node.left, vl-1, minMax);
        widthOfShadow(node.right, vl+1, minMax);
    
    }

    public static class vPair{
        TreeNode node = null;
        int vl = 0;
        int level = 0;

        vPair(TreeNode node, int vl){
            this.node = node;
            this.vl = vl;
        }
        vPair(TreeNode node, int vl,int level){
            this.node = node;
            this.vl = vl;
            this.level = level;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(new ArrayList<>());
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null){
                    que.addLast(new vPair(node.left, vl - 1));
                }
                if(node.right != null){
                    que.addLast(new vPair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> bottomView(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(null);
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.set(vl, node.val);

                if(node.left != null){
                    que.addLast(new vPair(node.left, vl - 1));
                }
                if(node.right != null){
                    que.addLast(new vPair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> topView(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(null);
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                if(ans.get(vl) == null) ans.set(vl, node.val);

                if(node.left != null){
                    que.addLast(new vPair(node.left, vl - 1));
                }
                if(node.right != null){
                    que.addLast(new vPair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.val);
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> antiDiagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    smallAns.add(node.val);
                    if (node.right != null)
                        que.addLast(node.right);
                    node = node.left;
                }
            }

            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<Integer> verticalOrderSum(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minMax[0])));

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(null);
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.set(vl, ans.get(vl) + node.val);

                if(node.left != null){
                    que.addLast(new vPair(node.left, vl - 1));
                }
                if(node.right != null){
                    que.addLast(new vPair(node.right, vl + 1));
                }
            }
        }
        return ans;
    }

    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();

        ArrayList<Integer> ans = new ArrayList<>();
        que.addLast(root);

        while (que.size() != 0) {
            int size = que.size();
            int sum = 0;
            while (size-- > 0) { // diagonal
                TreeNode node = que.removeFirst();
                while (node != null) { // clusters of diagonal
                    sum += node.val;
                    if (node.left != null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }

            ans.add(sum);
        }

        return ans;
    }

    // do this vertical order sum method============================================================

    public static class ListNode{
        int data = 0;
        Node prev = null;
        Node next = null;

        ListNode(int data){
            this.data = data;
        }
    }
    public static verticalOrderSum_02(TreeNode root, ListNode node){
        node.data += root.val;
        if(root.left != null){
            if(node.prev == null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            verticalOrderSum_02(root.left, node.prev);
        }
        if(root.right != null){
            if(node.next == null){
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            verticalOrderSum_02(root.right, node.next);
        }
    }

    public static verticalOrderSum_02(TreeNode root){
        ListNode curr = new ListNode(0);
        verticalOrderSum_02(root, curr);
    }

    public static daigonalOrderSum_02(TreeNode root, ListNode node){
        node.data += root.val;
        if(root.left != null){
            if(node.prev == null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            daigonalOrderSum_02(root.left, node.prev);
        }
        if(root.right != null){
            daigonalOrderSum_02(root.right, node);
        }
    }

    public static daigonalOrderSum_02(TreeNode root){
        ListNode curr = new ListNode(0);
        daigonalOrderSum_02(root, curr);
    }

    public static antiDaigonalOrderSum_02(TreeNode root, ListNode node){
        node.data += root.val;
        if(root.left != null){
            antiDaigonalOrderSum_02(root.left, node);
        }
        if(root.right != null){
            if(node.next == null){
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            antiDaigonalOrderSum_02(root.right, node.next);
        }
    }

    public static antiDaigonalOrderSum_02(TreeNode root){
        ListNode curr = new ListNode(0);
        antiDaigonalOrderSum_02(root, curr);
    }

    //987==========================

    public static List<List<Integer>> verticalOrderTraversal(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.vl != b.vl) return a.vl - b.vl;
            return a.node.val-b.node.val;
        });

        PriorityQueue<vPair> childQue = new PriorityQueue<>((a,b)->{
            if(a.vl != b.vl) return a.vl - b.vl;
            return a.node.val-b.node.val;
        });
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0])));

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(new ArrayList<>());
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.remove();
                int vl = rp.vl;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null)
                    childQue.add(new vPair(node.left, vl - 1));
                
                if(node.right != null)
                    childQue.add(new vPair(node.right, vl + 1));

            }
            PriorityQueue<vPair> temp = que;
            que = childQue;
            childQue = temp;
        }
        return ans;
    }

    public static List<List<Integer>> verticalTraversal_02(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.level != b.level){
                return a.level - b.level;
            }
            else if(a.vl != b.vl) 
                return a.vl - b.vl;
            return a.node.val - b.node.val;
        });

        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int len = minMax[1] - minMax[0] + 1;

        que.add(new vPair(root, Math.abs(minMax[0]),0));

        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < len; i++){
            ans.add(new ArrayList<>());
        }
        while(que.size() != 0){
            int size = que.size();
            while(size-- > 0){
                vPair rp = que.remove();
                int vl = rp.vl;
                int level = rp.level;
                TreeNode node = rp.node;

                ans.get(vl).add(node.val);

                if(node.left != null)
                    que.add(new vPair(node.left, vl - 1, level+1));
                
                if(node.right != null)
                    que.add(new vPair(node.right, vl + 1, level+1));

            }
        }
        return ans;
    }
}