package co.aladinjunior.tfitstudio.models

import androidx.room.*


@Dao
interface FeesDao {

    @Insert
    fun insert(fees: Fees)

    @Delete
    fun delete(fees: Fees)

    @Update
    fun update(fees: Fees)

    @Query("SELECT * FROM Fees WHERE type = :type")
    fun query(type: String?) : List<Fees>


}