package datastructure.tree.traversal

import datastructure.tree.BinarySearchTree
import spock.lang.Specification
import spock.lang.Subject

class InOrderTraversalSpec extends Specification {

    @Subject
    def inOrder = new InOrderTraversal()

    def "given a tree check inorder traversal"() {
        given:
        def tree = new BinarySearchTree()
        tree.add(20)
        tree.add(30)
        tree.add(40)
        tree.add(10)
        tree.add(25)
        expect:
        inOrder.traverse(tree) == [10,20,25,30,40]
    }
}