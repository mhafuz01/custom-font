package com.example.islamicname.data

import com.example.islamicname.R
import com.example.islamicname.model.Description

class DataSource {
    fun loadAboutName() : List<Description> {
        return listOf(
            Description(R.string.about_name_title),
            Description(R.string.about_name_title2),
            Description(R.string.about_name_title4),
            Description(R.string.about_name_title5),
            Description(R.string.about_name_title6),
            Description(R.string.about_name_title7),
            Description(R.string.about_name_title8),
            Description(R.string.about_name_title9),
            Description(R.string.about_name_title10),
            Description(R.string.about_name_title11)
        )
    }
}