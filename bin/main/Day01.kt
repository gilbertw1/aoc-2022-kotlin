fun main() {

    fun parseCounts(input: List<String>): List<Int> {
        val counts = mutableListOf<Int>()
        var curr = 0
        for (line in input) {
            if (line.isEmpty()) {
                counts.add(curr)
                curr = 0
            } else {
                curr += line.toInt()
            }
        }
        counts.add(curr)
        return counts
    }

    fun part1(input: List<String>): Int {
        val counts = parseCounts(input)
        return counts.maxOrNull() ?: 0
    }

    fun part2(input: List<String>): Int {
        val counts = parseCounts(input)
        return counts.sorted().reversed().take(3).sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
