package ru.tanom.ui.ad_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ad_details.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment
import ru.tanom.base.viewmodel.Status
import ru.tanom.common.AppConst
import ru.tanom.common.getProgressBar
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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.hideBottomNavigation()
        val id = arguments?.getInt(AppConst.AD_ID_KEY, 0)
        adDetailsViewModel = ViewModelProviders.of(this).get(AdDetailsViewModel::class.java)
        adDetailsViewModel.ad.observe(this.viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> onProgress()
                Status.SUCCESS -> onSuccess(it.data)
                Status.ERROR -> onError()
            }
        })
        adDetailsViewModel.getAd(id)
    }


    override fun <T> onSuccess(content: T?) {
        hideProgress()
        view?.title?.text = (content as? Ad)?.title
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
        view?.price?.text = "50 руб."
        view?.creation_date?.text = (content as? Ad)?.creationDate

    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }
}