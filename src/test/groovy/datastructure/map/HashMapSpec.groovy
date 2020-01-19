package datastructure.map


import spock.lang.Specification
import spock.lang.Subject

class HashMapSpec extends Specification {

    @Subject
    def map = new HashMap()

    def "given an empty map check its invariants"() {
        given:
        def hashMap = new HashMap(32, 0.75f)
        expect:
        hashMap.threshold == (int) (32 * 0.75f)
        hashMap.buckets.length == 32
        hashMap.size() == 0
        hashMap.isEmpty()
    }

    def "when a map is initialized check that it always allocates buckets with power of two capacity"() {
        when:
        def hashMap = new HashMap(capacity, 0.75f)
        then:
        hashMap.buckets.length == bucketSize
        where:
        capacity    | bucketSize
        -1          | 1
        0           | 1
        1           | 1
        6           | 8
        16          | 16
        45          | 64
    }

    def "given a key and value put them to the map"() {
        when:
        map.put(1, "one")
        then:
        map.size() == 1
        !map.isEmpty()
        map.get(1) == "one"
        map.containsKey(1)
    }

    def "given a map put null key and value"() {
        when:
        map.put(null, null)
        then:
        map.size() == 1
        map.get(null) == null
        map.containsKey(null)
    }

    def "given a map with null key and value remove null key"() {
        given:
        map.put(null, null)
        when:
        map.remove(null)
        then:
        map.size() == 0
        !map.containsKey(null)
    }

    def "given a map get a key that the map doesn't contain"() {
        given:
        map.put(1, "one")
        expect:
        map.get(2) == null
        !map.containsKey(2)
    }

    def "given a map remove a key that the map doesn't contain"() {
        given:
        map.put(1, "one")
        expect:
        map.remove(2) == null
        map.size() == 1
    }

    def "given keys with the same hash put them to the map"() {
        given:
        def hashMap = new HashMap(4, 1f)
        when:
        hashMap.put(1, "one") // hash 1
        hashMap.put(5, "five") // hash 1
        hashMap.put(9, "nine") // hash 1
        then:
        hashMap.get(1) == "one"
        hashMap.get(5) == "five"
        hashMap.get(9) == "nine"
    }

    def "given a map with a key put the same key with a new value"() {
        given:
        map.put(1, "one")
        expect:
        map.get(1) == "one"
        map.put(1, "number one") == "one"
        map.get(1) == "number one"
        map.size() == 1
    }

    def "given a map remove a key from it"() {
        given:
        map.put(1, "one")
        expect:
        map.remove(1) == "one"
        map.get(1) == null
        map.size() == 0
    }

    def "given a map with the keys with same hash remove one key from it"() {
        given:
        def hashMap = new HashMap(4, 1f)
        hashMap.put(1, "one") // hash 1
        hashMap.put(5, "five") // hash 1
        hashMap.put(9, "nine") // hash 1
        expect:
        hashMap.remove(1) == "one"
        hashMap.get(5) == "five"
        hashMap.get(9) == "nine"
    }

    def "given a full map check that it is resized correctly when a new element is added"() {
        given:
        def hashMap = new HashMap(2, 1)
        def initialThreshold = hashMap.threshold
        def initialBucketSize = hashMap.buckets.length
        hashMap.put(1, "one")
        hashMap.put(2, "two")
        when:
        hashMap.put(3, "three")
        then:
        hashMap.threshold == initialThreshold * 2
        hashMap.buckets.length == initialBucketSize * 2
        hashMap.get(1) == "one"
        hashMap.get(2) == "two"
        hashMap.get(3) == "three"
    }
}
