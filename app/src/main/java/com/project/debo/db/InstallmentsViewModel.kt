package com.project.debo.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InstallmentsViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<InstallmentsData>>
    private val repository: InstallmentsRepository

    init {
        val installmentsDao = InstallmentsDatabase.getDatabase(application).installmentsDao()
        repository = InstallmentsRepository(installmentsDao)
        readAllData = repository.readAllData
    }

    fun addInstallment(installment: InstallmentsData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addInstallment(installment)
        }
    }

    fun deleteInstallment(installment: InstallmentsData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteInstallment(installment)
        }
    }

    private fun updateInstallment(item: InstallmentsData) {
        viewModelScope.launch {
            repository.updateInstallment(item)
        }
    }

    fun decreaseInstallment(item: InstallmentsData) {
        if (item.installments > 1) {
            val newItem = item.copy(installments = item.installments - 1)
            updateInstallment(newItem)
        } else {
            deleteInstallment(item)
        }
    }
}