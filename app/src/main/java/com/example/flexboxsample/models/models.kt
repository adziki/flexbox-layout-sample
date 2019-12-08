package com.example.flexboxsample.models

import android.util.Log
import java.util.Date
import java.util.concurrent.atomic.AtomicInteger

data class Ticket(
    val orderNumber: String,
    val initialDate: Date,
    val items: List<Item>
)

data class Item(
    val name: String,
    val quantity: Int,
    val modifiers: List<Item>)

fun generateTicket(numberOfItems: Int, numberOfModifiers: Int, orderNumber: Int = getAndIncrementOrder()) : Ticket {
    return Ticket(
        orderNumber = orderNumber.toString(),
        initialDate = Date(),
        items = arrayListOf(*generateItems(numberOfItems, numberOfModifiers, orderNumber))
    ).also {
        Log.d("adzikitest", "${it.items.size} items created for order $orderNumber")
    }
}

private fun generateItems(numberOfItems: Int, numberOfModifiers: Int, orderNumber: Int) : Array<Item> {
    val items = mutableListOf<Item>()
    for (x in 1..numberOfItems) {
        val mods = mutableListOf<Item>()
        for (y in 1..numberOfModifiers) {
            mods.add(
                Item(
                    name = "Sub-item $orderNumber-$x.$y",
                    quantity = 1,
                    modifiers = listOf()
                )
            )
        }
        items.add(
            Item("Item $orderNumber-$x", 1, mods)
        )
    }
    return items.toTypedArray()
}

private val orderCount = AtomicInteger(1)

private fun getAndIncrementOrder() = orderCount.getAndIncrement()
