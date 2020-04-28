package datastructure.trie

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class StringTrieSpec extends Specification {

    @Subject
    def trie = new StringTrie()

    def "insert to the trie"() {
        expect:
        trie.insert("string")
        !trie.isEmpty()
        trie.size() == 1
    }

    def "insert duplicates to the trie"() {
        given:
        trie.insert("string")
        expect:
        !trie.insert("string")
        trie.size() == 1
    }

    def "find a string in the empty trie"() {
        expect:
        !trie.exists("string")
    }

    @Unroll
    def "check existence of a string '#str' in the trie"(def inserted, def searched, def result) {
        when:
        trie.insert(inserted)
        then:
        trie.exists(searched) == result
        where:
        inserted | searched | result
        "string" | "string" | true
        "string" | "strin"  | false
        "string" | ""       | false
        ""       | ""       | true
        ""       | "string" | false
    }

    def "remove from the trie"() {
        given:
        trie.insert("string")
        when:
        def removed = trie.remove("string")
        then:
        removed
        !trie.exists("string")
        trie.size() == 0
    }

    def "remove strings with the common prefix from the trie"() {
        given:
        trie.insert("string")
        trie.insert("store")
        expect:
        trie.exists("string")
        trie.remove("string")
        !trie.exists("string")

        trie.exists("store")
        trie.remove("store")
        !trie.exists("store")

        trie.isEmpty()
    }

    def "remove an empty string from the trie"() {
        expect:
        !trie.remove("")
        trie.insert("")
        trie.exists("")
        trie.remove("")
    }
}
