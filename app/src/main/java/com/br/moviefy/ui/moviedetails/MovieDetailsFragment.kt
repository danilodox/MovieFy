package com.br.moviefy.ui.moviedetails


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.br.moviefy.R
import com.br.moviefy.data.model.MovieDetails
import com.br.moviefy.databinding.FragmentMovieDetailsBinding
import com.br.moviefy.util.GenresAdapter
import com.br.moviefy.util.isVisible
import com.br.moviefy.util.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details){

    private val mViewModel : MovieDetailsViewModel by viewModel()
    private lateinit var binding : FragmentMovieDetailsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    private val genresAdapter = GenresAdapter()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)



        initObservers()
        initUiElements()
        onClickShare()

        return binding.root
    }

    private fun initObservers() {
        showLoading()
        showError()
    }

    private fun showLoading(){
        with(mViewModel) {
            loadingDetailsLiveData.observe(viewLifecycleOwner, {
                binding.progressLoadingEvent.root.isVisible(it)

            })
        }
    }

    private fun showError(){
        with(mViewModel) {
            errorMovieDetailsLiveData.observe(viewLifecycleOwner, {
                binding.errorNetworkEvent.root.isVisible(it)
            })
        }
    }

    private fun initUiElements() {

        with(mViewModel) {
            mMoviesDetailsLiveData.observe(viewLifecycleOwner,  {


                it?.let {movie ->
                    fillBindingElements(movie)
                }

            })
            getMovie(args.id)
        }

    }

    private fun fillBindingElements( movie : MovieDetails){

        binding.movieDetailsPoster.loadImage(movie.poster_path)
            binding.movieDetailsRelease.text = movie.release_date!!.substring(0, 4)
            binding.movieDetailsTitle.text = movie.title
            binding.movieDetailsLanguage.text = movie.original_language
            binding.movieDetailsOverview.text = movie.overview

            //Convert object Genre in String
            val genres : String = genresAdapter.switchGenresIntForString(movie.genres!!)

            //Change the color of a word in the sentence
            com.br.moviefy.util.Spannable().customText(genres, binding.movieDetailsGenre)


    }

    private fun onClickShare(){
        binding.buttonShare.setOnClickListener{

            val shareText = mViewModel.getShareText()
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }




}