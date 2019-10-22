package datastructure.tree.traversal

import datastructure.tree.BinarySearchTree
import spock.lang.Specification
import spock.lang.Subject

class PreOrderTraversalSpec extends Specification {

    @Subject
    def preOrder = new PreOrderTraversal()

    def "given a tree check traversal order"() {
        given:
        def tree = new BinarySearchTree()
        tree.add(20)
        tree.add(30)
        tree.add(40)
        tree.add(10)
        tree.add(25)
        expect:
        preOrder.traverse(tree) == [20,10,30,25,40]
    }
}