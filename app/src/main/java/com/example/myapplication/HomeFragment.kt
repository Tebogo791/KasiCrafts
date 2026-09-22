package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvProducts = view.findViewById<RecyclerView>(R.id.rvProducts)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        adapter = ProductAdapter { product ->
            CartManager.addItem(product)
            Toast.makeText(requireContext(), "${product.title} added to basket!", Toast.LENGTH_SHORT).show()
        }

        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.adapter = adapter

        fetchProducts(progressBar)
    }

    private fun fetchProducts(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE

        // Simulating API call to make the prototype look polished for your video
        CoroutineScope(Dispatchers.Main).launch {
            delay(1500) // Fake network delay

            val fakeCraftData = listOf(
                Product(1, "Handmade Zulu Basket", "Woven by local artisans in KwaZulu-Natal.", "R 250.00", "Sibusiso Zulu", imageResId = R.drawable.zulu_basket),
                Product(2, "Beaded African Necklace", "Traditional beading with modern colors.", "R 180.00", "Elena Khumalo", imageResId = R.drawable.beaded_necklace),
                Product(3, "Wooden Elephant Carving", "Hand-carved from sustainable wood.", "R 450.00", "Thabo Mokoena", imageResId = R.drawable.wooden_elephant),
                Product(4, "Handmade Wooden Mug", "Locally sourced wood, perfect for morning coffee.", "R 120.00", "Nomsa Dlamini", imageResId = R.drawable.wooden_mug)
            )

            progressBar.visibility = View.GONE
            adapter.setData(fakeCraftData)
        }
    }
}

class ProductAdapter(private val onClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var products: List<Product> = emptyList()

    fun setData(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], onClick)
    }

    override fun getItemCount() = products.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product, onClick: (Product) -> Unit) {
            itemView.findViewById<android.widget.TextView>(R.id.tvTitle).text = product.title
            itemView.findViewById<android.widget.TextView>(R.id.tvSeller).text = product.sellerName
            itemView.findViewById<android.widget.TextView>(R.id.tvPrice).text = product.price
            
            val ivProduct = itemView.findViewById<android.widget.ImageView>(R.id.ivProduct)
            
            // Professional Glide integration for dynamic web image loading and caching
            com.bumptech.glide.Glide.with(itemView.context)
                .load(product.imageResId ?: product.imageUrl)
                .placeholder(R.drawable.ic_placeholder_logo)
                .error(R.drawable.ic_placeholder_logo)
                .into(ivProduct)

            itemView.setOnClickListener { onClick(product) }
        }
    }
}