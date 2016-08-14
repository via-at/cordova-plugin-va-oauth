package plugin.va.oauth;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

public class GoogleOAuth extends CordovaPlugin implements VAOAuth, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;

    private CallbackContext callbackContext;

    private GoogleApiClient mGoogleApiClient;

    @Override
    public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        buildGoogleApiClient(args.getString(0));

        if (action.equals("signIn")) {
            signIn();
        } else if (action.equals("signOut")) {
            signOut();
        } else {
            return false;
        }

        return true;
    }

    public void signIn() {
        this.cordova.setActivityResultCallback(this);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient);
        this.cordova.getActivity().startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signOut() {
    }

    public void onConnectionFailed(ConnectionResult result) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void buildGoogleApiClient(String clientId) {
        if (this.mGoogleApiClient == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(clientId)
                    .requestEmail()
                    .build();

            this.mGoogleApiClient = new GoogleApiClient.Builder(webView.getContext())
                    .addOnConnectionFailedListener(this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            this.callbackContext.success(acct.getIdToken());
        } else {
            this.callbackContext.error(result.getStatus().getStatusCode());
        }
    }
}