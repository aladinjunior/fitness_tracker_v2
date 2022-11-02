package co.aladinjunior.tfitstudio

import kotlin.math.pow

object Calculate {


    fun calculateBMI(weight: Int, height: Int) : Double{
        return weight / (height.toDouble().pow(2) / 10000.0)
    }

     fun isValid(weight: String, height: String): Boolean {
        return (weight.isNotEmpty()
                && height.isNotEmpty()
                && !weight.startsWith("0")
                && !height.startsWith("0"))
    }

//    fun calculateBMR (weight: Int, height: Int, lifestyle: String) : Double{
//
//    }



}