package ru.tanom.ui.ad_details

import android.os.Bundle
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ad_details.*
import kotlinx.android.synthetic.main.fragment_ad_details.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.*
import ru.tanom.model.network.dto.Ad
import ru.tanom.ui.MainActivity

class AdDetailsFragment : BaseFragment() {

    private lateinit var adDetailsViewModel: AdDetailsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ad_details, container, false)
        root.setOnTouchListener(object: OnSwipeTouchListener(activity){
            override fun onSwipeRight() {
                findNavController().navigate(R.id.action_to_ads_search)
            }
        })
        return root
    }

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
    }


    override fun <T> onSuccess(content: T?) {
        view?.content?.visible()
        hideProgress()
        val ad = (content as? Ad)
        view?.description?.text = ad?.description.toString()
        context?.let {
            view?.image?.apply {
                val path = "saffdasfd"
                Picasso.get()
                    .load(path)
                    .error(R.drawable.ic_error)
                    .placeholder(getProgressBar(context = it))
                    .into(image)
            }
        }
        view?.title?.text =
            "${ad?.carFactory} ${ad?.carModel}, ${ad?.productionYear}, ${ad?.mileage}"
        view?.inspectionPlace?.text = ad?.inspectionPlace.toString()
        view?.price?.text = "${ad?.price} ₽"
        view?.context?.let {
            view?.creation_date?.text =
                TimeUtils(it).getDateWithInTime(it, ad?.creationDate ?: 0)
        }

    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun setToolbar() {
        text_toolbar.setText(R.string.to_search)
        back.setOnClickListener { findNavController().navigate(R.id.action_to_ads_search) }
    }
}