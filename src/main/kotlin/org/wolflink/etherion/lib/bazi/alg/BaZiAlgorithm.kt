package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonElement
import org.omg.CORBA.Object
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi

sealed class BaZiAlgorithm {
    abstract fun checkInput(arguments: Array<out Any>): Boolean
    abstract fun compute(abstractBaZi: AbstractBaZi,arguments: Array<out Any>): JsonElement
}