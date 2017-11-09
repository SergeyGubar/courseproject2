package com.example.sergey.courseproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Chronometer
import android.widget.Spinner
import android.widget.Toast
import com.example.sergey.courseproject.entities.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterActivityApi {


    private lateinit var mPresenter : RegisterActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterActivityPresenter(this, this)

        mPresenter.initializeSpinnerData()

        register_button.setOnClickListener { _ ->  mPresenter.addUser() }
    }

    override fun showSuccessToast() {
        Toast.makeText(this, R.string.user_success_add, Toast.LENGTH_SHORT).show()
    }

    override fun getUserData(): User? {
        val email : String = register_email_edit_text.text.toString()
        val password : String = register_password_edit_text.text.toString()
        val role : String = role_spinner.selectedItem.toString()
        return null
    }

    override fun getSpinner(): Spinner {
        return role_spinner
    }
}
