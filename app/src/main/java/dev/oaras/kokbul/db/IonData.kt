/*
 * Copyright (c) 2023 Olcay Aras.
 */

package dev.oaras.kokbul.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.oaras.kokbul.db.converters.IonConverter
import dev.oaras.kokbul.ui.composable.Ion


@Entity
@TypeConverters(IonConverter::class)
data class IonData(
    val name: String,
    val knownAs: String,
    val ion: Ion,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var index: Long = 0L,
    var timesAnswered: Int = 0,
) {
    fun withIndex(id: Long): IonData = this.also { index = id }
}
//CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT);
//INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5efb96334962d4895cdb1d963953ccea');
//CREATE TABLE IF NOT EXISTS IonData (`name` TEXT NOT NULL, `knownAs` TEXT NOT NULL, `ion` TEXT NOT NULL, PRIMARY KEY(`name`));
val DATA = listOf(
    IonData("Na", "Sodyum", Ion.Plus1),
    IonData("Li", "Lityum", Ion.Plus1),
    IonData("K", "Potasyum", Ion.Plus1),
    IonData("Ag", "Gümüş", Ion.Plus1),

    IonData("Mg", "Magnezyum", Ion.Plus2),
    IonData("Ca", "Kalsiyum", Ion.Plus2),
    IonData("Zn", "Çinko", Ion.Plus2),
    IonData("Be", "Berilyum", Ion.Plus2),
    IonData("Ba", "Baryum", Ion.Plus2),

    IonData("Al", "Aluminyum", Ion.Plus3),

    IonData("Cu", "Bakır", Ion.Plus1or2),
    IonData("Hg", "Civa", Ion.Plus1or2),

    IonData("Fe", "Demir", Ion.Plus2or3),
    IonData("Co", "Kobalt", Ion.Plus2or3),
    IonData("Ni", "Nikel", Ion.Plus2or3),

    IonData("Pb", "Kurşun", Ion.Plus2or4),
    IonData("Sn", "Kalay", Ion.Plus2or4),

    IonData("F", "Florür", Ion.Minus1),
    IonData("Cl", "Klorür", Ion.Minus1),
    IonData("Br", "Bromür", Ion.Minus1),
    IonData("I", "İyodür", Ion.Minus1),

    IonData("O", "Oksit", Ion.Minus2),
    IonData("S", "Sülfür", Ion.Minus2),

    IonData("P", "Fosfür", Ion.Minus3),
    IonData("N", "Nitrür", Ion.Minus3),

    IonData("OH", "Hidroksit", Ion.Minus1),
    //₀₁₂₃₄₅₆₇₈₉
    IonData("NO₃", "Nitrat", Ion.Minus1),
    IonData("NO₂", "Nitrit", Ion.Minus1),
    IonData("CN", "Siyanür", Ion.Minus1),
    IonData("HCO₃", "Bikarbonat", Ion.Minus1),
    IonData("MnO₄", "Permaganat", Ion.Minus1),
    IonData("CH₃COO", "Asetat", Ion.Minus1),

    IonData("SO₄", "Sülfat", Ion.Minus2),
    IonData("SO₃", "Sülfit", Ion.Minus2),
    IonData("C₂O₄", "Oksalat", Ion.Minus2),
    IonData("CrO₄", "Kromat", Ion.Minus2),
    IonData("Cr₂O₇", "Dikromat", Ion.Minus2),
    IonData("S₂O₃", "Tiyosülfat", Ion.Minus2),
    IonData("MnO₄", "Manganat", Ion.Minus2),
    IonData("CO₃", "Karbonat", Ion.Minus2),

    IonData("NH₄", "Amonyum", Ion.Plus1),
    IonData("H₃0", "Hidronyum", Ion.Plus1),

    IonData("PO₄", "Fosfat", Ion.Minus3),
    IonData("PO₃", "Fosfit", Ion.Minus3),
)
val DATA_SHUFFLED = DATA.shuffled()
//    .shuffled()


