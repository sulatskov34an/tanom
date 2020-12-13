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
import ru.tanom.databinding.FragmentSearchBinding
import ru.tanom.model.network.dto.Ad
import ru.tanom.ui.MainActivity

class SearchFragment : BaseFragment() {

    lateinit var searchViewModel: SearchViewModel
    private var fragmentSearchBinding: FragmentSearchBinding? = null

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
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return fragmentSearchBinding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.showBottomNavigation()
        fragmentSearchBinding?.adsRv?.layoutManager = GridLayoutManager(view.context, 2)
        fragmentSearchBinding?.adsRv?.adapter = adsAdapter
        fragmentSearchBinding?.shimmerAdsRv?.layoutManager = GridLayoutManager(view.context, 2)
        fragmentSearchBinding?.shimmerAdsRv?.adapter = ShimmerAdsAdapter()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.swipe_container.setColorSchemeColors(
                resources.getColor(
                    R.color.colorPrimary,
                    activity?.theme
                )
            )
        } else {
            fragmentSearchBinding?.swipeContainer?.setColorSchemeColors(resources.getColor(R.color.colorPrimary))
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

    override fun <T> onSuccess(data: T?) {
        hideProgress()
        hidePlaceholder()
        adsAdapter.setData((data as? List<Ad>) ?: emptyList())
    }

    override fun showPlaceholder() {
        fragmentSearchBinding?.noInternet?.visible()
    }

    override fun hidePlaceholder() {
        fragmentSearchBinding?.noInternet?.gone()
    }

    override fun setupToolbar() {

    }

    override fun destroyBinding() {
        fragmentSearchBinding = null
    }


    override fun onProgress() {
        fragmentSearchBinding?.shimmerSearch?.visible()
        swipe_container.isRefreshing = false
    }

    override fun hideProgress() {
        fragmentSearchBinding?.shimmerSearch?.gone()
        swipe_container.isRefreshing = false
    }

}