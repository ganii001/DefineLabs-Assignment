package com.example.definelabsassignment.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definelabsassignment.R
import com.example.definelabsassignment.Utils
import com.example.definelabsassignment.databinding.FragmentSavedMatchesBinding
import com.example.definelabsassignment.network.local.entity.VenuesEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedMatchesFragment : Fragment() {

    private var _binding: FragmentSavedMatchesBinding? = null
    val viewModel: ViewModelMatches by viewModels()
    private val binding get() = _binding!!
    lateinit var pDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSavedMatchesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.executePendingBindings()
        binding.viewModel = viewModel
        pDialog = Utils.generateProgressDialog(context)!!

        observeData()
        getData()

        return root
    }

    private fun getData() {
        if (Utils.isDataConnected(requireActivity())) {
            viewModel.getVenuefromDB()
            pDialog.show()
        } else {
            Utils.showSNACK_BAR_NO_INTERNET(requireActivity(), activity?.localClassName!!)
        }
    }

    private fun observeData() {
        viewModel.venueDBList.observe(viewLifecycleOwner, Observer { list ->

            if (list != null) {
                val linearLayoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rv1.layoutManager = linearLayoutManager
                binding.rv1.itemAnimator = DefaultItemAnimator()
                binding.rv1.setHasFixedSize(true)

                val adapterMatches =
                    AdapterSavedMatches(
                        requireContext(),
                        object : AdapterSavedMatches.MatchesListener {
                            override fun onSelect(id: String?, name: String?, address: String?) {
                                val venuesEntity = VenuesEntity(id, name, address)
                                viewModel.deleteVenueData(id!!)
                            }
                        })
                binding.rv1.adapter = adapterMatches
                adapterMatches.refresh(list)
            }
            pDialog.dismiss()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}