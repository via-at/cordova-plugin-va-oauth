package plugin.va.oauth;

import org.apache.cordova.*;

public class GoogleOAuth implements VAOAuth, GoogleApiClient.OnConnectionFailedListener {

    private OAuthCallback callback;

    private GoogleApiClient mGoogleApiClient;

    GoogleOAuth(Context webViewContext) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        this.mGoogleApiClient = new GoogleApiClient.Builder(webViewContext)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    /**
     * Sign in with Google.
     *
     * @param callbackContext
     */
    public void signIn(OAuthCallback callback) {
        this.callback = callback;
        cordova.setActivityResultCallback(this);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient);
        cordova.getActivity().startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onConnectionFailed(ConnectionResult result) {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            this.callback.onAuthSuccess(credential);
        } else {
        }
    }
}