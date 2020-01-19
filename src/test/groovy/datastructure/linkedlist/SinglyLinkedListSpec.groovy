package datastructure.linkedlist

import spock.lang.Specification
import spock.lang.Subject

class SinglyLinkedListSpec extends Specification {

    @Subject
    def ll = new SinglyLinkedList()

    def "given an empty list get first and last element"() {
        expect:
        ll.getFirst() == null
        ll.getLast() == null
    }

    def "given an empty list remove first element"() {
        expect:
        ll.removeFirst() == null
    }

    def "given an element add it to the beginning of the empty linked list"() {
        given:
        def element = 1
        when:
        ll.addFirst(element)
        then:
        ll.getFirst() == element
        ll.getLast() == element
        ll.size() == 1
        !ll.isEmpty()
    }

    def "given an element add it to the beginning of the linked list with 1 element"() {
        given:
        ll.addFirst(2)
        def element = 1
        when:
        ll.addFirst(element)
        then:
        ll.size() == 2
        ll.getFirst() == 1
        ll.getLast() == 2
    }

    def "given an element add it to the beginning of the linked list with 2 elements"() {
        given:
        ll.addFirst(3)
        ll.addFirst(2)
        when:
        ll.addFirst(1)
        then:
        ll.size() == 3
        ll.getFirst() == 1
        ll.getLast() == 3
    }

    def "given an element add it to the end of the empty list"() {
        when:
        ll.addLast(10)
        then:
        ll.getFirst() == 10
        ll.getLast() == 10
        !ll.isEmpty()
        ll.size() == 1
    }

    def "given the list with 1 element add a new element to the end of the list"() {
        given:
        ll.addLast(1)
        when:
        ll.addLast(2)
        then:
        ll.getFirst() == 1
        ll.getLast() == 2
    }

    def "given the list with 2 elements add a new one to the end of the list"() {
        given:
        ll.addLast(1)
        ll.addLast(2)
        when:
        ll.addLast(3)
        then:
        ll.getFirst() == 1
        ll.getLast() == 3
    }

    def "given a list with the 1 element remove the first"() {
        given:
        ll.addFirst(1)
        expect:
        ll.removeFirst() == 1
        ll.getFirst() == null
        ll.getLast() == null
        ll.size() == 0
        ll.isEmpty()
    }

    def "given a list with the 2 elements remove the first"() {
        given:
        ll.addFirst(2)
        ll.addFirst(1)
        when:
        ll.removeFirst()
        then:
        ll.getFirst() == 2
        ll.getLast() == 2
        ll.size() == 1
    }

    def "given a list remove all elements starting from the beginning"() {
        given:
        (1..3).each {ll.addFirst(it)}
        expect:
        ll.removeFirst() == 3
        ll.removeFirst() == 2
        ll.removeFirst() == 1
        ll.isEmpty()
    }

    def "given a list when the last element is removed then the exception is thrown"() {
        given:
        ll.addFirst(1)
        when:
        ll.removeLast() == 1
        then:
        thrown(UnsupportedOperationException)
    }
}
