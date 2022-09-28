package com.example.wmtstore.ui.productsDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wmtstore.databinding.FragmentProductDetailsListDialogBinding

class ProductDetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


}