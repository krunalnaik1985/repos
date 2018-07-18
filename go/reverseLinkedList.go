package go

func reverseList(head *ListNode) *ListNode {
    var reverseListNode *ListNode
    reverseListNode = nil
    for head != nil {
        var tempListNode *ListNode
        tempListNode = head.Next
        head.Next = reverseListNode
        reverseListNode = head
        head = tempListNode
    }
    return reverseListNode 
}
