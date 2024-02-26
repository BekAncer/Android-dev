package com.example.lab1

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PizzaAdapter
    private lateinit var pizzaList: List<Pizza>
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pizzaList = createPizzaList()
        recyclerView = findViewById(R.id.recyclerView)
        adapter = PizzaAdapter(pizzaList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        showPizzaList(pizzaList)

        searchEditText = findViewById(R.id.textInputEdit)

        searchEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun createPizzaList(): List<Pizza> {
        return listOf(
            Pizza("Вау! Кебаб", "Кебаб из говядины, соус ранч,\n" +
                    "моцарелла, сладкий перец,\n" +
                    "томаты, красный лук, фирменный\n" +
                    "томатный соус", R.drawable.img1, "2900"),
            Pizza("Пепперони с грибами", "Пикантная пепперони из\n" +
                    "цыпленка, моцарелла,\n" +
                    "шампиньоны, фирменный соус\n" +
                    "альфредо", R.drawable.img2, "2000"),
            Pizza("Ветчина и огурчики", "Соус ранч, ветчина из цыпленка,\n" +
                    "моцарелла, маринованные\n" +
                    "огурчики, красный лук", R.drawable.img3, "2000"),
            Pizza("Миксик", "Пицца четвертинками с ветчиной,\n" +
                    "цыпленком, томатами, брынзой,\n" +
                    "моцареллой, фирменным\n" +
                    "соусом альфредо и набор юного\n" +
                    "садовода в подарок", R.drawable.img4, "2000"),
        )
    }

    private fun showPizzaList(list: List<Pizza>) {
        adapter.setPizzaList(list)
    }

    private fun performSearch() {
        adapter.filter(searchEditText.text.toString())
    }
}

