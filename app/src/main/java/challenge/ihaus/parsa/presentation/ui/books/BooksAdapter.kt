package challenge.ihaus.parsa.presentation.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import challenge.ihaus.parsa.R
import challenge.ihaus.parsa.databinding.ItemBookBinding
import challenge.ihaus.parsa.domain.model.Book
import com.bumptech.glide.RequestManager

class BooksAdapter(val glide: RequestManager, val listener: OnClickListener) :
    PagingDataAdapter<Book, BooksAdapter.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }

    interface OnClickListener {
        fun onItemClick(book: Book)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    inner class ViewHolder(
        private val binding: ItemBookBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                root.setOnClickListener {
                    listener.onItemClick(book)
                }

                book.imageUrl?.let {
                    glide
                        .load(it)
                        .circleCrop()
                        .placeholder(R.drawable.ic_image_place_holder)
                        .into(imageViewBanner)
                }

                textViewTitle.text = book.title
                // We can show all Authors as well,
                // but I used the first one as a test (base on the code challenge UI)
                if (book.authors.isNotEmpty()) {
                    textViewAuthor.text =
                        "${book.authors[0].name} - ${book.authors[0].birthYear} - ${book.authors[0].deathYear}"
                }
                textViewSubjects.text = book.subjects[0]
                if (book.subjects.size > 1)
                    textViewSubjects.text =
                        "${textViewSubjects.text} + ${book.subjects.size - 1} more..."
            }
        }

    }
}