package com.wayne.crypto.test.module.wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wayne.crypto.test.R
import com.wayne.crypto.test.component.AppViewModelFactory
import com.wayne.crypto.test.module.wallet.business.WalletTabViewModel

/**
 * Created by wayne.liu on 2020/9/8
 */

class WalletTabFragment : Fragment() {

    private val viewModel: WalletTabViewModel by lazy {
        requireActivity().let {
            val defaultArgs = it.intent?.extras
            ViewModelProvider(requireActivity(), AppViewModelFactory(it.application, it, defaultArgs))[WalletTabViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        viewModel.requestData()
    }
}