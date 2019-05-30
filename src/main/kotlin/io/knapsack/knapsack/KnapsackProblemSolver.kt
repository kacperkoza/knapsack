package io.knapsack.knapsack

interface KnapsackProblemSolver {

    fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack

}

class HillClimbingKnapsackProblemSolver(
    private val heuristicEvaluator: HeuristicEvaluator,
    private val neighborGenerator: NeighborGenerator
) : KnapsackProblemSolver {

    override fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack {
        var bestKnapsack = knapsack

        while (bestKnapsack.canBeAddedAnyOf(items) && !bestKnapsack.isFull()) {
            val possibleNewItems = generateNeighbors(items, bestKnapsack)

            if (possibleNewItems.isNotEmpty()) {
                val sortedByHeuristic = sortByHeuristicEvaluate(possibleNewItems)
                bestKnapsack = sortedByHeuristic[0]
                items.remove(bestKnapsack.items.last())
            }
        }
        return bestKnapsack
    }

    private fun generateNeighbors(items: List<Item>, knapsack: Knapsack) = neighborGenerator.generate(items, knapsack)

    private fun sortByHeuristicEvaluate(items: List<Knapsack>) =
        items.sortedByDescending { heuristicEvaluator.evaluate(it) }

}

class GreedyKnapsackProblem : KnapsackProblemSolver {

    override fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack {
        val itemsWithValue = items
            .map {
                ItemWithValue(
                    it,
                    it.value.toDouble() / it.weight.toDouble()
                )
            }
            .sortedByDescending { it.valueToWeightRatio }
            .toMutableList()

        while (!knapsack.isFull() && knapsack.canBeAddedAnyOf(itemsWithValue.map { it.item }.toMutableList())) {
            val currentMostValuableThatCanBePut = itemsWithValue.find {
                knapsack.canBeAddedAnyOf(listOf(it.item).toMutableList())
            }
            knapsack.put(currentMostValuableThatCanBePut!!.item)
            itemsWithValue.removeAt(itemsWithValue.indexOf(currentMostValuableThatCanBePut))
        }
        return knapsack
    }

    data class ItemWithValue(
        val item: Item,
        val valueToWeightRatio: Double
    )

}

class BruteForceKnapsackProblem : KnapsackProblemSolver {

    var bestSoFar = 0
    var itemsCount: Int = 0
    lateinit var solution: BooleanArray
    lateinit var current: BooleanArray

    override fun findSolution(items: MutableList<Item>, knapsack: Knapsack): Knapsack {
        itemsCount = items.size
        solution = BooleanArray(itemsCount) // true entries = items giving best value
        current = BooleanArray(itemsCount) // true entries = items in current candidate
        solve(items, knapsack, itemsCount - 1)

        solution.forEachIndexed { index, solution ->
            if (solution) {
                val item = items[index]
                knapsack.put(item)
            }
        }
        return knapsack
    }

    private fun solve(items: MutableList<Item>, knapsack: Knapsack, k: Int) {
        // Base case: All items have been considered, so check result:
        if (k < 0) {
            // Find total weight and total value:
            var totalWeight = 0
            var totalValue = 0
            for (i in 0 until itemsCount) {
                if (current[i]) {
                    totalWeight += items[i].weight
                    totalValue += items[i].value
                }
            }
            // Check to see if we've got a better solution:
            if (totalWeight <= knapsack.capacity && totalValue > bestSoFar) {
                bestSoFar = totalValue
                copySolution()
            }


            return
        }
        // Recursive case: there are two possibilities for item k -- either
        // we select it for the knapsack or we don't. Try each possibility:
        current[k] = true
        solve(items, knapsack, k - 1)

        current[k] = false
        solve(items, knapsack, k - 1)
    }

    private fun copySolution() {
        for (i in 0 until itemsCount)
            solution[i] = current[i]
    }

}
