package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Description : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val intent = intent

        val pizzaCost = intent.getStringExtra("pizzaCost")
        val pizzaName = intent.getStringExtra("pizzaName")
        val pizzaDescription = intent.getStringExtra("pizzaDescription")
        val pizzaImage = intent.getStringExtra("pizzaImage")
        val pizzaImageResId = intent.getIntExtra("pizzaImageResId", 0)

        val imageView : ImageView = findViewById(R.id.imageView)
        if (pizzaImage != null) {
            imageView.setImageResource(pizzaImage.toInt())
        }
            //app:srcCompat="@drawable/image1"
        val button : Button = findViewById(R.id.button)

        val textPizzaName: TextView = findViewById(R.id.textPizzaName)
        val textPizzaDescription: TextView = findViewById(R.id.textPizzaDescription)

        button.text = " В КОРЗИНУ ЗА $pizzaCost KZT"
        textPizzaName.text = pizzaName
        textPizzaDescription.text = pizzaDescription

        if (pizzaImageResId != 0) {
            imageView.setImageResource(pizzaImageResId)
        }

        val button2 : Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }
    }
}