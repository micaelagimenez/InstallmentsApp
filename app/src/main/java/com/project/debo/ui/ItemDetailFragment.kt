package com.project.debo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.debo.databinding.FragmentItemDetailBinding
import com.project.debo.db.InstallmentsData
import com.project.debo.db.InstallmentsViewModel


class ItemDetailFragment : Fragment() {
    private lateinit var binding: FragmentItemDetailBinding
    private val installmentViewModel by viewModels<InstallmentsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.modalButtonPositive.setOnClickListener {
            insertDataToDatabase()
            activity?.onBackPressed()
        }
        binding.modalButtonNegative.setOnClickListener { activity?.onBackPressed() }
    }

    private fun insertDataToDatabase() {
        val title = binding.compraName.text.toString()
        val compraPrice = binding.compraTotalPrice.text.toString().toInt()
        val compraPicker = binding.cuotasPicker.text.toString().toInt()

        //create installment
        val installment =
            InstallmentsData(
                id = 0,
                title,
                compraPrice,
                compraPicker
            )

        //add data to db
        installmentViewModel.addInstallment(installment)
    }
}
