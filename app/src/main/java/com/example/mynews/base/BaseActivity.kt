package com.example.mynews.base

import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.example.mynews.R
import com.example.mynews.utils.CommonUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){


    private var TAG :String = "BaseActivity"

    private var mProgressDialog: ProgressDialog? = null


    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //subscribeObservers()

    }
    fun hideLoading() {

        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    private fun performDataBinding() {

    }

    private fun showToast(s : String){
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    protected fun startActivity(activity: Class<*>, finishSelf: Boolean) {
        val intent = Intent(this, activity)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    /*protected fun startActivityAndClearAll(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    protected fun showForwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
        } catch (e: Exception) {
            Log.e("ActivityForwardTransi", e.message)
        }

    }

    protected fun showBackwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
        } catch (e: Exception) {
            Log.e("ActivityBackwardTransi", e.message)
        }
    }

    fun getResString(@StringRes id: Int): String {
        return resources.getString(id)
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this,
            Observer<AuthResource<User>> { userAuthResource ->
                if (userAuthResource != null) {
                    when (userAuthResource.status) {
                        AuthResource.AuthStatus.LOADING -> {
                            Log.d(TAG, "onChanged: BaseActivity: LOADING...")
                        }
                        AuthResource.AuthStatus.AUTHENTICATED -> {
                            Log.d(
                                TAG, "onChanged: BaseActivity: AUTHENTICATED... " +
                                        "Authenticated as: " + userAuthResource.data?.email
                            )
                        }
                        AuthResource.AuthStatus.ERROR -> {
                           // Log.d(TAG, "onChanged: BaseActivity: ERROR...")
                        }
                        AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                            Log.d(
                               TAG,
                                "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login screen."
                            )
                            navLoginScreen()
                        }
                    }
                }
            })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }*/

}
