package challenge.ihaus.parsa.presentation.ui.books

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.FragmentBooksBinding
import challenge.ihaus.parsa.domain.model.Book
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.fragment_books), BooksAdapter.OnClickListener {

    private val viewModel: BookViewModel by viewModels()
    private lateinit var binding: FragmentBooksBinding
    private lateinit var booksAdapter: BooksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBooksBinding.bind(view)

        init()
        subscribe()
    }

    private fun init() {
        booksAdapter = BooksAdapter(Glide.with(requireContext()), this)

        setUpAdapter()
    }

    private fun subscribe() {
        binding.apply {

            lifecycleScope.launchWhenResumed {
                viewModel.books.collectLatest {
                    booksAdapter.submitData(it)
                }
            }
        }
    }

    override fun onItemClick(book: Book) {
        val action = BooksFragmentDirections.actionBooksToBookDetail(book)
        findNavController().navigate(action)
    }

    private fun setUpAdapter() {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL))
            booksAdapter.addLoadStateListener { loadState ->

                if (loadState.mediator?.refresh is LoadState.Loading) {
                    if (booksAdapter.snapshot().isEmpty()) {
                        binding.progressBar.isVisible = true
                    }
                } else {
                    binding.progressBar.isVisible = false

                    val error = when {
                        loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                        loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                        loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error

                        else -> null
                    }
                    error?.let {
                        if (booksAdapter.snapshot().isEmpty()) {
                            Toast.makeText(requireContext(),
                                it.error.localizedMessage,
                                Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
            adapter = booksAdapter
        }
    }
}