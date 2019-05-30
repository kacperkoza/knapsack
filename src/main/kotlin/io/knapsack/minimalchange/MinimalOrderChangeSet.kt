package io.knapsack.minimalchange

class GraySet(private val elementBinaryLength: Int) {
    private val size: Int
    private var current: GrayCode

    init {
        current = unRank(0)
        size = Math.pow(2.0, elementBinaryLength.toDouble()).toInt()
    }

    /**
     * next element in gray code
     */
    fun next(): GrayCode {
        val rank = nextRank(current.rank())
        current = unRank(rank)
        return current
    }

    /**
     * previous element in gray code
     */
    fun previous(): GrayCode {
        val rank = previousRank(current.rank())
        current = unRank(rank)
        return current
    }

    /**
     * current element in gray code
     */
    fun current() = current

    /**
     * get element of gray code at given index
     */
    fun unRank(index: Int) = GrayCode(index, elementBinaryLength)

    private fun previousRank(rank: Int) =
        if (rank == 0) {
            Math.pow(2.0, elementBinaryLength.toDouble()).toInt() - 1
        } else {
            rank - 1
        }

    private fun nextRank(rank: Int) = if (rank == size - 1) 0 else rank + 1

}
