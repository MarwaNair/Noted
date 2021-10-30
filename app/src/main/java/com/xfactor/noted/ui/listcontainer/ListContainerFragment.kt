package com.xfactor.noted.ui.listcontainer

import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xfactor.noted.*
import com.xfactor.noted.database.ListWithListItems
import com.xfactor.noted.database.ListsToCompare
import com.xfactor.noted.database.getSubItems

import kotlinx.android.synthetic.main.fragment_listcontainer.view.*
import kotlinx.android.synthetic.main.fragment_listitem.view.*

private lateinit var statusText : TextView;

fun updateStatus() {
    if(ListsToCompare.size == 0)  return
    var status = "Selected: ".plus(ListsToCompare[0].list.title)
    if(ListsToCompare.size == 2) {
        status = status.plus(", ").plus(ListsToCompare[1].list.title)
    }
    statusText.text = status
}

class ListContainerFragment : Fragment() {

    private lateinit var adapter : ListsAdapter
    private lateinit var listContainerViewModel : ListContainerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_listcontainer, container, false)
        listContainerViewModel =
            ViewModelProviders.of(this).get(ListContainerViewModel::class.java)
        adapter = ListsAdapter(listContainerViewModel.dataRepo.getAll())
        root.all_lists.layoutManager = GridLayoutManager(context, 2)
        root.all_lists.adapter = adapter
        root.findViewById<AppCompatEditText>(R.id.Search).addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        val search = s.toString()
                        val results = listContainerViewModel.dataRepo.query(search)
                        Log.e("results",  results.toString())
                        root.all_lists.adapter = ListsAdapter(results)

                    }

                override fun afterTextChanged(s: Editable?) {

                }

            }
        )
        statusText = root.findViewById(R.id.status)
        updateStatus()
        return root
    }

}
class ListsAdapter(private val dataSet: List<ListWithListItems>) :
    RecyclerView.Adapter<ListsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: LinearLayout = view.findViewById(R.id.listitem)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.listItem.setOnClickListener {
            if(ListsToCompare.size > 1) {
                ListsToCompare.clear()
            }
            ListsToCompare.add(dataSet[position])
            updateStatus()
        }
        val title = viewHolder.listItem.list_title
        title.text = dataSet[position].list.title
        title.paintFlags = title.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        viewHolder.listItem.list_elements.text = getSubItems(dataSet[position])
        val visibility: Int = viewHolder.listItem.visibility
        viewHolder.listItem.visibility = View.GONE
        viewHolder.listItem.visibility = visibility
    }

    override fun getItemCount():Int {
        return dataSet.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_listitem, parent, false)
        return ViewHolder(view)
    }

}