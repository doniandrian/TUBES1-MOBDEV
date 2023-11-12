package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


import androidx.fragment.app.Fragment
import com.example.tubes.databinding.ListItemFontSizeBinding

class ListFontSizeFragment : Fragment() {
    private lateinit var binding: ListItemFontSizeBinding
    private lateinit var small : androidx.appcompat.widget.AppCompatButton
    private lateinit var medium : androidx.appcompat.widget.AppCompatButton
    private lateinit var large : androidx.appcompat.widget.AppCompatButton
    private lateinit var close : ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ListItemFontSizeBinding.inflate(inflater, container, false)



        return binding.root
    }
    fun setCloseButtonClickListener(listener: () -> Unit) {
        close.setOnClickListener {
            listener()
        }
    }

}