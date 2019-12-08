package com.example.flexboxsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flexboxsample.models.generateTicket
import com.example.flexboxsample.tickets.TicketsAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var flexboxLayoutManager: FlexboxLayoutManager
    private val ticketsAdapter = TicketsAdapter()

    val random = Random(Date().time)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        flexboxLayoutManager = FlexboxLayoutManager(baseContext)
        flexboxLayoutManager.setFlexDirection(FlexDirection.COLUMN)
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP)
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START)
        recycler_view.layoutManager = flexboxLayoutManager
        recycler_view.adapter = ticketsAdapter

        fab.setOnClickListener { view ->
            run {
                val count = when (random.nextBoolean()) {
                    true -> 5
                    false -> 1
                }
                ticketsAdapter.add(generateTicket(count, 4))
                Snackbar.make(view, "Ticket Generated", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
