package ru.tanom.ui.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.AppConst
import ru.tanom.common.gone
import ru.tanom.common.visible
import ru.tanom.model.network.dto.Ad
import ru.tanom.ui.MainActivity

class SearchFragment : BaseFragment(){

    private lateinit var searchViewModel: SearchViewModel
    private var adsAdapter =
        AdsAdapter {
            val bundle = Bundle()
            bundle.putInt(AppConst.AD_ID_KEY, it.id ?: 0)
            findNavController().navigate(R.id.action_to_ad_details, bundle)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.showBottomNavigation()
        view.ads_rv?.layoutManager = GridLayoutManager(view.context, 2)
        view.ads_rv?.adapter = adsAdapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.swipe_container.setColorSchemeColors(
                resources.getColor(
                    R.color.colorPrimary,
                    activity?.theme
                )
            )
        } else {
            view.swipe_container.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
        }
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        searchViewModel.list.observe(this.viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> onProgress()
                Status.SUCCESS -> onSuccess(it.data?.result)
                Status.ERROR -> onError()
            }
        })
        searchViewModel.getAdsList()
        swipe_container.setOnRefreshListener {
            searchViewModel.getAdsList()
        }
    }

    override fun <T> onSuccess(content: T?) {
        hideProgress()
        hidePlaceholder()
        adsAdapter.setData((content as? List<Ad>) ?: emptyList())
    }

    override fun showPlaceholder() {
        view?.no_internet?.visible()
    }

    override fun hidePlaceholder() {
        view?.no_internet?.gone()
    }


    override fun onProgress() {
        (activity as? MainActivity)?.showProgress()
        swipe_container.isRefreshing = false
    }

    override fun hideProgress() {
        (activity as? MainActivity)?.hideProgress()
        swipe_container.isRefreshing = false
    }

}