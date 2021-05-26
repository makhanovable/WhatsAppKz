package kz.whatsapp.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_message.view.*
import kotlinx.android.synthetic.main.message.view.*
import kotlinx.android.synthetic.main.message.view.messengerImageView
import kotlinx.android.synthetic.main.message.view.messengerTextView
import kz.whatsapp.R
import kz.whatsapp.ui.MainActivity.Companion.ANONYMOUS
import kz.whatsapp.ui.model.FriendlyMessage

// The FirebaseRecyclerAdapter class and options come from the FirebaseUI library
// See: https://github.com/firebase/FirebaseUI-Android
class FriendlyMessageAdapter(
    private val options: FirebaseRecyclerOptions<FriendlyMessage>,
    private val currentUserName: String?
) :
    FirebaseRecyclerAdapter<FriendlyMessage, ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: FriendlyMessage) {
        (holder as MessageViewHolder).bind(model)

    }

    override fun getItemViewType(position: Int): Int {
        return if (options.snapshots[position].text != null) VIEW_TYPE_TEXT else VIEW_TYPE_IMAGE
    }

    inner class MessageViewHolder(private val view: View) : ViewHolder(view) {
        fun bind(item: FriendlyMessage) {
            view.messageTextView.text = item.text.toString()
            setTextColor(item.name, view.ccc)

            view.messengerTextView.text = if (item.name == null) ANONYMOUS else item.name
            if (item.photoUrl != null) {
                loadImageIntoView(view.messengerImageView, item.photoUrl!!)
            } else {
//                view.messengerImageView.setImageResource(R.drawable.ic_launcher_background)
            }
        }

        private fun setTextColor(userName: String?, textView: View) {
            if (userName != ANONYMOUS && currentUserName == userName && userName != null) {
                textView.setBackgroundResource(R.drawable.rounded_message_blue)
//                textView.setTextColor(Color.WHITE)
            } else {
                textView.setBackgroundResource(R.drawable.rounded_message_gray)
//                textView.setTextColor(Color.BLACK)
            }
        }
    }



    private fun loadImageIntoView(view: ImageView, url: String) {
        if (url.startsWith("gs://")) {
            val storageReference = Firebase.storage.getReferenceFromUrl(url)
            storageReference.downloadUrl
                .addOnSuccessListener { uri ->
                    val downloadUrl = uri.toString()
                    Picasso.get()
                        .load(downloadUrl)
                        .into(view)
                }
                .addOnFailureListener { e ->
                    Log.w(
                        TAG,
                        "Getting download url was not successful.",
                        e
                    )
                }
        } else {
            Picasso.get().load(url).into(view)
        }
    }

    companion object {
        const val TAG = "MessageAdapter"
        const val VIEW_TYPE_TEXT = 1
        const val VIEW_TYPE_IMAGE = 2
    }
}
