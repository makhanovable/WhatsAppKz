package kz.whatsapp.ui.chatList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.*
import kz.whatsapp.R
import kz.whatsapp.ui.MainActivity

class ChatListFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf(
            ChatItem(
                image = "https://lh3.googleusercontent.com/a/AATXAJxnf_l8M19WlHQQ64lVkpXpFznL9PwwV369GX3i=s96-c",
                name = "Madiyar Makhanov",
                lastMsg = "Привет",
                date = "Только что"
            ),
            ChatItem(
                image = "https://lh3.googleusercontent.com/a/AATXAJzrddw6yzYEf-PauBM6JEB38fyP1nAwCRcsC2WB=s96-c",
                name = "Aiya Talapkaliyeva",
                lastMsg = "Скинь апк",
                date = "Вчера"
            )
        )

        chats_list.adapter = ChatListAdapter(list) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }
    }
}