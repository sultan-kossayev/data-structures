package datastructure.array

import spock.lang.Specification
import spock.lang.Subject

class FixedSizeArrayLazyIteratorSpec extends Specification {

    @Subject
    def array = new FixedSizeArray(3)

    def setup() {
        array.append(10)
        array.append(11)
        array.append(12)
    }

    def "given an array iterate through it"() {
        given:
        def arr = [10, 11, 12]
        int i = 0
        expect:
        array.every {
            it == arr[i++]
        }
    }

    def "given an empty iterator check whether there are any elements left for iteration"() {
        given:
        def it = array.iterator()
        it.next()
        it.next()
        it.next()
        expect:
        !it.hasNext()
    }

    def "try to iterate when there are no elements left for iteration"() {
        given:
        def it = array.iterator()
        it.next()
        it.next()
        it.next()
        when:
        it.next()
        then:
        thrown(NoSuchElementException)
    }
}
