package com.hotwaxsystems.productplus;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hotwaxsystems.productplus.network.ProductCalls;
import com.hotwaxsystems.productplus.pojo.productDetails.ProductDetails;
import com.hotwaxsystems.productplus.pojo.productDetails.ProductDetailsResponse;
import com.hotwaxsystems.productplus.pojo.productDetails.RecommondedProduct;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hotwaxsystems.productplus.MainActivity.getUnsafeOkHttpClient;

/**
 * Created by Tanmay on 29/12/16.
 */

public class ProductTab extends Fragment {
    CarouselView carouselView;
    TextView originalprice, saveMsg;
    ProgressDialog progressDialog;
    ArrayList<RecommondedProduct> recommendation = new ArrayList<>();
    String URL = "", token = "";
    Retrofit retrofit;
    ProductCalls productCalls;
    CardView productCard;
    EditText productUpc;
    TextView productName, productPrice, productDescription;
    ImageView productImage;
    LinearLayout productDescriptionView;
    DecimalFormat format = new DecimalFormat("#0.00");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View view = inflater.inflate(R.layout.product_tab, container, false);
       /* URL = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString("posURL", null);
        token = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString("token", null);

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(getUnsafeOkHttpClient())
                .build();
        productCalls = retrofit.create(ProductCalls.class);

        productCard = (CardView) view.findViewById(R.id.product_card);
        productUpc = (EditText) view.findViewById(R.id.product_upc);
        productName = (TextView) view.findViewById(R.id.product_name);
        productDescription = (TextView) view.findViewById(R.id.product_description);
        productPrice = (TextView) view.findViewById(R.id.product_price);
        productImage = (ImageView) view.findViewById(R.id.product_image);
        productDescriptionView = (LinearLayout) view.findViewById(R.id.product_description_container);
        originalprice = (TextView) view.findViewById(R.id.originalPrice);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        saveMsg = (TextView) view.findViewById(R.id.saveMsg);
        carouselView = (CarouselView) view.findViewById(R.id.linear);
        carouselView.setViewListener(viewListener);

        productCard.setVisibility(View.GONE);

        productUpc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String upc = productUpc.getText().toString();
                    if (!upc.isEmpty()) {
                        productUpc.setText("");
                        productUpc.setFocusable(true);
                        progressDialog.setMessage("Please wait");
                        progressDialog.show();
                        Call<ProductDetailsResponse> getProductDetails = productCalls.getProductDetails(token, new PostProductDetails(upc, "", "9000", CustomerTab.shoppingListId), PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("jsessionid", null));
                        getProductDetails.enqueue(new Callback<ProductDetailsResponse>() {
                            @Override
                            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                                if (response.body() != null) {
                                    updateProductView(response.body().getProductDetails());
                                    progressDialog.dismiss();
                                    productUpc.requestFocus();
                                    //Log.d("Product Response",response.body().getProductDetails().toString());
                                } else {
                                    Toast.makeText(getActivity(), "Error occured in response from server", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    productUpc.requestFocus();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                                Log.d("Failure ProductDetails", "Some error occured");
                                productUpc.requestFocus();

                            }
                        });
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "UPC field empty", Toast.LENGTH_LONG);
                        productUpc.requestFocus();
                    }
                }
                return false;
            }
        });
*/
        return view;
    }

    /*public static class PostProductDetails {
        String upc, productStoreId, shoppingListId, productId;

        public PostProductDetails(String upc, String productId, String productStoreId, String shoppingListId) {
            this.upc = upc;
            this.productId = productId;
            this.productStoreId = productStoreId;
            this.shoppingListId = shoppingListId;
        }
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(final int position) {
            View customView = getLayoutInflater(null).inflate(R.layout.related_product_list_item, null);
            ImageView img = (ImageView) customView.findViewById(R.id.related_product_image);
            TextView recomProductName = (TextView) customView.findViewById(R.id.related_product_name);
            TextView recomProductPrice = (TextView) customView.findViewById(R.id.related_product_cost);
            if (recommendation.get(position).getImageURL().isEmpty()) {
                img.setImageResource(R.drawable.notfound);
            } else {
                Picasso.with(getActivity())
                        .load(responseToUrl(recommendation.get(position).getImageURL().toString()))
                        .resize(75, 75)
                        .placeholder(R.drawable.notfound)
                        .into(img);
            }
            recomProductName.setText(recommendation.get(position).getProductId().toString());
            recomProductPrice.setText("$"+recommendation.get(position).getPrice().getDefaultPrice());
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();
                    Call<ProductDetailsResponse> getProductDetails = productCalls.getProductDetails(token, new PostProductDetails("", recommendation.get(position).getProductId().toString(), "9000", CustomerTab.shoppingListId), PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("jsessionid", null));
                    getProductDetails.enqueue(new Callback<ProductDetailsResponse>() {
                        @Override
                        public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                            updateProductView(response.body().getProductDetails());
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                            Log.d("Failure ProductDetails", t.getCause().toString());
                        }
                    });
                }
            });
            return customView;
        }
    };

    public void updateProductView(ProductDetails response) {
        if (response != null) {
            productCard.setVisibility(View.VISIBLE);
            productName.setText(response.getProductName().toString());
            productPrice.setText("$" + format.format(new Double(response.getPrice() - 2)));
            originalprice.setText("$" + format.format(new Double(response.getPrice())));
            if(false) {
                productDescriptionView.setVisibility(View.VISIBLE);
                productDescription.setText("");
            } else {
                productDescriptionView.setVisibility(View.GONE);
            }
            recommendation.clear();
            recommendation.addAll(response.getRecommondedProducts());
            carouselView.setPageCount(recommendation.size());
            originalprice.setPaintFlags(originalprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            saveMsg.setText("save $2");
            if (response.getImageURL().isEmpty()) {
                productImage.setImageResource(R.drawable.notfound);
            } else {
                Picasso.with(getActivity())
                        .load(responseToUrl(response.getImageURL().toString()))
                        .resize(150, 150)
                        .placeholder(R.drawable.notfound)
                        .into(productImage);
            }
        } else {
            Toast.makeText(getActivity(), "Please enter a valid product UPC", Toast.LENGTH_LONG).show();
            productCard.setVisibility(View.GONE);
        }
    }

    String responseToUrl(String imageUrl) {

        String completeUrl= "https://wasatch-nxt.hotwaxsystems.com"+imageUrl;
        String result=null;
        try {
             result = java.net.URLDecoder.decode(completeUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }*/
}