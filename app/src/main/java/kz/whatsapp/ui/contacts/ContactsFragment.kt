package kz.whatsapp.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_contact.*
import kz.whatsapp.R
import kz.whatsapp.ui.chatList.ChatItem
import kz.whatsapp.ui.chatList.ChatListAdapter2

class ContactsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = arrayListOf(
            ChatItem(
                image = "https://lh3.googleusercontent.com/a/AATXAJzrddw6yzYEf-PauBM6JEB38fyP1nAwCRcsC2WB=s96-c",
                name = "Alma Murziyatova",
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
        val adapter = ChatListAdapter2(list) {

        }
        chats_list.adapter = adapter
    }
}