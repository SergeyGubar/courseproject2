package com.example.sergey.courseproject.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Spinner
import android.widget.Toast
import com.example.sergey.courseproject.R
import com.example.sergey.courseproject.entities.Worker
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterActivityApi {
    private lateinit var mPresenter: RegisterActivityPresenter

    override val spinner: Spinner
        get() = role_spinner

    override val worker: Worker
        get() = Worker(register_email_edit_text.text.toString(),
                register_password_edit_text.text.toString(),
                role_spinner.selectedItem.toString().toLowerCase(),
                register_name_edit_text.text.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterActivityPresenter(this, this)

        mPresenter.initializeSpinnerData()

        register_button.setOnClickListener {
            mPresenter.addUser()
        }
    }

    override fun showSuccessToast() {
        Toast.makeText(this, R.string.user_success_add, Toast.LENGTH_SHORT).show()
    }

    override fun showFailMessage() {
        Toast.makeText(this, R.string.user_fail_add, Toast.LENGTH_SHORT).show()
    }

    override fun cleanInputs() {
        register_email_edit_text.setText("")
        register_password_edit_text.setText("")
    }


}
