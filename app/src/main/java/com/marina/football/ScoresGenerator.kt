package com.marina.football

import android.util.Log
import kotlin.math.abs

class ScoresGenerator {

    private var scores: List<Game>? = null
    private var maxDiff: Int = 0

    // генерируем результаты матчей
    fun generateScores(count: Int) {
        val scores = mutableListOf<Game>()
        repeat(count) {
            scores.add(
                Game(
                    firstTeamScore = (0..5).random(),
                    secondTeamScore = (0..5).random()
                )
            )
            // проверяю прямо при генерации, чтобы лишний раз не пробегать по массиву
            updateMaxDiff(scores.last())
        }
        this.scores = scores
        printScoresLog(scores)
    }

    // проверяет, не нашли ли новый максимум
    private fun updateMaxDiff(game: Game) {
        val diff = getAbsDiff(game)
        if (diff > maxDiff) {
            maxDiff = diff
        }
    }

    fun printScoresLog(list: List<Game>) {
        list.forEach {
            basePrint(it)
        }
    }

    fun printSetOfMaxes() {
        val maxes = getSetOfMaxes()
        printMaxesInLog(maxes)
    }

    private fun getSetOfMaxes(): Set<Game> {
        val setOfMaxes = mutableSetOf<Game>()
        scores?.forEach { game ->
            if (getAbsDiff(game) == maxDiff) {
                setOfMaxes.add(game)
            }
        }
        return setOfMaxes
    }

    private fun printMaxesInLog(set: Set<Game>) {
        Log.e(this.javaClass.simpleName, "Set of max diff")
        Log.e(this.javaClass.simpleName, "Max diff == $maxDiff")
        set.forEach {
            basePrint(it)
        }
    }

    fun removeGamesWithEqualScores() {
        if (scores != null) {
            val gamesWithDifferentScores = scores!!.toSet()
            scores = gamesWithDifferentScores.toList()
            printScoresLog(scores!!)
        }
        Log.e(this.javaClass.simpleName, "Removed")
    }

    private fun basePrint(game: Game) {
        Log.e(this.javaClass.simpleName, "${game.firstTeamScore} ${game.secondTeamScore}")
    }

    private fun getAbsDiff(game: Game): Int {
        return abs(game.firstTeamScore - game.secondTeamScore)
    }
}