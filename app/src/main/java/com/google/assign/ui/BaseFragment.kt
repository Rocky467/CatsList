package com.google.assign.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.google.assign.utils.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    val sharedViewModel: SharedViewModel by activityViewModels()

    val listViewModel: ListViewModel by lazy {
        getViewModel()
    }

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    //for both click
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


    fun isConnected(): Boolean {
        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun navigateTo(fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }
}