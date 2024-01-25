package com.example.qloq.presentation

class QloSprite(var name:String, var hunger:Int, var thirst:Int, var happiness:Int) {
    var hungerStat = hunger
    var thirstStat = thirst
    var happinessStat = happiness
    var decrementMultiplier = 0.0001
    fun incHunger(){
        this.hungerStat++
    }
    fun incThirst(){
        this.thirstStat++
    }
    fun incHappiness(){
        this.happinessStat++
    }

    fun updateStats(timeDelta:Long){
        this.hungerStat -= (timeDelta * decrementMultiplier).toInt()
        this.thirstStat -= (timeDelta * decrementMultiplier).toInt()
        this.happinessStat -= (timeDelta * decrementMultiplier).toInt()

        checkStats()
    }

    fun checkStats() {
        //Sets and verifies upper and lower bounds
        if (hungerStat <= 0) {
            hungerStat = 0
        }
        if (thirstStat <= 0) {
            thirstStat = 0
        }
        if (happinessStat <= 0) {
            happinessStat = 0
        }
        if (happinessStat >=100) {
            happinessStat = 100
        }
        if (thirstStat >=100) {
            thirstStat = 100
        }
        if (hungerStat >=100) {
            hungerStat = 100
        }
    }

    fun getAverage(): Int {
        return ((happinessStat + thirstStat + hungerStat) / 3)
    }
}