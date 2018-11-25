package kr.co.picklecode.helper.helper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

import bases.BaseActivity;

public class LoginActivity extends BaseActivity {

    private CallbackManager callbackManager;

    private Button btn_login;
    private Button btn_join;

    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login: {
                final Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            }
                break;
            case R.id.btn_join: {
                final Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            }
                break;
            default: break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(RESULT_OK == resultCode) showToast("Facebook logged in");

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView(){

        // boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

        callbackManager = CallbackManager.Factory.create();

        btn_login = findViewById(R.id.btn_login);
        btn_join = findViewById(R.id.btn_join);

        loginButton = findViewById(R.id.btn_facebook);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            Log.i("TAG", "user: " + user.toString());
                            setResult(RESULT_OK);

//                            Intent i = new Intent(LoginActivity.this, SampleActivity.class);
//                            startActivity(i);
//                            finish();
                        }

                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });


        setClick(btn_login, btn_join);
    }

}
