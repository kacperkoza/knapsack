package io.knapsack

import io.knapsack.knapsack.Item
import io.knapsack.knapsack.KnapsackProblemSolver
import spock.lang.Specification

class KnapsackProblemSpec extends Specification {

    KnapsackProblemSolver solver

    List<Item> generateItems() {
        return [
                new Item(8, 5),
                new Item(7, 6),
                new Item(12, 10),
                new Item(6, 4),
//                new Item(2, 1),
//                new Item(3, 1),
                new Item(5,3),
                new Item(2, 5),
                new Item(4, 5),
//                new Item(100, 34),
                new Item(40, 21),
                new Item(40, 19),
                new Item(5, 5),
                new Item(8, 6),
                new Item(12, 3),
                new Item(4, 4),
                new Item(10, 8),
                new Item(15, 3),
                new Item(10, 8),
                new Item(20, 7),
                new Item(3, 1),
                new Item(4, 8),
                new Item(12, 5),
                new Item(18, 7),
                new Item(70, 40),
                new Item(10, 8),
        ]
    }
}
