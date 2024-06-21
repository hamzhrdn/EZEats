package com.example.ezeats.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ezeats.MainActivity
import com.example.ezeats.R
import com.example.ezeats.databinding.FragmentRegisterBinding
import com.example.ezeats.utils.Result
import com.example.ezeats.utils.ViewModelFactory

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegister.setOnClickListener{
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            viewModel.register(name, email, password).observe(requireActivity()){result->
                if(result!=null){
                    when(result){
                        is Result.Loading->{
                            showLoading(true)
                        }
                        is Result.Success->{
                            Toast.makeText(requireContext(), result.data.message, Toast.LENGTH_LONG).show()
                            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                            findNavController().navigate(action)
                            showLoading(false)
                        }
                        is Result.Error->{
                            Toast.makeText(requireContext(),result.error, Toast.LENGTH_LONG).show()
                            showLoading(false)
                        }
                    }
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        //(activity as MainActivity).navBar.visibility = View.GONE
    }
    private fun showLoading(con : Boolean){
        binding.progressbar.isVisible = con
    }
}