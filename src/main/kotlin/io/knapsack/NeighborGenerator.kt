package io.knapsack

interface NeighborGenerator {

    fun generate(items: List<Item>, knapsack: Knapsack): List<Knapsack>

}

class SimpleNeighborGenerator : NeighborGenerator {

    override fun generate(items: List<Item>, knapsack: Knapsack): List<Knapsack> {
        return items.mapNotNull {
            if (knapsack.willExceedSize(it))
                null
            else
                knapsack.copyWithItem(it)
        }
    }

}


