package com.google.assign.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.google.assign.utils.SharedViewModel

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    val sharedViewModel: SharedViewModel by activityViewModels()

    open fun onCreateView() {}
    open fun onViewCreated() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        onCreateView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun alertDialog(
        title: String,
        msg: String,
        okClick: () -> Unit,
        cancelClick: (() -> Unit)? = null
    ) {
        MaterialDialog(requireContext(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            title(text = title)
            message(text = msg)
            cornerRadius(10f)
            cancelable(false)
            positiveButton(text = "Okay") { okClick() }
            negativeButton(text = "Cancel") { cancelClick?.invoke() }
        }
    }

    @Suppress("DEPRECATION")
    fun isConnected(): Boolean {
        val cm =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun navigateTo(fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}