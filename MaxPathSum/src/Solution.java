public class Solution {

    public int maxPathSum(TreeNode root) {

        if(root.left == null && root.right == null) return root.val;

        if(root.left != null && root.right != null){

            maxpath = maxPathsum(root.left);

        }
    }
}
