data class Round(val elfPick: Selection, val selfPick: Selection) {
    fun result(): Result {
        return when (Pair(selfPick, elfPick)) {
            Pair(Selection.Rock, Selection.Paper) -> Result.Loss
            Pair(Selection.Rock, Selection.Scissors) -> Result.Win
            Pair(Selection.Paper, Selection.Rock) -> Result.Win
            Pair(Selection.Paper, Selection.Scissors) -> Result.Loss
            Pair(Selection.Scissors, Selection.Paper) -> Result.Win
            Pair(Selection.Scissors, Selection.Rock) -> Result.Loss
            else -> Result.Draw
        }
    }

    fun resultScore(): Int {
        return when (result()) {
            Result.Win -> 6
            Result.Draw -> 3
            Result.Loss -> 0
        }
    }

    fun fullScore(): Int {
        return resultScore() + selfPick.value()
    }
}

enum class Selection {
    Rock {
        override fun value() = 1
    },
    Paper {
        override fun value() = 2
    },
    Scissors {
        override fun value() = 3
    };

    companion object {
        fun fromChar(ch: Char): Selection {
            return when (ch) {
                'A', 'X' -> Rock
                'B', 'Y' -> Paper
                'C', 'Z' -> Scissors
                else -> throw Exception("Invalid Selection Char: $ch")
            }
        }
    }

    abstract fun value(): Int

    fun selectionForResult(result: Result): Selection {
        return when (Pair(this, result)) {
            Pair(Rock, Result.Win) -> Paper
            Pair(Rock, Result.Loss) -> Scissors
            Pair(Paper, Result.Win) -> Scissors
            Pair(Paper, Result.Loss) -> Rock
            Pair(Scissors, Result.Win) -> Rock
            Pair(Scissors, Result.Loss) -> Paper
            else -> this
        }
    }
}

enum class Result {
    Loss,
    Win,
    Draw;

    companion object {
        fun fromChar(ch: Char): Result {
            return when (ch) {
                'X' -> Loss
                'Y' -> Draw
                'Z' -> Win
                else -> throw Exception("Invalid Result Char: $ch")
            }
        }
    }
}

fun main() {

    fun parsePart1Rounds(input: List<String>): List<Round> {
        val rounds = mutableListOf<Round>()
        for (line in input) {
            val round = Round(Selection.fromChar(line[0]), Selection.fromChar(line[2]))
            rounds.add(round)
        }
        return rounds
    }

    fun parsePart2Rounds(input: List<String>): List<Round> {
        val rounds = mutableListOf<Round>()
        for (line in input) {
            val elfPick = Selection.fromChar(line[0])
            val selfPick = elfPick.selectionForResult(Result.fromChar(line[2]))
            val round = Round(elfPick, selfPick)
            rounds.add(round)
        }
        return rounds
    }

    fun part1(input: List<String>): Int {
        val rounds = parsePart1Rounds(input)
        return rounds.sumOf { it.fullScore() }
    }

    fun part2(input: List<String>): Int {
        val rounds = parsePart2Rounds(input)
        return rounds.sumOf { it.fullScore() }
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
