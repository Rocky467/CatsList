package com.google.assign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.assign.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val data = args.data
        binding.textId.text = data?.first_name
        binding.textAddress.text = data?.last_name
    }

}
