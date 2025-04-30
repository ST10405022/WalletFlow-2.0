package com.example.prog7313ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.prog7313ui.data.entity.Expense
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.net.toUri

class ExpenseAdapter(
    private val context: ExpenseListActivity
) : ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(ExpenseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)

        holder.bind(expense)
    }

    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionText: TextView = itemView.findViewById(R.id.expenseDescription)
        private val amountText: TextView = itemView.findViewById(R.id.expenseAmount)
        private val dateText: TextView = itemView.findViewById(R.id.expenseDate)
        private val categoryText: TextView = itemView.findViewById(R.id.expenseCategory)
        private val photoButton: Button = itemView.findViewById(R.id.viewPhotoBtn)
        private val recurringTextView: TextView = itemView.findViewById(R.id.expenseRecurring)

        fun bind(expense: Expense) {
            descriptionText.text = expense.description
            amountText.text = "\$${expense.amount}"
            categoryText.text = "Category: ${expense.categoryId ?: "Uncategorized"}"

            val dateFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
            dateText.text = dateFormat.format((expense.date))

            recurringTextView.text = buildString {
                append("Recurring: ")
                append(if (expense.startDate != null && expense.endDate != null) "Yes" else "No")
            }

            // Handle photo button if path is available
            photoButton.visibility = if (expense.photoPath != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            photoButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(expense.photoPath?.toUri(), "image/*")
                itemView.context.startActivity(intent)
            }
        }
    }

    /**
     * A DiffUtil.ItemCallback for comparing expenses in the RecyclerView.
     * This is used to efficiently update the RecyclerView when data changes.
     * @see DiffUtil.ItemCallback
     */
    class ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id // Assuming each expense has a unique ID
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }
}