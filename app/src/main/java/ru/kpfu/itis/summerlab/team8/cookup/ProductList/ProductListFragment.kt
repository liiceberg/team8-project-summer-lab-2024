package ru.kpfu.itis.summerlab.team8.cookup.ProductList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.databinding.AddProductBottomSheetBinding
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentProductListBinding

class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private var binding : FragmentProductListBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductListBinding.bind(view)

        initAdapter()

        binding?.apply {
            toolBar.setOnMenuItemClickListener {
                ProductsRepository.products.clear()
                rvProductList.adapter?.notifyDataSetChanged()
                false
            }

            fabAdd.setOnClickListener{
                val bottomSheetDialog = BottomSheetDialog(view.context)
                val dialogBinding = AddProductBottomSheetBinding.inflate(layoutInflater)
                bottomSheetDialog.setContentView(dialogBinding.root)

                dialogBinding.apply {
                    addBtn.setOnClickListener{
                        val product = productName.editText?.text.toString()
                        ProductsRepository.products.add(product)
                        bottomSheetDialog.dismiss()
                        rvProductList.adapter?.notifyDataSetChanged()
                    }
                }

                bottomSheetDialog.show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun initAdapter(){
        binding?.apply {
            rvProductList.adapter = ProductAdapter(
                list = ProductsRepository.products
            )
            rvProductList.layoutManager = LinearLayoutManager(requireContext())


        }
    }
}