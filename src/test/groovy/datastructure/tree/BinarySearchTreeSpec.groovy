package datastructure.tree

import datastructure.tree.traversal.BreadthFirstTraversal
import spock.lang.Specification
import spock.lang.Subject

import java.util.stream.Collectors;

class BinarySearchTreeSpec extends Specification {

    @Subject
    def tree = new BinarySearchTree()

    def "add a value to the tree"() {
        expect:
        tree.add(20).value() == 20
        tree.add(21).value() == 21
    }

    def "add a duplicate to the tree"() {
        given:
        def first = tree.add(20)
        expect:
        tree.add(20) == first
    }

    def "find a value in the tree"() {
        given:
        tree.add(20)
        tree.add(21)
        tree.add(13)
        expect:
        tree.find(20).value() == 20
        tree.find(21).value() == 21
        tree.find(13).value() == 13
        tree.find(-1) == null
    }

    def "check children of the node"() {
        given:
        Tree.Node parent = tree.add(20)
        tree.add(21)
        tree.add(13)
        when:
        def children = tree.childrenOf(parent).stream().map({n -> n.value()}).collect(Collectors.toList())
        then:
        children == [13, 21]
    }

    def "add values to the tree"() {
        when:
        tree.add(20)
        tree.add(30)
        tree.add(40)
        tree.add(10)
        tree.add(25)
        then:
        !tree.isEmpty()
        tree.size() == 5
        tree.root().value() == 20
        tree.values(new BreadthFirstTraversal()) == [20, 10, 30, 25, 40]
    }

    def "remove a value that the tree doesn't contain"() {
        given:
        tree.add(20)
        expect:
        !tree.remove(-1)
    }

    def "remove the root from the tree"() {
        given:
        tree.add(20)
        expect:
        tree.remove(20)
        tree.root() == null
        tree.isEmpty()
    }

    def "remove a leaf from the tree"() {
        given:
        tree.add(20)
        tree.add(30)
        tree.add(10)
        tree.add(11)
        when:
        boolean removed = tree.remove(11)
        then:
        removed
        tree.values(new BreadthFirstTraversal()) == [20, 10, 30]
    }

    def "remove a node that has only right subtree"() {
        given:
        tree.add(20)
        tree.add(10)
        tree.add(30)
        tree.add(35)
        when:
        boolean removed = tree.remove(30)
        then:
        removed
        tree.values(new BreadthFirstTraversal()) == [20, 10, 35]
    }

    def "remove a node that has only left subtree"() {
        given:
        tree.add(20)
        tree.add(10)
        tree.add(8)
        tree.add(35)
        when:
        boolean removed = tree.remove(10)
        then:
        removed
        tree.values(new BreadthFirstTraversal()) == [20, 8, 35]
    }

    def "remove a node that has both left and right subtree"() {
        given:
        tree.add(20)
        tree.add(10)
        tree.add(13)
        tree.add(8)
        tree.add(25)
        tree.add(21)
        tree.add(35)
        tree.add(23)

        def tree2 = new BinarySearchTree()
        tree2.add(20)
        tree2.add(10)
        tree2.add(25)
        when:
        boolean removed = tree.remove(20)
        boolean removed2 = tree2.remove(20)
        then:
        removed
        removed2
        tree.values(new BreadthFirstTraversal()) == [21,10,25,8,13,23,35]
        tree2.values(new BreadthFirstTraversal()) == [25, 10]
    }

    def "check left and right children of the node"() {
        given:
        def node = tree.add(20)
        def left = tree.add(10)
        def right = tree.add(25)
        expect:
        tree.leftOf(node) == left
        tree.rightOf(node) == right
    }
}
