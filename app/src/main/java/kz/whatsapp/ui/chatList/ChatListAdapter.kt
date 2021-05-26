package kz.whatsapp.ui.chatList

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_chat.view.*
import kotlinx.android.synthetic.main.image_message.view.*
import kotlinx.android.synthetic.main.message.view.*
import kz.whatsapp.R
import kz.whatsapp.ui.MainActivity
import kz.whatsapp.ui.model.FriendlyMessage

class ChatListAdapter(
    private val items: List<ChatItem>,
    private val pressed: () -> Unit
) : RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.image_chat, parent, false)
        return ChatListViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.itemView.cont.setOnClickListener {
            pressed.invoke()
        }
        holder.itemView.name.text = items[position].name
        holder.itemView.lastMsg.text = items[position].lastMsg
        holder.itemView.date.text = items[position].date

        Picasso.get().load(items[position].image).into(holder.itemView.image)
    }


    inner class ChatListViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
}
