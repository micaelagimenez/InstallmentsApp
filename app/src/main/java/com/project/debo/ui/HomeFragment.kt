package com.project.debo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.debo.R
import com.project.debo.databinding.FragmentHomeBinding
import com.project.debo.db.InstallmentsData
import com.project.debo.db.InstallmentsViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var boxesAdapter: BoxesAdapter
    private val installmentViewModel by viewModels<InstallmentsViewModel>()
    private val updateHandler: (InstallmentsData) -> Unit = {
        installmentViewModel.decreaseInstallment(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boxesAdapter = BoxesAdapter(updateHandler)
        val recyclerView = binding.rvBoxes
        recyclerView.adapter = boxesAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.btAdd.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.nav_item_detail_fragment)
        }

        installmentViewModel.readAllData.observe(this, { installment ->
            if (installment.isEmpty()) {
                binding.emptyState.visibility = View.VISIBLE
            } else {
                boxesAdapter.setData(installment)
                binding.emptyState.visibility = View.GONE
            }

        })

        // swipe to delete item
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                installmentViewModel.deleteInstallment(boxesAdapter.getItemAt(viewHolder.adapterPosition))
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }
}
