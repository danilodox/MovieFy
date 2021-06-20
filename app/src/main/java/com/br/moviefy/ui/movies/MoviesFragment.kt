package com.br.moviefy.ui.movies


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.br.moviefy.R
import com.br.moviefy.databinding.FragmentMoviesBinding
import com.br.moviefy.util.isVisible
import org.koin.androidx.viewmodel.ext.android.viewModel




class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private val mViewModel : MovieViewModel by viewModel()
    private lateinit var binding : FragmentMoviesBinding

    private val searchTextWatcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            mViewModel.onSearchQuery(editable.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        initUiElements()
        initObservers()
        listenEditText()
        return binding.root
    }

    private fun initUiElements() {
        initRecyclerObserver()
    }

    private fun initObservers() {
        showLoading()
        showError()
    }

    private fun initRecyclerObserver() {

        mViewModel.mMoviesLiveData.observe(viewLifecycleOwner, Observer{

            it.results?.let {movies ->
                with(binding.recyclerListMovie){
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)


                    adapter = MovieAdapter(movies){ results ->


                        val action =  MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(results.id!!)
                        findNavController().navigate(action)

                    }

                }
            }

        })
        mViewModel.getMovies()
    }

    private fun listenEditText() {

        binding.searchEditText.addTextChangedListener(searchTextWatcher)
        binding.searchEditText.addTextChangedListener()

    }


    private fun showLoading(){
        mViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressLoadingEvent.root.isVisible(it)
        })
    }

    private fun showError(){
        mViewModel.errorMoviesLiveData.observe(viewLifecycleOwner, Observer {
            binding.errorNetworkEvent.root.isVisible(it)
        })
    }


}