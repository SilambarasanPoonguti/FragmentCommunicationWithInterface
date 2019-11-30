package com.silambar.fragmentcommunicationwithinterface

interface IMessage {
    fun sendMessageToA(message: String)
    fun sendMessageToB(message: String)
}