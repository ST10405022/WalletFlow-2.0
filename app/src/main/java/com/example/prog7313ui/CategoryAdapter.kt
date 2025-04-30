package com.example.prog7313ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prog7313ui.data.entity.BudgetCategory
import org.w3c.dom.Text

class CategoryAdapter(
    private var categories: List<BudgetCategory>,
    private val onClick: (BudgetCategory) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.categoryButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.button.text = category.name

        holder.button.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("CATEGORY_NAME", category.name) // Passing the category name
            intent.putExtra("CATEGORY_ID", category.id)     // Passing the category id
            intent  .putExtra("CATEGORY_IMAGE", category.imageUri)  // Passing the category image
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = categories.size

    fun updateData(newCategories: List<BudgetCategory>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}