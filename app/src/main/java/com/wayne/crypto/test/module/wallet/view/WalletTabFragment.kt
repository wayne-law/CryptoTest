package com.wayne.crypto.test.module.wallet.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import com.drakeet.multitype.MultiTypeAdapter
import com.wayne.crypto.test.R
import com.wayne.crypto.test.component.AppViewModelFactory
import com.wayne.crypto.test.model.Status
import com.wayne.crypto.test.module.wallet.business.WalletTabViewModel
import kotlinx.android.synthetic.main.fragment_wallet.*

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

    private lateinit var adapter: MultiTypeAdapter
    private lateinit var listDiffer: AsyncListDiffer<Any>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        initObserver()
    }

    private fun initView() {
        adapter = MultiTypeAdapter().apply {
            register(WalletCurrencyViewBinder())
        }
        rv_wallet_container.adapter = adapter
        listDiffer = AsyncListDiffer(adapter, WalletDiffCallback())
    }

    private fun initEvent() {
        srl_wallet_container.setOnRefreshListener {
            viewModel.requestData()
        }
    }

    private fun initObserver() {
        viewModel.pageStatusData.observe(viewLifecycleOwner, {
            when (it) {
                is Status.Loading -> srl_wallet_container.isRefreshing = true
                is Status.Success -> srl_wallet_container.isRefreshing = false
                is Status.Failed -> {
                    srl_wallet_container.isRefreshing = false
                    Toast.makeText(requireContext(), "request data failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.pageData.observe(viewLifecycleOwner, {
            it?.also {
                adapter.items = it
                listDiffer.submitList(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestData()
    }
}