package com.example.sergey.courseproject.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.sergey.courseproject.station.AddStationActivity
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Worker
import com.example.sergey.courseproject.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginActivityApi {
    private lateinit var mPresenter : LoginActivityPresenter

    override val worker : Worker
        get() = Worker(login_email_edit_text.text.toString(),
                login_password_edit_text.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mPresenter = LoginActivityPresenter(this, this)
        login_login_button.setOnClickListener {
            mPresenter.logIn()
        }

        login_add_button.setOnClickListener(
                {
                    startActivity(Intent(LoginActivity@this, AddStationActivity::class.java))
                }
        )

        login_register_button.setOnClickListener {
            startActivity(Intent(LoginActivity@this, RegisterActivity::class.java))
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
