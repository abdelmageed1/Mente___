package com.example.mente.Parent.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.models.SlideModel
import com.example.mente.R
import com.example.mente.databinding.FragmentInstructionBinding
import com.denzcoskun.imageslider.constants.ScaleTypes


class InstructionFragment : Fragment() {
    private lateinit var binding: FragmentInstructionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.instruction_1))
        imageList.add(SlideModel(R.drawable.instruction_2))
        imageList.add(SlideModel(R.drawable.instruction_3))
        imageList.add(SlideModel(R.drawable.instruction_4))
        imageList.add(SlideModel(R.drawable.instruction_5))
        imageList.add(SlideModel(R.drawable.instruction_6))
        imageList.add(SlideModel(R.drawable.instruction_7))

        binding.sliderImage.setImageList(imageList , ScaleTypes.CENTER_CROP)

        binding.sliderImage


//        slider.setOrientation(SliderAnimations.Orientation.RIGHT_TO_LEFT)



    }

    companion object {

    }
}