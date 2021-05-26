package kz.whatsapp.ui.chatList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_contact.view.*
import kz.whatsapp.R

class ChatListAdapter2(
    private val items: List<ChatItem>,
    private val pressed: () -> Unit
) : RecyclerView.Adapter<ChatListAdapter2.ChatListViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder2 {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.image_contact, parent, false)
        return ChatListViewHolder2(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChatListViewHolder2, position: Int) {
        holder.itemView.cont.setOnClickListener {
            pressed.invoke()
        }
        holder.itemView.name.text = items[position].name

        Picasso.get().load(items[position].image).into(holder.itemView.image)
    }


    inner class ChatListViewHolder2(private val view: View) : RecyclerView.ViewHolder(view)
}
