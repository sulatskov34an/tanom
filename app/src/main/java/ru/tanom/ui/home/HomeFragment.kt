package ru.tanom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import ru.tanom.R
import ru.tanom.common.SimpleDividerItemDecoration
import ru.tanom.data.model.Ads
import ru.tanom.data.mvvm.Status


class HomeFragment : Fragment() {

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
        view.ads_rv?.layoutManager = LinearLayoutManager(view.context)
        view.ads_rv?.adapter = adsAdapter
        view.ads_rv?.addItemDecoration(SimpleDividerItemDecoration(context))
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.simpleLiveData.observe(this.viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> showSuccess(it.data)
                Status.ERROR -> showError()
            }
        })
        homeViewModel.getAdsList()
    }

    private fun showLoading() {

    }

    private fun showSuccess(ads: List<Ads>?) {
        adsAdapter.setData(ads?: mutableListOf())
    }

    private fun showError() {
        Toast.makeText(view?.context, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
    }

}