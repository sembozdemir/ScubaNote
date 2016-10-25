package com.sembozdemir.scubanote.data.provider

import com.orhanobut.hawk.Hawk
import com.sembozdemir.scubanote.data.model.Dive
import java.util.*

class HawkProvider : DataProvider {

    companion object {
        private val KEY_DIVES: String = "dives"
    }

    override fun getDives(): List<Dive> {
        val dives: List<Dive> = Hawk.get(KEY_DIVES, ArrayList<Dive>())
        return dives
    }

    override fun addDive(dive: Dive) {
        val dives: MutableList<Dive> = Hawk.get(KEY_DIVES, ArrayList<Dive>())
        dives.add(dive)
        Hawk.put(KEY_DIVES, dives)
    }

    override fun editDive(no: Int, newDive: Dive) {
        val dives: MutableList<Dive> = Hawk.get(KEY_DIVES, ArrayList<Dive>())
        dives.removeAt(no)
        dives.add(no, newDive)
        Hawk.put(KEY_DIVES, dives)
    }

    override fun getDive(no: Int): Dive {
        val dives: List<Dive> = Hawk.get(KEY_DIVES, ArrayList<Dive>())
        return dives[no]
    }

    override fun getDiveCount(): Int {
        val dives: List<Dive> = Hawk.get(KEY_DIVES, ArrayList<Dive>())
        return dives.size
    }
}