package com.project.debo.db

import androidx.lifecycle.LiveData

class InstallmentsRepository(private val installmentsDao: InstallmentsDao) {

    val readAllData: LiveData<List<InstallmentsData>> = installmentsDao.readALlData()

    suspend fun addInstallment(installment: InstallmentsData) {
        installmentsDao.addInstallment(installment)
    }

    suspend fun deleteInstallment(installment: InstallmentsData) {
        installmentsDao.deleteInstallment(installment)
    }

    suspend fun updateInstallment(installment: InstallmentsData) {
        installmentsDao.update(installment)
    }
}