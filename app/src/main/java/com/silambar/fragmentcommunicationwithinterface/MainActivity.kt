package com.silambar.fragmentcommunicationwithinterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silambar.fragmentcommunicationwithinterface.fragments.PersonA
import com.silambar.fragmentcommunicationwithinterface.fragments.PersonB

class MainActivity : AppCompatActivity(), IMessage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPersons()
    }

    private fun initPersons() {
        supportFragmentManager.beginTransaction()
            .add(R.id.containerPersonA, PersonA())
            .addToBackStack(null)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.containerPersonB, PersonB())
            .addToBackStack(null)
            .commit()
    }

    override fun sendMessageToA(message: String) {
        val personA: PersonA =
            supportFragmentManager.findFragmentById(R.id.containerPersonA) as PersonA
        personA.receivedMessage(message)
    }

    override fun sendMessageToB(message: String) {
        val personB:PersonB = supportFragmentManager.findFragmentById(R.id.containerPersonB) as PersonB
        personB.receivedMessage(message)
    }
}
