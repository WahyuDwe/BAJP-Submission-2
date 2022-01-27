package com.example.bajp_submission2.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bajp_submission2.databinding.FragmentMovieBinding
import com.example.bajp_submission2.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            showProgressBar(true)

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner) { movies ->
                showProgressBar(false)
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            }

            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
        }
    }

    private fun showProgressBar(state: Boolean) {
        binding.progressBar.isVisible = state
        binding.rvMovie.isGone = state
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}