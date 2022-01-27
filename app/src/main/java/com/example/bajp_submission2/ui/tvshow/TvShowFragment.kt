package com.example.bajp_submission2.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bajp_submission2.databinding.FragmentTvShowBinding
import com.example.bajp_submission2.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowAdapter = TvShowAdapter()

            viewModel.getTvShow().observe(viewLifecycleOwner) { tvShow ->
                showProgressBar(false)
                tvShowAdapter.setTvShow(tvShow)
                tvShowAdapter.notifyDataSetChanged()
            }

            with(binding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = tvShowAdapter
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.rvTvshow.isGone = state
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}