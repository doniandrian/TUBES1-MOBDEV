package com.example.tubes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.ListItemFontSizeBinding

class ListFontSizeFragment : Fragment() {
    private lateinit var binding: ListItemFontSizeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListItemFontSizeBinding.inflate(inflater, container, false)
        return binding.root
    }
}