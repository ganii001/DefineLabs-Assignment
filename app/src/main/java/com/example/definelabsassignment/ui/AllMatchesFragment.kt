package com.example.definelabsassignment.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.definelabsassignment.R
import com.example.definelabsassignment.Utils
import com.example.definelabsassignment.databinding.FragmentAllMatchesBinding
import com.example.definelabsassignment.network.local.entity.VenuesEntity
import com.example.definelabsassignment.network.responses.VenuesItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllMatchesFragment : Fragment() {

    private var _binding: FragmentAllMatchesBinding? = null
    val viewModel: ViewModelMatches by viewModels()
    private val binding get() = _binding!!
    lateinit var pDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllMatchesBinding.inflate(inflater, container, false)
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
            viewModel.getVenueList()
            pDialog.show()
        } else {
            Utils.showSNACK_BAR_NO_INTERNET(requireActivity(), activity?.localClassName!!)
        }
    }

    private fun observeData() {
        viewModel.venueList.observe(viewLifecycleOwner, Observer { list ->

            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rv1.layoutManager = linearLayoutManager
            binding.rv1.itemAnimator = DefaultItemAnimator()
            binding.rv1.setHasFixedSize(true)

            val adapterMatches =
                AdapterMatches(requireContext(), object : AdapterMatches.MatchesListener {
                    override fun onSelect(
                        img: AppCompatImageView,
                        id: String?,
                        name: String?,
                        address: String?
                    ) {
                        val venuesEntity = VenuesEntity(id, name, address)
                        if (img.drawable.constantState == resources.getDrawable(
                                R.drawable.ic_star_inactive,
                                activity?.theme
                            ).constantState
                        ) {
                            viewModel.insertVenueData(venuesEntity)
                            img.setImageDrawable(
                                resources.getDrawable(
                                    R.drawable.ic_star_active,
                                    activity?.theme
                                )
                            )
                        } else if (img.drawable.constantState == resources.getDrawable(
                                R.drawable.ic_star_active,
                                activity?.theme
                            ).constantState
                        ) {
                            viewModel.deleteVenueData(id!!)
                            img.setImageDrawable(
                                resources.getDrawable(
                                    R.drawable.ic_star_inactive,
                                    activity?.theme
                                )
                            )
                            getData()
                        }
                    }
                })
            binding.rv1.adapter = adapterMatches
            adapterMatches.refresh(list)

            pDialog.dismiss()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}