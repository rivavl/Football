package com.marina.football

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    val generator = ScoresGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        printLog("Start")
        // генерируем 10 матчей
        generator.generateScores(count = COUNT)

        printLog("Remove equal")
        // убираем матчи с одинаковыми результатами
        generator.removeGamesWithEqualScores()

        printLog("Max diff")
        // выводим результаты матчей с максимальной разницей
        generator.printSetOfMaxes()
    }

    private fun printLog(string: String) {
        Log.e(this.javaClass.simpleName, string)
    }

    companion object {
        // кол-во матчей, которое нужно сгенерировать
        private const val COUNT = 10
    }
}