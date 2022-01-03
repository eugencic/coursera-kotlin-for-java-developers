package board

import java.lang.IllegalArgumentException

open class DefaultSquareBoard(size: Int) : SquareBoard {
    private val board: Array<Array<Cell>>

    init {
        board = Array(size) { i -> Array(size) { j -> Cell(i + 1, j + 1) } }
    }

    override val width: Int
        get() = board.size

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i <= 0 || j <= 0 || i > width || j > width) null

        else return board[i - 1][j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        return board.flatten()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val list = ArrayList<Cell>()
        val row = board[i - 1]

        jRange.forEach { j ->
            if (j > width || j <= 0) {
                return@forEach
            }

            list.add(row[j - 1])
        }

        return list
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val list = ArrayList<Cell>()
        val columnIndex = j - 1

        iRange.forEach { i ->
            if (i > width || i <= 0) {
                return@forEach
            }

            list.add(board[i - 1][columnIndex])
        }

        return list
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            Direction.UP -> getCellOrNull(i - 1, j)
            Direction.DOWN -> getCellOrNull(i + 1, j)
            Direction.LEFT -> getCellOrNull(i, j - 1)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}

class DefaultGameBoard<T>(size: Int) : DefaultSquareBoard(size), GameBoard<T> {
    private val boardValues = mutableMapOf<Cell, T?>()

    init {
        getAllCells().forEach { boardValues[it] = null }
    }

    override fun get(cell: Cell): T? {
        return boardValues[cell]
    }

    override fun set(cell: Cell, value: T?) {
        boardValues[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return boardValues.entries.filter { (_, value) -> predicate(value) }.map { entry -> entry.key }
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return filter(predicate).firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return find(predicate) != null
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return filter(predicate).size == boardValues.size
    }
}

fun createSquareBoard(width: Int): SquareBoard = DefaultSquareBoard(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = DefaultGameBoard(width)