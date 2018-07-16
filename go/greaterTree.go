import "fmt"
var Value int = 0

func convertBST(root *TreeNode) *TreeNode {
    if root == nil {
        return nil
    }
    convertBST(root.Right)
    root.Val = root.Val + Value
    Value = root.Val
    fmt.Println("Found Value is:", Value)
    convertBST(root.Left)
    return root
}
