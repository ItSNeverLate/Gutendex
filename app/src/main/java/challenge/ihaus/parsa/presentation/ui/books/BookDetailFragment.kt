package challenge.ihaus.parsa.presentation.ui.books

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.FragmentBookDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {

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
        }
    }

    private fun subscribe() {

    }
}