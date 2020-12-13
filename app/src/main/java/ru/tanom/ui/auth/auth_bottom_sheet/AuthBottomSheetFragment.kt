package ru.tanom.ui.auth.auth_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.tanom.R
import ru.tanom.databinding.BottomSheetAuthBinding

class AuthBottomSheetFragment : BottomSheetDialogFragment() {

    private var bottomSheetAuthBinding: BottomSheetAuthBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomSheetAuthBinding = BottomSheetAuthBinding.inflate(inflater, container, false)
        return bottomSheetAuthBinding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetAuthBinding?.login?.setOnClickListener {
            findNavController().navigate(R.id.action_to_login)
        }
    }

    override fun onDestroyView() {
        bottomSheetAuthBinding = null
        super.onDestroyView()
    }

}