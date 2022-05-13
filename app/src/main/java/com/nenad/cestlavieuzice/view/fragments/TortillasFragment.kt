package com.nenad.cestlavieuzice.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.FragmentTortillasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TortillasFragment : Fragment() {

    private lateinit var mBinding: FragmentTortillasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tortillas, container, false)





        return mBinding.root
    }


}