package com.project.debo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface InstallmentsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addInstallment(installment: InstallmentsData)

    @Query("SELECT * FROM installments_table ORDER BY id ASC")
    fun readALlData(): LiveData<List<InstallmentsData>>

    @Delete
    suspend fun deleteInstallment(installment: InstallmentsData)

    @Update
    suspend fun update(item: InstallmentsData)
}