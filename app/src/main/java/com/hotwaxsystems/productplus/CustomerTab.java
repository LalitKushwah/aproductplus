package com.hotwaxsystems.productplus;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hotwaxsystems.productplus.adapter.FavoritesListAdapter;
import com.hotwaxsystems.productplus.network.CustomerCalls;
import com.hotwaxsystems.productplus.pojo.customer.CustomerDetails;
import com.hotwaxsystems.productplus.pojo.customer.SuggestedCustomerDeatilsList;
import com.hotwaxsystems.productplus.pojo.customerDetails.CustomerDetailsResponse;
import com.hotwaxsystems.productplus.pojo.customerDetails.FavoriteProduct;
import com.google.gson.Gson;
import com.hotwaxsystems.productplus.pojo.customerDetails.PrimaryAddress;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hotwaxsystems.productplus.MainActivity.getUnsafeOkHttpClient;

public class CustomerTab extends Fragment {

    public EditText searchContactNumber,addFirstName,addLastName,addEmailId,addContactNumber;
    Dialog dialog;
    Button createCustomerBtn;
    RecyclerView favoriteListRecyclerView;
    public static LinearLayout customerCard;
    static ArrayList<FavoriteProduct> favProductArray = new ArrayList<>();
    static ArrayList<String> favCategoryArray = new ArrayList<>();
    static Context context;
    ProgressDialog progressDialog;
    TextView name, billingNumber,  age, address, email;
    LinearLayout contactNumberView, billingNumberView,  emailAddressView, addressView;
    CardView favouritesView;
    static String shoppingListId = "";
     FavoritesListAdapter favouritesListAdapter;
    CarouselView carouselView;
//             = new FavoritesListAdapter(getContext(),favProductArray,favCategoryArray);



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_tab, container, false);
        carouselView = (CarouselView) view.findViewById(R.id.linear);
        carouselView.setPageCount(3);
        carouselView.setViewListener(viewListener);
       /* customerCard = (LinearLayout) view.findViewById(R.id.customer_card);
        customerCard.setVisibility(View.VISIBLE);


        String URL = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString("posURL", null);
        final String jsessionid = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString("jsessionid", null);
        final String token = PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext()).getString("token", null);
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(getUnsafeOkHttpClient())
                .build();
        final CustomerCalls customerCalls = retrofit.create(CustomerCalls.class);

        searchContactNumber = (EditText) view.findViewById(R.id.contactNo);
        name = (TextView) view.findViewById(R.id.customer_name);
        billingNumber = (TextView) view.findViewById(R.id.home_contact);
        //shippingNumber = (TextView) view.findViewById(R.id.mobile_contact);
        age = (TextView) view.findViewById(R.id.customer_age);
        address = (TextView) view.findViewById(R.id.customer_address);
        email = (TextView) view.findViewById(R.id.customer_email);
        contactNumberView = (LinearLayout) view.findViewById(R.id.contact_number_view);
        billingNumberView = (LinearLayout) view.findViewById(R.id.billing_phone_view);
        //shippingNumberView = (LinearLayout) view.findViewById(R.id.shipping_phone_view);
        emailAddressView = (LinearLayout) view.findViewById(R.id.email_address_view);
        addressView = (LinearLayout) view.findViewById(R.id.address_view);
        favouritesView = (CardView) view.findViewById(R.id.favorites_view);
        favoriteListRecyclerView = (RecyclerView) view.findViewById(R.id.favourite_list);

        final ImageView customerImage = (ImageView) view.findViewById(R.id.customer_image);
        Button walkOut = (Button) view.findViewById(R.id.walk_out);
        progressDialog=new ProgressDialog(getActivity());


        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");

        walkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingListId = "";
                searchContactNumber.setText("");
                customerCard.setVisibility(View.GONE);
            }
        });

        customerCard.setVisibility(View.GONE);

        context = getActivity();

        LinearLayoutManager favouriteListLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        favoriteListRecyclerView.setLayoutManager(favouriteListLayoutManager);
        favouritesListAdapter = new FavoritesListAdapter(getContext(),favProductArray,favCategoryArray);
        favoriteListRecyclerView.setAdapter(favouritesListAdapter);

        favoriteListRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("scroll",String.valueOf(scrollY));
            }
        });

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.create_customer_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        createCustomerBtn = (Button) dialog.findViewById(R.id.createCustomerBtn);
        addFirstName = (EditText) dialog.findViewById(R.id.add_first_name);
        addLastName = (EditText) dialog.findViewById(R.id.add_last_name);
        addContactNumber = (EditText) dialog.findViewById(R.id.add_number);
        addEmailId = (EditText) dialog.findViewById(R.id.add_email);

        createCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 progressDialog.setMessage("Please wait");
                 progressDialog.show();
                if (addFirstName.getText().toString().equals("")||addLastName.getText().toString().equals("")) {
                    Toast.makeText(getActivity(),"Please provide all required fields",Toast.LENGTH_SHORT).show();
                } else {
                    CustomerCalls customerService = retrofit.create(CustomerCalls.class);
                    Call<CustomerDetails> createCustomerDetails = customerService.createCustomer(token, new CustomerDetailsToPost("PERSON", addFirstName.getText().toString(), addLastName.getText().toString(), addEmailId.getText().toString(), addContactNumber.getText().toString(),"9000"), jsessionid);

                    createCustomerDetails.enqueue(new Callback<CustomerDetails>() {
                        @Override
                        public void onResponse(Call<CustomerDetails> call, Response<CustomerDetails> response) {
                            Toast.makeText(getActivity(),"Customer created",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            final Call<CustomerDetailsResponse> getDetails = customerCalls.getCustomerDetails(token,new PostAddCustomer(response.body().getCustomer().getPartyId(),"9000"), jsessionid);
                            getDetails.enqueue(new Callback<CustomerDetailsResponse>() {
                                @Override
                                public void onResponse(Call<CustomerDetailsResponse> call, Response<CustomerDetailsResponse> response) {
                                    progressDialog.dismiss();
                                    customerCard.setVisibility(View.VISIBLE);
                                    setCustomerView(response.body());
                                }

                                @Override
                                public void onFailure(Call<CustomerDetailsResponse> call, Throwable t) {
                                    progressDialog.dismiss();
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<CustomerDetails> call, Throwable t) {
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });
*/
       /* searchContactNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    progressDialog.show();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    if (searchContactNumber.getText() != null) {
                        Call<SuggestedCustomerDeatilsList> findCustomer = customerCalls.searchCustomerDetails(token, searchContactNumber.getText().toString(), PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("jsessionid", null));
                        findCustomer.enqueue(new Callback<SuggestedCustomerDeatilsList>() {
                            @Override
                            public void onResponse(Call<SuggestedCustomerDeatilsList> call, Response<SuggestedCustomerDeatilsList> response) {
                                if (response.body().getResult().getListSize() > 0) {
                                    final Call<CustomerDetailsResponse> getDetails = customerCalls.getCustomerDetails(token,new PostAddCustomer(response.body().getResult().getCustomerDetailsList().get(0).getPartyId(),"9000"), jsessionid);
                                    getDetails.enqueue(new Callback<CustomerDetailsResponse>() {
                                        @Override
                                        public void onResponse(Call<CustomerDetailsResponse> call, Response<CustomerDetailsResponse> response) {
                                            progressDialog.dismiss();
                                            customerCard.setVisibility(View.VISIBLE);
                                            setCustomerView(response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<CustomerDetailsResponse> call, Throwable t) {
                                            Log.d("customer detail failure",t.getCause().toString());
                                        }
                                    });
                                } else {
                                    progressDialog.dismiss();
                                    dialog.show();
                                    Toast.makeText(getActivity(),"Customer does not exist",Toast.LENGTH_SHORT).show();
                                    searchContactNumber.setText("");

                                }
                            }

                            @Override
                            public void onFailure(Call<SuggestedCustomerDeatilsList> call, Throwable t) {
                                Log.e("Failure getCust", t.getCause().toString());
                            }
                        });
                    }
                    return true;
                }
                return false;
            }
        });*/
        return view;
    }

   /* public void setCustomerView (CustomerDetailsResponse response) {
        shoppingListId = response.getCustomerDetail().getShoppingListId();
        Log.d("shopping list id",shoppingListId);
        favCategoryArray.clear();
        favProductArray.clear();
        if (response.getCustomerDetail().getBirthDate() != null) {
            age.setVisibility(View.VISIBLE);
            String[] birthDay = response.getCustomerDetail().getBirthDate().toString().split("-");
            int curAge = CalculateAge(Integer.parseInt(birthDay[0]), Integer.parseInt(birthDay[1]), Integer.parseInt(birthDay[2]));
            age.setText(curAge + " years");
        } else {
            age.setVisibility(View.GONE);
        }
        PrimaryAddress primaryAddress = response.getCustomerDetail().getPrimaryAddress();
        if (primaryAddress.getAddress1() != null) {
            addressView.setVisibility(View.VISIBLE);
            String addressString = primaryAddress.getAddress1().toString();
            if(primaryAddress.getAddress2() != null) {
                addressString += "\n"+primaryAddress.getAddress2();
            }
            if (primaryAddress.getCity() != null) {
                addressString += "\n"+primaryAddress.getCity();
            }
            if (primaryAddress.getStateProvinceGeoId() != null && primaryAddress.getPostalCode() != null) {
                addressString += "\n"+primaryAddress.getStateProvinceGeoId()+primaryAddress.getPostalCode();
            }
            if (primaryAddress.getCountryGeoId() != null) {
                addressString += "\n"+primaryAddress.getCountryGeoId();
            }
            address.setText(addressString);
        } else {
            addressView.setVisibility(View.GONE);
        }

        if (response.getCustomerDetail().getName() != null) {
            name.setText(response.getCustomerDetail().getName().toString());
        }
        if (response.getCustomerDetail().getEmailAddressInfo().getEmailAddress() != null) {
            emailAddressView.setVisibility(View.VISIBLE);
            email.setText(response.getCustomerDetail().getEmailAddressInfo().getEmailAddress().toString());
        } else {
            emailAddressView.setVisibility(View.GONE);
        }
        if (response.getCustomerDetail().getTelecomNumber().getPrimaryPhone().getContactNumber() != null) {
            contactNumberView.setVisibility(View.VISIBLE);
            if (response.getCustomerDetail().getTelecomNumber().getPrimaryPhone() != null) {
                billingNumberView.setVisibility(View.VISIBLE);
                billingNumber.setText(response.getCustomerDetail().getTelecomNumber().getPrimaryPhone().getCountryCode() + "-" + response.getCustomerDetail().getTelecomNumber().getPrimaryPhone().getAreaCode() + "-" + response.getCustomerDetail().getTelecomNumber().getPrimaryPhone().getContactNumber().toString());
            } else {
                billingNumberView.setVisibility(View.GONE);
            }
            if (response.getCustomerDetail().getTelecomNumber().getShippingPhone() != null) {
                //shippingNumberView.setVisibility(View.VISIBLE);
                //shippingNumber.setText(response.getCustomerDetail().getTelecomNumber().getShippingPhone().getCountryCode() + "-" + response.getCustomerDetail().getTelecomNumber().getShippingPhone().getAreaCode() + "-" + response.getCustomerDetail().getTelecomNumber().getShippingPhone().getContactNumber().toString());
            } else {
                //shippingNumberView.setVisibility(View.GONE);
            }
        } else {
            contactNumberView.setVisibility(View.GONE);
        }
        if (response.getCustomerDetail().getFavoriteProducts() == null && response.getCustomerDetail().getFavoriteCategories() == null) {
            favouritesView.setVisibility(View.GONE);
        } else {
            favouritesView.setVisibility(View.VISIBLE);
            if (response.getCustomerDetail().getFavoriteProducts() != null) {
                favProductArray.addAll(response.getCustomerDetail().getFavoriteProducts());
            }
            if (response.getCustomerDetail().getFavoriteCategories() != null) {
                favCategoryArray.addAll(response.getCustomerDetail().getFavoriteCategories());
            }
            favouritesListAdapter.notifyDataSetChanged();
        }
    }

    public static class CustomerDetailsToPost {
        String partyType, firstName, lastName, contactNumber, emailAddress, productStoreId;

    public CustomerDetailsToPost(String partyType, String firstName, String lastName, String emailAddress, String contactNumber, String productStoreId) {
        this.partyType = partyType;
        this.contactNumber = contactNumber;
        this.lastName=lastName;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.productStoreId = productStoreId;
        }
    }

    public static class PostAddCustomer {
        String customerId, productStoreId;

        public PostAddCustomer(String customerId, String productStoreId) {
            this.customerId = customerId;
            this.productStoreId = productStoreId;
        }
    }

    int CalculateAge (int year, int month,int day) {
        // enter your date of birth
        Calendar dateOfYourBirth = new GregorianCalendar(year, month , day);
        Calendar today = Calendar.getInstance();
        int yourAge = today.get(Calendar.YEAR) - dateOfYourBirth.get(Calendar.YEAR);
        dateOfYourBirth.add(Calendar.YEAR, yourAge);
        if (today.before(dateOfYourBirth)) {
            yourAge--;
        }
        return yourAge;
    }*/



    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(final int position) {
            View customView = getLayoutInflater(null).inflate(R.layout.related_product_list_item, null);
            ImageView img = (ImageView) customView.findViewById(R.id.related_product_image);
            TextView recomProductName = (TextView) customView.findViewById(R.id.related_product_name);
            TextView recomProductPrice = (TextView) customView.findViewById(R.id.related_product_cost);
          //  if (recommendation.get(position).getImageURL().isEmpty()) {
                img.setImageResource(R.drawable.notfound);
           // }
            /*else {
                Picasso.with(getActivity())
                        .load(responseToUrl(recommendation.get(position).getImageURL().toString()))
                        .resize(75, 75)
                        .placeholder(R.drawable.notfound)
                        .into(img);
            }*/
            recomProductName.setText("Mac-Book");
            recomProductPrice.setText("$20.34");
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
     //               progressDialog.setMessage("Please wait");
       //             progressDialog.show();
                    //Call<ProductDetailsResponse> getProductDetails = productCalls.getProductDetails(token, new PostProductDetails("", recommendation.get(position).getProductId().toString(), "9000", CustomerTab.shoppingListId), PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("jsessionid", null));
                   /* getProductDetails.enqueue(new Callback<ProductDetailsResponse>() {
                        @Override
                        public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                            //updateProductView(response.body().getProductDetails());
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                            Log.d("Failure ProductDetails", t.getCause().toString());
                        }
                    });*/
                }
            });
            return customView;
        }
    };

}





