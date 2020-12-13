package ru.tanom.ui.ad_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ad_details.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.*
import ru.tanom.databinding.FragmentAdDetailsBinding
import ru.tanom.model.network.dto.Ad
import ru.tanom.ui.MainActivity

class AdDetailsFragment : BaseFragment() {

    private lateinit var adDetailsViewModel: AdDetailsViewModel
    private var fragmentAdDetailsBinding: FragmentAdDetailsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAdDetailsBinding = FragmentAdDetailsBinding.inflate(inflater, container, false)
        return fragmentAdDetailsBinding?.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.hideBottomNavigation()
        view.content.gone()
        val id = arguments?.getInt(AppConst.AD_ID_KEY, 0)
        adDetailsViewModel = ViewModelProviders.of(this).get(AdDetailsViewModel::class.java)
        adDetailsViewModel.ad.observe(this.viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> onProgress()
                Status.SUCCESS -> onSuccess(it.data?.result)
                Status.ERROR -> onError()
            }
        })
        adDetailsViewModel.getAd(id)

        fragmentAdDetailsBinding?.root?.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeRight() {
                findNavController().navigate(R.id.action_to_ads_search)
            }
        })
    }

    override fun <T> onSuccess(content: T?) {
        fragmentAdDetailsBinding?.content?.visible()
        hideProgress()
        val ad = (content as? Ad)
        fragmentAdDetailsBinding?.description?.text = ad?.description.toString()
        context?.let {
            fragmentAdDetailsBinding?.image?.apply {
                val path = "hardCode"
                Picasso.get()
                    .load(path)
                    .error(R.drawable.ic_error)
                    .placeholder(getProgressBar(context = it))
                    .into(image)
            }
        }
        fragmentAdDetailsBinding?.title?.text =
            "${ad?.carFactory} ${ad?.carModel}, ${ad?.productionYear}, ${ad?.mileage}"
        fragmentAdDetailsBinding?.inspectionPlace?.text = ad?.inspectionPlace.toString()
        fragmentAdDetailsBinding?.price?.text = "${ad?.price} ₽"
        context?.let {
            fragmentAdDetailsBinding?.creationDate?.text =
                TimeUtils(it).getDateWithInTime(it, ad?.creationDate ?: 0)
        }

    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun setupToolbar() {
        fragmentAdDetailsBinding?.toolbar?.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_to_ads_search)
        }
    }

    override fun destroyBinding() {
        fragmentAdDetailsBinding = null
    }
}