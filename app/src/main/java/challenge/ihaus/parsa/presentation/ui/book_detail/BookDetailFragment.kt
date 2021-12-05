package challenge.ihaus.parsa.presentation.ui.book_detail

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.FragmentBookDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {

    private val viewModel: BookDetailViewModel by viewModels()
    private lateinit var binding: FragmentBookDetailBinding
    private val args: BookDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookDetailBinding.bind(view)

        init()
        subscribe()
    }

    private fun init() {
        val book = args.book
        requireActivity().title = book.title

        binding.apply {
            Glide.with(requireContext())
                .load(book.imageUrl)
                .circleCrop()
                .into(imageViewBanner)
            if (book.authors.isNotEmpty()) {
                textViewAuthor.text =
                    "${book.authors[0].name} - ${book.authors[0].birthYear} - ${book.authors[0].deathYear}"
            }

            val subjectsListAdapter =
                ArrayAdapter(requireContext(), R.layout.item_simple_text, book.subjects)
            listViewSubjects.adapter = subjectsListAdapter

            val otherInformationList = mutableListOf<String>()
            book.otherInformation.keys.map { key ->
                otherInformationList.add("$key: ${book.otherInformation[key]}")
            }
            val otherInformationListAdapter = ArrayAdapter(requireContext(),
                R.layout.item_simple_text,
                otherInformationList)
            listViewOtherInformation.adapter = otherInformationListAdapter

            fabFavorite.setOnClickListener {
                viewModel.onBookFavoriteToggle(book)
            }
        }
    }

    private fun subscribe() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.bookDetailEvent.collectLatest { event ->
                when (event) {
                    is BookDetailViewModel.BookDetailEvent.ShowMakeBookFavoriteMessage -> {
                        Toast.makeText(requireContext(),
                            getString(R.string.added_to_favorites),
                            Toast.LENGTH_LONG).show()
                    }
                    is BookDetailViewModel.BookDetailEvent.ShowMakeBookUnFavoriteMessage -> {
                        Toast.makeText(requireContext(),
                            getString(R.string.removed_from_favorites),
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewModel.isFavoriteLiveData.observe(viewLifecycleOwner) { isFavorite ->

            val favoriteResId =
                if (isFavorite) {
                    R.drawable.ic_filled_star
                } else {
                    R.drawable.ic_outlined_star
                }
            binding.fabFavorite.setImageResource(favoriteResId)
        }
    }
}