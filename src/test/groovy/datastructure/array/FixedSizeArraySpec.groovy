package datastructure.array

import spock.lang.Specification
import spock.lang.Subject

class FixedSizeArraySpec extends Specification {

    @Subject
    def array = new FixedSizeArray<Integer>(10)

    def "given the empty array expect the correct size"() {
        expect:
        array.size() == 0
        array.isEmpty()
    }

    def "given the element append it to the array"() {
        when:
        array.append(1)
        array.append(2)
        then:
        array.size() == 2
    }

    def "given the element insert it by index to the array"() {
        given:
        (1..3).each {array.append(it)}
        def element = 10
        when:
        array.insertTo(1, element)
        then:
        array.getBy(0) == 1
        array.getBy(1) == 10
        array.getBy(2) == 2
    }

    def "given the array with elements get an element by index"() {
        given:
        array.append(10)
        array.append(11)
        expect:
        array.getBy(0) == 10
        array.getBy(1) == 11
    }

    def "given the array with elements then remove the one by the index"() {
        given:
        (1..3).each {array.append(it)}
        expect:
        array.removeBy(1) == 2
        array.getBy(1) == 3
        array.size() == 2
    }

    def "given the full array when a new element is appended then expect the exception"() {
        given:
        (1..10).each{array.append(it)}
        when:
        array.append(11)
        then:
        thrown(IllegalStateException)
    }

    def "given the array with elements when it is accessed by the wrong index then expect the exception"() {
        given:
        (0..4).each{array.append(it)}
        when:
        array.getBy(index)
        then:
        thrown(ArrayIndexOutOfBoundsException)
        where:
        index << [-1, 5]
    }

    def "given the array with elements when an element by wrong index is removed then expect the exception"() {
        given:
        (0..4).each{array.append(it)}
        when:
        array.removeBy(index)
        then:
        thrown(ArrayIndexOutOfBoundsException)
        where:
        index << [-1, 5]
    }
}
