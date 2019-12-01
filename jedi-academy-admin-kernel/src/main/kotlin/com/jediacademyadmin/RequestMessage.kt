package com.jediacademyadmin

class RequestMessage {
    var ref: String? = null

    constructor() {}
    constructor(ref: String?) {
        this.ref = ref
    }

    override fun toString(): String {
        return "RequestMessage{" +
                "ref='" + ref + '\'' +
                '}'
    }
}
