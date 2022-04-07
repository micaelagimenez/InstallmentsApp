package com.project.debo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "installments_table")
data class InstallmentsData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Int,
    val installments: Int
)