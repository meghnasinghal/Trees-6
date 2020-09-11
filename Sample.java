// Time Complexity : O(n)
// Space Complexity : O(h) recursion stack space
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : None


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
class Solution {
    int result;
    public int rangeSumBST(TreeNode root, int L, int R) {
        //edge
        if(root == null)
            return 0;
        result = 0;
        dfs(root,L,R);
        return result;
    }
    
    private void dfs(TreeNode root, int L, int R)
    {
        //base
        if(root == null)
            return;
        
        //logic
        //check the range
        if(root.val>=L && root.val<= R)
        {
            result+=root.val;
        }
        
        //not checking left if value < L
        if(root.val>L)
            dfs(root.left, L, R);
        //not checking left if value > R
        if(root.val<R)
            dfs(root.right, L, R);
        
    }
}

// Time Complexity : O(n)
// Space Complexity : O(h) stack space
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : None

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
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        if(root == null)
            return result;
        
        Stack<TreeNode> st = new Stack<>();
        while(!st.isEmpty() || root!=null)
        {
            while(root!=null)
            {
                st.push(root);
                if(root.val< L)
                    break;
                root = root.left;
            }
    
        
         root = st.pop();
         if(root.val>=L && root.val <= R)
            result+= root.val;
        
        if(!st.isEmpty() && root.val>R)
            root = st.pop();
            
        root = root.right;
        
            
        }
       
        return result;
    }
}

// Time Complexity : O(n) + O(l)
// Space Complexity : O(n) queue
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this : None


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //edge 
        if(root == null)
            return "";
       StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            if(curr != null) // when not null process left and right
            {
                sb.append(curr.val);
                sb.append(",");
                q.add(curr.left);
                q.add(curr.right); // we add nulls as well
            }
            else // when curr is null no need to process left and right
            {
                sb.append("null");
                sb.append(",");
            }
        }
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //edge case
        if(data == "")
            return null;
        
        Queue<TreeNode> q = new LinkedList<>();
        int i = 0;
        String[] split = data.split(","); // split the string
        TreeNode root = new TreeNode(Integer.parseInt(split[i])); // add root
        q.add(root);
        i++; // traverse data string
        
        while(!q.isEmpty() && i<split.length) // till we dont finish the string or q gets empty
        {
            TreeNode curr = q.poll(); // get curr
            if(!split[i].equals("null")) //check if not null at i
            {
                curr.left = new TreeNode(Integer.parseInt(split[i])); // make it left child of curr
                q.add(curr.left);
            }
            i++;
             //increase i and if not null make it the right child of i
            if(!split[i].equals("null"))
            {
                curr.right = new TreeNode(Integer.parseInt(split[i]));
                q.add(curr.right);
            }
            i++; // increase i
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));