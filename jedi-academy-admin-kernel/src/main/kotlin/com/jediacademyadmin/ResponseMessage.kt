package com.jediacademyadmin

class ResponseMessage {
    var message: String? = null
    var requestRef: String? = null

    constructor() {}
    constructor(message: String?, requestRef: String?) {
        this.message = message
        this.requestRef = requestRef
    }

    override fun toString(): String {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", requestRef='" + requestRef + '\'' +
                '}'
    }
}