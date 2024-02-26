package com.example.lab1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PizzaAdapter(private var pizzaList: List<Pizza>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    private var originalPizzaList: List<Pizza> = pizzaList.toList()

    class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pizzaName: TextView = itemView.findViewById(R.id.textPizzaName)
        val pizzaDescription: TextView = itemView.findViewById(R.id.textPizzaDescription)
        val pizzaImage: ImageView = itemView.findViewById(R.id.imagePizza)
        val pizzaCost: TextView = itemView.findViewById(R.id.costView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza, parent, false)
        return PizzaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val currentPizza = pizzaList[position]

        holder.pizzaName.text = currentPizza.name
        holder.pizzaDescription.text = currentPizza.description
        holder.pizzaImage.setImageResource(currentPizza.imageResource)
        holder.pizzaCost.text = "от ${currentPizza.cost}KZT"

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Description::class.java)
            intent.putExtra("pizzaName", currentPizza.name)
            intent.putExtra("pizzaDescription", currentPizza.description)
            intent.putExtra("pizzaImageResId", currentPizza.imageResource)
            intent.putExtra("pizzaCost", currentPizza.cost)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pizzaList.size
    }

    fun setPizzaList(list: List<Pizza>) {
        pizzaList = list
        notifyDataSetChanged()
    }

    fun filter(searchText: String) {
        val filteredList = originalPizzaList.filter { it.name.contains(searchText, ignoreCase = true) }
        setPizzaList(filteredList)
    }

    fun resetList() {
        setPizzaList(originalPizzaList)
    }
}
