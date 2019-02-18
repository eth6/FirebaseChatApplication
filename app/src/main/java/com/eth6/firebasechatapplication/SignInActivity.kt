package com.eth6.firebasechatapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.longSnackbar

class SignInActivity : AppCompatActivity() {

    private val SIGN_IN: Int = 1
    private val signInProvider = listOf(
        AuthUI.IdpConfig.EmailBuilder()
            .setAllowNewAccounts(true)
            .setRequireName(true)
            .build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        button_signin.setOnClickListener {
            val intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(signInProvider)
                .setLogo(R.drawable.ic_firebase_chat)
                .build()
            startActivityForResult(intent, SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = IdpResponse.fromResultIntent(data)

        if (resultCode ==Activity.RESULT_OK){
            val progressDialog = indeterminateProgressDialog("Setting up your account")
            startActivity(intentFor<MainActivity>().newTask().clearTask())
            progressDialog.dismiss()
        }
        else if (resultCode == Activity.RESULT_CANCELED){
            if (response == null) return

            when (response.error?.errorCode){
                ErrorCodes.NO_NETWORK ->
                    longSnackbar(signin_layout,"No network :(")
                ErrorCodes.UNKNOWN_ERROR ->
                    longSnackbar(signin_layout,"Unknown error :(")
            }
        }
    }
}
