package challenge.ihaus.parsa.presentation.ui.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.FragmentBookDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {

    private lateinit var binding: FragmentBookDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookDetailBinding.bind(view)
    }
}