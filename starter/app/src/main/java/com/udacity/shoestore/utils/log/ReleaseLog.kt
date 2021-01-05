package com.udacity.shoestore.utils.log

class ReleaseLog {
    companion object {
        fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            throw NotImplementedError()
        }

        fun logError(t: Throwable?) {
            throw NotImplementedError()
        }

        fun logWarning(t: Throwable?) {
            throw NotImplementedError()
        }
    }
}
