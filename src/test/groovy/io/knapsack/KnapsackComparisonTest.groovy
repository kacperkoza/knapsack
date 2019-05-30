package io.knapsack

import io.knapsack.knapsack.BruteForceKnapsackProblem
import io.knapsack.knapsack.GreedyKnapsackProblem
import io.knapsack.knapsack.HillClimbingKnapsackProblemSolver
import io.knapsack.knapsack.Item
import io.knapsack.knapsack.Knapsack
import io.knapsack.knapsack.SimpleNeighborGenerator
import io.knapsack.knapsack.ValueToWeightRatioEvaluator

class KnapsackComparisonTest extends KnapsackProblemSpec {

    def items = generateItems()
    def knapsack = new Knapsack(50)

    def 'hill climbing knapsack problem'() {
        given:
        solver = new HillClimbingKnapsackProblemSolver(new ValueToWeightRatioEvaluator(), new SimpleNeighborGenerator())

        when:
        def result = solver.findSolution(items, knapsack)

        then:
        print("Hill climbing ")
        result.printSummary()
    }

    def 'greedy knapsack problem'() {
        given:
        solver = new GreedyKnapsackProblem()

        when:
        def result = solver.findSolution(items, knapsack)

        then:
        print("Greedy ")
        result.printSummary()
    }

    def 'brute force knapsack problem'() {
        given:
        solver = new BruteForceKnapsackProblem()

        when:
        def result = solver.findSolution(items
                + [
                new Item(8, 6),
                new Item(12, 3),
                new Item(4, 4),
                new Item(10, 8),
                new Item(15, 3),
                ]
                , knapsack)

        then:
        print("Brute force ")
        result.printSummary()
    }

}
