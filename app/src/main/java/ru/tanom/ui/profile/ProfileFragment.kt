package ru.tanom.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import ru.tanom.R
import ru.tanom.base.view.BaseFragment

class ProfileFragment : BaseFragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun <T> onSuccess(content: T?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.login_or_reg.setOnClickListener {
            findNavController().navigate(R.id.action_to_auth)
        }
    }

    override fun showPlaceholder() {

    }

    override fun hidePlaceholder() {

    }

    override fun setToolbar() {
        toolbar.setTitle(R.string.profile)
    }
}