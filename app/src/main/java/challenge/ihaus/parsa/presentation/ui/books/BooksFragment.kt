package challenge.ihaus.parsa.presentation.ui.books


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment(R.layout.fragment_books) {

    private lateinit var binding: FragmentBooksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBooksBinding.bind(view)
    }
}