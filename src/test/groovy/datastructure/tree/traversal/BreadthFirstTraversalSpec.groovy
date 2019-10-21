package datastructure.tree.traversal

import spock.lang.Specification
import spock.lang.Subject
import datastructure.tree.BinarySearchTree

class BreadthFirstTraversalSpec extends Specification {

    @Subject
    def breadthFirst = new BreadthFirstTraversal()

    def "given a tree check breadth first traversal order"() {
        given:
        def tree = new BinarySearchTree()
        tree.add(20)
        tree.add(30)
        tree.add(40)
        tree.add(10)
        tree.add(25)
        expect:
        breadthFirst.traverse(tree) == [20,10,30,25,40]
    }
}