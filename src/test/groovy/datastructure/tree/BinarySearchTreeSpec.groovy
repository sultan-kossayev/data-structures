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

    def "add a duplicated value to the tree"() {
        when:
        tree.add(20)
        tree.add(20)
        then:
        thrown(IllegalArgumentException)
    }

    def "find a value from the tree"() {
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
}
