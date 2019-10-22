package datastructure.tree.traversal

import datastructure.tree.BinarySearchTree
import spock.lang.Specification
import spock.lang.Subject

class PostOrderTraversalSpec extends Specification {

    @Subject
    def postOrder = new PostOrderTraversal()

    def "given a tree traverse it using postorder traversal"() {
        given:
        def tree = new BinarySearchTree()
        tree.add(20)
        tree.add(30)
        tree.add(40)
        tree.add(10)
        tree.add(25)
        expect:
        postOrder.traverse(tree) == [10,25,40,30,20]
    }
}
