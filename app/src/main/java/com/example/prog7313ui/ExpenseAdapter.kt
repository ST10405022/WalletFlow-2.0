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
import com.example.prog7313ui.data.dao.BudgetCategoryDao
import com.example.prog7313ui.data.entity.BudgetCategory
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.net.toUri
import java.text.NumberFormat

/**
 * Adapter for displaying a list of expenses in a RecyclerView.
 * @param context The context of the activity.
 * @see Expense
 * @reference (Android, 2025).
 */
class ExpenseAdapter(
    private val context: ExpenseListActivity
) : ListAdapter<Expense, ExpenseAdapter.ExpenseViewHolder>(ExpenseDiffCallback()) {

    private var categories = listOf<BudgetCategory>()

    /**
     * Updates the list of categories.
     * @param newCategories The new list of categories.
     * @see BudgetCategory
     */
    fun updateCategories(newCategories: List<BudgetCategory>) {
        categories = newCategories
        notifyDataSetChanged()
    }

    /**
     * Inflates the layout for each expense item and returns a ViewHolder.
     * @param parent The parent ViewGroup.
     * @param viewType The view type.
     * @return A ViewHolder for the expense item.
     * @reference (Android, 2025).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    /**
     * Binds the expense data to the ViewHolder.
     * @param holder The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     * @see ExpenseViewHolder
     * @see Expense
     * @reference (Android, 2025).
     */
    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = getItem(position)
        holder.bind(expense, categories)
    }

    /**
     * ViewHolder for each expense item.
     * @param itemView The root view of the item.
     * @see RecyclerView.ViewHolder
     * @see Expense
     * @see BudgetCategory
     * @see ExpenseListActivity
     * @reference (Android, 2025).
     */
    class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val descriptionText: TextView = itemView.findViewById(R.id.expenseDescription)
        private val amountText: TextView = itemView.findViewById(R.id.expenseAmount)
        private val dateText: TextView = itemView.findViewById(R.id.expenseDate)
        private val categoryText: TextView = itemView.findViewById(R.id.expenseCategory)
        private val photoButton: Button = itemView.findViewById(R.id.viewPhotoBtn)
        private val recurringTextView: TextView = itemView.findViewById(R.id.expenseRecurring)
        private val startDateTextView: TextView = itemView.findViewById(R.id.expenseStartDate)
        private val endDateTextView: TextView = itemView.findViewById(R.id.expenseEndDate)

        /**
         * Binds the expense data to the ViewHolder.
         * @param expense The expense to bind.
         * @param categories The list of categories.
         * @see Expense
         * @see BudgetCategory
         * @see ExpenseListActivity
         * @reference (Android, 2025).
         */
        fun bind(expense: Expense, categories: List<BudgetCategory>) {
            descriptionText.text = expense.description // Set the expense description

            // Format the amount as ZAR with two decimal places
            val formattedAmount = formatAmountInZAR(expense.amount)
            amountText.text = formattedAmount

            // Set the expense category
            categoryText.text = "Category: ${expense.categoryId ?: "Uncategorized"}"

            // Format the date
            val dateFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
            dateText.text = dateFormat.format((expense.date))

            // Format the recurring status and dates
            recurringTextView.text = buildString {
                append("Recurring: ")
                append(if (expense.startDate != null && expense.endDate != null) "Yes" else "No")
            }

            // Format the start and end dates
            startDateTextView.text = buildString {
                append("Start Date: ")
                append(if (expense.startDate != null) dateFormat.format(expense.startDate) else "-")
            }
            endDateTextView.text = buildString {
                append("End Date: ")
                append(if (expense.endDate != null) dateFormat.format(expense.endDate) else "-")
            }

            // Handle photo button visibility
            photoButton.visibility = if (expense.photoPath != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            // Set the click listener to open the photo in an external viewer (Gallery)
            photoButton.setOnClickListener {
                val context = itemView.context
                val uri = expense.photoPath?.toUri()

                if (uri != null) {
                    try {
                        // Use Intent to open the image in the default gallery or image viewer
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            setDataAndType(uri, "image/*") // Set the URI and MIME type for images
                            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION // Ensure the app can read the URI
                        }
                        context.startActivity(intent) // Start the activity to open the image
                    } catch (e: Exception) {
                        e.printStackTrace()
                        // Show an error if the intent fails
                        Toast.makeText(context, "Unable to open image", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Handle the case where there's no photo path available
                    Toast.makeText(context, "No image available", Toast.LENGTH_SHORT).show()
                }
            }

        }

        /**
         * Formats the amount as ZAR with two decimal places.
         * @param amount The amount to format.
         * @return The formatted amount as a string.
         * @see NumberFormat
         * @see Locale
         * @see Currency
         */
        private fun formatAmountInZAR(amount: Double): String {
            val locale = Locale("en", "ZA") // South Africa locale
            val currency = Currency.getInstance("ZAR") // ZAR currency
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            numberFormat.currency = currency
            return numberFormat.format(amount)
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

/*
 * Reference List
 *     AndroidDevelopers, 2021. Save data in a local database using Room. [Online]
 *     Available at: https://developer.android.com/training/data-storage/room
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. Kotlin coroutines and lifecycle. [Online]
 *     Available at: https://developer.android.com/topic/libraries/architecture/coroutines
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. CardView. [Online]
 *     Available at: https://developer.android.com/reference/androidx/cardview/widget/CardView
 *     [Accessed 25 April 2025].
 *     AndroidDevelopers, 2021. View binding. [Online]
 *     Available at: https://developer.android.com/topic/libraries/view-binding
 *     [Accessed 23 April 2025].
 *     AndroidDevelopers, 2021. AlertDialog. [Online]
 *     Available at: https://developer.android.com/reference/androidx/appcompat/app/AlertDialog
 *     [Accessed 24 April 2025].
 *     AndroidDevelopers, 2021. SimpleDateFormat. [Online]
 *     Available at: https://developer.android.com/reference/java/text/SimpleDateFormat
 *     [Accessed 23 April 2025].
 *     MikeT, 2022. stackOverflow. [Online]
 *     Available at: https://stackoverflow.com/questions/74477964/android-studio-add-a-database
 *     [Accessed 28 April 2025].
 *     Android. 2025. Create dynamic lists with RecyclerView:   views:   Android developers,
 *     Android Developers. [Online].
 *     Available at: https://developer.android.com/develop/ui/views/layout/recyclerview
 *     [Accessed: 15 April 2025].
 */