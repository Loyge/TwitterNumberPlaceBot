object SolveNumberPlace {

    @Throws(Exception::class)
    fun solve(numberPlace: Array<IntArray>): Array<IntArray> {
        if (numberPlace.size != 9 || numberPlace[0].size != 9) {
            throw Exception()
        }
        return if (solveAct(0, 0, numberPlace)) {
            numberPlace
        } else {
            throw Exception()
        }
    }

    private fun solveAct(x: Int, y: Int, numberPlace: Array<IntArray>): Boolean {
        var newX: Int
        var newY: Int

        if (x == 0 && y == 9) {
            return true
        }

        if (numberPlace[y][x] == 0) {
            for (t in 1..9) {
                numberPlace[y][x] = t
                if (isValid(x, y, numberPlace)) {
                    newX = x + 1
                    newY = y
                    if (newX > 8) {
                        newX = 0
                        newY++
                    }
                    if (solveAct(newX, newY, numberPlace)) {
                        return true
                    }
                }
            }
            numberPlace[y][x] = 0
            return false
        } else {
            newX = x + 1
            newY = y
            if (newX > 8) {
                newX = 0
                newY++
            }
            if (solveAct(newX, newY, numberPlace)) {
                return true
            }
        }
        return false
    }

    private fun isValid(x: Int, y: Int, numberPlace: Array<IntArray>): Boolean {
        return isValidRow(x, y, numberPlace) && isValidHeight(x, y, numberPlace) && isValid3x3(x, y, numberPlace)
    }

    private fun isValid3x3(x: Int, y: Int, numberPlace: Array<IntArray>): Boolean {
        val xBase = x / 3 * 3
        val yBase = y / 3 * 3
        for (i in yBase until yBase + 3) {
            for (j in xBase until xBase + 3) {
                if (j != x && i != y) {
                    if (numberPlace[y][x] == numberPlace[i][j]) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun isValidHeight(x: Int, y: Int, numberPlace: Array<IntArray>): Boolean {
        for (i in 0..8) {
            if (i != y) {
                if (numberPlace[y][x] == numberPlace[i][x]) {
                    return false
                }
            }
        }
        return true
    }

    private fun isValidRow(x: Int, y: Int, numberPlace: Array<IntArray>): Boolean {
        for (i in 0..8) {
            if (i != x) {
                if (numberPlace[y][x] == numberPlace[y][i]) {
                    return false
                }
            }
        }
        return true
    }
}
