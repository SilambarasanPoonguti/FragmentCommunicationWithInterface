package com.silambar.fragmentcommunicationwithinterface.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.silambar.fragmentcommunicationwithinterface.IMessage
import com.silambar.fragmentcommunicationwithinterface.R
import com.silambar.fragmentcommunicationwithinterface.adapter.MessageAdapter
import kotlinx.android.synthetic.main.fragment_person_b.*
import java.lang.IllegalStateException

class PersonB : Fragment() {

    private var iMessage: IMessage? = null
    private var message:String = ""
    private val messages = ArrayList<String>()
    private lateinit var messageAdapter: MessageAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IMessage)
            iMessage = context
        else throw IllegalStateException("$context must implement IMessage callback")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_b, container, false)
    }

    override fun onResume() {
        super.onResume()

        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        messageListB.layoutManager = layoutManager
        messageAdapter = MessageAdapter(messages)
        messageListB.adapter = messageAdapter

        sendBtn.setOnClickListener {
            val msg = msgBox.text.toString()
            if (msg.isNotEmpty() && !msg.contentEquals(this.message)){
                msgBox.setText("")
                this.message = msg
                iMessage?.sendMessageToA(msg)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        iMessage = null
    }

    fun receivedMessage(message: String) {
        messages.add(message)
        messageAdapter.notifyItemInserted(messages.size)
    }

}