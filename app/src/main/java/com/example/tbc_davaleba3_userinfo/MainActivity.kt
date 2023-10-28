package com.example.tbc_davaleba3_userinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tbc_davaleba3_userinfo.databinding.ActivityMainBinding
import com.example.tbc_davaleba3_userinfo.model.User


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.btnSave.setOnClickListener {
            if (validate()) {
                val user = User(
                    firstName = binding.etFirstName.text.toString(),
                    lastName = binding.etLastName.text.toString(),
                    username = binding.etUsername.text.toString(),
                    age = binding.etAge.text.toString().toInt(),
                    email = binding.etEmail.text.toString(),
                )
                setUserInfo(user)
                visibilitySave()
            } else {
                Toast.makeText(this, "Something didn't validate", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnLongClickListener {
            clear()
            false
        }

        binding.btnAgain.setOnClickListener {
            visibilityAgain()
        }
    }

    private fun validate(): Boolean {
        if (binding.etFirstName.text.isEmpty() || binding.etLastName.text.isEmpty() ||
            binding.etUsername.text.isEmpty() || binding.etAge.text.isEmpty() ||
            binding.etEmail.text.isEmpty()
        ) {
            setEtEmptyError()
            return false
        } else if (binding.etUsername.text.toString().length < 10
        ) {
            binding.etUsername.error = "Username minimum length is 10"
            return false
        } else if (binding.etAge.text.toString().contains(".") || binding.etAge.text.toString()
                .toInt() < 1
        ) {
            binding.etAge.error = "Age should be a positive integer"
            return false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                .matches()
        ) {
            binding.etEmail.error = "Provide Email with valid address"
            return false
        }
        return true
    }

    private fun visibilitySave() {
        binding.etFirstName.visibility = View.GONE
        binding.etLastName.visibility = View.GONE
        binding.etUsername.visibility = View.GONE
        binding.etAge.visibility = View.GONE
        binding.etEmail.visibility = View.GONE
        binding.btnSave.visibility = View.GONE
        binding.btnClear.visibility = View.GONE

        binding.tvEmail.visibility = View.VISIBLE
        binding.tvUsername.visibility = View.VISIBLE
        binding.tvFullName.visibility = View.VISIBLE
        binding.tvAge.visibility = View.VISIBLE
        binding.btnAgain.visibility = View.VISIBLE
    }

    private fun visibilityAgain() {
        binding.etFirstName.visibility = View.VISIBLE
        binding.etLastName.visibility = View.VISIBLE
        binding.etUsername.visibility = View.VISIBLE
        binding.etAge.visibility = View.VISIBLE
        binding.etEmail.visibility = View.VISIBLE
        binding.btnSave.visibility = View.VISIBLE
        binding.btnClear.visibility = View.VISIBLE

        binding.tvEmail.visibility = View.GONE
        binding.tvUsername.visibility = View.GONE
        binding.tvFullName.visibility = View.GONE
        binding.tvAge.visibility = View.GONE
        binding.btnAgain.visibility = View.GONE
    }

    private fun clear() {
        binding.etFirstName.text.clear()
        binding.etLastName.text.clear()
        binding.etUsername.text.clear()
        binding.etAge.text.clear()
        binding.etEmail.text.clear()
    }

    private fun setUserInfo(user: User) {
        binding.tvEmail.text = user.email
        binding.tvUsername.text = user.username
        binding.tvFullName.text = user.firstName.plus(" ${user.lastName}")
        binding.tvAge.text = user.age.toString()
    }

    private fun setEtEmptyError() {
        binding.etFirstName.error = "Fill out all of the fields"
        binding.etLastName.error = "Fill out all of the fields"
        binding.etUsername.error = "Fill out all of the fields"
        binding.etAge.error = "Fill out all of the fields"
        binding.etEmail.error = "Fill out all of the fields"
    }
}
