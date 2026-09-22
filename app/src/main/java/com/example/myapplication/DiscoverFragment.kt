package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DiscoverFragment : Fragment() {
    private lateinit var adapter: DiscoverProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvProducts = view.findViewById<RecyclerView>(R.id.rvProducts)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        adapter = DiscoverProductAdapter { product ->
            CartManager.addItem(product)
            Toast.makeText(requireContext(), "${product.title} added to basket!", Toast.LENGTH_SHORT).show()
        }

        // Set LinearLayoutManager for an immersive, vertical magazine-style feed
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = adapter

        fetchProducts(progressBar)
    }

    private fun fetchProducts(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            delay(600)

            val fakeCraftData = listOf(
                Product(1, "Handmade Zulu Basket", "Woven by local artisans in KwaZulu-Natal.", "R 250.00", "Sibusiso Zulu", imageResId = R.drawable.zulu_basket),
                Product(2, "Beaded African Necklace", "Traditional beading with modern colors.", "R 180.00", "Elena Khumalo", imageResId = R.drawable.beaded_necklace),
                Product(3, "Wooden Elephant Carving", "Hand-carved from sustainable wood.", "R 450.00", "Thabo Mokoena", imageResId = R.drawable.wooden_elephant),
                Product(4, "Handmade Wooden Mug", "Locally sourced wood, perfect for morning coffee.", "R 120.00", "Nomsa Mabusela", imageResId = R.drawable.wooden_mug)
            )

            progressBar.visibility = View.GONE
            adapter.setData(fakeCraftData)
        }
    }
}

class DiscoverProductAdapter(private val onClick: (Product) -> Unit) : RecyclerView.Adapter<DiscoverProductAdapter.DiscoverViewHolder>() {
    private var products: List<Product> = emptyList()

    fun setData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discover_product, parent, false)
        return DiscoverViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        holder.bind(products[position], onClick)
    }

    override fun getItemCount() = products.size

    class DiscoverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, onClick: (Product) -> Unit) {
            itemView.findViewById<android.widget.TextView>(R.id.tvTitle).text = product.title
            itemView.findViewById<android.widget.TextView>(R.id.tvSeller).text = "by " + product.sellerName
            itemView.findViewById<android.widget.TextView>(R.id.tvPrice).text = product.price
            
            val ivProduct = itemView.findViewById<android.widget.ImageView>(R.id.ivProduct)
            
            com.bumptech.glide.Glide.with(itemView.context)
                .load(product.imageResId ?: product.imageUrl)
                .placeholder(R.drawable.ic_placeholder_logo)
                .error(R.drawable.ic_placeholder_logo)
                .into(ivProduct)

            itemView.setOnClickListener { onClick(product) }
        }
    }
}
