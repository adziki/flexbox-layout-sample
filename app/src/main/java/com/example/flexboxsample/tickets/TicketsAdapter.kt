package com.example.flexboxsample.tickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flexboxsample.R
import com.example.flexboxsample.models.Item
import com.example.flexboxsample.models.Ticket
import com.google.android.flexbox.FlexboxLayout

class TicketsAdapter : RecyclerView.Adapter<TicketViewHolder>() {

    private val tickets = ArrayList<Ticket>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        return TicketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vh_ticket, parent, false)
        )
    }

    override fun getItemCount() = tickets.size

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(tickets.get(position))
    }

    fun add(ticket: Ticket) {
        tickets.add(ticket)
        notifyDataSetChanged()
    }
}

class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val topLeft = itemView.findViewById<TextView>(R.id.primary_left)
    private val flexboxlayout = itemView.findViewById<FlexboxLayout>(R.id.flexboxlayout)

    fun bind(ticket: Ticket) {
        // set header
        topLeft.text = ticket.orderNumber
        flexboxlayout.removeAllViews()

        ticket.items.forEach {
            flexboxlayout.apply {
                val wrapper = LinearLayout(flexboxlayout.context)
                wrapper.orientation = VERTICAL
                addItem(it, 0).forEach {
                    wrapper.addView(it)
                }
                flexboxlayout.addView(wrapper)
            }
        }
    }

    private fun addItem(item: Item, indent: Int): List<View> {
        val views = ArrayList<View>()
        val view = LayoutInflater.from(itemView.context).inflate(R.layout.item_row, flexboxlayout, false)
        view.findViewById<TextView>(R.id.quantity).apply {
            if (indent == 0 || item.quantity > 1) {
                text = item.quantity.toString()
            }
        }
        view.findViewById<TextView>(R.id.modifier_name).also {
            it.text = "  ".repeat(indent) + item.name
        }
        view.findViewById<TextView>(R.id.seat_number).apply {
            text = ""
        }
        views.add(view)
        item.modifiers.forEach {
            addItem(it, indent + 1).forEach { views.add(it) }
        }
        return views
    }
}
