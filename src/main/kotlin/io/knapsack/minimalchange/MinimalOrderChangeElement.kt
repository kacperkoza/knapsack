package io.knapsack.minimalchange


data class GrayCode(
    private val index: Int,
    private val binaryLength: Int
) {

    private var value: Int = 0

    init {
        this.value = index xor (index shr 1)
        this.value = this.value and mask()
    }

    private fun mask(): Int {
        var result = 0
        for (i in 0 until binaryLength) {
            result = result or (1 shl i)
        }
        return result
    }

    /**
     * returns index in minimal order change set
     */
    fun rank(): Int {
        var temp = value
        var mask = temp shr 1
        while (mask != 0) {
            temp = temp xor mask
            mask = mask shr 1
        }
        return temp
    }

}
