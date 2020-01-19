package datastructure.queue

import spock.lang.Specification
import spock.lang.Subject

class LinkedQueueSpec extends Specification {

    @Subject
    def queue = new LinkedQueue()

    def "given an empty queue check it"() {
        expect:
        queue.size() == 0
        queue.isEmpty()
        queue.first() == null
        queue.dequeue() == null
    }

    def "given an empty queue insert the element"() {
        when:
        queue.enqueue(10)
        then:
        queue.size() == 1
        !queue.isEmpty()
        queue.first() == 10
        queue.dequeue() == 10
    }

    def "given a queue remove elements"() {
        given:
        queue.enqueue(10)
        queue.enqueue(11)
        expect:
        queue.first() == 10
        queue.dequeue() == 10
        queue.size() == 1
        queue.first() == 11
        queue.dequeue() == 11
        queue.size() == 0
        queue.first() == null
        queue.dequeue() == null
    }

    def "given a queue remove elements and then add new"() {
        given:
        queue.enqueue(10)
        queue.enqueue(11)
        when:
        queue.dequeue()
        queue.dequeue()
        queue.enqueue(12)
        queue.enqueue(13)
        then:
        queue.first() == 12
        queue.dequeue() == 12
        queue.first() == 13
        queue.dequeue() == 13
    }
}
