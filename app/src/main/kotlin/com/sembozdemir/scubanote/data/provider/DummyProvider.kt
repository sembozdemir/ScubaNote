package com.sembozdemir.scubanote.data.provider

import com.sembozdemir.scubanote.data.model.Dive
import com.sembozdemir.scubanote.extensions.DateHelper

class DummyProvider : DataProvider {

    companion object {

        private val dummyDiveList: MutableList<Dive> = mutableListOf(
                Dive("Sığacık - Teos Duvar", DateHelper.create("04.05.2016"), "18", "37", "200", "50", Dive.BOAT, "Orfoz gördük."),
                Dive("Çeşme - Batık", DateHelper.create("05.05.2016"), "20", "45", "210", "80", Dive.BOAT, "Batık gördük."),
                Dive("Ayvalık - Ilyosta", DateHelper.create("06.05.2016"), "25", "51", "220", "40", Dive.BOAT, "Müren gördük."),
                Dive("Bodrum - Uçak", DateHelper.create("07.05.2016"), "18", "43", "200", "70", Dive.BOAT, "Uçak gördük.")
        )
    }
    override fun getDive(no: Int): Dive = dummyDiveList[no]

    override fun getDives(): List<Dive> {
        return dummyDiveList
    }

    override fun addDive(dive: Dive) {
        dummyDiveList.add(dive)
    }

    override fun editDive(no: Int, newDive: Dive) {
        dummyDiveList.removeAt(no)
        dummyDiveList.add(no, newDive)
    }

    override fun getDiveCount(): Int = dummyDiveList.size
}