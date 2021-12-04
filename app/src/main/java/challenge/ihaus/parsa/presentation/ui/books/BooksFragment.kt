package challenge.ihaus.parsa.presentation.ui.books

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
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

        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_books, menu)

        val item = menu?.findItem(R.id.action_filter_by)
        val spinner = item?.actionView as AppCompatSpinner

        val filterSpinnerList = listOf(
            getString(R.string.all_books),
            getString(R.string.copy_right_free),
            getString(R.string.century_19th),
        )
        var arrayAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_filtered_by,
                filterSpinnerList
            )
        arrayAdapter.setDropDownViewResource(R.layout.item_action_filter_by)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> viewModel.onFilterByFreeCopyRight()
                    2 -> viewModel.onFilterBy19thCentury()
                    else -> viewModel.onRemoveFilters()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}