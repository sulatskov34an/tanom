package ru.tanom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseViewInterface
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.SimpleDividerItemDecoration
import ru.tanom.common.gone
import ru.tanom.common.toast
import ru.tanom.common.visible
import ru.tanom.model.network.dto.Ads
import ru.tanom.ui.MainActivity

class HomeFragment : Fragment(), BaseViewInterface {

    private lateinit var homeViewModel: HomeViewModel
    private var adsAdapter = AdsAdapter {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).main_toolbar.gone()
        view.ads_rv?.layoutManager = LinearLayoutManager(view.context)
        view.ads_rv?.adapter = adsAdapter
        view.ads_rv?.addItemDecoration(SimpleDividerItemDecoration(view.context))
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.list.observe(this.viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showProgress()
                Status.SUCCESS -> showSuccess(it.data)
                Status.ERROR -> showError()
            }
        })
        homeViewModel.getAdsList()
        swipe_container.setOnRefreshListener {
            homeViewModel.getAdsList()
        }
    }

    private fun showSuccess(ads: List<Ads>?) {
        hideProgress()
        adsAdapter.setData(ads ?: emptyList())
    }

    private fun showError() {
        toast(getString(R.string.error_text))
    }

    override fun showProgress() {
        (activity as? MainActivity)?.showProgress()
        swipe_container.isRefreshing = false
    }

    override fun hideProgress() {
        (activity as? MainActivity)?.hideProgress()
    }

}