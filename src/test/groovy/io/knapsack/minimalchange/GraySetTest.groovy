package io.knapsack.minimalchange

import spock.lang.Specification

class GraySetTest extends Specification {

    def 'grayset example'() {
        given:
        def graySet = new GraySet(4)

        expect:
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.next())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.previous())
        println(graySet.unRank(3))
        println(graySet.unRank(4))
        println(graySet.unRank(5))
        println(graySet.unRank(6))
        println(graySet.unRank(7))
        println(graySet.unRank(4).rank())
        println(graySet.unRank(5).rank())
        println(graySet.unRank(6).rank())
        println(graySet.unRank(7).rank())
    }

    def 'rank should return index of elemnt in set'() {
        given:
        def grayCode = new GrayCode(2, 2)

        expect:
        grayCode.rank() == 2
    }

    def 'should return previous element of set'() {
        given:
        GraySet graySet = new GraySet(3)

        when:
        graySet.next()
        graySet.next()
        def grayCode = graySet.previous()

        then:
        grayCode == new GrayCode(1, 3)
    }

    def 'should return first element of set'() {
        given:
        GraySet graySet = new GraySet(3)

        when:
        def current = graySet.current()

        then:
        current == new GrayCode(0, 3)
    }

}
