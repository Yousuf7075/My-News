package com.example.mynews.utils

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.Log
import android.util.Patterns
import com.example.mynews.R
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*


// This utility class is not publicly instantiable
object CommonUtils {

    val timestamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @Throws(IOException::class)
    internal fun loadJSONFromAsset(context: Context, RPname: String): String? {
        var json: String? = null
        try {
            val `is` = context.assets.open(RPname)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }
    fun logErrorMessage(errorMessage: String?) {
        Log.d("FirebaseAppTag", errorMessage)
    }
}
