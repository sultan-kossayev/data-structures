package datastructure.stack

import spock.lang.Specification
import spock.lang.Subject

class LinkedStackSpec extends Specification {

    @Subject
    def stack = new LinkedStack()

    def "given the empty stack check it"() {
        expect:
        stack.size() == 0
        stack.isEmpty()
        stack.top() == null
        stack.pop() == null
    }

    def "given the empty stack insert the element"() {
        when:
        stack.push(10)
        then:
        stack.size() == 1
        !stack.isEmpty()
        stack.top() == 10
        stack.pop() == 10
    }

    def "given the empty stack insert 2 elements"() {
        when:
        stack.push(10)
        stack.push(11)
        then:
        stack.top() == 11
        stack.pop() == 11
    }

    def "given the stack with 1 element remove it"() {
        given:
        stack.push(10)
        when:
        int el = stack.pop()
        then:
        el == 10
        stack.top() == null
        stack.pop() == null
        stack.size() == 0
        stack.isEmpty()
    }

    def "given the stack with 2 elements remove all"() {
        given:
        stack.push(10)
        stack.push(11)
        expect:
        stack.size() == 2
        stack.top() == 11
        stack.pop() == 11
        stack.size() == 1
        stack.top() == 10
        stack.pop() == 10
        stack.size() == 0
        stack.pop() == null
        stack.top() == null
    }
}
