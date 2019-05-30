package io.knapsack.knapsack

interface HeuristicEvaluator {

    fun evaluate(knapsack: Knapsack): Int

}

class ValueToWeightRatioEvaluator : HeuristicEvaluator {

    override fun evaluate(knapsack: Knapsack) = knapsack.calculateValueDividedByWeight()

}
