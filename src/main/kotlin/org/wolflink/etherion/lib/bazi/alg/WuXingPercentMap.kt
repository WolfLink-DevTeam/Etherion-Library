package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi

data object WuXingPercentMap: BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): Boolean {
        return false
    }

    override fun compute(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): JsonElement {
        return JsonObject()
    }
}