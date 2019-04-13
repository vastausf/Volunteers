package com.vastausf.volunteers.adapter.pager.pages

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_accept.view.*

class FragmentAccept : Fragment() {

    private lateinit var acceptListener: AcceptListener

    interface AcceptListener {

        fun onAccept()

    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_accept, container, false)

        view.bAccept.setOnClickListener {
            acceptListener.onAccept()
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        acceptListener = parentFragment as AcceptListener
    }

}
